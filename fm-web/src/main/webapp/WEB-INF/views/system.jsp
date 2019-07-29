<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统管理</title>
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
        .el-form-item{
            margin-bottom: 0px;
        }
        .new{
            float: right;
            margin-right: 5%;
        }
        .batchDelete{
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

                <%--<el-button type="primary" class="new"><i class="el-icon-plus">&nbsp;&nbsp;</i>添加系统</el-button>--%>
                <el-button type="primary" class="new"><i class="el-icon-refresh">&nbsp;&nbsp;</i>刷新</el-button>
                <el-button type="primary" class="new" @click="newSystem">
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
                prop="systemName"
                label="系统名称"
                min-width="10%"
                align="center">
        </el-table-column>
        <el-table-column
                prop="companyName"
                label="公司名称"
                min-width="10%"
                align="center">
        </el-table-column>
        <el-table-column
                prop="companyPhone"
                label="公司电话"
                min-width="15%"
                align="center">
        </el-table-column>
        <el-table-column
                prop="companyFax"
                label="公司传真"
                min-width="10%"
                align="center">
        </el-table-column>
        <el-table-column
                prop="companyAddress"
                label="公司地址"
                min-width="10%"
                align="center">
        </el-table-column>
        <el-table-column
                prop="companyWebsite"
                label="公司网址"
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
            title="系统设置"
            :visible.sync="dialogFormVisible"
            :close-on-click-modal="false"
            :lock-scroll="true"
            top="20px"
            :close-on-press-escape="true"
            :before-close="handleDialogClose">
        <el-form :model="ruleForm"  ref="ruleForm" label-width="250px" class="demo-ruleForm" label-position="left">
            <el-form-item label="系统名称" prop="systemName">
                <el-input v-model="ruleForm.systemName" placeholder="请输入用户名"></el-input>
            </el-form-item>
            <el-form-item label="公司名称" prop="companyName">
                <el-input v-model="ruleForm.companyName" placeholder="请输入您的真实姓名"></el-input>
            </el-form-item>
            <el-form-item label="公司电话" prop="companyPhone">
                <el-input v-model="ruleForm.companyPhone" placeholder="请选择您的地址"></el-input>
            </el-form-item>
            <el-form-item label="公司传真" prop="companyFax">
                <el-input v-model="ruleForm.companyFax" placeholder="性别"></el-input>
            </el-form-item>
            <el-form-item label="公司地址" prop="companyAddress">
                <el-input v-model="ruleForm.companyAddress" placeholder="备注"></el-input>
            </el-form-item>
            <el-form-item label="公司网址" prop="companyWebsite">
                <el-input v-model="ruleForm.companyWebsite" placeholder="备注"></el-input>
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
<script type="text/javascript" src="/static/js/model/system.js"></script>
</html>
