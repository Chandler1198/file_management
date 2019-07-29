<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/2
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
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

<script type="text/javascript" src="static/js/jquery.min.js"></script>

<%--引入公共的js--%>
<script type="text/javascript" src="static/common/common.js"></script>

<%--没网用这个--%>
<%--<link rel="stylesheet" href="static/js/element-ui/lib/theme-chalk/index.css">--%>
<%--<script type="text/javascript" src="static/js/vue.min.js"></script>--%>
<%--<script type="text/javascript" src="static/js/element-ui/lib/index.js"></script>--%>
<%--<script type="text/javascript" src="static/js/vue-router.js"></script>--%>

<%--有网用这个--%>
<link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
<script type="text/javascript" src="static/js/vue.min.js"></script>
<script type="text/javascript" src="https://unpkg.com/element-ui/lib/index.js"></script>
<script type="text/javascript" src="static/js/vue-router.js"></script>