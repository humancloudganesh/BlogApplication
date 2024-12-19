package com.example.BlogApplication.Exception;

public class ResourceNotFoundException extends RuntimeException{

    String resourceName;
    String fileName;
    long fieldValue;

    public ResourceNotFoundException(String resourceName, String fileName, long fieldValue) {
        super(String.format("%s is not found with %s:%s",resourceName,fileName,fieldValue));
        this.resourceName = resourceName;
        this.fileName = fileName;
        this.fieldValue = fieldValue;
    }
}
