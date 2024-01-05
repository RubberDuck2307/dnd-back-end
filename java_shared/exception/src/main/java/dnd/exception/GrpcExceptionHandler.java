package dnd.exception;

import dnd.generated.Shared;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.protobuf.ProtoUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class GrpcExceptionHandler {

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
        Metadata metadata = buildErrorResponseMetadata("InternalException", e.getMessage(),
                e.getStackTrace());
        return Status.INTERNAL.asException(metadata);
    }

    private StatusException handleIllegalArgumentException(IllegalArgumentException e) {
        Metadata metadata = buildErrorResponseMetadata("IllegalArgumentException", e.getMessage(),
                e.getStackTrace());
        return Status.INVALID_ARGUMENT.asException(metadata);
    }

    protected Metadata buildErrorResponseMetadata(String errorType, String message, StackTraceElement[] stackTrace) {
        Metadata metadata = new Metadata();
        Metadata.Key<Shared.ErrorResponse> errorResponseKey =
                ProtoUtils.keyForProto(Shared.ErrorResponse.getDefaultInstance());
        Shared.ErrorResponse errorResponse = Shared.ErrorResponse.newBuilder()
                .setErrorType(errorType)
                .setMessage(message)
                .setStackTrace(Arrays.toString(stackTrace))
                .build();
        metadata.put(errorResponseKey, errorResponse);
        return metadata;
    }

}
