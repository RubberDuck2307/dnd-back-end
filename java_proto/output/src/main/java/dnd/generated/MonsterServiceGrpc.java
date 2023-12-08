package dnd.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: monster_service.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MonsterServiceGrpc {

  private MonsterServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "MonsterService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<dnd.generated.MonsterServiceOuterClass.RandomMonsterRequestRpc,
      dnd.generated.MonsterServiceOuterClass.MonsterShortListRpc> getGetRandomMonstersByCrMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getRandomMonstersByCr",
      requestType = dnd.generated.MonsterServiceOuterClass.RandomMonsterRequestRpc.class,
      responseType = dnd.generated.MonsterServiceOuterClass.MonsterShortListRpc.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dnd.generated.MonsterServiceOuterClass.RandomMonsterRequestRpc,
      dnd.generated.MonsterServiceOuterClass.MonsterShortListRpc> getGetRandomMonstersByCrMethod() {
    io.grpc.MethodDescriptor<dnd.generated.MonsterServiceOuterClass.RandomMonsterRequestRpc, dnd.generated.MonsterServiceOuterClass.MonsterShortListRpc> getGetRandomMonstersByCrMethod;
    if ((getGetRandomMonstersByCrMethod = MonsterServiceGrpc.getGetRandomMonstersByCrMethod) == null) {
      synchronized (MonsterServiceGrpc.class) {
        if ((getGetRandomMonstersByCrMethod = MonsterServiceGrpc.getGetRandomMonstersByCrMethod) == null) {
          MonsterServiceGrpc.getGetRandomMonstersByCrMethod = getGetRandomMonstersByCrMethod =
              io.grpc.MethodDescriptor.<dnd.generated.MonsterServiceOuterClass.RandomMonsterRequestRpc, dnd.generated.MonsterServiceOuterClass.MonsterShortListRpc>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getRandomMonstersByCr"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dnd.generated.MonsterServiceOuterClass.RandomMonsterRequestRpc.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dnd.generated.MonsterServiceOuterClass.MonsterShortListRpc.getDefaultInstance()))
              .setSchemaDescriptor(new MonsterServiceMethodDescriptorSupplier("getRandomMonstersByCr"))
              .build();
        }
      }
    }
    return getGetRandomMonstersByCrMethod;
  }

  private static volatile io.grpc.MethodDescriptor<dnd.generated.MonsterServiceOuterClass.GetMonstersRequestRpc,
      dnd.generated.MonsterServiceOuterClass.MonsterShortListRpc> getGetMonstersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getMonsters",
      requestType = dnd.generated.MonsterServiceOuterClass.GetMonstersRequestRpc.class,
      responseType = dnd.generated.MonsterServiceOuterClass.MonsterShortListRpc.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dnd.generated.MonsterServiceOuterClass.GetMonstersRequestRpc,
      dnd.generated.MonsterServiceOuterClass.MonsterShortListRpc> getGetMonstersMethod() {
    io.grpc.MethodDescriptor<dnd.generated.MonsterServiceOuterClass.GetMonstersRequestRpc, dnd.generated.MonsterServiceOuterClass.MonsterShortListRpc> getGetMonstersMethod;
    if ((getGetMonstersMethod = MonsterServiceGrpc.getGetMonstersMethod) == null) {
      synchronized (MonsterServiceGrpc.class) {
        if ((getGetMonstersMethod = MonsterServiceGrpc.getGetMonstersMethod) == null) {
          MonsterServiceGrpc.getGetMonstersMethod = getGetMonstersMethod =
              io.grpc.MethodDescriptor.<dnd.generated.MonsterServiceOuterClass.GetMonstersRequestRpc, dnd.generated.MonsterServiceOuterClass.MonsterShortListRpc>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getMonsters"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dnd.generated.MonsterServiceOuterClass.GetMonstersRequestRpc.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dnd.generated.MonsterServiceOuterClass.MonsterShortListRpc.getDefaultInstance()))
              .setSchemaDescriptor(new MonsterServiceMethodDescriptorSupplier("getMonsters"))
              .build();
        }
      }
    }
    return getGetMonstersMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MonsterServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MonsterServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MonsterServiceStub>() {
        @java.lang.Override
        public MonsterServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MonsterServiceStub(channel, callOptions);
        }
      };
    return MonsterServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MonsterServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MonsterServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MonsterServiceBlockingStub>() {
        @java.lang.Override
        public MonsterServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MonsterServiceBlockingStub(channel, callOptions);
        }
      };
    return MonsterServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MonsterServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MonsterServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MonsterServiceFutureStub>() {
        @java.lang.Override
        public MonsterServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MonsterServiceFutureStub(channel, callOptions);
        }
      };
    return MonsterServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void getRandomMonstersByCr(dnd.generated.MonsterServiceOuterClass.RandomMonsterRequestRpc request,
        io.grpc.stub.StreamObserver<dnd.generated.MonsterServiceOuterClass.MonsterShortListRpc> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetRandomMonstersByCrMethod(), responseObserver);
    }

    /**
     */
    default void getMonsters(dnd.generated.MonsterServiceOuterClass.GetMonstersRequestRpc request,
        io.grpc.stub.StreamObserver<dnd.generated.MonsterServiceOuterClass.MonsterShortListRpc> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetMonstersMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service MonsterService.
   */
  public static abstract class MonsterServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return MonsterServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service MonsterService.
   */
  public static final class MonsterServiceStub
      extends io.grpc.stub.AbstractAsyncStub<MonsterServiceStub> {
    private MonsterServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MonsterServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MonsterServiceStub(channel, callOptions);
    }

    /**
     */
    public void getRandomMonstersByCr(dnd.generated.MonsterServiceOuterClass.RandomMonsterRequestRpc request,
        io.grpc.stub.StreamObserver<dnd.generated.MonsterServiceOuterClass.MonsterShortListRpc> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetRandomMonstersByCrMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getMonsters(dnd.generated.MonsterServiceOuterClass.GetMonstersRequestRpc request,
        io.grpc.stub.StreamObserver<dnd.generated.MonsterServiceOuterClass.MonsterShortListRpc> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetMonstersMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service MonsterService.
   */
  public static final class MonsterServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<MonsterServiceBlockingStub> {
    private MonsterServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MonsterServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MonsterServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public dnd.generated.MonsterServiceOuterClass.MonsterShortListRpc getRandomMonstersByCr(dnd.generated.MonsterServiceOuterClass.RandomMonsterRequestRpc request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetRandomMonstersByCrMethod(), getCallOptions(), request);
    }

    /**
     */
    public dnd.generated.MonsterServiceOuterClass.MonsterShortListRpc getMonsters(dnd.generated.MonsterServiceOuterClass.GetMonstersRequestRpc request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetMonstersMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service MonsterService.
   */
  public static final class MonsterServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<MonsterServiceFutureStub> {
    private MonsterServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MonsterServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MonsterServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dnd.generated.MonsterServiceOuterClass.MonsterShortListRpc> getRandomMonstersByCr(
        dnd.generated.MonsterServiceOuterClass.RandomMonsterRequestRpc request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetRandomMonstersByCrMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dnd.generated.MonsterServiceOuterClass.MonsterShortListRpc> getMonsters(
        dnd.generated.MonsterServiceOuterClass.GetMonstersRequestRpc request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetMonstersMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_RANDOM_MONSTERS_BY_CR = 0;
  private static final int METHODID_GET_MONSTERS = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_RANDOM_MONSTERS_BY_CR:
          serviceImpl.getRandomMonstersByCr((dnd.generated.MonsterServiceOuterClass.RandomMonsterRequestRpc) request,
              (io.grpc.stub.StreamObserver<dnd.generated.MonsterServiceOuterClass.MonsterShortListRpc>) responseObserver);
          break;
        case METHODID_GET_MONSTERS:
          serviceImpl.getMonsters((dnd.generated.MonsterServiceOuterClass.GetMonstersRequestRpc) request,
              (io.grpc.stub.StreamObserver<dnd.generated.MonsterServiceOuterClass.MonsterShortListRpc>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getGetRandomMonstersByCrMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              dnd.generated.MonsterServiceOuterClass.RandomMonsterRequestRpc,
              dnd.generated.MonsterServiceOuterClass.MonsterShortListRpc>(
                service, METHODID_GET_RANDOM_MONSTERS_BY_CR)))
        .addMethod(
          getGetMonstersMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              dnd.generated.MonsterServiceOuterClass.GetMonstersRequestRpc,
              dnd.generated.MonsterServiceOuterClass.MonsterShortListRpc>(
                service, METHODID_GET_MONSTERS)))
        .build();
  }

  private static abstract class MonsterServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MonsterServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return dnd.generated.MonsterServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MonsterService");
    }
  }

  private static final class MonsterServiceFileDescriptorSupplier
      extends MonsterServiceBaseDescriptorSupplier {
    MonsterServiceFileDescriptorSupplier() {}
  }

  private static final class MonsterServiceMethodDescriptorSupplier
      extends MonsterServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    MonsterServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (MonsterServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MonsterServiceFileDescriptorSupplier())
              .addMethod(getGetRandomMonstersByCrMethod())
              .addMethod(getGetMonstersMethod())
              .build();
        }
      }
    }
    return result;
  }
}
