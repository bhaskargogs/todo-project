package com.todo.web;

import org.springframework.http.HttpStatus;

public class ApplicationMessage {

	private HttpStatus status;
	private String message;

	public ApplicationMessage(HttpStatus ok, String message) {
		this.status = ok;
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
