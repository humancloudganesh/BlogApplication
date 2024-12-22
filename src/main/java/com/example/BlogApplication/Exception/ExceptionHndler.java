package com.example.BlogApplication.Exception;

import com.example.BlogApplication.Responce.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHndler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> ResourceNotFoundExceptionHandler(ResourceNotFoundException ex)
    {
        String errorMessage = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse("errorMessage",false);

        return  new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex)
    {
        Map<String,String> stringStringMap = new HashMap<String,String>();
         ex.getBindingResult().getAllErrors().forEach((error) ->{

             String fieldName=((FieldError) error).getField();
             String ErrorMessage = error.getDefaultMessage();

             stringStringMap.put(fieldName,ErrorMessage);

         });

         return new ResponseEntity<Map<String, String>>(stringStringMap, HttpStatus.BAD_REQUEST);
    }
}
