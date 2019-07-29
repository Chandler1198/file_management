<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>丢失找回</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/static/js/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/static/layuiadmin/style/admin.css" media="all">

    <style>
        body {
            margin: 10px;
        }

        .demo-carousel {
            height: 200px;
            line-height: 200px;
            text-align: center;
        }
    </style>
</head>
<body>
<div class="layui-row layui-col-space15">
    <div class="layui-card-body">
        <table class="layui-hide" id="test-table-toolbar" lay-filter="test-table-toolbar"></table>
        <script type="text/html" id="test-table-toolbar-toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-sm layui-icon layui-icon-add-1" lay-event="add">添加</button>
                <button class="layui-btn layui-btn-sm layui-icon layui-icon-delete" lay-event="batchdel">删除</button>
                <button class="layui-btn layui-btn-sm layui-icon layui-icon-refresh" lay-event="reload">刷新</button>
                <button class="layui-btn layui-btn-sm " lay-event="getCheckData">当前选中行的数据</button>
                <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">选中数量</button>
                <button class="layui-btn layui-btn-sm" lay-event="isAll">是否全选</button>

                <form id="expandToolBar" style="display: inline;font-size: smaller" lay-filter="searchData">
                    <div class="layui-input-inline" style="width: 150px">
                        <input type="text" name="fromDate" id="fromDate"
                               placeholder="起始日期"  class="layui-input layui-icon-face-smile">
                    </div>
                </form>
                <div class="layui-btn-group" id="expandToolBarBtn" style="display: inline">
                    <button lay-event="search" class="layui-btn layui-btn-sm layui-btn-sm">
                        <i class="layui-icon">&#xe615;</i>查询
                    </button>
                </div>
            </div>
        </script>
        <script type="text/html" id="test-table-toolbar-barDemo">
            <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看详情</a>
            <%--<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>--%>
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除记录</a>
        </script>
        <div class="layui-row" id="popUpdateTest" style="display:none;">
            <form class="layui-form" action="">
                <div class="layui-form-item">
                    <label class="layui-form-label">操作用户</label>
                    <div class="layui-input-block">
                        <input type="text" name="title" required lay-verify="required" placeholder="当前用户"
                               autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">找回时间</label>
                    <div class="layui-input-block">
                        <input type="date" name="title" required lay-verify="required" placeholder="" autocomplete="off"
                               class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">档案编号</label>
                    <div class="layui-input-block">
                        <select name="city" lay-verify="required">
                            <option value=""></option>
                            <option value="0">3463156435</option>
                            <option value="1">4534534533</option>
                            <option value="2">7683453445</option>
                            <option value="3">4567834534</option>
                            <option value="4">4554368787</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">备注</label>
                    <div class="layui-input-block">
                        <textarea name="desc" placeholder="请输入内容" class="layui-textarea"></textarea>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    </div>
                </div>
            </form>

        </div>
    </div>
