package com.karmelos.kpoll.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class KeywordPollResponse extends PollResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 554379879474140930L;
	
	private KeywordOption keywordResponse;
	
	
   @ManyToOne
	public KeywordOption getKeywordResponse() {
		return keywordResponse;
	}
  
	public void setKeywordResponse(KeywordOption keywordResponse) {
		this.keywordResponse = keywordResponse;
	}

}
