package dnd.monster_service.rpc;

import dnd.exception.GrpcExceptionHandler;
import io.grpc.StatusException;
import org.springframework.stereotype.Component;

@Component
public class MonsterExceptionHandler extends GrpcExceptionHandler {


    @Override
    public StatusException buildException(Exception e) {
        return super.buildException(e);
    }
}
