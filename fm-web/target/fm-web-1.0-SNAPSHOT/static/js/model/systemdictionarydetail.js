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

            total2: 5,
            currentPage2: 1,
            pageSize2: 10,
            tableData2: [],
            multipleSelection2: [],
            formInline2: {
                user: '',
                region: ''
            },
            dialogFormVisible: false,
            ruleForms: {
                id: '',
                detailName: '',
                catalogNum: '',
                typeId: '',
            },
            ruleForm: {
                id: '',
                detailName: '',
                catalogNum: '',
                typeId: '',
            },
            // rules: {},
            isEdit: true,
        }
    },
    created: function () {
        // 组件创建完后获取数据，
        // 此时 data 已经被 observed 了
        this.fetchData();
        this.defaultData();
        this.fetchData2();
    },
    methods: {
        checkSelectable (row, index) {
            if(row.state == 1){
                return false;//禁用
            }else{
                // table.disButton = false;
                return true;//不禁用
            }
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
                url: "/systemdictionarydetail/selectAllForList",
                data: {
                    "currentPage": currentPage,
                    "pageSize": pageSize,
                },
                dataType: "json",
                error: function () {
                },
                success: function (pageList) {
                    table.tableData = []
                    var tablelist = table.tableData;
                    table.total = pageList.total
                    for (var i = 0; i < pageList.rows.length; i++) {
                        tablelist.push(pageList.rows[i]);
                        // table.systemdictionarytype.type = pageList.rows[i].systemdictionarytype.type;
                        // tablelist[i].systemdictionarytype = table.systemdictionarytype;
                    }
                }
            })
        },
        handleSizeChange(val) {
            this.pageSize = val;
            this.currentPage = 1;
            this.fetchData(1, val);
        },
        handleCurrentChange(val) {
            this.currentPage = val;
            this.fetchData(val, this.pageSize);
        },
        //保存选中状态
        getRowKey(row) {
            return row.id
        },
        //新建和修改
        newUser() {
            table.isEdit = false;
            table.ruleForm = table.ruleForms
            table.dialogFormVisible = true;
        },
        //编辑
        handleEdit(index, row) {
            table.isEdit = true;
            table.dialogFormVisible = true;
            row = JSON.stringify(row)
            row = JSON.parse(row)
            table.ruleForm = row
        },
        //删除
        handleDelete(index, row) {
            this.$confirm('此操作将删除该用户, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                $.ajax({
                    url: "/systemdictionarydetail/delete",
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
            delete table.ruleForm.systemdictionarytype
            $.post("/systemdictionarydetail/saveOrUpdate", table.ruleForm , result => {
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
                });
            } else {
                this.$refs[formName].resetFields();
                table.dialogFormVisible = false;
            }
        },
        handleDialogClose() {
            table.dialogFormVisible = false;
        },
        defaultData() {
            // //获取一级字典
            // $.ajax({
            //     url: "/systemdictionarytype/selectAllForList",
            //     data: {
            //         ident: "dalb"
            //     },
            //     type: "post",
            //     dataType: "json",
            //     success: function (arcType) {
            //         table.arcType = arcType
            //     }
            // })
        },








        //分页查数据
        fetchData2(currentPage2, pageSize2) { //获取数据
            $.ajax({
                url: "/systemdictionarytype/selectAllForList",
                data: {
                    "currentPage": currentPage2,
                    "pageSize": pageSize2,
                },
                dataType: "json",
                error: function () {
                },
                success: function (pageList) {
                    table.tableData2 = []
                    var tablelist2 = table.tableData2;
                    table.total2 = pageList.total
                    for (var i = 0; i < pageList.rows.length; i++) {
                        tablelist2.push(pageList.rows[i])
                    }
                }
            })
        },
        handleSizeChange2(val) {
            this.pageSize2 = val;
            this.currentPage2 = 1;
            this.fetchData2(1, val);
            console.log(`每页 ${this.pageSize2} 条`);
        },
        handleCurrentChange2(val) {
            this.currentPage2 = val;
            this.fetchData2(val, this.pageSize2);
            console.log(`当前页: ${val}`);
        },
        //保存选中状态
        getRowKey2(row) {
            return row.id
        },
        //编辑
        handleEdit2(index, row) {
            console.log(index, row);
        },
        //删除
        handleDelete2(index, row) {
            this.$confirm('此操作将删除该用户, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                $.ajax({
                    url: "/systemdictionarytype/delete",
                    data: {
                        "id": row.id
                    },
                    dataType: "json",
                    error: function (result) {
                    },
                    success: function (result) {
                        table.fetchData2(table.currentPage2, table.pageSize2)
                        if (result.success) {
                            result.msg = "删除成功！！"
                            result.success = "success"
                            table.open(result)
                        } else {
                            result.msg = "请先删除对应的二级菜单"
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
        //提交高级查询表单
        onSubmit2() {
            console.log(table.formInline2);
        },

    },
})



