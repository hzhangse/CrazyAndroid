package com.train.ws;

import java.util.*;

import javax.jws.WebService;

//����Web Services�ӿڵ�ʵ���࣬Web Services������
@WebService(endpointInterface = "com.train.ws.FirstWs",
            serviceName = "firstWs")
public class FirstWsImpl implements FirstWs 
{
	@Override
	public List<User> getUserList(String base) 
	{
		System.out.println("---����getUserList����---" + base);
		List<User> result = new ArrayList<User>();
		result.add(new User(1 , base + "crazyit" , "123" , 173));
		result.add(new User(2 , base + "leegang" , "456" , 178));
		return result;
	}
}