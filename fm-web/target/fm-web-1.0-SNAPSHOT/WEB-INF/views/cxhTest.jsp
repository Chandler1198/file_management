<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>档案管理</title>
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
                <el-form-item label="审批人">
                    <el-input v-model="formInline.user" placeholder="审批人"></el-input>
                </el-form-item>
                <el-form-item label="活动区域">
                    <el-select v-model="formInline.region" placeholder="活动区域">
                        <el-option label="区域一" value="shanghai"></el-option>
                        <el-option label="区域二" value="beijing"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="onSubmit"><i class="el-icon-search">&nbsp;&nbsp;</i>查询</el-button>
                </el-form-item>

                <el-button type="primary" class="new" @click="newArchive">
                    <i class="el-icon-plus">&nbsp;&nbsp;</i>
                    新建档案
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
        <%--<el-table-column--%>
        <%--prop="address"--%>
        <%--label="保密等级"--%>
        <%--min-width="20%"--%>
        <%--align="center">--%>
        <%--</el-table-column>--%>
        <el-table-column
                prop="createdDate"
                label="档案日期"
                value-format="yyyy-MM-dd HH:mm:ss"
                min-width="10%"
                align="center">
        </el-table-column>

        <el-table-column
                prop="state"
                label="状态"
                min-width="10%"
                align="center">
        </el-table-column>
        <el-table-column
                label="操作"
                min-width="10%"
                align="center">
            <template slot-scope="scope">
                <el-button
                        size="mini"
                        @click="handleEdit(scope.$index, scope.row)">编辑
                </el-button>
                <el-button
                        size="mini"
                        type="danger"
                        @click="handleDelete(scope.$index, scope.row)">删除
                </el-button>
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
            title="档案录入"
            :visible.sync="dialogFormVisible"
            :close-on-click-modal="false"
            :lock-scroll="true"
            top="20px"
    <%--:show-close="false"--%>
            :close-on-press-escape="true"
            :before-close="handleDialogClose">
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="250px" class="demo-ruleForm" label-position="left">
            <el-form-item label="档案类型" prop="arcType">
                <el-select v-model="ruleForm.arcType" placeholder="请选择档案类型">
                    <el-option v-for="item in arcType"
                               :key="item.id"
                               :label="item.detailName"
                               :value="item.catalogNum"
                    ></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="存档点" prop="arcPath">
                <el-select v-model="ruleForm.arcPath" placeholder="请选择存档点">
                    <el-option v-for="item in arcPath"
                               :key="item.id"
                               :label="item.detailName"
                               :value="item.catalogNum"
                    ></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="档案编号" prop="arcNum">
                <el-input v-model="ruleForm.arcNum" placeholder="请输入编号"></el-input>
            </el-form-item>
            <el-form-item label="档案标题" prop="arcTitle">
                <el-input v-model="ruleForm.arcTitle" placeholder="请输入档案标题"></el-input>
            </el-form-item>
            <el-form-item label="建档用户" prop="arcUser.realName">
                <el-input v-model="arcUser.realName" placeholder="建档用户" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="建档日期" prop="createdDate">
                <el-date-picker
                        v-model="ruleForm.createdDate"
                        type="date"
                        placeholder="选择日期"
                        :disabled="true">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="修改用户" prop="modifyUser">
                <el-input type="text" v-model="ruleForm.modifyUser" placeholder="修改用户" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="修改时间" prop="modifyDate">
                <el-date-picker
                        v-model="ruleForm.modifyDate"
                        type="date"
                        placeholder="选择日期"
                        :disabled="true">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="借阅状态" prop="borrowStatu">
                <el-select v-model="ruleForm.borrowStatu" placeholder="请选择借阅状态">
                    <el-option label="未借阅" value="0"></el-option>
                    <el-option label="已借阅" value="1"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="过期时间" prop="expirationTime">
                <el-date-picker
                        v-model="ruleForm.expirationTime"
                        type="date"
                        placeholder="选择日期"
                        value-format="yyyy-MM-dd">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="过期是否提前通知" prop="expirationNotice">
                <el-select v-model="ruleForm.expirationNotice" placeholder="请选择">
                    <el-option label="是" value="1"></el-option>
                    <el-option label="否" value="0"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="提前通知的天数" prop="notificationDay">
                <el-input v-model="ruleForm.notificationDay" type="number" placeholder="请输入天数"></el-input>
            </el-form-item>
            <el-form-item label="档案内容" prop="contentArchive">
                <el-input type="textarea" :rows="3" placeholder="请输入内容" v-model="ruleForm.contentArchive">
                </el-input>
            </el-form-item>
            <el-form-item label="内容简介" prop="contentValidity">
                <el-input type="text" :rows="1" placeholder="请输入内容" v-model="ruleForm.contentValidity" maxlength="10" show-word-limit>
                </el-input>
            </el-form-item>
            <el-form-item label="是否常用" prop="commonlyUsed">
                <el-select v-model="ruleForm.commonlyUsed" placeholder="请选择">
                    <el-option label="是" :value="1"></el-option>
                    <el-option label="否" :value="0"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="文件编号" prop="docNum">
                <el-input v-model="ruleForm.docNum" placeholder="请输入文件编号"></el-input>
            </el-form-item>
            <el-form-item label="来文部门" prop="comDepart">
                <el-input v-model="ruleForm.comDepart" placeholder="来文部门"></el-input>
            </el-form-item>
            <el-form-item label="发文单位" prop="publishUnit">
                <el-input v-model="ruleForm.publishUnit" placeholder="请输入发文单位"></el-input>
            </el-form-item>
            <el-form-item label="发文人" prop="publisher">
                <el-input v-model="ruleForm.publisher" placeholder="请输入"></el-input>
            </el-form-item>
            <el-form-item label="批准时间" prop="approvalTime">
                <el-date-picker
                        v-model="ruleForm.approvalTime"
                        type="date"
                        placeholder="选择日期">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="鉴定时间" prop="identificaTime">
                <el-date-picker
                        v-model="ruleForm.identificaTime"
                        type="date"
                        placeholder="选择日期">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="产品批号" prop="productNum">
                <el-input v-model="ruleForm.productNum" placeholder="请输入产品批号"></el-input>
            </el-form-item>
            <el-form-item label="原料批号" prop="rawNumber">
                <el-input v-model="ruleForm.rawNumber" placeholder="请输入原料批号"></el-input>
            </el-form-item>
            <el-form-item label="数量" prop="number">
                <el-input v-model="ruleForm.number" type="number" placeholder="请输入数量"></el-input>
            </el-form-item>
            <el-form-item label="页数" prop="page">
                <el-input v-model="ruleForm.page" type="number" placeholder="请输入页数"></el-input>
            </el-form-item>
            <el-form-item label="专利号" prop="patentNo">
                <el-input v-model="ruleForm.patentNo" placeholder="请输入专利号"></el-input>
            </el-form-item>
            <el-form-item label="年检负责人" prop="inspectPerson">
                <el-input v-model="ruleForm.inspectPerson" placeholder="年检负责人"></el-input>
            </el-form-item>
            <el-form-item label="状态" prop="state">
                <el-select v-model="ruleForm.state" placeholder="请选择档案类型" :disabled="true">
                    <el-option v-for="item in state"
                               :key="item.id"
                               :label="item.detailName"
                               :value="item.catalogNum"
                    ></el-option>
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
</div>
</body>
<script type="text/javascript" src="/static/js/model/archive.js"></script>
</html>
