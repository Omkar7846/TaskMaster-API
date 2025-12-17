package com.TaskMaster.TaskMaster.Model.DTO;


import java.time.LocalDateTime;

public class ErrorDetailsDTO {
    private LocalDateTime timestamp;
    private String message;
    private String details; // The request URI or path where the error occurred
    private int status;
    

	public ErrorDetailsDTO(LocalDateTime timestamp, String message, String details, int status) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
		this.status = status;
	}
    
    
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "ErrorDetailsDTO [timestamp=" + timestamp + ", message=" + message + ", details=" + details + ", status="
				+ status + "]";
	}
    
}