package cn.charge.ssmv.web.controller;


import cn.charge.ssmv.domain.Permission;
import cn.charge.ssmv.query.PermissionQuery;
import cn.charge.ssmv.service.PermissionService;
import cn.charge.ssmv.util.AjaxResult;
import cn.charge.ssmv.util.PageResult;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    //跳转资源主页
    @RequestMapping("/index")
    public String index() {
        return "permission";
    }

    //查询资源
    @RequestMapping("/list")
    @ResponseBody
    public PageResult list(PermissionQuery query) {
        PageResult pageResult = permissionService.selectAllForList(query);
        return pageResult;
    }

    //新增资源 修改资源
    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult add(Permission permission) {
        try {
            if (permission.getId() != null) {
                permissionService.updateByPrimaryKey(permission);
            } else {
                permissionService.insert(permission);
            }
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("出错啦");
        }
    }

    //删除  修改状态为禁用
    @RequestMapping("/zt")
    @ResponseBody
    public AjaxResult zt(Long id) {
        try {
            permissionService.updateZT(id);
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
                permissionService.updateZT(id);
            }
            return AjaxResult.success();
        } catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error(e.getMessage());
        }
    }
}
