<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>角色管理</title>
    <jsp:include page="/static/common/common_header.jsp"/>
    <%-- 引入easyui核心css--%>
    <link rel="stylesheet" href="/static/js/easyui/themes/default/easyui.css"/>
    <%--引入easyui图标css--%>
    <link rel="stylesheet" href="/static/js/easyui/themes/icon.css"/>
    <%--引入jquery核心插件--%>
    <script type="text/javascript" src="/static/js/easyui/jquery.min.js"></script>
    <style>
        #table {
            width: 100%;
        }

        .page {
            margin-right: 20px;
            float: right;
        }

        .top {
            clear: both;
            text-align: center
        }

        .searchFrom {
            margin: 0 auto;
            float: none;
        }

        .el-form-item {
            margin-bottom: 0px;
        }

        .new {
            float: right;
            margin-right: 5%;
        }

        .batchDelete {
            margin-left: 5%;
            float: left;
            margin-top: 20px
        }


    </style>

</head>
<body>
<div id="table">
    <div class="top">
        <div class="searchFrom">
            <el-form :inline="true" :model="formInline" class="demo-form-inline">
                <el-form-item label="角色">
                    <el-input v-model="formInline.user" placeholder="角色名"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="onSubmit"><i class="el-icon-search">&nbsp;&nbsp;</i>查询</el-button>
                </el-form-item>


                <el-button type="primary" class="new" @click="newRole">
                    <i class="el-icon-plus">&nbsp;&nbsp;</i>
                    新增角色
                </el-button>


            </el-form>
        </div>
    </div>

    <el-table
            :data="tableData"
            style="width: 100%"
            border="true"
            fit="true"
            highlight-current-row="true"
            stripe="true"
            :row-key="getRowKey"
            @selection-change="handleSelectionChange"
    >
        <el-table-column
                fixed
                prop="id"
                label="ID"
                align="center"
                type="selection"
                :reserve-selection="true"
                min-width="10%"
                align="center">
        </el-table-column>
        <el-table-column width="150px"
                prop="roleName"
                label="角色"
                min-width="10%"
                align="center">
        </el-table-column>
        <el-table-column
                prop="permissions"
                label="权限"
                :formatter="formpermissions"
                min-width="10%"
                align="center">
        </el-table-column>
        <el-table-column width="150px"

                prop="state"
                label="启用状态"
                :formatter="formstate"
                min-width="10%"
                align="center">
        </el-table-column>

        <el-table-column width="260px"
                label="操作"
                min-width="10%"
                align="center">
            <template slot-scope="scope">
                <div  v-if="scope.row.roleId!=1" >
                <el-tooltip class="item" effect="dark" content="点击可對资源进行修改" placement="left-start">
                    <el-button
                        size="mini"
                        @click="handleEdit(scope.$index, scope.row)">编辑
                    </el-button>
                </el-tooltip>
                <el-tooltip class="item" effect="dark" content="点击可一键删除或恢复资源" placement="left-start">
                    <el-button v-if="scope.row.state ==0"
                               size="mini"
                               type="danger"
                               @click="handleDelete(scope.$index, scope.row)">禁用
                    </el-button>
                    <el-button v-else
                               size="mini"
                               type="success"
                               @click="handleDelete(scope.$index, scope.row)">启用
                    </el-button>
                </el-tooltip>
                </div>
                <div v-else>

                    <el-button
                            :disabled=true
                            size="mini"
                            @click="handleEdit(scope.$index, scope.row)">编辑
                    </el-button>

                    <el-button
                            :disabled=true
                            size="mini"
                            type="warning"
                            @click="handleDelete(scope.$index, scope.row)">不可操作
                    </el-button>
                    </el-tooltip>
                </div>
            </template>
        </el-table-column>
    </el-table>
    <el-dialog
            title="角色编辑"
            :visible.sync="dialogFormVisible"
            :close-on-click-modal="false"
            :lock-scroll="true"
            top="20px"
            :close-on-press-escape="true"
            :before-close="handleDialogClose">
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="250px" class="demo-ruleForm" label-position="left">

            <el-form-item label="角色名称" prop="roleName">
                <el-input v-model="ruleForm.roleName" placeholder="请输入角色名称"></el-input>
            </el-form-item>
            <el-form-item label="角色标识" prop="roleId">
                <el-input v-model="ruleForm.roleId" placeholder="请输入角色标识"></el-input>
            </el-form-item>
            <el-form-item label="资源简介" prop="remarks">
                <el-input v-model="ruleForm.remarks" placeholder="角色简介"></el-input>
            </el-form-item>
            <el-form-item label="资源" prop="permissions">
                <el-input v-model="ruleForm.permissions" placeholder="资源"></el-input>
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
<script type="text/javascript" src="/static/js/model/role.js"></script>
</html>
