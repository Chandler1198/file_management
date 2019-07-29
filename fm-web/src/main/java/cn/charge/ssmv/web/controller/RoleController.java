package cn.charge.ssmv.web.controller;

import cn.charge.ssmv.domain.Role;
import cn.charge.ssmv.query.RoleQuery;
import cn.charge.ssmv.service.RoleService;
import cn.charge.ssmv.util.AjaxResult;
import cn.charge.ssmv.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    //跳转资源主页
    @RequestMapping("/index")
    public String index() {
        return "role";
    }

    //高级分页查询
    //查询资源
    @RequestMapping("/list")
    @ResponseBody
/*    public PageResult list(RoleQuery query){
        return roleService.selectAllForList(query);
    }*/
    public List<Role> findAll(RoleQuery roleQuery) {
        return roleService.selectAll(roleQuery);
    }

    //删除  修改状态为禁用
    @RequestMapping("/zt")
    @ResponseBody
    public AjaxResult zt(Long id) {
        try {
            roleService.updateZT(id);
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("删除失败 ，请检查");
        }
    }
    //批量删除(恢复状态)
    @RequestMapping("/moreDelete")
    @ResponseBody
    public AjaxResult batchDelete(Long[] ids){
        try {
            for (Long id : ids) {
                roleService.updateZT(id);
            }
            return AjaxResult.success();
        } catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error(e.getMessage());
        }
    }

}
