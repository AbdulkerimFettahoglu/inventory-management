package dev.kerimfettahoglu.inventorymanagement.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@ControllerAdvice
public class AppExceptionHandler {

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String argumentExceptionHandler(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage(), ex);
        return "Validation error.\n" + ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String dataNotFoundHandler(DataNotFoundException ex) {
        log.error(ex.getMessage(), ex);
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String businessExceptionHandler(BusinessException ex) {
        log.error(ex.getMessage(), ex);
        return "Business exception happened.\n" + ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String runtimeExceptionHandler(RuntimeException ex) {
        log.error(ex.getMessage(), ex);
        return "Runtime exception happened.\n" + ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String exceptionHandler(Exception ex) {
        log.error("Something bad happened.", ex);
        return "Something bad happened. " + ex.getMessage();
    }

}
