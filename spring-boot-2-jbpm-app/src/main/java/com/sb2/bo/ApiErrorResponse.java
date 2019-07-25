package com.sb2.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ApiErrorResponse implements Serializable{
	private static final long serialVersionUID = 1L;
    private int responseCode;
    private String responseText;
    private String status;
    private String message;
    private List<Error> errors = new ArrayList<>();
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseText() {
		return responseText;
	}
	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Error> getErrors() {
		return errors;
	}
	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}
    
    
}
