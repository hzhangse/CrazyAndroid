package com.train.ws;

import java.util.List;

import javax.jws.WebService;


//以@WebService Annotation标注，表明该接口将对应一个Web Services
@WebService
public interface FirstWs 
{
	//定义一个方法，该方法将被暴露成一个Web Services操作
	List<User> getUserList(String base);
}