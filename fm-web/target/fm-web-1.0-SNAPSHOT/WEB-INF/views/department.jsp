<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理</title>
    <jsp:include page="/static/common/common_header.jsp"/>
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
        .el-dialog__body{
            padding: 30px 200px 30px 150px;
        }
        .el-form-item {
            margin-bottom: 0px;
            margin-top: 20px;
        }

        .submit{
            float: right;
        }

        .new {
            float: right;
            margin-right: 5%;
            margin-bottom: 0px;
            margin-top: 20px;
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

                <el-form-item label="关键字">
                    <el-input v-model="formInline.user" placeholder="关键字"></el-input>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary" @click="onSubmit"><i class="el-icon-search">&nbsp;&nbsp;</i>查询</el-button>
                </el-form-item>

                <%--<el-button type="primary" class="new"><i class="el-icon-plus">&nbsp;&nbsp;</i>添加部门</el-button>--%>
                <el-button type="primary" class="new" @click="winReload"><i class="el-icon-refresh">&nbsp;&nbsp;</i>刷新</el-button>
                <el-button type="primary" class="new" @click="newDepartment">
                    <i class="el-icon-plus">&nbsp;&nbsp;</i>
                    新建部门
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
        <el-table-column
                prop="departIden"
                label="部门标识"
                min-width="10%"
                align="center">
        </el-table-column>
        <el-table-column
                prop="departName"
                label="部门名称"
                min-width="10%"
                align="center">
        </el-table-column>
        <el-table-column
                prop="phone"
                label="电话"
                min-width="15%"
                align="center">
        </el-table-column>
        <el-table-column
                prop="fax"
                label="传真"
                min-width="10%"
                align="center">
        </el-table-column>
        <el-table-column
                prop="departSrc"
                label="部门路径"
                min-width="10%"
                align="center">
        </el-table-column>
        <el-table-column
                prop="superdepartId"
                label="上级部门编号"
                min-width="10%"
                align="center">
        </el-table-column>
        <el-table-column
                prop="departManager"
                label="上级主管"
                min-width="10%"
                align="center">
        </el-table-column>

        <el-table-column
                fixed="right"
                label="操作"
                min-width="20%"
                align="center">
            <template slot-scope="scope">
                <el-button
                        size="mini"
                        @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                <el-button
                        size="mini"
                        type="danger"
                        @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            </template>
        </el-table-column>
    </el-table>
    <div class="batchDelete">
        <el-button type="danger" @click="batchDelete()"><i class="el-icon-delete">&nbsp;&nbsp;</i>批量删除</el-button>
    </div>
    <div id="page" style="margin-top: 20px">
        <el-pagination class="page"
                       @size-change="handleSizeChange"
                       @current-change="handleCurrentChange"
                       :pager-count="5"
                       :current-page="currentPage"
                       :hide-on-single-page="true"
                       :page-sizes="[5,10,20,40]"
                       :page-size="pageSize"
                       layout="total, sizes, prev, pager, next, jumper"
                       :total="total">
        </el-pagination>
    </div>

    <el-dialog
            title="部门录入"
            :visible.sync="dialogFormVisible"
            :close-on-click-modal="false"
            :lock-scroll="true"
            top="20px"
    <%--:show-close="false"--%>
            :close-on-press-escape="true"
            :before-close="handleDialogClose">
        <el-form :model="ruleForm"  ref="ruleForm" label-width="250px" class="demo-ruleForm" label-position="left">
            <el-form-item label="部门标识" prop="departIden">
                <el-input v-model="ruleForm.departIden" placeholder="请输入用户名"></el-input>
            </el-form-item>
            <el-form-item label="部门名称" prop="departName">
                <el-input v-model="ruleForm.departName" placeholder="请输入您的真实姓名"></el-input>
            </el-form-item>
            <el-form-item label="传真" prop="fax">
                <el-input v-model="ruleForm.fax" placeholder="请选择您的地址"></el-input>
            </el-form-item>
            <el-form-item label="部门路径" prop="departSrc">
                <el-input v-model="ruleForm.departSrc" placeholder="性别"></el-input>
            </el-form-item>
            <el-form-item label="上级部门编号" prop="superdepartId">
                <el-input v-model="ruleForm.superdepartId" placeholder="备注"></el-input>
            </el-form-item>
            <el-form-item label="上级主管" prop="departManager">
                <el-input v-model="ruleForm.departManager" placeholder="备注"></el-input>
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
</div>
</body>
<script type="text/javascript" src="/static/js/model/department.js"></script>
</html>
