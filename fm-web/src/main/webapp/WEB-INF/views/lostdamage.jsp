<%--
  Created by IntelliJ IDEA.
  User: Assinssa
  Date: 2019/7/12
  Time: 18:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>事故查询</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/static/js/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/static/layuiadmin/style/admin.css" media="all">

    <%--<style>--%>
    <%--body{margin: 10px;}--%>
    <%--.demo-carousel{height: 200px; line-height: 200px; text-align: center;}--%>
    <%--</style>--%>
</head>
<body>
<!--数据展示表格-->
<table class="layui-hide" id="test" lay-filter="toolbtn"></table>
<script type="text/html" id="test-table-toolbar-barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看详情</a>
    <%--<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>--%>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除记录</a>
</script>
<!--顶部工具栏-->
<script type="text/html" id="crudBtn">
    <div class="layui-btn-group">
        <button lay-event="reload" class="layui-btn layui-btn-primary layui-btn-sm">
            <i class="layui-icon">&#xe669;</i>刷新
        </button>
    </div>
    <form id="expandToolBar" style="display: inline;font-size: smaller" lay-filter="searchData">
        <div class="layui-input-inline" style="width: 150px">
            <input type="text" name="fromDate" id="fromDate"
                   placeholder="起始日期" class="layui-input layui-icon-face-smile">
        </div>
        <div class="layui-input-inline" style="width: 150px">
            <input type="text" name="toDate" id="toDate"
                   placeholder="截止日期" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-input-inline" style="width: 200px">
            <input type="text" name="keyword" placeholder="请输入档案名/编号/关键字信息" id="keyword"
                   autocomplete="off" class="layui-input">
        </div>
    </form>
    <div class="layui-btn-group" id="expandToolBarBtn" style="display: inline">
        <button lay-event="search" class="layui-btn layui-btn-primary layui-btn-sm">
            <i class="layui-icon">&#xe615;</i>查询
        </button>
    </div>
</script>
<script type="text/html" id="addDialog">
    <%--onsubmit="return notSubmit();"阻止默认提交，使用自己定义的ajax提交--%>
    <form align="center" id="addForm" class="layui-form" lay-filter="backData" onsubmit="return notSubmit();"
          style="margin-top: 20px;align:center;width: 300px">
        <%--隐藏字段id,区分添加和修改--%>
        <input type="hidden" name="id"/>
        <div class="layui-form-item">
            <label class="layui-form-label">档案编号</label>
            <div class="layui-input-block">
                <input type="text" name="archive" id="archive" lay-verify="required" placeholder="请输入档案编号"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">档案类型</label>
            <div class="layui-input-block">
                <input type="text" name="archiveClassify" id="archiveClassify" lay-verify="required" placeholder="备注信息"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">销毁原因</label>
            <div class="layui-input-block">
                <input type="text" name="dictionaryDetail" id="dictionaryDetail" lay-verify="required" placeholder="必填"
                       autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item" lay-verify="required">
            <label class="layui-form-label">销毁原因</label>
            <div class="layui-input-block">
                <select name="dictionaryDetail" class="layui-select">
                    <option value="">必选</option>
                    <option value="0">档案错误</option>
                    <option value="1">档案过期</option>
                    <option value="2">档案失效</option>
                </select>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">销毁备注</label>
            <div class="layui-input-block">
                <input type="text" name="remark" id="remark" lay-verify="remark" placeholder="备注信息" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button id="subBtn" class="layui-btn layui-btn-radius layui-btn-normal">确认</button>
                <button type="reset" class="layui-btn layui-btn-radius layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</script>

<script src="/static/js/layui/layui.js"></script>
<script>
    layui.config({
        base: '/static/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['table', 'form', 'layer', 'laydate'], function () {
        var table = layui.table;
        var form = layui.form;
        var layer = layui.layer;
        var laydate = layui.laydate;
        var nowTime = new Date().valueOf();
        table.render({
            elem: '#test'
            , url: '/lostdamage/selectAllForList'
            // ,url: layui.setter.base + 'json/table/demo.js'
            , toolbar: '#crudBtn'
            , title: '事故查询表'
            , cols: [[
                {type: 'checkbox', fixed: 'left'}
                , {field: 'id', title: '找回编号', width: 80, fixed: 'left', unresize: true, sort: true}
                // ,{field:'sn', title:'找回编号', width:180, edit: 'text', templet: function(res){
                //         return '<em>'+ res.sn +'</em>'
                //     }}
                , {
                    field: 'arcId', title: '档案编号', width: 120, edit: 'text', templet: function (res) {
                        return '<em>' + res.archive.arcNum + '</em>'
                    }
                }
                , {
                    field: 'userId', title: '操作用户', width: 120, templet: function (res) {
                        return '<em>' + res.user.realName + '</em>'
                    }
                }
                , {
                    field: 'date', type: 'date', title: '事故时间', width: 160, templet: function (res) {
                        return '<em>' + res.date + '</em>'
                    }
                }
                , {
                    field: 'type', type: '', title: '事故类型', width: 100, templet: function (res) {
                        switch (res.type.detailName) {
                            case
                            '22'
                            :
                                return '<em>' + "丢失" + '</em>';
                            case
                            '24'
                            :
                                return '<em>' + "找回" + '</em>';
                            case
                            '25'
                            :
                                return '<em>' + "销毁" + '</em>';
                        }

                    }
                }
                , {field: 'remake', title: '备注', width: 250}
                , {fixed: 'right', title: '操作', toolbar: '#test-table-toolbar-barDemo', width: 350}
            ]]
            , page: true
            , id: 'tb'
            , parseData: function (res) {
                return {
                    "code": 0
                    , "msg": ""
                    , "count": res.total
                    , "data": res.rows
                }
            }
            , done: function () {//数据渲染完的回调
                /*初始化高级查询日期框渲染*/
                laydate.render({//起始时间
                    elem: '#fromDate', //指定元素
                    theme: 'molv',
                    max: 0
                });
                laydate.render({//截止时间
                    elem: '#toDate', //指定元素
                    theme: 'molv',
                    max: 0
                });
            }
        });
        /*监听顶部工具栏，执行不同的事件*/
        table.on('toolbar(toolbtn)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id)
                , data = checkStatus.data; //获取选中的数据
            var index;
            switch (obj.event) {
                case 'reload':
                    // 刷新页面数据
                    methodsObj.reload();
                    break;
                case 'search':
                    // 关键字查询  获取关键字
                    var keyword = $("#keyword").val();
                    var fromDate = $("#fromDate").val();
                    var toDate = $("#toDate").val();
                    methodsObj.search(keyword, fromDate, toDate, form);

            }
        });
    });
    /*具体事件函数*/
    var methodsObj = {
        reload: function () {
            $(".layui-laypage-btn")[0].click();
        },

        search: function (keyword, fromDate, toDate, form) {
            var table = layui.table;
            table.reload('test', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {//这里传参  向后台
                    keyword: keyword,
                    fromDate: fromDate,
                    toDate: toDate
                }
                , url: 'destroy/page'//后台做模糊搜索接口路径
                , method: 'post'
                , done: function () {
                    /*初始化高级查询日期框渲染*/
                    laydate.render({//起始时间
                        elem: '#fromDate', //指定元素
                        theme: 'molv',
                        max: 0
                    });
                    laydate.render({//截止时间
                        elem: '#toDate', //指定元素
                        theme: 'molv',
                        max: 0
                    });
                }
            }, 'data');
        },
    }

    function notSubmit() {
        return false;
    }
</script>
</body>
</html>

