package com.petcommunity.pet_lover_server_side.dto;

public class ResponseMessage<T> {
	private T data;
	private String message;
	private int statusCode;
	private String error;
	
	public ResponseMessage() {}
	public ResponseMessage(String message, int statusCode, String error) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.error = error;
	}
	public ResponseMessage(T data, String message, int statusCode, String error) {
		super();
		this.data = data;
		this.message = message;
		this.statusCode = statusCode;
		this.error = error;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
}
