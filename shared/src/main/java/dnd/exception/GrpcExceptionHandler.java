package dnd.exception;

import io.grpc.Status;
import io.grpc.StatusException;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class GrpcExceptionHandler {

    Logger logger = Logger.getLogger(GrpcExceptionHandler.class.getName());
    public StatusException buildException(Exception e) {
        StatusException statusException;
        switch (e.getClass().getSimpleName()) {
            case "IllegalArgumentException" ->
            {
                return handleIllegalArgumentException((IllegalArgumentException) e);
            }
            default -> statusException = handleInternalException(e);
        }
        return statusException;
    }

    private StatusException handleInternalException(Exception e) {
//        Metadata metadata = buildErrorResponseMetadata("InternalException", e.getMessage(),
//                e.getStackTrace());
        logger.log(java.util.logging.Level.SEVERE, "InternalException", e);
        return Status.INTERNAL.asException();
    }

    private StatusException handleIllegalArgumentException(IllegalArgumentException e) {
//        Metadata metadata = buildErrorResponseMetadata("IllegalArgumentException", e.getMessage(),
//                e.getStackTrace());
        return Status.INVALID_ARGUMENT.asException();
    }

//    protected Metadata buildErrorResponseMetadata(String errorType, String message, StackTraceElement[] stackTrace) {
//        Metadata metadata = new Metadata();
//        Metadata.Key<Shared.ErrorResponse> errorResponseKey =
//                ProtoUtils.keyForProto(Shared.ErrorResponse.getDefaultInstance());
//        Shared.ErrorResponse errorResponse = Shared.ErrorResponse.newBuilder()
//                .setErrorType(errorType)
//                .setMessage(message)
//                .setStackTrace(Arrays.toString(stackTrace))
//                .build();
//        metadata.put(errorResponseKey, errorResponse);
//        return metadata;
//    }

}
