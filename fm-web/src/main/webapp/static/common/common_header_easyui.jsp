<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  request.getScheme() -> http
  request.getServerName() -> 服务名 ： localhost
  request.getServerPort() -> 端口
  request.getContextPath() -> 获取上下路径
--%>
<%
    //http://localhost:80/crm/
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>" >

<link rel="stylesheet" type="text/css" href="static/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="static/js/easyui/themes/icon.css">
<script type="text/javascript" src="static/js/jquery.min.js"></script>
<script type="text/javascript" src="static/js/easyui/jquery.easyui.min.js"></script>
