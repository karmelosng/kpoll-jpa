package com.karmelos.kpoll.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class ChoicePoll extends PollSurvey implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5923892456271409490L;

	private List<ChoicePollOption> pollOptions;

	@OneToMany(mappedBy = "choicePoll")
	public List<ChoicePollOption> getPollOptions() {
		return pollOptions;
	}

	public void setPollOptions(List<ChoicePollOption> pollOptions) {
		this.pollOptions = pollOptions;
	}
}
