package com.karmelos.kpoll.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class PollOwner implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5130529835299676289L;
	
	private String emailAddress;
	
	private String passwordHash;
	
	private byte[] hashSalt;
	
	private String ownerName;
	
	private List<PollSurvey> pollActivities;
	
	private List<Participant> affiliates;

	@Id
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Lob
	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	@Lob
	public byte[] getHashSalt() {
		return hashSalt;
	}

	public void setHashSalt(byte[] hashSalt) {
		this.hashSalt = hashSalt;
	}

	@OneToMany(mappedBy = "pollOwner")
	public List<PollSurvey> getPollActivities() {
		return pollActivities;
	}

	public void setPollActivities(List<PollSurvey> pollActivities) {
		this.pollActivities = pollActivities;
	}

	@ManyToMany
	public List<Participant> getAffiliates() {
		return affiliates;
	}

	public void setAffiliates(List<Participant> affiliates) {
		this.affiliates = affiliates;
	}
}
