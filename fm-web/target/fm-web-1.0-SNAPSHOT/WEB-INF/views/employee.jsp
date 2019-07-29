<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/2
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>员工管理</title>
    <jsp:include page="/static/common/common_header.jsp" />
    <script src="static/js/model/employee.js"></script>

</head>
<body>

<%--工具条--%>
<div id="employeeDataGridToolbar">
    <a href="javascript:void(0)" data-method="add" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
    <a href="javascript:void(0)" data-method="edit"  class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
    <a href="javascript:void(0)" data-method="remove"  class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
    <a href="javascript:void(0)" data-method="reload"  class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true">刷新</a>

    &emsp;
    关键字：<input type="text" id="keyword">
    <a href="javascript:void(0)" class="easyui-linkbutton"  data-method="search"  data-options="iconCls:'icon-search',plain:true">查询</a>
</div>

<%--员工管理表格--%>
<table id="employeeDataGrid"></table>

<%--添加/编辑 的弹出窗口--%>
<div id="employeeAddDialog" class="easyui-dialog" title="添加/编辑"
     data-options="iconCls:'icon-save',resizable:true,modal:true">

    <form id="employeeAddDialogForm">
        <input type="hidden" name="id" />
        <table>
            <tr>
                <td>用户名</td>
                <td><input type="text" name="userName"/> </td>
            </tr>

            <tr>
                <td>姓&emsp;名</td>
                <td><input type="text" name="realName"/> </td>
            </tr>

            <tr>
                <td>年&emsp;龄</td>
                <td><input type="text" name="age"/> </td>
            </tr>
        </table>
    </form>

</div>
<%--添加/编辑 的弹出窗口的按钮组--%>
<div id="employeeAddDialogButtons">
    <a href="javascript:void(0)" class="easyui-linkbutton" data-method="save" data-options="iconCls:'icon-ok',plain:true">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-method="close" data-options="iconCls:'icon-cancel',plain:true">关闭</a>
</div>

</body>
</html>
