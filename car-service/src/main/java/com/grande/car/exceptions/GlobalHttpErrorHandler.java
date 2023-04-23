package com.grande.car.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PlatesAlreadyUsedException.class)
    public ResponseEntity<Object> handlePlatesAlreadyUsedException(PlatesAlreadyUsedException e){
        return new ResponseEntity<>("Car with given plates already exists in database !", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(PlatesNotFoundException.class)
    public ResponseEntity<Object> handlePlatesNotFoundException(PlatesNotFoundException e){
        return new ResponseEntity<>("Car with given plates doesn't exists in database !", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<Object> handleCarNotFoundException(CarNotFoundException e){
        return new ResponseEntity<>("Car with given id doesn't exists in database !", HttpStatus.BAD_REQUEST);
    }

}
