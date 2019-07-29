<%--
  Created by IntelliJ IDEA.
  User: Assinssa
  Date: 2019/7/14
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="static/css/mainChars.css" rel="stylesheet">
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/static/js/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/static/layuiadmin/style/admin.css" media="all">

</head>
<body scrolling="no">
<div class="layui-row layui-col-space24">
    <div class="layui-row layui-col-space24">

        <div class="layui-card-body layui-text" style="background-color: white"></div>

        <div style="background-color: white" data-v-af130026="" class="el-row" style="margin-top: 20px;">
            <div data-v-af130026="" class="welinfo"><span data-v-af130026="">
                    <img data-v-af130026=""
                         src="http://demo.cssmoban.com/cssthemes4/zwtp_2_gi/images/sun.png"
                         alt="天气"></span> <b data-v-af130026="">亲爱的<span style="color: red">${ sessionScope.loginuser.userName}</span>用户，欢迎使用源码档案管理平台</b>
            </div>
            <div data-v-af130026="" class="welinfo"><span data-v-af130026="">
                    <img data-v-af130026=""
                         src="http://demo.cssmoban.com/cssthemes4/zwtp_2_gi/images/time.png"
                         alt="时间"></span> <i
                    data-v-af130026="">您上次登录的时间：${ sessionScope.roottime}
                </i></div>
            <div data-v-af130026="" class="welinfo"><span data-v-af130026="">
                    <img data-v-af130026=""
                         src="http://demo.cssmoban.com/cssthemes4/zwtp_2_gi/images/time.png"
                         alt="时间"></span> <i
                    data-v-af130026="">当前时间：<span id=localtime></span>
            </i></div>
            <div data-v-af130026="" class="xline"></div>
        </div>
        <div class="layui-card">
            <div class="layui-card-header">待办事项</div>
            <div class="layui-card-body layui-text">
                <div class="layui-carousel layadmin-carousel layadmin-backlog">
                    <div carousel-item>
                        <ul class="layui-row layui-col-space10">
                            <li class="layui-col-xs6">
                                <a lay-href="app/content/comment.html" class="layadmin-backlog-body">
                                    <h3>档案总量</h3>
                                    <p><cite>66</cite></p>
                                </a>
                            </li>
                            <li class="layui-col-xs6">
                                <a lay-href="app/forum/list.html" class="layadmin-backlog-body">
                                    <h3>已借出</h3>
                                    <p><cite>12</cite></p>
                                </a>
                            </li>
                            <li class="layui-col-xs6">
                                <a lay-href="template/goodslist.html" class="layadmin-backlog-body">
                                    <h3>丢失损坏</h3>
                                    <p><cite>03</cite></p>
                                </a>
                            </li>
                            <li class="layui-col-xs6">
                                <a href="javascript:;" onclick="layer.tips('不跳转', this, {tips: 3});"
                                   class="layadmin-backlog-body">
                                    <h3>已找回</h3>
                                    <p><cite>12</cite></p>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="layui-card-header">版本信息</div>
            <div class="layui-card-body layui-text">
                <table class="layui-table">
                    <colgroup>
                        <col width="100">
                        <col>
                    </colgroup>
                    <tbody>
                    <tr>
                        <td>当前版本</td>
                        <td>
                            <script type="text/html" template>V1.002
                            <a href="http://www.baidu.com/" target="_blank" style="padding-left: 15px;">更新日志</a>
                            </script>
                        </td>
                    </tr>
                    <tr>
                        <td>基于框架</td>
                        <td>
                            <script type="text/html" template>
                                VUE+Element+LayUI
                            </script>
                        </td>
                    </tr>
                    <tr>
                        <td>主要特色</td>
                        <td>零门槛 / 响应式 / 清爽 / 极简</td>
                    </tr>
                    <tr>
                        <td>研发人员</td>
                        <td style="padding-bottom: 0;">
                            俊哥,州哥,鹏哥,曾哥,文哥,小曹
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>

        </div>


    </div>


</div>

<script src="/static/js/layui/layui.js"></script>
<script>
    layui.config({
        base: '/static/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'console']);
</script>
<%--时间--%>
<script type="text/javascript">
    function showLocale(objD){
        var str,colorhead,colorfoot;
        var yy = objD.getYear();
        if(yy<1900) yy = yy+1900;
        var MM = objD.getMonth()+1;
        if(MM<10) MM = '0' + MM;
        var dd = objD.getDate();
        if(dd<10) dd = '0' + dd;
        var hh = objD.getHours();
        if(hh<10) hh = '0' + hh;
        var mm = objD.getMinutes();
        if(mm<10) mm = '0' + mm;
        var ss = objD.getSeconds();
        if(ss<10) ss = '0' + ss;
        var ww = objD.getDay();
        if  ( ww==0 )  colorhead="<font color=\"#00bfff\">";
        if  ( ww > 0 && ww < 6 )  colorhead="<font color=\"#00bfff\">";
        if  ( ww==6 )  colorhead="<font color=\"#00bfff\">";
        if  (ww==0)  ww="星期日";
        if  (ww==1)  ww="星期一";
        if  (ww==2)  ww="星期二";
        if  (ww==3)  ww="星期三";
        if  (ww==4)  ww="星期四";
        if  (ww==5)  ww="星期五";
        if  (ww==6)  ww="星期六";
        colorfoot="</font>"
        str = colorhead + yy + "-" + MM + "-" + dd + " " + hh + ":" + mm + ":" + ss + "  " + ww + colorfoot;
        return(str);
    }

    function tick(){
        var today;
        today = new Date();
        document.getElementById("localtime").innerHTML = showLocale(today);
        window.setTimeout("tick()", 1000);
    }
    tick();
</script>

</body>
</html>
