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

                <el-button type="primary" class="new"><i class="el-icon-plus">&nbsp;&nbsp;</i>添加</el-button>
                <el-button type="primary" class="new"><i class="el-icon-plus">&nbsp;&nbsp;</i>刷新</el-button>
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
                label="系统名称"
                min-width="10%"
                align="名称">
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
                label="状态"
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
</div>
</body>
<script type="text/javascript" src="/static/js/model/test.js"></script>
</html>
