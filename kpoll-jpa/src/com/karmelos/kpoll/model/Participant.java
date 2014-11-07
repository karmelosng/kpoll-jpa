package com.karmelos.kpoll.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

@Entity
public class Participant implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1585242427460420954L;
	
	private String phoneNumber;
	
	private String passwordHash;
	
	private byte[] hashSalt;

	private List<InterestArea> interestAreas;

	@Id
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Lob
	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	@Lob
	public byte[] getHashSalt() {
		return hashSalt;
	}

	public void setHashSalt(byte[] hashSalt) {
		this.hashSalt = hashSalt;
	}

	@ManyToMany
	public List<InterestArea> getInterestAreas() {
		return interestAreas;
	}

	public void setInterestAreas(List<InterestArea> interestAreas) {
		this.interestAreas = interestAreas;
	}
}
