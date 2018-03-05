package com.marco.spittr.api;

public class Error {

	private int code;
	private String message;

	public Error(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
