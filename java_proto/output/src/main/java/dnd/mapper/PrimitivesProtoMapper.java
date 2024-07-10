package dnd.mapper;

import dnd.generated.Primitives;

public class PrimitivesProtoMapper {

    static public Primitives.Long buildProtoLong(long value){
        return Primitives.Long.newBuilder().setValue(value).build();
    }

    static public Long parseProtoLong(Primitives.Long proto){
        return proto.getValue();
    }

    static public Primitives.Empty buildProtoEmpty(){
        return Primitives.Empty.newBuilder().build();
    }

}
