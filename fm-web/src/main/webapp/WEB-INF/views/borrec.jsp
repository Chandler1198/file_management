<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>借阅记录</title>
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
                prop="borrowName"
                label="借阅人"
                min-width="10%"
                align="center">
        </el-table-column>
        <el-table-column
                prop="systemdictionary"
                label="借阅人证件类型"
                min-width="10%"
                align="center">
        </el-table-column>
        <el-table-column
                prop="borrowNumber"
                label="借阅人证件号码"
                min-width="10%"
                align="center">
        </el-table-column>

        <el-table-column
                prop="borrowPhone"
                label="借阅人联系方式"
                min-width="10%"
                align="center">
        </el-table-column>
        <el-table-column
                prop="borrowDate"
                label="借阅日期"
                value-format="yyyy-MM-dd HH:mm:ss"
                min-width="10%"
                align="center">
        </el-table-column>
        <el-table-column
                prop="returnDate"
                label="归还日期"
                value-format="yyyy-MM-dd HH:mm:ss"
                min-width="10%"
                align="center">
        </el-table-column>
        <el-table-column
                prop="borrowRemarks"
                label="档案借阅信息"
                min-width="10%"
                align="center">
        </el-table-column>
        <el-table-column
                prop="borrowState"
                label="借阅状态"
                min-width="10%"
                :formatter="formperborrowState"
                align="center">
        </el-table-column>

        <el-table-column
                label="操作"
                min-width="10%"
                align="center">
            <template slot-scope="scope">
                <el-button
                        size="mini"
                        @click="handleDelete(scope.$index, scope.row)">删除记录
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

</div>
</body>
<script type="text/javascript" src="/static/js/model/borrec.js"></script>
</html>
