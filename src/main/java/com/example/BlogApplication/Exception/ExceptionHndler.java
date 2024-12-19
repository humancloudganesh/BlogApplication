package com.example.BlogApplication.Exception;

import com.example.BlogApplication.Responce.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHndler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<UserResponse> ResourceNotFoundExceptionHandler(ResourceNotFoundException ex)
    {
        String errorMessage = ex.getMessage();
        UserResponse userResponse = new UserResponse("errorMessage",false);

        return  new ResponseEntity<>(userResponse, HttpStatus.NOT_FOUND);
    }
}
