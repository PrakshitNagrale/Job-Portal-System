package com.prakshit.jobportalsystem.exceptions;

import com.prakshit.jobportalsystem.dto.ExceptionResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class UserControllerExceptionHandler {

    //to handle duplicate emails
    @ExceptionHandler(EmailAlreadyExistsException.class)     //to tell controller that if any email related exception occurs send here
    public ResponseEntity<ExceptionResponseDTO> handleEmailAlreadyExistsException(EmailAlreadyExistsException exception){

        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(
                exception.getMessage(),409);

        return new ResponseEntity<>(exceptionResponseDTO , HttpStatus.CONFLICT);
    }

    //to handle all the validations errors(ex.@notnull,@Email)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationErrors(MethodArgumentNotValidException ex){

        Map<String,String> errors = new HashMap<>();

        for(FieldError error : ex.getBindingResult().getFieldErrors()){ //iterating on all the validations errors and putting in map
            errors.put(error.getField(),error.getDefaultMessage());
        }
        return  new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }

    //to handle the enum error, if the enum values are not put properly
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponseDTO> handleInvalidEnum(HttpMessageNotReadableException ex){

        String message = "Invalid Request! Check Enum Fields and Values";

        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(

                message, HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(exceptionResponseDTO,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> handleResourceNotFoundException(ResourceNotFoundException exception){

        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(
                exception.getMessage(),HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(exceptionResponseDTO ,HttpStatus.NOT_FOUND);
    }


    //to handle EmployerProfileAlreadyExistsException
    @ExceptionHandler(ProfileAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponseDTO> handleProfileAlreadyExists(ProfileAlreadyExistsException exception){

        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(
                exception.getMessage(),HttpStatus.CONFLICT.value() );

        return new ResponseEntity<>(exceptionResponseDTO,HttpStatus.CONFLICT);
    }



}
