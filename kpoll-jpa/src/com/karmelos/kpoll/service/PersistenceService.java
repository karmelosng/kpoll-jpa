package com.karmelos.kpoll.service;

import java.io.File;
import java.util.List;

import com.karmelos.kpoll.model.InterestArea;
import com.karmelos.kpoll.model.Participant;
import com.karmelos.kpoll.model.PollSurvey;
import com.karmelos.kpoll.model.PollOwner;
import com.karmelos.kpoll.model.PollResponse;

public interface PersistenceService {
	/**
	 * 
	 * @param participant
	 * @return
	 */
	String registerParticipant(Participant participant);
	
	/**
	 * 
	 * @param pollActivity
	 * @param participants
	 */
	void pushToPartipants(PollSurvey pollActivity, boolean affiliatesFlag, boolean interestAreas);
	
	/**
	 * 
	 * @param pollActivity
	 * @param pollResponse
	 * @param participant
	 */
	void respondToPoll(PollSurvey pollActivity, PollResponse pollResponse, Participant participant);
	
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
	PollSurvey createPoll(PollOwner pollOwner, Class<PollSurvey> pollType, List<InterestArea> interestAreas);
	
	/**
	 * 
	 * @param pollOwner
	 * @param affiliateFile
	 * @param affiliateString
	 */
	void addAffiliates(PollOwner pollOwner, File affiliateFile, String affiliateString);
	
	PollSurvey retrievePollSurvey(Long pollId);
		
	
}
