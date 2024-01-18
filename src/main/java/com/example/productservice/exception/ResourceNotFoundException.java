package com.example.productservice.exception;

public class ResourceNotFoundException extends RuntimeException{
    String resourceName;
    String fieldName;
    String fieldValue;
    public ResourceNotFoundException(String resourceName,String fieldName, String fieldValue){
        this.resourceName=resourceName;
        this.fieldName=fieldName;
        this.fieldValue=fieldValue;
    }
}
