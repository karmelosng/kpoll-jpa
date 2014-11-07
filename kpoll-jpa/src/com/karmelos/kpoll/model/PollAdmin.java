package com.karmelos.kpoll.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PollAdmin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5130529835299676289L;
	
	private String emailAddress;
	
	private String passwordHash;
	
	private byte[] hashSalt;

	@Id
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public byte[] getHashSalt() {
		return hashSalt;
	}

	public void setHashSalt(byte[] hashSalt) {
		this.hashSalt = hashSalt;
	}
}
