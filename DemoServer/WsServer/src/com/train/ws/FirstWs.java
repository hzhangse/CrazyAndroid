package com.train.ws;

import java.util.List;

import javax.jws.WebService;


//��@WebService Annotation��ע�������ýӿڽ���Ӧһ��Web Services
@WebService
public interface FirstWs 
{
	//����һ���������÷���������¶��һ��Web Services����
	List<User> getUserList(String base);
}