package com.karmelos.kpoll.service;

import java.io.File;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.karmelos.kpoll.model.InterestArea;
import com.karmelos.kpoll.model.Participant;
import com.karmelos.kpoll.model.PollSurvey;
import com.karmelos.kpoll.model.PollOwner;
import com.karmelos.kpoll.model.PollResponse;

@Transactional
public class PersistenceServiceImpl implements PersistenceService {

	@PersistenceContext        
	protected EntityManager entityManager;
        
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	@Override
	public String registerParticipant(Participant participant) {
		// TODO Auto-generated method stub
	
		return "fgfgfgf";
	}

	@Override
	public void pushToPartipants(PollSurvey pollSurvey, boolean affiliateFlag, boolean interestAreas) {
		// TODO Auto-generated method stub
    //send a notification to User return a List of Participants and add to recieverList
	}

	@Override
	public void respondToPoll(PollSurvey pollActivity, PollResponse pollResponse, Participant participant) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object viewPollResults(List<PollResponse> pollResponse) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public PollSurvey createPoll(PollOwner pollAdmin,
			Class<PollSurvey> pollType, List<InterestArea> interestAreas) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void addAffiliates(PollOwner pollOwner, File affiliateFile,
			String affiliateString) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public PollSurvey retrievePollSurvey(Long pollId) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
