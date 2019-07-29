<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
欢迎<span style="color: red"><shiro:principal property="userName"/></span>登录系统&emsp;
<a href="/cookieclear">注销</a><br/>
    成功界面
</body>
</html>
