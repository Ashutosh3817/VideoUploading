package com.vidupload.exception;


public class ResourceNotFoundException extends RuntimeException{

	private boolean status;
	private String message;
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "ResourceNotFoundException [status=" + status + ", message=" + message + "]";
	}
	public ResourceNotFoundException(boolean status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	public ResourceNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}
}