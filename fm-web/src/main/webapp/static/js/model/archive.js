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
                id: '',
                arcNum: '',
                arcType: '',
                arcPath: '',
                arcTitle: '',
                arcUser:{
                    realName:''
                },
                createdDate: '',
                modifyUser: '',
                modifyDate: '',
                borrowStatu: '',
                expirationTime: '',
                expirationNotice: '',
                notificationDay: '',
                contentArchive: '',
                contentValidity: '',
                commonlyUsed: '',
                docNum: '',
                comDepart: '',
                publishUnit: '',
                publisher: '',
                approvalTime: '',
                identificaTime: '',
                productNum: '',
                rawNumber: '',
                number: '',
                page: '',
                patentNo: '',
                inspectPerson: '',
                state: ''
            },
            ruleForm: {
                id: '',
                arcNum: '',
                arcType: '',
                arcPath: '',
                arcTitle: '',
                arcUser:{
                    realName:''
                },
                createdDate: '',
                modifyUser: '',
                modifyDate: '',
                borrowStatu: '',
                expirationTime: '',
                expirationNotice: '',
                notificationDay: '',
                contentArchive: '',
                contentValidity: '',
                commonlyUsed: '',
                docNum: '',
                comDepart: '',
                publishUnit: '',
                publisher: '',
                approvalTime: '',
                identificaTime: '',
                productNum: '',
                rawNumber: '',
                number: '',
                page: '',
                patentNo: '',
                inspectPerson: '',
                state: 1
            },
            ruleFormNone: {
                id: '',
                arcNum: '',
                arcType: '',
                arcPath: '',
                arcTitle: '',
                arcUser:{
                    realName:''
                },
                createdDate: '',
                modifyUser: '',
                modifyDate: '',
                borrowStatu: '',
                expirationTime: '',
                expirationNotice: '',
                notificationDay: '',
                contentArchive: '',
                contentValidity: '',
                commonlyUsed: '',
                docNum: '',
                comDepart: '',
                publishUnit: '',
                publisher: '',
                approvalTime: '',
                identificaTime: '',
                productNum: '',
                rawNumber: '',
                number: '',
                page: '',
                patentNo: '',
                inspectPerson: '',
                state: 1
            },
            arcUser:{
                realName:''
            },
            arcType: [],
            arcPath: [],
            state: [],
            // rules: {
            //     arcNum: [{}],
            //     arcType: [{}],
            //     arcPath: [{}],
            //     arcTitle: [{}],
            //     createdDate: [{}],
            //     modifyUser: [{}],
            //     modifyDate: [{}],
            //     borrowStatu: [{}],
            //     expirationTime: [{}],
            //     expirationNotice: [{}],
            //     notificationDay: [{}],
            //     contentArchive: [{}],
            //     contentValidity: [{}],
            //     commonlyUsed: [{}],
            //     docNum: [{}],
            //     comDepart: [{}],
            //     publishUnit: [{}],
            //     publisher: [{}],
            //     approvalTime: [{}],
            //     identificaTime: [{}],
            //     productNum: [{}],
            //     rawNumber: [{}],
            //     number: [{}],
            //     page: [{}],
            //     patentNo: [{}],
            //     inspectPerson: [{}],
            //     state: 1
            //     // name: [
            //     //     {required: true, message: '请输入活动名称', trigger: 'blur'},
            //     //     {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'}
            //     // ],
            //     // region: [
            //     //     {required: true, message: '请选择活动区域', trigger: 'change'}
            //     // ],
            //     // date1: [
            //     //     {type: 'date', required: true, message: '请选择日期', trigger: 'change'}
            //     // ],
            //     // date2: [
            //     //     {type: 'date', required: true, message: '请选择时间', trigger: 'change'}
            //     // ],
            //     // type: [
            //     //     {type: 'array', required: true, message: '请至少选择一个活动性质', trigger: 'change'}
            //     // ],
            //     // resource: [
            //     //     {required: true, message: '请选择活动资源', trigger: 'change'}
            //     // ],
            //     // desc: [
            //     //     {required: true, message: '请填写活动形式', trigger: 'blur'}
            //     // ]
            // },
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
        //新建和修改
        newArchive() {
            //建档用户
            table.isEdit = false;
            {
                var username = ''
                var name = document.cookie.split('=')[1].toString()
                for (var i = 0; i < document.cookie.split('=')[1].indexOf(";"); i++) {
                    username += name[i]
                }
                this.arcUser.realName = username
            }

            table.ruleForms = table.ruleForm
            table.dialogFormVisible = true;
        },
        //批量删除
        batchDelete() {
            var id = []
            for (var i = 0; i < this.multipleSelection.length; i++) {
                id[i] = this.multipleSelection[i].id
            }
            this.$confirm('此操作将删除所选档案, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                center: true
            }).then(() => {
                $.ajax({
                    url: "/archive/batchDelete",
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
                var result = {msg: "", success: ""}
                result.msg = "已取消删除"
                result.success = "info"
                table.open(result)
            });
        },
        //编辑
        handleEdit(index, row) {

            table.isEdit = true;

            var arcUser = row.arcUser.realName
            row = JSON.stringify(row)
            row = JSON.parse(row)
            table.ruleForm = row
            //渲染建档用户
            {
                table.arcUser.realName = arcUser
            }
            //渲染修改用户
            {
                var username = ''
                var name = document.cookie.split('=')[1].toString()
                for (var i = 0; i < document.cookie.split('=')[1].indexOf(";"); i++) {
                    username += name[i]
                }
                table.ruleForm.modifyUser = username
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
        handleDelete(index, row) {
            this.$confirm('此操作将删除该档案, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                center: true
            }).then(() => {
                $.ajax({
                    url: "/archive/delete",
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
            table.ruleForm.arcUser = table.arcUser

            table.ruleForm["arcUser.realName"] = table.ruleForm.arcUser.realName;
            delete table.ruleForm.arcUser
            $.post("/archive/save", table.ruleForm, result => {
                if (result.success) {
                    result.msg = "保存成功！！"
                    result.success = "success"
                    table.open(result)
                    table.ruleForm = table.ruleFormNone
                    // this.$refs['ruleForm'].resetFields();
                    table.dialogFormVisible = false;
                    table.fetchData(table.currentPage, table.pageSize)
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
                    // table.ruleForm = table.ruleFormNone
                    this.$refs[formName].resetFields();
                    // table.ruleForm = table.ruleFormNone
                    var result = {msg: "数据已清除！", success: "inof"}
                    table.open(result)
                    table.dialogFormVisible = false;
                });
            } else {
                table.ruleForm = table.ruleFormNone
                table.dialogFormVisible = false;
            }

        },
        handleDialogClose() {
            table.dialogFormVisible = false;
        },
        defaultData() {
            //获取档案类型
            $.ajax({
                url: "/systemdictionarydetail/selectAllById",
                data: {
                    ident: "dalb"
                },
                type: "post",
                dataType: "json",
                error: function () {
                },
                success: function (arcType) {
                    table.arcType = arcType
                }
            })
            //获取存档地点
            $.ajax({
                url: "/systemdictionarydetail/selectAllById",
                data: {
                    ident: "cddd"
                },
                type: "post",
                dataType: "json",
                error: function () {
                },
                success: function (arcPath) {
                    table.arcPath = arcPath
                }
            })

            //建档日期
            {
                var date = new Date();
                var seperator1 = "-";
                var seperator2 = ":";
                var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
                var strDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
                var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
                    + " " + date.getHours() + seperator2 + date.getMinutes()
                    + seperator2 + date.getSeconds();
                this.ruleForms.createdDate = currentdate;
            }
            //获取档案状态
            $.ajax({
                url: "/systemdictionarydetail/selectAllById",
                data: {
                    ident: "zt"
                },
                type: "post",
                dataType: "json",
                error: function () {
                },
                success: function (state) {
                    table.state = state
                }
            })
        },
        stateFormat(val){
            if(val.state == 1){
                return "正常"
            }else if(val.state == 2){
                return "丢失"
            }else if(val.state == 3){
                return "找回"
            }else if(val.state == 4){
                return "损坏"
            }else if(val.state == 5){
                return "销毁"
            }
        }
    }
});






