package dnd.mapper;

import dnd.generated.Primitives;

public class PrimitivesProtoMapper {

    static public Primitives.LongRpc buildProtoLong(long value){
        return Primitives.LongRpc.newBuilder().setValue(value).build();
    }

    static public Long parseProtoLong(Primitives.LongRpc proto){
        return proto.getValue();
    }

    static public Primitives.EmptyRpc buildProtoEmpty(){
        return Primitives.EmptyRpc.newBuilder().build();
    }

}
