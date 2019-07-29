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
            formLabelWidth: '120px'
        }
    },
    created: function () {
        // 组件创建完后获取数据，
        // 此时 data 已经被 observed 了
        this.fetchData();
    },
    methods: {

        //格式化
        formperborrowState(val){
            if(val.borrowState == 1)
            {return "已借阅"}
            else if (val.borrowState == 0)
            {return "未借阅"}
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
                url: "/archivesbor/selectAllForList",
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
        //提交高级查询表单
        onSubmit() {
            console.log(table.formInline);
        },

        //删除
        handleDelete(index, row) {
            this.$confirm('是否要删除记录，删除后就彻底消失了哦?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                center: true
            }).then(() => {
                $.ajax({
                    url: "/archivesbor/delete",
                    data: {
                        "id": row.id
                    },
                    dataType: "json",
                    type: "post",
                    error: function (result) {
                    },
                    success: function (result) {
                        table.fetchData(table.currentPage, table.pageSize)
                        if (result.success) {
                            result.msg = "删除成功！！"
                            result.success = "success"
                            table.open(result)
                        } else {
                            result.msg = "已经被借出去了，不能删哦！！！"
                            result.success = "error"
                            table.open(result)
                        }
                    }
                })
            }).catch(() => {
                var result = {msg: "", success: ""}
                result.msg = "已取消删除"
                result.success = "info"
                table.open(result)
            });
            table.fetchData(table.currentPage, table.pageSize)
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
                });
            } else {
                this.$refs[formName].resetFields();
                table.dialogFormVisible = false;
            }

        },
        handleDialogClose() {
            table.dialogFormVisible = false;
        },
    }
})




