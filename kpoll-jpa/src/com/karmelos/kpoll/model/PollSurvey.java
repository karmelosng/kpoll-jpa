package com.karmelos.kpoll.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public  abstract class PollSurvey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6686244210821387069L;
	
	private Long id;

	private PollOwner pollOwner;
	
	private String description;
	
	private int pushCount;
	
	private List<Participant> participants;

	private List<InterestArea> interestAreas;
	
	private List<Participant> receivers;
	
	private Date startDate;
	
	private Date endDate;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	public PollOwner getPollOwner() {
		return pollOwner;
	}

	public void setPollOwner(PollOwner pollAdmin) {
		this.pollOwner = pollAdmin;
	}

	@ManyToMany
	public List<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}

	@ManyToMany
	public List<InterestArea> getInterestAreas() {
		return interestAreas;
	}

	public void setInterestAreas(List<InterestArea> interestAreas) {
		this.interestAreas = interestAreas;
	}

	@ManyToMany
	public List<Participant> getReceivers() {
		return receivers;
	}

	public void setReceivers(List<Participant> receivers) {
		this.receivers = receivers;
	}
	
    @Lob
	public String getDescription() {
		return description;
	}

	public void setDescription(String pollName) {
		this.description = pollName;
	}

	public int getPushCount() {
		return pushCount;
	}

	public void setPushCount(int pushCount) {
		this.pushCount = pushCount;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
    @Temporal(TemporalType.DATE)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
  
}
