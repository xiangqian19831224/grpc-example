package com.rpc.mail;

import java.util.concurrent.TimeUnit;
import com.rpc.mail.SendMailServiceGrpc.SendMailServiceBlockingStub;
import io.grpc.ManagedChannel;
import io.grpc.netty.NettyChannelBuilder;

public class GrpcClient {
	public static void main(String[] args) throws Exception {
		ManagedChannel channel = NettyChannelBuilder.forAddress("127.0.0.1", 8080).usePlaintext(true).build();
		//同步调用(异步调用的话，就是：SendMailServiceGrpc.newFutureStub(channel))
		SendMailServiceBlockingStub stub = SendMailServiceGrpc.newBlockingStub(channel);

		//设置请求参数
		SendMailRequest param = SendMailRequest.newBuilder().setRecipient("admin@google.com").setTitle("运维邮件").setContent("SOA服务挂了").build();
		SendMailResponse resp = stub.sendMail(param);
		System.out.println(resp.getMsg() + "\t" + resp.getCode());

		//close
		channel.shutdown().awaitTermination(1, TimeUnit.SECONDS);
	}
}