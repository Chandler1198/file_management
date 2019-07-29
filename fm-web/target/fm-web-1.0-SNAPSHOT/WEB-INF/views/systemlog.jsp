<%--
  Created by IntelliJ IDEA.
  User: WeigJ
  Date: 2019/7/14
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/static/common/common_header.jsp"/>
    <title>备份与恢复</title>
    <style>

        .el-dialog{
            width: 30%;
        }
    </style>
</head>
<body>
<div id="app">
    <el-col :span="24" style="height: 20%"></el-col>
    <el-col :span="6" offset="8" style="text-align:center;">
        <el-card shadow="always" style="height: 40%">
            <el-col :span="24" style="margin-top: 10%">
                <el-tag type="success">每天3点会自动备份，当然也可以选择马上备份</el-tag>
            </el-col>
            <el-col :span="24" style="height: 40%"></el-col>
            <el-button type="primary" plain @click="systemlogback">现在备份</el-button>
            <el-button type="primary" plain @click="newUser">数据恢复</el-button>
        </el-card>
    </el-col>
    <el-dialog
            title="数据恢复"
            :visible.sync="dialogFormVisible"
            :close-on-click-modal="false"
            :lock-scroll="true"
            top="20px"
    <%--:show-close="false"--%>
            :close-on-press-escape="true"
            :before-close="handleDialogClose">
        <el-form :model="ruleForm"  ref="ruleForm" label-width="250px" class="demo-ruleForm" label-position="left">
            <el-select v-model="value" placeholder="请选择用于恢复的数据">
                <el-option v-for="item in options"
                            <%--:key="item.flieName"--%>
                            :value="item.flieName">
                </el-option>
            </el-select>
            </el-form-item>
            <div class="submit">
                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">保存</el-button>
                    <el-button @click="closeDialog('ruleForm')">取消</el-button>
                </el-form-item>
            </div>
            <br style="clear: both">
        </el-form>
    </el-dialog>
</div>
</body>
<script>
    var table = new Vue({
        el:"#app",
        data() {
            return {
                options: [],
                value: '',
                dialogFormVisible: false,
                isEdit: true,
                ruleForm:{
                    fileName:''
                },
            };
        },
        mounted (){
            this.getfile()
        },
        methods: {
            systemlogback(){
                this.$confirm('保存过程请勿刷新界面', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    $.ajax({
                        url: "/datarecovery/back",
                        dataType: "json",
                        success: function (result) {
                            if (result.success) {
                                result.msg = "保存成功！！"
                                result.success = "success"
                                table.open(result)
                            } else {
                                result.msg = "网络繁忙！！请稍后再试！！"
                                result.success = "error"
                                table.open(result)
                            }
                        }
                    })
                }).catch(() => {
                    var result = {msg:"",success:""}
                    result.msg = "已取消保存"
                    result.success = "info"
                    table.open(result)
                });
            },
            //消息弹出框
            open(result) {
                this.$message({
                    showClose: true,
                    message: result.msg,
                    type: result.success,
                    // center: true
                });
            },
            //新建
            newUser() {
                table.isEdit = false;
                table.dialogFormVisible = true;
                // table.options = [{
                //     fileName: '选项1',
                // }, {
                //     fileName: '选项2',
                // }, {
                //     fileName: '选项3',
                // }, {
                //     fileName: '选项4',
                // }, {
                //     fileName: '选项5',
                // }]


            },
            getfile(){
                $.ajax({
                    url:"/datarecovery/getfile",
                    type:"post",
                    success:function (result) {
                        table.options = result;
                        console.log(table.options);
                    }
                });
            },
            submitForm(formName) {
                $.post("/datarecovery/restore", {fileName:table.value}, result => {
                    if (result.success) {
                        result.msg = "恢复成功！！"
                        result.success = "success"
                        table.open(result)
                    } else {
                        result.msg = "恢复失败"
                        result.success = "error"
                        table.open(result)
                    }
                });

            },
            //关闭弹出框
            closeDialog(formName) {
                this.$refs[formName].resetFields();
                table.dialogFormVisible = false;
            },
            handleDialogClose() {
                table.dialogFormVisible = false;
            },
            systemlogrestore(){
                this.$confirm('请选择恢复到哪个版本', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    $.ajax({
                        url: "/datarecovery/back",
                        dataType: "json",
                        success: function (result) {
                            if (result.success) {
                                result.msg = "恢复成功！！"
                                result.success = "success"
                                table.open(result)
                            } else {
                                result.msg = "网络繁忙！！请稍后再试！！"
                                result.success = "error"
                                table.open(result)
                            }
                        }
                    })
                }).catch(() => {
                    var result = {msg:"",success:""}
                    result.msg = "已取消保存"
                    result.success = "info"
                    table.open(result)
                });
            }
        }
    });
</script>
</html>
