package com.karmelos.kpoll.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
public class FreeTextPollResponse extends PollResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1796718307354504027L;

	private String textResponse;

	@Lob
	public String getTextResponse() {
		return textResponse;
	}

	public void setTextResponse(String textResponse) {
		this.textResponse = textResponse;
	}
}
