<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>资源管理</title>
    <jsp:include page="/static/common/common_header.jsp"/>
    <style>
        #table{
            width: 100%;
        }
        .page{
            margin-right: 20px;
            float: right;
        }
        .top{
            clear:both;
            text-align: center
        }
        .searchFrom{
            margin: 0 auto;
            float: none;
        }
        .new{
            float: right;
            margin-right: 5%;
        }
        .batchDelete{
            margin-left: 1%;
            float: right;
            margin-top: 20px
        }

    </style>

</head>
<body>
<div id="table">
    <div class="top">
        <form action="/permission/list" method="post">
        <div class="searchFrom">
            <el-form :inline="true" :model="formInline" class="demo-form-inline">
                <el-form-item label="资源名称">
                    <el-input v-model="formInline.user" placeholder="资源名称"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-tooltip class="item" effect="dark" content="根据资源名称进行查询" placement="right-start">
                    <el-button type="primary" @click="onSubmit"><i class="el-icon-search">&nbsp;&nbsp;</i>查询</el-button>
                    </el-tooltip>
                </el-form-item>

                <el-button type="primary" class="new" @click="newPermission">
                    <i class="el-icon-plus">&nbsp;&nbsp;</i>
                    新增资源
                </el-button>
            </el-form>
        </div>
        </form>
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
        <el-table-column width="200px"
                prop="name"
                label="资源名称"
                min-width="10%"
                align="center">
        </el-table-column>
        <el-table-column
                prop="url"
                label="资源路径"
                min-width="10%"
                align="center">
        </el-table-column>
        <el-table-column
                prop="sn"
                label="资源编号"
                min-width="10%"
                align="center">
        </el-table-column>
        <el-table-column
                prop="remarks"
                label="资源简介"
                min-width="10%"
                align="center">
        </el-table-column>
       <el-table-column width="150px"
                prop="state"
                label="状态"
                :formatter="formstate"
                min-width="10%"
                align="center">
        </el-table-column>
        <el-table-column
                label="操作"
                min-width="10%"
                align="center">
            <template slot-scope="scope" >
                <div v-if="scope.row.id ==15">
                <el-button
                        :disabled=true
                        size="mini"
                        type="warning"
                        @click="handleDelete(scope.$index, scope.row)">不可修改
                </el-button>
                </div>
                <div v-else>
                    <el-tooltip class="item" effect="dark" content="点击可對资源进行修改" placement="left-start">
                        <el-button
                                size="mini"
                                @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
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
            </template>
        </el-table-column>
    </el-table>
    <div class="batchDelete">
        <el-tooltip class="item" effect="dark" content="点此可选择批量删除或者恢复" placement="bottom">
        <el-button type="danger" @click="batchDelete()"><i class="el-icon-delete">&nbsp;&nbsp;</i>批量禁用/启用</el-button>
        </el-tooltip>
    </div>
    <div id="page" style="margin-top: 20px">
        <el-pagination class="page"
                       @size-change="handleSizeChange"
                       @current-change="handleCurrentChange"
                       :pager-count="5"
                       :current-page="currentPage"
                       :hide-on-single-page="true"
                       :page-sizes="[5,10,15,20]"
                       :page-size="pageSize"
                       layout="total, sizes, prev, pager, next, jumper"
                       :total="total">
        </el-pagination>
    </div>
    <el-dialog
            title="资源新增"
            :visible.sync="dialogFormVisible"
            :close-on-click-modal="false"
            :lock-scroll="true"
            top="20px"
            :close-on-press-escape="true"
            :before-close="handleDialogClose">
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="250px" class="demo-ruleForm" label-position="left">

            <el-form-item label="资源名称" prop="name">
                <el-input v-model="ruleForm.name" placeholder="请输入资源名称"></el-input>
            </el-form-item>
            <el-form-item label="资源路径" prop="url">
                <el-input v-model="ruleForm.url" placeholder="请输入资源路径"></el-input>
            </el-form-item>
            <el-form-item label="资源编号" prop="sn">
                <el-input v-model="ruleForm.sn" placeholder="资源编号"></el-input>
            </el-form-item>
            <el-form-item label="资源简介" prop="remarks">
                <el-input v-model="ruleForm.remarks" placeholder="资源简介"></el-input>
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
<script type="text/javascript" src="/static/js/model/permission.js"></script>
</html>
