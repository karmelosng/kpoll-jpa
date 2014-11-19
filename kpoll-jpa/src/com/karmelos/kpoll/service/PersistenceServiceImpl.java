package com.karmelos.kpoll.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javassist.expr.Instanceof;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.hamcrest.core.IsInstanceOf;
import org.springframework.transaction.annotation.Transactional;

import com.karmelos.kpoll.model.ChoicePoll;
import com.karmelos.kpoll.model.ChoicePollOption;
import com.karmelos.kpoll.model.ChoicePollResponse;
import com.karmelos.kpoll.model.FreeTextPoll;
import com.karmelos.kpoll.model.FreeTextPollResponse;
import com.karmelos.kpoll.model.InterestArea;
import com.karmelos.kpoll.model.KeywordPoll;
import com.karmelos.kpoll.model.KeywordPollResponse;
import com.karmelos.kpoll.model.Participant;
import com.karmelos.kpoll.model.PollAdmin;
import com.karmelos.kpoll.model.PollSurvey;
import com.karmelos.kpoll.model.PollOwner;
import com.karmelos.kpoll.model.PollResponse;
import com.karmelos.kpoll.util.ServiceUtil;
import com.karmelos.kpoll.util.PollContent;

@Transactional
public class PersistenceServiceImpl implements PersistenceService {
   
	@PersistenceContext        
	protected EntityManager entityManager;
        
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	@Override
	public String registerPollUser(Object pollUser) {
		// TODO Auto-generated method 
		// hashSalt the password
		
		if(pollUser instanceof PollOwner){
			PollOwner user =(PollOwner)pollUser;
			 if(entityManager.find(PollOwner.class,user.getEmailAddress()) == null){
		        	entityManager.persist(user);
		        }
		        else{
		        	entityManager.merge(user);
		        }
		}
		
		else  if(pollUser instanceof PollAdmin){
			PollAdmin user =(PollAdmin)pollUser;
			 if(entityManager.find(PollAdmin.class,user.getEmailAddress()) == null){
		        	entityManager.persist(user);
		        }
		        else{
		        	entityManager.merge(user);
		        }
		}
		else {
			Participant user =(Participant)pollUser;
			 if(entityManager.find(Participant.class,user.getPhoneNumber()) == null){
		        	entityManager.persist(user);
		        }
		        else{
		        	entityManager.merge(user);
		        }
		}
				
       
		
		return "registered";
		
	}

	@Override
	public void pushToPartipants(PollSurvey pollSurvey, boolean affiliateFlag, boolean interestAreas) {
		// TODO Auto-generated method stub
    //send a notification to User using google cloud manager,return a List of Participants and add to recieverList
		PollContent poll = new PollContent();
		List<Participant> pollParticipants =pollSurvey.getParticipants();
		// add participants to pollcontentr
		for(int i =0;i<pollParticipants.size();i++){
			poll.addRegId(pollParticipants.get(i).getParticipantGcmId());	
		}
		//"APA91bGrVQahWoz6RIFMo18cK1QhmxDESzAnuLcuEc-aZ-ZNVfCEDP1rWprkAtcbTzR2NLCzX4gY-FSxCThPxtW1F9snRQK5d0si2FS8RVxUCrdR3rkFUQg2fTFldsEvn7h2X_z-0WWQv2yuHkHFk24i9Z_ysrGNNalTgNOm0iGYeP2A15ILR8w"
		
		poll.createData(pollSurvey.getDescription(),pollSurvey.getDescription());
	    // post to  GCM Server
		String response =ServiceUtil.post(poll);
		//add to recieversList
		pollSurvey.setReceivers(pollParticipants);
		
		entityManager.merge(pollSurvey);
		
	}

	

	@Override
	public void respondToPoll(PollSurvey pollActivity,
			ChoicePollOption cOption, String keyOrFreeResponse,
			PollResponse pollResponse, Participant participant) {
		// TODO Auto-generated method stub
				if(pollResponse instanceof ChoicePollResponse){
					ChoicePollResponse cpr = new ChoicePollResponse();
					cpr.setParticipant(participant);
					cpr.setPollSurvey(pollActivity);
					cpr.setPollOption(cOption);
					entityManager.persist(cpr);
				}
				else if(pollResponse instanceof KeywordPollResponse){
					KeywordPollResponse kResponse = new KeywordPollResponse();
					kResponse.setParticipant(participant);
					kResponse.setPollSurvey(pollActivity);
					//kResponse.setKeywordResponse(keyOrFreeResponse);
					entityManager.persist(kResponse);
				}
				else {
					FreeTextPollResponse fResponse = new FreeTextPollResponse();
					fResponse.setParticipant(participant);
					fResponse.setPollSurvey(pollActivity);
					fResponse.setTextResponse(keyOrFreeResponse);
					entityManager.persist(fResponse);
				}
			
		
	}
	@Override
	public Object viewPollResults(Long pollId) {
		// TODO Auto-generated method stub

		 Query q = entityManager.createQuery("select o from PollResponse o WHERE o.pollSurvey.id =:pollId ");
		 q.setParameter("pollId", pollId);
         //convert list to SimModule[] and return
         List<PollResponse> list = q.getResultList();
         
         return list;
	
	}

    
	
