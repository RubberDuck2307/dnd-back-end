package dnd.monster_service.http.error;

import dnd.monster_service.http.error.exception.IdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ErrorHandler {


    @ExceptionHandler(value = IdNotFoundException.class)
    public ResponseEntity<?> handleStatusRuntimeException(IdNotFoundException e) {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.valueOf(400))
                .type("INVALID_ID")
                .message(e.getMessage())
                .build();
        return buildResponseEntity(apiError);
    }


    private ResponseEntity<ApiError> buildResponseEntity(ApiError apiError) {
        apiError.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
