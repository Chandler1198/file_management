<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/static/common/common_header.jsp" />
    <title>Title</title>
    <%--<script type="text/javascript">--%>
        <%--$(function (){--%>
            <%--$(document.documentElement).on("keyup", function (e) {--%>
                <%--console.debug(e.keyCode);--%>
                <%--if (e.keyCode==13) {--%>
                    <%--submitForm('ruleForm')--%>
                <%--}--%>
            <%--});--%>
        <%--});--%>
        <%--if(window != top){--%>
            <%--top.location.href = window.location.href;--%>
        <%--};--%>
    <%--</script>--%>
    <script type="text/javascript">
        if(window != top){
        top.location.href = window.location.href
        };
    </script>
</head>
<body>
<div id="app">
    <el-col :span="24" style="height: 20%"></el-col>
    <el-col :span="4" offset="10" style="text-align:center;">
        <el-card shadow="always">
            <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" inline=true class="demo-ruleForm" @submit.native.prevent>
                <el-form-item prop="username">
                    <el-input placeholder="用户名" v-model="ruleForm.username" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input type="password" placeholder="密码" v-model="ruleForm.password" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" native-type="submit" @click="submitForm('ruleForm')">Login</el-button>
                    <el-button @click="resetForm('ruleForm')">Reset</el-button>
                </el-form-item>
            </el-form>
        </el-card>
    </el-col>
</div>
</body>
<script src="/static/js/model/login.js"></script>
</html>
