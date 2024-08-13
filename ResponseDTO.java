package com.example.User;

public class ResponseDTO <T> {
    private String message;
    private T data;
    private boolean success;

    // Constructor with all fields
    public ResponseDTO(String message, T data, boolean success) {
        this.message = message;
        this.data = data;
        this.success = success;
    }

    // Constructor with message and data
    public ResponseDTO(String message, T data) {
        this.message = message;
        this.data = data;
        this.success = true; // Default to true if not specified
    }

    // Constructor with only message
    public ResponseDTO(String message) {
        this.message = message;
        this.data = null;
        this.success = false; // Default to false if not specified
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
