package dnd.api_gateway.generated.monster_service;

import dnd.api_gateway.generated.Shared;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
        value = "by gRPC proto compiler (version 1.59.0)",
        comments = "Source: monster_service.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MonsterServiceGrpc {

  private MonsterServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "MonsterService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<Shared.IdRpc,
          Shared.AmountRpc> getGetAmountOfMonsterByGroupIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
          fullMethodName = SERVICE_NAME + '/' + "getAmountOfMonsterByGroupId",
          requestType = Shared.IdRpc.class,
          responseType = Shared.AmountRpc.class,
          methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<Shared.IdRpc,
          Shared.AmountRpc> getGetAmountOfMonsterByGroupIdMethod() {
    io.grpc.MethodDescriptor<Shared.IdRpc, Shared.AmountRpc> getGetAmountOfMonsterByGroupIdMethod;
    if ((getGetAmountOfMonsterByGroupIdMethod = MonsterServiceGrpc.getGetAmountOfMonsterByGroupIdMethod) == null) {
      synchronized (MonsterServiceGrpc.class) {
        if ((getGetAmountOfMonsterByGroupIdMethod = MonsterServiceGrpc.getGetAmountOfMonsterByGroupIdMethod) == null) {
          MonsterServiceGrpc.getGetAmountOfMonsterByGroupIdMethod = getGetAmountOfMonsterByGroupIdMethod =
                  io.grpc.MethodDescriptor.<Shared.IdRpc, Shared.AmountRpc>newBuilder()
                          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                          .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getAmountOfMonsterByGroupId"))
                          .setSampledToLocalTracing(true)
                          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                  Shared.IdRpc.getDefaultInstance()))
                          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                  Shared.AmountRpc.getDefaultInstance()))
                          .setSchemaDescriptor(new MonsterServiceMethodDescriptorSupplier("getAmountOfMonsterByGroupId"))
                          .build();
        }
      }
    }
    return getGetAmountOfMonsterByGroupIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<Shared.IdRpc,
          MonsterServiceOuterClass.CrListRpc> getGetCrsByMonsterGroupIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
          fullMethodName = SERVICE_NAME + '/' + "getCrsByMonsterGroupId",
          requestType = Shared.IdRpc.class,
          responseType = MonsterServiceOuterClass.CrListRpc.class,
          methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<Shared.IdRpc,
          MonsterServiceOuterClass.CrListRpc> getGetCrsByMonsterGroupIdMethod() {
    io.grpc.MethodDescriptor<Shared.IdRpc, MonsterServiceOuterClass.CrListRpc> getGetCrsByMonsterGroupIdMethod;
    if ((getGetCrsByMonsterGroupIdMethod = MonsterServiceGrpc.getGetCrsByMonsterGroupIdMethod) == null) {
      synchronized (MonsterServiceGrpc.class) {
        if ((getGetCrsByMonsterGroupIdMethod = MonsterServiceGrpc.getGetCrsByMonsterGroupIdMethod) == null) {
          MonsterServiceGrpc.getGetCrsByMonsterGroupIdMethod = getGetCrsByMonsterGroupIdMethod =
                  io.grpc.MethodDescriptor.<Shared.IdRpc, MonsterServiceOuterClass.CrListRpc>newBuilder()
                          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                          .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getCrsByMonsterGroupId"))
                          .setSampledToLocalTracing(true)
                          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                  Shared.IdRpc.getDefaultInstance()))
                          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                  MonsterServiceOuterClass.CrListRpc.getDefaultInstance()))
                          .setSchemaDescriptor(new MonsterServiceMethodDescriptorSupplier("getCrsByMonsterGroupId"))
                          .build();
        }
      }
    }
    return getGetCrsByMonsterGroupIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<MonsterServiceOuterClass.GetMonstersCrGroupRequestRpc,
          MonsterServiceOuterClass.MonstersByCrRpc> getGetMonstersByCrAndGroupMethod;

  @io.grpc.stub.annotations.RpcMethod(
          fullMethodName = SERVICE_NAME + '/' + "getMonstersByCrAndGroup",
          requestType = MonsterServiceOuterClass.GetMonstersCrGroupRequestRpc.class,
          responseType = MonsterServiceOuterClass.MonstersByCrRpc.class,
          methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<MonsterServiceOuterClass.GetMonstersCrGroupRequestRpc,
          MonsterServiceOuterClass.MonstersByCrRpc> getGetMonstersByCrAndGroupMethod() {
    io.grpc.MethodDescriptor<MonsterServiceOuterClass.GetMonstersCrGroupRequestRpc, MonsterServiceOuterClass.MonstersByCrRpc> getGetMonstersByCrAndGroupMethod;
    if ((getGetMonstersByCrAndGroupMethod = MonsterServiceGrpc.getGetMonstersByCrAndGroupMethod) == null) {
      synchronized (MonsterServiceGrpc.class) {
        if ((getGetMonstersByCrAndGroupMethod = MonsterServiceGrpc.getGetMonstersByCrAndGroupMethod) == null) {
          MonsterServiceGrpc.getGetMonstersByCrAndGroupMethod = getGetMonstersByCrAndGroupMethod =
                  io.grpc.MethodDescriptor.<MonsterServiceOuterClass.GetMonstersCrGroupRequestRpc, MonsterServiceOuterClass.MonstersByCrRpc>newBuilder()
                          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                          .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getMonstersByCrAndGroup"))
                          .setSampledToLocalTracing(true)
                          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                  MonsterServiceOuterClass.GetMonstersCrGroupRequestRpc.getDefaultInstance()))
                          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                  MonsterServiceOuterClass.MonstersByCrRpc.getDefaultInstance()))
                          .setSchemaDescriptor(new MonsterServiceMethodDescriptorSupplier("getMonstersByCrAndGroup"))
                          .build();
        }
      }
    }
    return getGetMonstersByCrAndGroupMethod;
  }

  private static volatile io.grpc.MethodDescriptor<MonsterServiceOuterClass.RandomMonsterRequestRpc,
          MonsterServiceOuterClass.MonsterShortListRpc> getGetRandomMonstersByCrMethod;

  @io.grpc.stub.annotations.RpcMethod(
          fullMethodName = SERVICE_NAME + '/' + "getRandomMonstersByCr",
          requestType = MonsterServiceOuterClass.RandomMonsterRequestRpc.class,
          responseType = MonsterServiceOuterClass.MonsterShortListRpc.class,
          methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<MonsterServiceOuterClass.RandomMonsterRequestRpc,
          MonsterServiceOuterClass.MonsterShortListRpc> getGetRandomMonstersByCrMethod() {
    io.grpc.MethodDescriptor<MonsterServiceOuterClass.RandomMonsterRequestRpc, MonsterServiceOuterClass.MonsterShortListRpc> getGetRandomMonstersByCrMethod;
    if ((getGetRandomMonstersByCrMethod = MonsterServiceGrpc.getGetRandomMonstersByCrMethod) == null) {
      synchronized (MonsterServiceGrpc.class) {
        if ((getGetRandomMonstersByCrMethod = MonsterServiceGrpc.getGetRandomMonstersByCrMethod) == null) {
          MonsterServiceGrpc.getGetRandomMonstersByCrMethod = getGetRandomMonstersByCrMethod =
                  io.grpc.MethodDescriptor.<MonsterServiceOuterClass.RandomMonsterRequestRpc, MonsterServiceOuterClass.MonsterShortListRpc>newBuilder()
                          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                          .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getRandomMonstersByCr"))
                          .setSampledToLocalTracing(true)
                          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                  MonsterServiceOuterClass.RandomMonsterRequestRpc.getDefaultInstance()))
                          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                  MonsterServiceOuterClass.MonsterShortListRpc.getDefaultInstance()))
                          .setSchemaDescriptor(new MonsterServiceMethodDescriptorSupplier("getRandomMonstersByCr"))
                          .build();
        }
      }
    }
    return getGetRandomMonstersByCrMethod;
  }

  private static volatile io.grpc.MethodDescriptor<MonsterServiceOuterClass.AmountOfCrRpc,
          MonsterServiceOuterClass.MonstersByCrRpc> getGetMonstersByCrAmountMethod;

  @io.grpc.stub.annotations.RpcMethod(
          fullMethodName = SERVICE_NAME + '/' + "getMonstersByCrAmount",
          requestType = MonsterServiceOuterClass.AmountOfCrRpc.class,
          responseType = MonsterServiceOuterClass.MonstersByCrRpc.class,
          methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<MonsterServiceOuterClass.AmountOfCrRpc,
          MonsterServiceOuterClass.MonstersByCrRpc> getGetMonstersByCrAmountMethod() {
    io.grpc.MethodDescriptor<MonsterServiceOuterClass.AmountOfCrRpc, MonsterServiceOuterClass.MonstersByCrRpc> getGetMonstersByCrAmountMethod;
    if ((getGetMonstersByCrAmountMethod = MonsterServiceGrpc.getGetMonstersByCrAmountMethod) == null) {
      synchronized (MonsterServiceGrpc.class) {
        if ((getGetMonstersByCrAmountMethod = MonsterServiceGrpc.getGetMonstersByCrAmountMethod) == null) {
          MonsterServiceGrpc.getGetMonstersByCrAmountMethod = getGetMonstersByCrAmountMethod =
                  io.grpc.MethodDescriptor.<MonsterServiceOuterClass.AmountOfCrRpc, MonsterServiceOuterClass.MonstersByCrRpc>newBuilder()
                          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                          .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getMonstersByCrAmount"))
                          .setSampledToLocalTracing(true)
                          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                  MonsterServiceOuterClass.AmountOfCrRpc.getDefaultInstance()))
                          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                  MonsterServiceOuterClass.MonstersByCrRpc.getDefaultInstance()))
                          .setSchemaDescriptor(new MonsterServiceMethodDescriptorSupplier("getMonstersByCrAmount"))
                          .build();
        }
      }
    }
    return getGetMonstersByCrAmountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<MonsterServiceOuterClass.GetMonstersRequestRpc,
          MonsterServiceOuterClass.MonsterShortListRpc> getGetMonstersMethod;

  @io.grpc.stub.annotations.RpcMethod(
          fullMethodName = SERVICE_NAME + '/' + "getMonsters",
          requestType = MonsterServiceOuterClass.GetMonstersRequestRpc.class,
          responseType = MonsterServiceOuterClass.MonsterShortListRpc.class,
          methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<MonsterServiceOuterClass.GetMonstersRequestRpc,
          MonsterServiceOuterClass.MonsterShortListRpc> getGetMonstersMethod() {
    io.grpc.MethodDescriptor<MonsterServiceOuterClass.GetMonstersRequestRpc, MonsterServiceOuterClass.MonsterShortListRpc> getGetMonstersMethod;
    if ((getGetMonstersMethod = MonsterServiceGrpc.getGetMonstersMethod) == null) {
      synchronized (MonsterServiceGrpc.class) {
        if ((getGetMonstersMethod = MonsterServiceGrpc.getGetMonstersMethod) == null) {
          MonsterServiceGrpc.getGetMonstersMethod = getGetMonstersMethod =
                  io.grpc.MethodDescriptor.<MonsterServiceOuterClass.GetMonstersRequestRpc, MonsterServiceOuterClass.MonsterShortListRpc>newBuilder()
                          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                          .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getMonsters"))
                          .setSampledToLocalTracing(true)
                          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                  MonsterServiceOuterClass.GetMonstersRequestRpc.getDefaultInstance()))
                          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                  MonsterServiceOuterClass.MonsterShortListRpc.getDefaultInstance()))
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
    default void getAmountOfMonsterByGroupId(Shared.IdRpc request,
                                             io.grpc.stub.StreamObserver<Shared.AmountRpc> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetAmountOfMonsterByGroupIdMethod(), responseObserver);
    }

    /**
     */
    default void getCrsByMonsterGroupId(Shared.IdRpc request,
                                        io.grpc.stub.StreamObserver<MonsterServiceOuterClass.CrListRpc> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetCrsByMonsterGroupIdMethod(), responseObserver);
    }

    /**
     */
    default void getMonstersByCrAndGroup(MonsterServiceOuterClass.GetMonstersCrGroupRequestRpc request,
                                         io.grpc.stub.StreamObserver<MonsterServiceOuterClass.MonstersByCrRpc> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetMonstersByCrAndGroupMethod(), responseObserver);
    }

    /**
     */
    default void getRandomMonstersByCr(MonsterServiceOuterClass.RandomMonsterRequestRpc request,
                                       io.grpc.stub.StreamObserver<MonsterServiceOuterClass.MonsterShortListRpc> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetRandomMonstersByCrMethod(), responseObserver);
    }

    /**
     */
    default void getMonstersByCrAmount(MonsterServiceOuterClass.AmountOfCrRpc request,
                                       io.grpc.stub.StreamObserver<MonsterServiceOuterClass.MonstersByCrRpc> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetMonstersByCrAmountMethod(), responseObserver);
    }

    /**
     */
    default void getMonsters(MonsterServiceOuterClass.GetMonstersRequestRpc request,
                             io.grpc.stub.StreamObserver<MonsterServiceOuterClass.MonsterShortListRpc> responseObserver) {
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
    public void getAmountOfMonsterByGroupId(Shared.IdRpc request,
                                            io.grpc.stub.StreamObserver<Shared.AmountRpc> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
              getChannel().newCall(getGetAmountOfMonsterByGroupIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getCrsByMonsterGroupId(Shared.IdRpc request,
                                       io.grpc.stub.StreamObserver<MonsterServiceOuterClass.CrListRpc> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
              getChannel().newCall(getGetCrsByMonsterGroupIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getMonstersByCrAndGroup(MonsterServiceOuterClass.GetMonstersCrGroupRequestRpc request,
                                        io.grpc.stub.StreamObserver<MonsterServiceOuterClass.MonstersByCrRpc> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
              getChannel().newCall(getGetMonstersByCrAndGroupMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getRandomMonstersByCr(MonsterServiceOuterClass.RandomMonsterRequestRpc request,
                                      io.grpc.stub.StreamObserver<MonsterServiceOuterClass.MonsterShortListRpc> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
              getChannel().newCall(getGetRandomMonstersByCrMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getMonstersByCrAmount(MonsterServiceOuterClass.AmountOfCrRpc request,
                                      io.grpc.stub.StreamObserver<MonsterServiceOuterClass.MonstersByCrRpc> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
              getChannel().newCall(getGetMonstersByCrAmountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getMonsters(MonsterServiceOuterClass.GetMonstersRequestRpc request,
                            io.grpc.stub.StreamObserver<MonsterServiceOuterClass.MonsterShortListRpc> responseObserver) {
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
    public Shared.AmountRpc getAmountOfMonsterByGroupId(Shared.IdRpc request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
              getChannel(), getGetAmountOfMonsterByGroupIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public MonsterServiceOuterClass.CrListRpc getCrsByMonsterGroupId(Shared.IdRpc request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
              getChannel(), getGetCrsByMonsterGroupIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public MonsterServiceOuterClass.MonstersByCrRpc getMonstersByCrAndGroup(MonsterServiceOuterClass.GetMonstersCrGroupRequestRpc request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
              getChannel(), getGetMonstersByCrAndGroupMethod(), getCallOptions(), request);
    }

    /**
     */
    public MonsterServiceOuterClass.MonsterShortListRpc getRandomMonstersByCr(MonsterServiceOuterClass.RandomMonsterRequestRpc request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
              getChannel(), getGetRandomMonstersByCrMethod(), getCallOptions(), request);
    }

    /**
     */
    public MonsterServiceOuterClass.MonstersByCrRpc getMonstersByCrAmount(MonsterServiceOuterClass.AmountOfCrRpc request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
              getChannel(), getGetMonstersByCrAmountMethod(), getCallOptions(), request);
    }

    /**
     */
    public MonsterServiceOuterClass.MonsterShortListRpc getMonsters(MonsterServiceOuterClass.GetMonstersRequestRpc request) {
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
    public com.google.common.util.concurrent.ListenableFuture<Shared.AmountRpc> getAmountOfMonsterByGroupId(
            Shared.IdRpc request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
              getChannel().newCall(getGetAmountOfMonsterByGroupIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<MonsterServiceOuterClass.CrListRpc> getCrsByMonsterGroupId(
            Shared.IdRpc request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
              getChannel().newCall(getGetCrsByMonsterGroupIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<MonsterServiceOuterClass.MonstersByCrRpc> getMonstersByCrAndGroup(
            MonsterServiceOuterClass.GetMonstersCrGroupRequestRpc request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
              getChannel().newCall(getGetMonstersByCrAndGroupMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<MonsterServiceOuterClass.MonsterShortListRpc> getRandomMonstersByCr(
            MonsterServiceOuterClass.RandomMonsterRequestRpc request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
              getChannel().newCall(getGetRandomMonstersByCrMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<MonsterServiceOuterClass.MonstersByCrRpc> getMonstersByCrAmount(
            MonsterServiceOuterClass.AmountOfCrRpc request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
              getChannel().newCall(getGetMonstersByCrAmountMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<MonsterServiceOuterClass.MonsterShortListRpc> getMonsters(
            MonsterServiceOuterClass.GetMonstersRequestRpc request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
              getChannel().newCall(getGetMonstersMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_AMOUNT_OF_MONSTER_BY_GROUP_ID = 0;
  private static final int METHODID_GET_CRS_BY_MONSTER_GROUP_ID = 1;
  private static final int METHODID_GET_MONSTERS_BY_CR_AND_GROUP = 2;
  private static final int METHODID_GET_RANDOM_MONSTERS_BY_CR = 3;
  private static final int METHODID_GET_MONSTERS_BY_CR_AMOUNT = 4;
  private static final int METHODID_GET_MONSTERS = 5;

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
        case METHODID_GET_AMOUNT_OF_MONSTER_BY_GROUP_ID:
          serviceImpl.getAmountOfMonsterByGroupId((Shared.IdRpc) request,
                  (io.grpc.stub.StreamObserver<Shared.AmountRpc>) responseObserver);
          break;
        case METHODID_GET_CRS_BY_MONSTER_GROUP_ID:
          serviceImpl.getCrsByMonsterGroupId((Shared.IdRpc) request,
                  (io.grpc.stub.StreamObserver<MonsterServiceOuterClass.CrListRpc>) responseObserver);
          break;
        case METHODID_GET_MONSTERS_BY_CR_AND_GROUP:
          serviceImpl.getMonstersByCrAndGroup((MonsterServiceOuterClass.GetMonstersCrGroupRequestRpc) request,
                  (io.grpc.stub.StreamObserver<MonsterServiceOuterClass.MonstersByCrRpc>) responseObserver);
          break;
        case METHODID_GET_RANDOM_MONSTERS_BY_CR:
          serviceImpl.getRandomMonstersByCr((MonsterServiceOuterClass.RandomMonsterRequestRpc) request,
                  (io.grpc.stub.StreamObserver<MonsterServiceOuterClass.MonsterShortListRpc>) responseObserver);
          break;
        case METHODID_GET_MONSTERS_BY_CR_AMOUNT:
          serviceImpl.getMonstersByCrAmount((MonsterServiceOuterClass.AmountOfCrRpc) request,
                  (io.grpc.stub.StreamObserver<MonsterServiceOuterClass.MonstersByCrRpc>) responseObserver);
          break;
        case METHODID_GET_MONSTERS:
          serviceImpl.getMonsters((MonsterServiceOuterClass.GetMonstersRequestRpc) request,
                  (io.grpc.stub.StreamObserver<MonsterServiceOuterClass.MonsterShortListRpc>) responseObserver);
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
                    getGetAmountOfMonsterByGroupIdMethod(),
                    io.grpc.stub.ServerCalls.asyncUnaryCall(
                            new MethodHandlers<
                                    Shared.IdRpc,
                                    Shared.AmountRpc>(
                                    service, METHODID_GET_AMOUNT_OF_MONSTER_BY_GROUP_ID)))
            .addMethod(
                    getGetCrsByMonsterGroupIdMethod(),
                    io.grpc.stub.ServerCalls.asyncUnaryCall(
                            new MethodHandlers<
                                    Shared.IdRpc,
                                    MonsterServiceOuterClass.CrListRpc>(
                                    service, METHODID_GET_CRS_BY_MONSTER_GROUP_ID)))
            .addMethod(
                    getGetMonstersByCrAndGroupMethod(),
                    io.grpc.stub.ServerCalls.asyncUnaryCall(
                            new MethodHandlers<
                                    MonsterServiceOuterClass.GetMonstersCrGroupRequestRpc,
                                    MonsterServiceOuterClass.MonstersByCrRpc>(
                                    service, METHODID_GET_MONSTERS_BY_CR_AND_GROUP)))
            .addMethod(
                    getGetRandomMonstersByCrMethod(),
                    io.grpc.stub.ServerCalls.asyncUnaryCall(
                            new MethodHandlers<
                                    MonsterServiceOuterClass.RandomMonsterRequestRpc,
                                    MonsterServiceOuterClass.MonsterShortListRpc>(
                                    service, METHODID_GET_RANDOM_MONSTERS_BY_CR)))
            .addMethod(
                    getGetMonstersByCrAmountMethod(),
                    io.grpc.stub.ServerCalls.asyncUnaryCall(
                            new MethodHandlers<
                                    MonsterServiceOuterClass.AmountOfCrRpc,
                                    MonsterServiceOuterClass.MonstersByCrRpc>(
                                    service, METHODID_GET_MONSTERS_BY_CR_AMOUNT)))
            .addMethod(
                    getGetMonstersMethod(),
                    io.grpc.stub.ServerCalls.asyncUnaryCall(
                            new MethodHandlers<
                                    MonsterServiceOuterClass.GetMonstersRequestRpc,
                                    MonsterServiceOuterClass.MonsterShortListRpc>(
                                    service, METHODID_GET_MONSTERS)))
            .build();
  }

  private static abstract class MonsterServiceBaseDescriptorSupplier
          implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MonsterServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return MonsterServiceOuterClass.getDescriptor();
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
                  .addMethod(getGetAmountOfMonsterByGroupIdMethod())
                  .addMethod(getGetCrsByMonsterGroupIdMethod())
                  .addMethod(getGetMonstersByCrAndGroupMethod())
                  .addMethod(getGetRandomMonstersByCrMethod())
                  .addMethod(getGetMonstersByCrAmountMethod())
                  .addMethod(getGetMonstersMethod())
                  .build();
        }
      }
    }
    return result;
  }
}