	@Override
	public void createPoll(PollOwner pollOwner,PollSurvey pollSurvey,List<InterestArea> interestAreas)
			 {
		// TODO Auto-generated method stub
		if(pollSurvey instanceof ChoicePoll){
			ChoicePoll newChoicePoll = new ChoicePoll();
			newChoicePoll.setInterestAreas(interestAreas);
			newChoicePoll.setDescription(pollSurvey.getDescription());
			newChoicePoll.setPollOwner(pollOwner);			
			entityManager.persist(newChoicePoll);
		}
		else if(pollSurvey instanceof FreeTextPoll){
			FreeTextPoll newFreeTextPoll = new FreeTextPoll();
			newFreeTextPoll.setInterestAreas(interestAreas);
			newFreeTextPoll.setDescription(pollSurvey.getDescription());
			newFreeTextPoll.setPollOwner(pollOwner);
			entityManager.persist(newFreeTextPoll);
		}
		else {
			KeywordPoll newKeywordPoll = new KeywordPoll();
			newKeywordPoll.setInterestAreas(interestAreas);
			newKeywordPoll.setDescription(pollSurvey.getDescription());
			newKeywordPoll.setPollOwner(pollOwner);
			entityManager.persist(newKeywordPoll);
		}
	}
	@Override
	public int addAffiliates(PollOwner pollOwner, File affiliateFile,
			String affiliateString) {
		String response="";
		List<Participant> affiliates = new ArrayList<>();
		// check if it is lineseperated or commaseperated
		String [] readParticipants = affiliateString.split(",");
	   //LookUp the participant with that id / discardi
		for(int i=0;i<readParticipants.length;i++){
			Participant dummy = new Participant();
			dummy.setPhoneNumber(readParticipants[i]);
		   Participant singlePart =(Participant)retrieveAnyEntity(Participant.class,readParticipants[i]);
			if(singlePart != null){
				affiliates.add(singlePart);
			}
			
		}
		
		pollOwner.setAffiliates(affiliates);
		
		entityManager.merge(pollOwner);
		
		return affiliates.size();
	}
	@Override
	public PollSurvey retrievePollSurvey(Long pollId) {
	// TODO Auto-generated method stub
		PollSurvey result =entityManager.find(PollSurvey.class, pollId);
      
		return result;
	}
	@Override
	public List<PollSurvey> loadPollSurveys(int interestArea_id) {
		// To load the pollsurvey from
		InterestArea dummy = new InterestArea();
		dummy.setId(Long.valueOf(interestArea_id));
		Query q=null;
	   if(interestArea_id ==0){
		    q = entityManager.createQuery("select o from PollSurvey o ");  
	   }
	   else{
		   q = entityManager.createQuery("select o from PollSurvey o WHERE :interestArea member Of o.interestAreas");  
	   }		 
	          q.setParameter("interestArea", dummy);
	          
		     List<PollSurvey> listed = q.getResultList();
		         
		         return listed;
	
	}
	@Override
	public List<InterestArea> loadInterestAreas() {
		// TODO Auto-generated method stue
		 Query q = entityManager.createQuery("select o from InterestArea o");

         //convert list to SimModule[] and return
         List<InterestArea> list = q.getResultList();
         
         return list;
	}
	
	@Override
	public  List<Participant> listParticipantsByInterestArea(InterestArea iArea){
		Query q= entityManager.createQuery("select o from Participant o WHERE :interestArea member Of o.interestAreas ");
		   q.setParameter("interestArea", iArea);
			   List<Participant> listed = q.getResultList();
			         
	  return listed;
	}
	
	@Override
	public  List<PollSurvey> listPollsByOwner(PollOwner owner){
		Query q= entityManager.createQuery("select o from PollSurvey o WHERE o.pollOwner =:owner ");
		   q.setParameter("owner", owner);
	   List<PollSurvey> listed = q.getResultList();
			         
	  return listed;
	}
	
