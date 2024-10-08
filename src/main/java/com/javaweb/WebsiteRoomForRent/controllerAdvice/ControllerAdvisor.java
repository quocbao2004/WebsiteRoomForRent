package com.javaweb.WebsiteRoomForRent.controllerAdvice;
import com.javaweb.WebsiteRoomForRent.customexceptions.InvalidParamException;
import com.javaweb.WebsiteRoomForRent.dtos.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidParamException.class)
    public ResponseEntity<Object> handleInvalidParamException(InvalidParamException ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO();
        List<String> errorsDetails = new ArrayList<>();
        errorsDetails.add("Invalid param");
        errorResponse.builder()
                .error(ex.getMessage())
                .details(errorsDetails)
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
