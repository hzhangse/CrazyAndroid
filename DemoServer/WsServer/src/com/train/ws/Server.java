package com.train.ws;

import javax.xml.ws.Endpoint;
import org.apache.cxf.interceptor.*;
import org.apache.cxf.jaxws.EndpointImpl;

public class Server
{
	public static void main(String[] args) 
	{
		System.out.println("����������");
		//����Web Services�����ṩ���ʵ��
		FirstWsImpl implementor = new FirstWsImpl();
		String address = "http://192.168.1.101:9999/ws";
		//��ָ��Web Services�����ṩ��Ķ��󷢲���Web Services
		EndpointImpl ep = (EndpointImpl)Endpoint.publish(address , implementor);

		//�������CXF�Դ��������������ڸ���SOAP��Ϣ
		ep.getServer().getEndpoint()
			.getInInterceptors().add(new LoggingInInterceptor());
		ep.getServer().getEndpoint()
			.getOutInterceptors().add(new LoggingOutInterceptor());
	}
}