</div>
<script src="/static/js/layui/layui.js"></script>
<script>
    layui.config({
        base: '/static/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'table', 'form'], function () {
        var $ = layui.$
        var admin = layui.admin
            , layer = layui.layer
            , element = layui.element
            , table = layui.table
            , form = layui.form;

        //监听提交
        form.on('submit(formDemo)', function (data) {
            layer.msg(JSON.stringify(data.field));
            return false;
        });

        table.render({
            elem: '#test-table-toolbar'
            , url: '/lostquery/selectAllForList'
            // ,url: layui.setter.base + 'json/table/demo.js'
            , toolbar: '#test-table-toolbar-toolbarDemo'
            , title: '丢失查询表'
            , cols: [[
                {type: 'checkbox', fixed: 'left'}
                , {field: 'id', title: '找回编号', width: 120, fixed: 'left', unresize: true, sort: true}
                // ,{field:'sn', title:'找回编号', width:180, edit: 'text', templet: function(res){
                //         return '<em>'+ res.sn +'</em>'
                //     }}
                , {
                    field: 'arcId', title: '档案编号', width: 160, edit: 'text', templet: function (res) {
                        return '<em>' + res.archive.arcNum + '</em>'
                    }
                }
                , {
                    field: 'userId', title: '操作用户', width: 130, templet: function (res) {
                        return '<em>' + res.user.realName + '</em>'
                    }
                }
                , {
                    field: 'date', type: 'date', title: '找回时间', width: 180, templet: function (res) {
                        return '<em>' + res.date + '</em>'
                    }
                }
                , {field: 'remake', title: '备注', width: 250}
                , {fixed: 'right', title: '操作', toolbar: '#test-table-toolbar-barDemo', width: 350}
            ]]
            , page: true
            , id: 'tb'
            , parseData: function (res) {
                // console.debug(res.rows.length);
                // console.debug(res.rows);
                return {
                    "code": 0
                    , "msg": ""
                    , "count": res.total
                    , "data": res.rows
                }
            }
        });

        //头工具栏事件
        table.on('toolbar(test-table-toolbar)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);

            switch (obj.event) {
                case 'reload'://刷新
                    table.reload('tb', {page: {curr: this.curr}});
                    break;
                case 'add'://添加
                    layer.open({
                        type: 1
                        //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                        , title: '添加'
                        , content: $("#popUpdateTest").html()
                        , maxmin: true
                        , area: ['550px', '380px']
                        // , btn: ['确定', '取消']
                        , yes: function (index, layero) {
                            // 点击确认触发 iframe 内容中的按钮提交
                            var submit = layero.find('iframe').contents().find("#layuiadmin-app-form-submit");
                            submit.click();
                            setFormValue(data);//动态向表单赋值
                        }
                    });

                    // table.reload('tb', {page: {curr: this.curr}});
                    break;
                case 'getCheckData'://当前选择项的数据
                    var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                    break;

                case 'getCheckLength'://当前选中个数
                    var data = checkStatus.data;
                    layer.msg('选中了：' + data.length + ' 个');
                    break;

                case 'isAll':
                    layer.msg(checkStatus.isAll ? '全选' : '未全选');
                    break;

                case 'batchdel'://批量删除
                    var data = checkStatus.data; //得到选中的数据
                    //判断是否有选择的行,如果没有就执行提示
                    if (data.length == 0) {
                        //弹框提示
                        layer.msg('请先选择数据再执行删除!');
                        return;
                    } else {
                        //定义数组，用来存所有选择数据的id
                        var ids = [];
                        //循环遍历将选中行的id存入数组
                        $.each(data, function (i, o) {
                            //把id装进数组中
                            ids.push(o.id);
                        });
                        console.log(ids);
                        //弹框提示
                        layer.confirm('真的删除吗？', function () {
                            layer.msg('正在删除', {icon: 16}, function () {
                                //发送ajax请求到/employee/delete,传入ids数组toString
                                $.post("/lostquery/deleteList", {"ids": ids.toString()}, function (result) {
                                    //如果成功,重载页面
                                    if (result.success) {
                                        layer.msg('成功删除', {icon: 1})
                                        // obj.del();
                                        //刷新界面
                                        table.reload('tb', {page: {curr: this.curr}});
                                    } else {
                                        layer.msg("错误:" + result.msg);
                                        obj.del();
                                        return;
                                    }
                                }, "json");
                            });
                        });
                    }
                    break;
            }
            ;
        });

        //监听行工具事件
        table.on('tool(test-table-toolbar)', function (obj) {
            //获取选中行
            var data = obj.data;
            //获取当前行id
            var thisId = data.id;
            console.log(obj, thisId);
            if (obj.event === 'del') {//删除
                if (data.length == 0) {
                    //判断是否有选择的行,如果没有就执行提示
                    layer.msg('请先选择数据再执行删除!');
                    return;
                } else {
                    //弹框提示
                    layer.confirm('真的删除吗？', function () {
                        layer.msg('正在删除', {icon: 16}, function () {
                            //发送ajax请求到/employee/delete,传入ids数组toString
                            $.post("/lostquery/delete", {"id": thisId}, function (result) {
                                //如果成功,重载页面
                                if (result.success) {
                                    layer.msg('成功删除', {icon: 1})
                                    // obj.del();
                                    //刷新界面
                                    table.reload('tb', {page: {curr: this.curr}});
                                } else {
                                    layer.msg("错误:" + result.msg);
                                    return;
                                }
                            }, "json");
                        });
                    });
                }
            } else if (obj.event === 'edit') {//编辑
                layer.prompt({formType: 2, value: data},
                    function (value, index) {
                        obj.update({data: value});
                        layer.close(index);
                    });
            } else if (obj.event === 'detail') {//查看详情
                layer.msg('ID：' + data.id + ' 的查看操作');
            }
        });

    });
</script>
</body>
</html>
