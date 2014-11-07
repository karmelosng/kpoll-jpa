package com.karmelos.kpoll.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ChoicePollResponse extends PollResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5727605380138426693L;
	
	private ChoicePollOption pollOption;

	@ManyToOne
	public ChoicePollOption getPollOption() {
		return pollOption;
	}

	public void setPollOption(ChoicePollOption pollOption) {
		this.pollOption = pollOption;
	}
}
