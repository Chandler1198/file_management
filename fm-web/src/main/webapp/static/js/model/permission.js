var table = new Vue({
    el: "#table",
    data() {
        return {
            total: 5,
            currentPage: 1,
            pageSize: 10,
            tableData: [],
            multipleSelection: [],
            formInline: {
                user: '',
                region: ''
            },
            dialogFormVisible: false,
            ruleForms: {
                name: null,
                url:null,
                remarks:null,

            },
            ruleForm: {
                name: null,
                url:null,
                remarks:null,
            },
            state: [],
            rules: {},
            isEdit: true,
        };
    },
    mounted: function () {
        // 组件创建完后获取数据，
        // 此时 data 已经被 observed 了
        this.fetchData();
    },
    methods: {
        //格式化
        formstate(val){
            /*console.log("aaaaaa");
            console.log(val.state);*/
            if(val.state == 0)
            {return "启用"}
            else if (val.state == -1)
            {return "禁止"}
        },
        toggleSelection(rows) {
            if (rows) {
                rows.forEach(function (row) {
                    this.$refs.multipleTable.toggleRowSelection(row);
                });
            } else {
                this.$refs.multipleTable.clearSelection();
            }
        },
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },
        callbackFunction(result) {
            alert(result + "aaa");
        },
        //分页查数据
        fetchData(currentPage, pageSize) { //获取数据
            $.ajax({
                url: "/permission/list",
                data: {
                    "currentPage": currentPage,
                    "pageSize": pageSize,
                },
                type: "post",
                dataType: "json",
                error: function () {
                },
                success: function (pageList) {
                    table.tableData = []
                    var tablelist = table.tableData;
                    table.total = pageList.total
                    for (var i = 0; i < pageList.rows.length; i++) {
                        tablelist.push(pageList.rows[i])
                    }
                }
            })
        },
        handleSizeChange(val) {
            this.pageSize = val;
            this.currentPage = 1;
            this.fetchData(1, val);
            console.log(`每页 ${val} 条`);
        },
        handleCurrentChange(val) {
            this.currentPage = val;
            this.fetchData(val, this.pageSize);
            console.log(`当前页: ${val}`);
        },
        //保存选中状态
        getRowKey(row) {
            return row.id
        },
        //新建和修改
        newPermission() {
            table.isEdit = false;
            table.ruleForm = table.ruleForms
            table.dialogFormVisible = true;
        },
        //批量删除
        batchDelete() {
            var id = []
            for (var i = 0; i < this.multipleSelection.length; i++) {
                id[i] = this.multipleSelection[i].id
            }
            this.$confirm('此操作将删除所选资源全部信息,请谨慎操作 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                $.ajax({
                    url: "/permission/moreDelete",
                    data: {
                        ids: id.toString()
                    },
                    type: "post",
                    dataType: "json",
                    error: function () {
                    },
                    success: function (result) {
                        table.fetchData(table.currentPage, table.pageSize)
                        if (result.success) {
                            result.msg = "删除成功！！"
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
                result.msg = "已取消删除"
                result.success = "info"
                table.open(result)
            });
        },

        //编辑
        handleEdit(index, row,val) {
            console.debug(row);
            table.isEdit = true;
            table.dialogFormVisible = true;
            row = JSON.stringify(row)
            row = JSON.parse(row)
            table.ruleForm = row
        },
        //删除
        handleDelete(index, row,val) {
            if(row.state==0){
                this.$confirm('此操作将删除该资源, 是否继续?', '提示', {
                    cancelButtonText: '取消',
                    confirmButtonText: '确定',
                    type: 'warning'
                }).then(() => {
                    $.ajax({
                        url: "/permission/zt",
                        data: {
                            "id": row.id
                        },
                        dataType: "json",
                        error: function (result) {
                        },
                        success: function (result) {
                            table.fetchData(table.currentPage, table.pageSize)
                            if (result.success) {
                                result.msg = "删除成功！！"
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
                    result.msg = "已取消删除"
                    result.success = "info"
                    table.open(result)
                });
            }else if(row.state==-1){
                this.$confirm('此操作将恢复该资源, 是否继续?', '提示', {
                    cancelButtonText: '取消',
                    confirmButtonText: '确定',
                    type: 'warning'
                }).then(() => {
                    $.ajax({
                        url: "/permission/zt",
                        data: {
                            "id": row.id
                        },
                        dataType: "json",
                        error: function (result) {
                        },
                        success: function (result) {
                            table.fetchData(table.currentPage, table.pageSize)
                            if (result.success) {
                                result.msg = "资源恢复成功！！"
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
                    result.msg = "资源恢复已取消"
                    result.success = "info"
                    table.open(result)
                });
            }

        },
        //提交高级查询表单
        onSubmit() {
            console.log(table.formInline);
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
        //提交表单
        submitForm(formName) {
            $.post("/permission/save", table.ruleForm, result => {
                if (result.success) {
                    result.msg = "保存成功！！"
                    result.success = "success"
                    table.open(result)
                } else {
                    result.msg = "网络繁忙！！请稍后再试！！"
                    result.success = "error"
                    table.open(result)
                }
            });

        },
        //关闭弹出框
        closeDialog(formName) {
            if (!table.isEdit) {
                this.$confirm('是否保留数据?', '提示', {
                    confirmButtonText: '保留',
                    cancelButtonText: '不保留',
                    type: 'warning',
                    center: true,
                    showClose: false,
                }).then(() => {
                    table.ruleForms = table.ruleForm
                    var result = {msg: "保留成功！", success: "success"}
                    table.open(result)
                    table.dialogFormVisible = false;
                }).catch(() => {
                    this.$refs[formName].resetFields();
                    var result = {msg: "数据已清除！", success: "inof"}
                    table.open(result)
                    table.dialogFormVisible = false;
                    that.dialogVisible = false;//关闭Dialog
                });
            } else {
                this.$refs[formName].resetFields();
                table.dialogFormVisible = false;
            }
        },
        handleDialogClose() {
            table.dialogFormVisible = false;
        }
    }
});






