// $(function () {
//     //变量的抽取:
//      var ${domain}Datagrid=$('#${domain}Datagrid');  //employeeDataGrid
//      var ${domain}Dlg=$('#${domain}Dlg');
//      var  ${domain}Form=$('#${domain}Form');
//
//
//     //datagrid
//     ${domain}Datagrid.datagrid({
//         url: '${domain}/list',
//         fit:true,
//         title:'部门管理',
//         rownumbers:true,
//         pagination:true,
//         singleSelect:true,
//         toolbar:"#toolbar",
//         columns: [[
//             /*使用遍历:遍历的是Velocity中上下文的对象:
//             * fieldList
//             *  #foreach( $elem in $allElems)
//                     $elem</br>
//                 #end
//                 {field: 'id', title: 'id', width: '10%',align:  'center'},
//             * */
//         #foreach( $fieldVo in $fieldList)
//            {field: '$fieldVo.field', title: '$fieldVo.title', width: '10%',align:  'center'},
//         #end
//         ]]
//     });
//
//     //按钮的事件
//     var cmdObj={
//         'add':function () {
//             //弹出一个dialog,里面装form表单  ${domain}Dlg
//             ${domain}Dlg.dialog('open').dialog('center').dialog('setTitle','部门添加');
//             //清空表单
//             ${domain}Form.form('clear');
//         },
//         'edit':function () {
//             //先获取编辑的数据
//             var row = ${domain}Datagrid.datagrid('getSelected');
//             //做一个判断:是否选中
//             if (row){
//                 //弹出dialog,进行数据的回显
//                 //弹出一个dialog,里面装form表单  ${domain}Dlg
//                 ${domain}Dlg.dialog('open').dialog('center').dialog('setTitle','部门修改');
//                 //清空表单
//                 ${domain}Form.form('clear');
//                 //表单数据的回显:
//                 ${domain}Form.form('load',row);
//             }else{
//                 $.messager.alert('温馨提示','请选中需要编辑的行!!','warning');
//             }
//         },
//         'delete':function () {
//             //先获取删除的数据
//             var row = ${domain}Datagrid.datagrid('getSelected');
//             //做一个判断:是否选中
//             if (row){
//                 $.messager.confirm('温馨提示','你确定删除:[<font color="pink">'+row.name+"</font>]吗?",function(r){
//                     // 选中:确认的操作:调用后台的删除方法:发送ajax调用
//                     if (r){
//                         // 发送ajax调用 $.get(url,params,function(d){},type)
//                         $.get('${domain}/delete?id='+row.id,function (d) {
//                             //d的处理
//                           if(d.success){
//                               //true:成功
//                               $.messager.alert('操作提示',d.msg,'info');
//                               //页面刷新
//                               cmdObj.refresh();
//                           }else{
//                               //false:失败:提示
//                               $.messager.alert('错误提示',d.msg,'error');
//                           }
//
//                         },'json')
//                     }
//                 });
//             }else{
//                 $.messager.alert('温馨提示','请选中需要删除的行!!','warning');
//             }
//         },
//         'refresh':function () {
//             //datagrid的重新加载: $("selector").datagrid('funName');
//             ${domain}Datagrid.datagrid('reload');
//         },
//         'submit':function(){
//             //提交:表单的提交:
//             ${domain}Form.form('submit', {
//                     url:'${domain}/saveOrUpdate',
//                 onSubmit: function(){
//             },
//             success:function(data){
//              // {"success":true,"msg":"操作成功"}==>json字符串
//                 var $data = $.parseJSON(data);
//                 //弹框提示和刷新
//                 if($data.success){
//                     $.messager.alert('操作提示',$data.msg,'info');
//                 }else{
//                     $.messager.alert('错误提示',$data.msg,'error');
//
//                 }
//
//                 ${domain}Dlg.dialog('close');
//                 //页面刷新
//                 cmdObj.refresh();
//             }
//         });
//         },
//         'cancel':function(){
//             //关闭dialog,不清空form,在打开dialog的时候清空
//             ${domain}Dlg.dialog('close');
//         }
//
//     };
//
//     //toolbar绑定事件:如果按钮禁用了怎么办?下午具体实现
//     $("a[data-cmd]").on('click',function () {
//         //data-cmd="add"
//         var dataCmd=$(this).data('cmd');
//         cmdObj[dataCmd]();
//     });
// });