	@Override
	public void createChoicePollOption(ChoicePoll choicePoll,String description){
	  ChoicePollOption choose = new ChoicePollOption();
	  choose.setChoicePoll(choicePoll);
	  choose.setDescription(description);
		entityManager.merge(choose);
			         
	 
	}
	
	@Override
	public String login(String userName, String password, int userType) {
		String response =null;
		// the userType . 0 = pollAdmin,1= pollOwner,2= participant
		     String inputHash= ServiceUtil.md5(password);
		 boolean isAuth = getLoginStatus(userType, userName, inputHash);  
		 if(isAuth){
			response = "success";
		 }
		 else{
			 response ="error";
		 }
		         //convert list to SimModule[] and return
		        // List<InterestArea> list = q.getSingleResult();
		return response;
	}
	@Override
public String manageOwner(String ownerName, String emailAddress,String password,byte[] hashSalt)
{
		String response =null;
		try{
			if(emailAddress == null){
				// when new participant
				PollOwner pollOwner = new PollOwner();
				pollOwner.setEmailAddress(emailAddress);
				pollOwner.setOwnerName(ownerName);
				pollOwner.setHashSalt(hashSalt);
				pollOwner.setPasswordHash(password);
				
				entityManager.persist(pollOwner);
			}
			else{
				PollOwner pollOwner = new PollOwner();
				pollOwner.setEmailAddress(emailAddress);
				pollOwner.setOwnerName(ownerName);
				pollOwner.setPasswordHash(password);
				
				entityManager.merge(pollOwner);
			  }
		}
		catch(Exception e){
			response = e.getLocalizedMessage();
		}
		
	return response;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object retrieveAnyEntity(Class entity,Object id) {
		Object entityRetr =null;
		try{
		entityRetr =entityManager.find(entity, id);
		}
		catch(Exception io){
			return null;
		}
	 return entityRetr;
	}
	
	public boolean getLoginStatus(int userType,String userName,String inputHash){
		boolean isAuth=false;
		 
		 if(userType == 0){
			 try{
			 Query q = entityManager.createQuery("select o from PollAdmin o WHERE o.emailAddress = :uname AND o.passwordHash =:password");
			 q.setParameter("uname",userName);
	         q.setParameter("password",inputHash);
	         PollAdmin admin = (PollAdmin) q.getSingleResult();
	         String dbHash = admin.getPasswordHash();	         
	  	       isAuth= compareHashSalt(inputHash, dbHash, admin.getHashSalt()); 
			 }
			 catch(NoResultException  e){
				 isAuth=false; 
			 }
	         
		 }
		 else if(userType == 1){
			 try{
			 Query q = entityManager.createQuery("select o from PollOwner o WHERE  o.emailAddress = :uname AND o.passwordHash =:password");
			 q.setParameter("uname",userName);
	         q.setParameter("password",inputHash);
	         PollOwner owner = (PollOwner) q.getSingleResult();	
	         String dbHash = owner.getPasswordHash();	         
	  	       isAuth= compareHashSalt(inputHash, dbHash, owner.getHashSalt()); 
			 }
			 catch(NoResultException e){
				 isAuth = false;
			 }
	         
		 }
		 else{
			 try{
			 Query q = entityManager.createQuery("select o from Participant o WHERE  o.phoneNumber = :uname AND o.passwordHash =:password");
			 q.setParameter("uname",userName);
	         q.setParameter("password",inputHash);
	         Participant participant = (Participant) q.getSingleResult();
	         String dbHash = participant.getPasswordHash();	         
	  	      isAuth= compareHashSalt(inputHash, dbHash, participant.getHashSalt());
			 }
			 catch(NoResultException e){
				isAuth = false; 
			 }
	         
		 }
		
		return isAuth;
	}
	
	
	public boolean compareHashSalt(String inputHash,String dbHash,byte[] salt){
		boolean isAuthenticated = false;
		// auth String from input
		String inputHex =ServiceUtil.md5(inputHash+ServiceUtil.bytetoString(salt));
		// auth String from Db
		String dbHex=ServiceUtil.md5(dbHash+ServiceUtil.bytetoString(salt));
		
		  if(inputHex.equals(dbHex)){
			  isAuthenticated = true;
		  }
		
		return isAuthenticated;
	}
	@Override
	public String manageInterestAreas(Long id, String description) {
		// Delete, update add Add new InterestAreas
		if(id == null){
			// new Participant
			InterestArea intArea = new InterestArea();
			intArea.setDescription(description);
			
			entityManager.persist(intArea);
		}
		else
		{
			InterestArea intArea = new InterestArea();
			intArea.setId(id);
			intArea.setDescription(description);
			
			entityManager.merge(intArea);
			
		}
		return null;
	}

}
