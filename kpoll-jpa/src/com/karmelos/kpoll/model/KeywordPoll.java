package com.karmelos.kpoll.model;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class KeywordPoll extends PollSurvey implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2824419239355139562L;
	
	private String keywordId;

	public String getKeywordId() {
		return keywordId;
	}

	public void setKeywordId(String keywordId) {
		this.keywordId = keywordId;
	}
}
