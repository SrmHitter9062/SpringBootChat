package com.srmhitter9062.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class ApiResponse {

    private String status = "success";

    private Object data;

    private Object error;

    private int statusCode = 200;

    public ApiResponse(String status,Object data,Object error,int statusCode) {
        this.status = status;
        this.data = data;
        this.error = error;
        this.statusCode = statusCode;
    }

    public String getStatus() {
        return this.status;
    }
    public void setStatus(String status){
        this.status = status;
    }

    public Object getData() {
        return this.data;
    }
    public void setData(Object data){
        this.data = data;
    }

    public Object getError() {
        return this.error;
    }
    public void setError(Object error){
        this.error = error;
    }

    public int getStatusCode() {
        return this.statusCode;
    }
    public void setStatusCode(int statusCode){
        this.statusCode = statusCode;
    }

}
