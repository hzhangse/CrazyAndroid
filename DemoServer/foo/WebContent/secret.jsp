
<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%
Object user = session.getAttribute("user");
if(user != null && user.toString().trim().equals("ryan"))
{
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title> 安全资源 </title>
	<meta name="website" content="http://www.163.com"/>
</head>
<body>
	安全资源，只有登录用户<br/>
	且用户名是ryan才可访问该资源
</body>
</html>
<%}
else
{
	out.println("您没有被授权访问该页面");	
}%>

