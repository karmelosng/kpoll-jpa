package com.karmelos.kpoll.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ChoicePollOption implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7807589244209839738L;

	private Long id;
	
	private String description;
	
	private ChoicePoll choicePoll;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne
	public ChoicePoll getChoicePoll() {
		return choicePoll;
	}

	public void setChoicePoll(ChoicePoll choicePoll) {
		this.choicePoll = choicePoll;
	}
}
