package cn.charge.ssmv.web.controller;


import cn.charge.ssmv.domain.Department;
import cn.charge.ssmv.domain.User;
import cn.charge.ssmv.query.DepartmentQuery;
import cn.charge.ssmv.service.DepartmentService;
import cn.charge.ssmv.util.AjaxResult;
import cn.charge.ssmv.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/department")
public class DepartmentController {


    @Autowired
    private DepartmentService departmentService;

    /**
     * 根据id查询部门
     *
     */
    @RequestMapping("/getById")
    @ResponseBody
    public Department getById(Long id) {
        return departmentService.selectByPrimaryKey(id);
    }
    /**
     * 部门主界面
     */
    @RequestMapping("/index")
    public String index(Model model){
        return "department";
    }

    /**
     * 用户列表数据
     *
     */
    @RequestMapping("/list")
    @ResponseBody
    public PageResult list(DepartmentQuery qo){
        return departmentService.selectAllForList(qo);
    }

    /**
     * 部门添加/修改
     */
//    @RequestMapping("/saveOrUpdate")
//    @ResponseBody
//    public AjaxResult saveOrUpdate(Department department){
//        try {
//            if(department.getId() == null){
//                departmentService.insert(department);
//            }else{
//                departmentService.updateByPrimaryKey(department);
//            }
//            return AjaxResult.success();
//        }catch (Exception e){
//            e.printStackTrace();
//            return AjaxResult.error("啊,系统异常啦,我们正在殴打程序员O(∩_∩)O~");
//        }
//    }
    /**
     * 用户添加/修改
     */
    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult add(Department department){
        System.out.println(department);
        try {
            if(department.getId() == null){
//                userService.insert(user);
                departmentService.updateByPrimaryKey(department);
            }else{
//                userService.updateByPrimaryKey(user);
                departmentService.insert(department);
            }
            return AjaxResult.success();
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error("啊,系统异常啦,我们正在殴打程序员O(∩_∩)O~");
        }

    }

    /**
     * 批量删除
     *
     */
    @RequestMapping("/batchDelete")
    @ResponseBody
    public AjaxResult batchDelete(Long[] ids){
        try {
            for (Long id : ids) {
                departmentService.deleteByPrimaryKey(id);
            }
            return AjaxResult.success();
        } catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error(e.getMessage());
        }
    }
    /**
     * 部门删除
     */
    @RequestMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Long id){
        try {
            departmentService.deleteByPrimaryKey(id);
            return AjaxResult.success();
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error("啊,系统异常啦,我们正在殴打程序员O(∩_∩)O~");
        }
    }

}
