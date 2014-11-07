package com.karmelos.kpoll.model;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class KeywordPollResponse extends PollResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 554379879474140930L;
	
	private String keywordResponse;

	public String getKeywordResponse() {
		return keywordResponse;
	}

	public void setKeywordResponse(String keywordResponse) {
		this.keywordResponse = keywordResponse;
	}

}
