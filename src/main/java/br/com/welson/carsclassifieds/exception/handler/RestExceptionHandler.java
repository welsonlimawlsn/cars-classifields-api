package br.com.welson.carsclassifieds.exception.handler;

import br.com.welson.carsclassifieds.exception.CustomConstraintViolationException;
import br.com.welson.carsclassifieds.exception.CustomDataIntegrityViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Date;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e) {
        CustomConstraintViolationException customConstraintViolationException = CustomConstraintViolationException.Builder.builder()
                .timestamp(new Date().getTime())
                .status(BAD_REQUEST.value())
                .title("Bad Request")
                .detail(e.getConstraintViolations().iterator().next().getMessage())
                .developerMessage(e.getClass().getName())
                .build();
        return new ResponseEntity<>(customConstraintViolationException, BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        CustomDataIntegrityViolationException customDataIntegrityViolationException = CustomDataIntegrityViolationException.Builder.builder()
                .timestamp(new Date().getTime())
                .status(BAD_REQUEST.value())
                .title("Bad Request")
                .detail(e.getRootCause().getMessage())
                .developerMessage(e.getClass().getName())
                .build();
        return new ResponseEntity<>(customDataIntegrityViolationException, BAD_REQUEST);
    }


}
