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
            forms: {
                borrowName: '',
                systemdictionaryt:'',
                borrowNumber:'',
                borrowPhone:'',
                arcUser:{
                    realName:''
                },
                borrowDate:'',
                returnDate:'',
                borrowRemarks:'',


            },
            form: {
                borrowName: '',
                systemdictionary:'',
                borrowNumber:'',
                borrowPhone:'',
                arcUser:{
                    realName:''
                },
                borrowDate:'',
                returnDate:'',
                borrowRemarks:'',


            },
            arcUser:{
                realName:''
            },
            systemdictionary: [],
            arcPath: [],
            state: [],
            rules: {


            },
            isEdit: true,
        }
    },
    mounted: function () {
        // 组件创建完后获取数据，
        // 此时 data 已经被 observed 了
        this.fetchData();
        this.defaultData();
    },
    methods: {

        //格式化
        formperstate(val){
            if(val.borrowStatu == 1)
            {return "已借阅"}
            else if (val.borrowStatu == 0)
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
                url: "/archive/selectAll",
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
                        table.arcUser.realName = pageList.rows[i].arcUser.realName
                        tablelist[i].arcUser = table.arcUser

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
        //编辑
        handleEdit(index, row) {


            table.form['id'] = row.id
            table.isEdit = true;

            var arcUser = row.arcUser.realName
            row = JSON.stringify(row)
            row = JSON.parse(row)
            table.ruleForm = row

            //渲染建档用户
            {
                table.arcUser.realName = arcUser
            }
            //修改日期
            {
                var date = new Date();
                var seperator1 = "-";
                var seperator2 = ":";
                var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
                var strDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
                var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
                    + " " + date.getHours() + seperator2 + date.getMinutes()
                    + seperator2 + date.getSeconds();
                table.ruleForm.modifyDate = currentdate;
            }
            table.dialogFormVisible = true;
        },

        //删除
   /*     handleDelete(index, row) {
            this.$confirm('此操作将删除该档案, 是否继续?', '提示', {
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
                            result.msg = "网络繁忙！！请稍后再试！！"
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
        },*/
        //提交高级查询表单
        onSubmit() {
            /*console.log(table.formInline);*/
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
            table.form.arcUser = table.arcUser
            table.form["arcUser.realName"] = table.form.arcUser.realName;
            delete table.form.arcUser
            $.post("/archivesbor/save", table.form, result => {
                if (result.success) {
                    result.msg = "保存成功！！"
                    result.success = "success"
                    table.open(result)
                    table.dialogFormVisible = false;
                    table.fetchData(table.currentPage, table.pageSize)
                    table.closeDialog(formName)

                } else {

                    result.msg = "已经借阅了。。。"
                    result.success = "error"
                    table.open(result)
                    table.dialogFormVisible = false;
                    table.fetchData(table.currentPage, table.pageSize)
                    table.closeDialog(formName)

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
                    table.forms = table.form
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
            //建档用户
            {
                var username = ''
                var name = document.cookie.split('=')[1].toString()
                for (var i = 0; i < document.cookie.split('=')[1].indexOf(";"); i++) {
                    username += name[i]
                }
                this.forms.arcUser = username
            }

        },


    }
})




