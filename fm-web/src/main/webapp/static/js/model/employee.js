function stateFormatter(value,row,index){
    return value == 1 ? "<span style='color:red'>禁用</span>" : "<span style='color:green'>正常</span>";
}
$(function () {

    var  employeeDataGrid = $('#employeeDataGrid');
    var employeeAddDialog = $("#employeeAddDialog");
    var employeeAddDialogForm = $("#employeeAddDialogForm");

    //统一绑定方法
    var methodObj = {
        search:function () {
            //获取关键字
            var keyword = $("#keyword").val();
            //查询
            employeeDataGrid.datagrid("load",{"keyword":keyword});
        },
        add:function () {
            //弹出添加的dialog
            employeeAddDialogForm.form("clear");
            employeeAddDialog.dialog("open");
        },
        edit:function () {
            //获取选中行
            var row = employeeDataGrid.datagrid("getSelected");
            if(row){
                //表单数据回显
                employeeAddDialogForm.form("clear");
                employeeAddDialogForm.form('load',row);

                //弹出编辑框
                employeeAddDialog.dialog("open");
            }else{
                //给出提示
                $.messager.alert("提示","你不选择数据我怎么给你修改？","error");
            }


        },
        remove:function () {
            //获取选中行
            var row = employeeDataGrid.datagrid("getSelected");
            if(row){
                var id = row.id;
                var state = row.state;
                if(state == 1){
                    $.messager.alert("提示","状态已经是删除","error");
                }else{
                    //删除请求ajax
                    $.post("employee/remove",{"id":id},function (data) {
                        if(data.success){
                            //列表刷新
                            $.messager.alert("提示","恭喜您,提交成功,列表已经刷新","info");
                            methodObj.reload();
                        }else{
                            $.messager.alert("提示","恭喜您,提交失败,错误原因："+data.msg,"error");
                        }
                    });
                }
            }else{
                //给出提示
                $.messager.alert("提示","你不选择数据我怎么给你删除？","error");
            }
        },
        reload:function () {
            employeeDataGrid.datagrid("reload");
        },
        save:function () {
            //获取表单
            //提交表单
            employeeAddDialogForm.form('submit', {
                url:"employee/saveOrUpdate",
                success:function(data){
                    data = $.parseJSON(data);
                    if(data.success){
                        //列表刷新
                        $.messager.alert("提示","恭喜您,提交成功,列表已经刷新","info");
                        methodObj.close();
                        methodObj.reload();
                    }else{
                        $.messager.alert("提示","恭喜您,提交失败,错误原因："+data.msg,"error");
                    }
                }
            });

        },
        close:function () {
            employeeAddDialog.dialog("close");
        }
    };

    //统一绑定事件
    $("a[data-method]").click(function () {
        var method = $(this).data("method");
        methodObj[method]();
    });


    //渲染表格
    employeeDataGrid.datagrid({
        url:'employee/list',
        fit:true,
        fitColumns:true,
        singleSelect:true,
        pagination:true,
        toolbar:"#employeeDataGridToolbar",
        columns:[[
            {field:'id',title:'ID',width:100},
            {field:'userName',title:'用户名',width:100},
            {field:'age',title:'年龄',width:100},
            {field:'state',title:'状态',width:100,formatter:stateFormatter},
            {field:'realName',title:'姓名',width:100}
        ]]
    });

    //添加的dialog
    employeeAddDialog.dialog({
        closed: true,
        modal: true,
        buttons:"#employeeAddDialogButtons"
    })
});