package dnd.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: encounter_service.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class EncounterServiceGrpc {

  private EncounterServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "EncounterService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<dnd.generated.EncounterServiceOuterClass.GenerateEncounterRequest,
      dnd.generated.EncounterServiceOuterClass.EncounterListRpc> getGenerateEncountersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "generateEncounters",
      requestType = dnd.generated.EncounterServiceOuterClass.GenerateEncounterRequest.class,
      responseType = dnd.generated.EncounterServiceOuterClass.EncounterListRpc.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dnd.generated.EncounterServiceOuterClass.GenerateEncounterRequest,
      dnd.generated.EncounterServiceOuterClass.EncounterListRpc> getGenerateEncountersMethod() {
    io.grpc.MethodDescriptor<dnd.generated.EncounterServiceOuterClass.GenerateEncounterRequest, dnd.generated.EncounterServiceOuterClass.EncounterListRpc> getGenerateEncountersMethod;
    if ((getGenerateEncountersMethod = EncounterServiceGrpc.getGenerateEncountersMethod) == null) {
      synchronized (EncounterServiceGrpc.class) {
        if ((getGenerateEncountersMethod = EncounterServiceGrpc.getGenerateEncountersMethod) == null) {
          EncounterServiceGrpc.getGenerateEncountersMethod = getGenerateEncountersMethod =
              io.grpc.MethodDescriptor.<dnd.generated.EncounterServiceOuterClass.GenerateEncounterRequest, dnd.generated.EncounterServiceOuterClass.EncounterListRpc>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "generateEncounters"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dnd.generated.EncounterServiceOuterClass.GenerateEncounterRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dnd.generated.EncounterServiceOuterClass.EncounterListRpc.getDefaultInstance()))
              .setSchemaDescriptor(new EncounterServiceMethodDescriptorSupplier("generateEncounters"))
              .build();
        }
      }
    }
    return getGenerateEncountersMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EncounterServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EncounterServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EncounterServiceStub>() {
        @java.lang.Override
        public EncounterServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EncounterServiceStub(channel, callOptions);
        }
      };
    return EncounterServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EncounterServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EncounterServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EncounterServiceBlockingStub>() {
        @java.lang.Override
        public EncounterServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EncounterServiceBlockingStub(channel, callOptions);
        }
      };
    return EncounterServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static EncounterServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EncounterServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EncounterServiceFutureStub>() {
        @java.lang.Override
        public EncounterServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EncounterServiceFutureStub(channel, callOptions);
        }
      };
    return EncounterServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void generateEncounters(dnd.generated.EncounterServiceOuterClass.GenerateEncounterRequest request,
        io.grpc.stub.StreamObserver<dnd.generated.EncounterServiceOuterClass.EncounterListRpc> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGenerateEncountersMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service EncounterService.
   */
  public static abstract class EncounterServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return EncounterServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service EncounterService.
   */
  public static final class EncounterServiceStub
      extends io.grpc.stub.AbstractAsyncStub<EncounterServiceStub> {
    private EncounterServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EncounterServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EncounterServiceStub(channel, callOptions);
    }

    /**
     */
    public void generateEncounters(dnd.generated.EncounterServiceOuterClass.GenerateEncounterRequest request,
        io.grpc.stub.StreamObserver<dnd.generated.EncounterServiceOuterClass.EncounterListRpc> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGenerateEncountersMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service EncounterService.
   */
  public static final class EncounterServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<EncounterServiceBlockingStub> {
    private EncounterServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EncounterServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EncounterServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public dnd.generated.EncounterServiceOuterClass.EncounterListRpc generateEncounters(dnd.generated.EncounterServiceOuterClass.GenerateEncounterRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGenerateEncountersMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service EncounterService.
   */
  public static final class EncounterServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<EncounterServiceFutureStub> {
    private EncounterServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EncounterServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EncounterServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dnd.generated.EncounterServiceOuterClass.EncounterListRpc> generateEncounters(
        dnd.generated.EncounterServiceOuterClass.GenerateEncounterRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGenerateEncountersMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GENERATE_ENCOUNTERS = 0;

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
        case METHODID_GENERATE_ENCOUNTERS:
          serviceImpl.generateEncounters((dnd.generated.EncounterServiceOuterClass.GenerateEncounterRequest) request,
              (io.grpc.stub.StreamObserver<dnd.generated.EncounterServiceOuterClass.EncounterListRpc>) responseObserver);
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
          getGenerateEncountersMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              dnd.generated.EncounterServiceOuterClass.GenerateEncounterRequest,
              dnd.generated.EncounterServiceOuterClass.EncounterListRpc>(
                service, METHODID_GENERATE_ENCOUNTERS)))
        .build();
  }

  private static abstract class EncounterServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    EncounterServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return dnd.generated.EncounterServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("EncounterService");
    }
  }

  private static final class EncounterServiceFileDescriptorSupplier
      extends EncounterServiceBaseDescriptorSupplier {
    EncounterServiceFileDescriptorSupplier() {}
  }

  private static final class EncounterServiceMethodDescriptorSupplier
      extends EncounterServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    EncounterServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (EncounterServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new EncounterServiceFileDescriptorSupplier())
              .addMethod(getGenerateEncountersMethod())
              .build();
        }
      }
    }
    return result;
  }
}
