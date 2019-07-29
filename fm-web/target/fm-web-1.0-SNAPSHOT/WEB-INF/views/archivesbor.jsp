<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>档案借阅</title>
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
        .submit{
            float: right;
        }

    </style>

</head>
<body>
<div id="table">
    <div class="top">
        <div class="searchFrom">
            <el-form :inline="true" :model="formInline" class="demo-form-inline">
                <el-form-item label="请输入档案编号">
                    <el-input v-model="formInline.user" placeholder=""></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="onSubmit"><i class="el-icon-search">&nbsp;&nbsp;</i>查询</el-button>
                </el-form-item>
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
                prop="arcNum"
                label="档案编号"
                min-width="10%"
                align="center">
        </el-table-column>
        <el-table-column
                prop="arcTitle"
                label="档案名称"
                min-width="10%"
                align="center">
        </el-table-column>

        <el-table-column
                prop="arcType"
                label="档案分类"
                min-width="10%"
                align="center">
        </el-table-column>
        <el-table-column
                prop="createdDate"
                label="档案日期"
                value-format="yyyy-MM-dd"
                min-width="10%"
                align="center">
        </el-table-column>
        <el-table-column
                prop="borrowStatu"
                label="状态"
                min-width="10%"
                :formatter="formperstate"
                align="center">
        </el-table-column>
        <el-table-column
                label="操作"
                min-width="10%"
                align="center">
            <template slot-scope="scope">
                <el-button
                        size="mini"
                        @click="handleEdit(scope.$index, scope.row)">借阅
                    <%--handleDelete(scope.$index, scope.row),--%>
                </el-button>

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
            title="借阅录入"
            :visible.sync="dialogFormVisible"
            :close-on-click-modal="false"
            :lock-scroll="true"
            top="20px"
    <%--:show-close="false"--%>
            :close-on-press-escape="true"
            :before-close="handleDialogClose">
        <el-form :model="form"  ref="form" label-width="250px" class="demo-form" label-position="left">

            <el-form-item label="借阅人" prop="borrowName">
                <el-input type="text" v-model="form.borrowName" placeholder="借阅人" :disabled="false"></el-input>
            </el-form-item>

            <el-form-item label="借阅人证件类型" prop="systemdictionary">
                <el-input v-model="form.systemdictionary" placeholder="借阅人证件类型">
                </el-input>
            </el-form-item>
            <el-form-item label="借阅人证件号码" prop="borrowNumber">
                <el-input type="text" v-model="form.borrowNumber" placeholder="借阅人证件号码" :disabled="false"></el-input>
            </el-form-item>
            <el-form-item label="借阅人联系方式" prop="borrowPhone">
                <el-input type="text" v-model="form.borrowPhone" placeholder="借阅人联系方式" :disabled="false"></el-input>
            </el-form-item>
            <el-form-item label="操作用户" prop="arcUser.realName">
                <el-input type="text" v-model="arcUser.realName" placeholder="操作用户" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="借阅日期" prop="borrowDate">
                <el-date-picker
                        v-model="form.borrowDate"
                        type="date"
                        placeholder="选择日期"
                        value-format="yyyy-MM-dd">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="要求归还日期" prop="returnDate">
                <el-date-picker
                        v-model="form.returnDate"
                        type="date"
                        placeholder="选择日期"
                        value-format="yyyy-MM-dd">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="借阅备注" prop="borrowRemarks">
                <el-input type="textarea" :rows="1" placeholder="请输入内容" v-model="form.borrowRemarks" maxlength="10" show-word-limit>
                </el-input>
            </el-form-item>
            <div class="submit">
                <el-form-item>
                    <el-button type="primary" @click="submitForm('form')">保存</el-button>
                    <el-button @click="closeDialog('form')">取消</el-button>
                </el-form-item>
            </div>
            <br style="clear: both">
        </el-form>
    </el-dialog>



</div>
</body>
<script type="text/javascript" src="/static/js/model/archivesbor.js"></script>
</html>
