package dnd.api_gateway.exception;

import dnd.generated.Shared;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.protobuf.ProtoUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Stream;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = Logger.getLogger(ApiExceptionHandler.class.getName());
    private final Map<Integer, Integer> statusMap = Map.of(
            Status.INVALID_ARGUMENT.getCode().value(), 400,
            Status.NOT_FOUND.getCode().value(), 404,
            Status.ALREADY_EXISTS.getCode().value(), 409,
            Status.PERMISSION_DENIED.getCode().value(), 403,
            Status.UNAUTHENTICATED.getCode().value(), 401,
            Status.UNIMPLEMENTED.getCode().value(), 501,
            Status.UNAVAILABLE.getCode().value(), 503,
            Status.UNKNOWN.getCode().value(), 500,
            Status.INTERNAL.getCode().value(), 500,
            Status.DATA_LOSS.getCode().value(), 500
    );

    @ExceptionHandler(value = StatusRuntimeException.class)
    public ResponseEntity<?> handleStatusRuntimeException(StatusRuntimeException e) {
        Shared.ErrorResponse errorResponse;
        try {
            errorResponse = getErrorResponse(e);
        } catch (Exception ex) {
            return buildInternalErrorResponse();
        }
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.valueOf(statusMap.getOrDefault(e.getStatus().getCode().value(), 500)))
                .type(errorResponse.getErrorType())
                .message(errorResponse.getMessage())
                .build();
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<ApiError> buildResponseEntity(ApiError apiError) {
        apiError.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }


    private ResponseEntity<?> buildInternalErrorResponse() {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .type("InternalError")
                .message("Something went wrong")
                .build();
        return buildResponseEntity(apiError);
    }

    private Shared.ErrorResponse getErrorResponse(StatusRuntimeException e) {
        Metadata metadata = Status.trailersFromThrowable(e);
        if (metadata == null) {
            logger.log(java.util.logging.Level.INFO,
                    "Thrown StatusRuntimeException: " + e.getMessage() + " was missing metadata");
            throw new RuntimeException("No metadata in exception", e);
        }
        return metadata.get(ProtoUtils.keyForProto(Shared.ErrorResponse.getDefaultInstance()));
    }

}
