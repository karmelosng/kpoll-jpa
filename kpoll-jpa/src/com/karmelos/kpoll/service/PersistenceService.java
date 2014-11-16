package com.karmelos.kpoll.service;

import java.io.File;
import java.util.List;

import com.karmelos.kpoll.model.ChoicePoll;
import com.karmelos.kpoll.model.ChoicePollOption;
import com.karmelos.kpoll.model.FreeTextPollResponse;
import com.karmelos.kpoll.model.InterestArea;
import com.karmelos.kpoll.model.KeywordPollResponse;
import com.karmelos.kpoll.model.Participant;
import com.karmelos.kpoll.model.PollSurvey;
import com.karmelos.kpoll.model.PollOwner;
import com.karmelos.kpoll.model.PollResponse;

/**
 * @author amacodecode
 *
 */
public interface PersistenceService {
	/**
	 * 
	 * @param participant
	 * @return
	 */
	String registerPollUser(Object pollUser);

	/**
	 * 
	 * @param pollActivity
	 * @param participants
	 */
	void pushToPartipants(PollSurvey pollActivity, boolean affiliatesFlag,
			boolean interestAreas);

	/**
	 * 
	 * @param pollActivity
	 * @param pollResponse
	 * @param participant
	 */
	void respondToPoll(PollSurvey pollActivity,ChoicePollOption cOption,String keyOrFreeResponse,PollResponse pollResponse,
			Participant participant);

	/**
	 * 
	 * @param pollResponse
	 * @return
	 */
	Object viewPollResults(List<PollResponse> pollResponse);

	/**
	 * 
	 * @param pollOwner
	 * @param pollType
	 * @param interestAreas
	 * @param participants
	 * @return
	 */
	void createPoll(PollOwner pollOwner,PollSurvey pollType,
			List<InterestArea> interestAreas);

	/**
	 * 
	 * @param pollOwner
	 * @param affiliateFile
	 * @param affiliateString
	 */
	int addAffiliates(PollOwner pollOwner, File affiliateFile,
			String affiliateString);
	

	/**
	 * @param pollId
	 * @return
	 */
	PollSurvey retrievePollSurvey(Long pollId);
	
   
	/**
	 * @param interestAreas
	 * @return
	 * This loads pollsurveys based on User interestAreas
	 */
	List<PollSurvey> loadPollSurveys(int interestArea_id);
	
	/**
	 * @param iArea
	 * @return
	 */
	List<Participant> listParticipantsByInterestArea(InterestArea iArea);
	
	/**
	 * @return
	 */
	List<InterestArea> loadInterestAreas();
	
	/**
	 * @param userName
	 * @param password
	 * @return
	 */
	String login(String userName, String password, int userType);
	
	/**
	 * @param ownerName
	 * @param emailAddress
	 * @return
	 */
	String manageOwner(String ownerName, String emailAddress);
	
	
	/**
	 * @param entity
	 * @param id
	 * @return
	 */
	Object retrieveAnyEntity(Class entity,Object id);
	
	/**
	 * @param owner
	 * @return
	 */
	List<PollSurvey> listPollsByOwner(PollOwner owner);
	
	/**
	 * @param poll_Id
	 */
	void createChoicePollOption(ChoicePoll poll_Id,String description);

}
