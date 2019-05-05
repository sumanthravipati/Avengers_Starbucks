package com.avengers.starbucks.dto;

import java.io.Serializable;

public class GenericResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String message;

	public GenericResponse() {
	}

	public GenericResponse(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}