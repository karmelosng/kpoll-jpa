package com.karmelos.kpoll.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class PollResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1690373786025167293L;
	
	private Long id;
	
	private Participant participant;
	
	private PollSurvey pollSurvey;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToOne
	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}

	@ManyToOne
	public PollSurvey getPollSurvey() {
		return pollSurvey;
	}

	public void setPollSurvey(PollSurvey pollSurvey) {
		this.pollSurvey = pollSurvey;
	}

}
