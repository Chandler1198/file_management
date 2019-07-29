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
        }
    },
    created: function () {
        // 组件创建完后获取数据，
        // 此时 data 已经被 observed 了
        this.fetchData();
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
        //批量删除
        batchDelete() {
            var id = []
            for (var i = 0; i < this.multipleSelection.length; i++) {
                id[i] = this.multipleSelection[i].id
            }
            this.$confirm('此操作将删除所选系统, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                $.ajax({
                    url: "/systemdictionarydetail/delete",
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
        handleEdit(index, row) {
            console.log(index, row);
        },
        //删除
        handleDelete(index, row) {
            this.$confirm('此操作将删除该用户, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                $.ajax({
                    url: "/systemdictionarydetail/remove",
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
    }
})



