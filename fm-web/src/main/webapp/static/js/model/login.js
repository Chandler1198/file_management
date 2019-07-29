var table = new Vue({
    el:"#app",
    data() {
        var validatePassword = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入密码'));
            } else {
                callback();
            }
        };
        var validateUsername = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入账号'));
            } else {
                callback();
            }
        };
        return {
            ruleForm: {
                password: '',
                username: '',
            },
            rules: {
                password: [
                    { validator: validatePassword, trigger: 'blur' }
                ],
                username: [
                    { validator: validateUsername, trigger: 'blur' }
                ],
            }
        };
    },
    methods: {
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    $.ajax({
                        url:"/login",
                        type:"post",
                        data:{
                            userName:this.ruleForm.username,
                            password:this.ruleForm.password
                        },
                        success:function (data) {
                            if(!data.success){
                                table.$message.error('用户名或密码错误，请重新输入');
                            }else{
                                window.location.href = '/main/index';
                            }
                        }
                    })
                } else {
                    table.$message.error('请完成用户名密码的输入');
                    return false;
                }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        },
    }
});