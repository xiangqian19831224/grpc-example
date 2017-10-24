package com.rpc.mail;

import io.grpc.Server;
import io.grpc.netty.NettyServerBuilder;

public class GrpcServer {
	public static void main(String[] args) throws Exception {
		Server server = NettyServerBuilder.forPort(8080).addService(SendMailServiceGrpc.bindService(new SendMailServiceImpl())).build();
		server.start();
		System.out.println("server startup at 8080");
		server.awaitTermination();
	}
}