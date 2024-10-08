package co.com.bancolombia.api;

import common.ResponseData;
import common.exceptions.BussinessException;
import common.exceptions.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResponseHandler {


    @ExceptionHandler({BussinessException.class})
    public ResponseEntity<ResponseData<String>> bussinesExceptionHandler
            (BussinessException e) {
        return new ResponseEntity<>(ResponseData.<String>builder()
                .status(e.getCode()).message(e.getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ServiceException.class})
    public ResponseEntity<ResponseData<String>> serviceExceptionHandler
            (ServiceException e){
        return new ResponseEntity<>(ResponseData.<String>builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value()).message(e.getMessage())
                .build(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
