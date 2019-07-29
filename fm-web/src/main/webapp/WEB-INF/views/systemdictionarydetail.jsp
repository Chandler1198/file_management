<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统管理</title>
    <jsp:include page="/static/common/common_header.jsp"/>
    <style>
        #table{
            width: 100%;
        }
        .page,.page2{
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
    <el-row class="tac">
        <el-col :span="11" offset="0">
            <el-card class="box-card">
            <div class="top">
                <div class="searchFrom2">
                    <el-form :inline="true" :model="formInline2" class="demo-form-inline">

                        <el-form-item>
                            <el-input v-model="formInline2.user" placeholder="关键字"></el-input>
                        </el-form-item>

                        <el-form-item>
                            <el-button type="primary" @click="onSubmit2"><i class="el-icon-search">&nbsp;&nbsp;</i>查询</el-button>
                        </el-form-item>

                        <el-button type="primary" class="new"><i class="el-icon-plus">&nbsp;&nbsp;</i>添加</el-button>
                        <el-button type="primary" class="new"><i class="el-icon-plus">&nbsp;&nbsp;</i>刷新</el-button>
                    </el-form>
                </div>
            </div>
            <el-table
                    :data="tableData2"
                    style="width: 100%"
                    border="true"
                    fit="true"
                    highlight-current-row="true"
                    stripe="true"
                    :row-key="getRowKey2"
                    @selection-change="handleSelectionChange"
            >
                <el-table-column
                        fixed
                        prop="id"
                        label="ID"
                        align="center"
                        type="selection"
                        :reserve-selection="true"
                        :selectable="checkSelectable"
                        min-width="10%"
                        align="center">
                </el-table-column>
                <el-table-column
                        prop="type"
                        label="一级名称"
                        min-width="10%"
                        align="center">
                </el-table-column>
                <el-table-column
                        prop="ident"
                        label="编号"
                        min-width="10%"
                        align="center">
                </el-table-column>

                <el-table-column
                        prop="state"
                        fixed="right"
                        label="操作"
                        min-width="20%"
                        align="center">
                    <template slot-scope="scope">
                        <div v-if="scope.row.state == 1">
                            <el-button size="mini" @click="handleEdit2(scope.$index, scope.row)" :disabled=true>编辑</el-button>
                            <el-button size="mini" type="danger" @click="handleDelete2(scope.$index, scope.row)" :disabled="true">删除</el-button>
                        </div>
                        <div v-else>
                            <el-button size="mini" @click="handleEdit2(scope.$index, scope.row)">编辑</el-button>
                            <el-button size="mini" type="danger" @click="handleDelete2(scope.$index, scope.row)">删除</el-button>
                        </div>

                    </template>
                </el-table-column>
            </el-table>
            <div id="page2" style="margin-top: 20px">
                <el-pagination class="page"
                               @size-change="handleSizeChange2"
                               @current-change="handleCurrentChange2"
                               :pager-count="5"
                               :current-page="currentPage2"
                               :hide-on-single-page="true"
                               :page-sizes="[5,10,20,40]"
                               :page-size="pageSize2"
                               layout="total, sizes, prev, pager, next, jumper"
                               :total="total2">
                </el-pagination>
            </div>
        </el-card>
        </el-col>

        <el-col :span="11" offset="2">
            <el-card class="box-card">
                    <div class="top">
                        <div class="searchFrom">
                            <el-form :inline="true" :model="formInline" class="demo-form-inline">

                                <el-form-item>
                                    <el-input v-model="formInline.user" placeholder="关键字"></el-input>
                                </el-form-item>

                                <el-form-item>
                                    <el-button type="primary" @click="onSubmit"><i class="el-icon-search">&nbsp;&nbsp;</i>查询</el-button>
                                </el-form-item>

                                <el-button type="primary" class="new"><i class="el-icon-plus">&nbsp;&nbsp;</i>刷新</el-button>
                                <el-button type="primary" class="new" @click="newUser">
                                    <i class="el-icon-plus">&nbsp;&nbsp;</i>
                                    新建菜单
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
                                :selectable="checkSelectable"
                                min-width="10%"
                                align="center">
                        </el-table-column>
                        <el-table-column
                                prop="detailName"
                                label="名称"
                                min-width="10%"
                                align="center">
                        </el-table-column>
                        <el-table-column
                                prop="catalogNum"
                                label="排序名称"
                                min-width="10%"
                                align="center">
                        </el-table-column>
                        <el-table-column
                                prop="systemdictionarytype.type"
                                label="上级字典"
                                min-width="15%"
                                align="center">
                        </el-table-column>

                        <el-table-column
                                prop="state"
                                fixed="right"
                                label="操作"
                                min-width="20%"
                                align="center">
                            <template slot-scope="scope">
                                <div v-if="scope.row.state == 1">
                                    <el-button size="mini" @click="handleEdit(scope.$index, scope.row)" :disabled=true>编辑</el-button>
                                    <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)" :disabled="true">删除</el-button>
                                </div>
                                <div v-else>
                                    <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                                    <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                                </div>

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
                        title="字典添加"
                        :visible.sync="dialogFormVisible"
                        :close-on-click-modal="false"
                        :lock-scroll="true"
                        top="20px"
                <%--:show-close="false"--%>
                        :close-on-press-escape="true"
                        :before-close="handleDialogClose">
                    <el-form :model="ruleForm"  ref="ruleForm" label-width="250px" class="demo-ruleForm" label-position="left">
                        <el-form-item label="名称" prop="detailName">
                            <el-input v-model="ruleForm.detailName" placeholder="请输入用户名"></el-input>
                        </el-form-item>
                        <el-form-item label="排序名称" prop="catalogNum">
                            <el-input v-model="ruleForm.catalogNum" placeholder="请输入您的真实姓名"></el-input>
                        </el-form-item>
                        <el-form-item label="上级字典" prop="typeId">
                            <el-input v-model="ruleForm.typeId" placeholder="字典"></el-input>
                        </el-form-item>
                        <el-form-item label="状态" prop="state">
                            <el-input v-model="ruleForm.state" placeholder="状态"></el-input>
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
                </el-card>
        </el-col>
    </el-row>
</div>

</body>
<script type="text/javascript" src="/static/js/model/systemdictionarydetail.js"></script>
</html>
