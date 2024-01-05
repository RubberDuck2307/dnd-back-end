package dnd.encounter_service.exception;

import dnd.exception.GrpcExceptionHandler;
import dnd.exception.NoSuchEncounterException;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.StatusException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class EncounterGrpcExceptionHandler extends GrpcExceptionHandler {

    @Override
    public StatusException buildException(Exception e) {
        StatusException statusException;
        switch (e.getClass().getSimpleName()) {
            case "NoSuchEncounterException" ->
            {
                return handleNoSuchEncounterException((NoSuchEncounterException) e);
            }
            default -> statusException = super.buildException(e);
        }
        return statusException;
    }

    private StatusException handleNoSuchEncounterException(NoSuchEncounterException e) {
        Metadata metadata = buildErrorResponseMetadata("NoSuchEncounterException", e.getMessage(),
                e.getStackTrace());
        return Status.INVALID_ARGUMENT.asException(metadata);
    }
}
