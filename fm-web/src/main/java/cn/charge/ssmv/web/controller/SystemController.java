package cn.charge.ssmv.web.controller;



import cn.charge.ssmv.domain.System;
import cn.charge.ssmv.query.SystemQuery;
import cn.charge.ssmv.service.SystemService;
import cn.charge.ssmv.util.AjaxResult;
import cn.charge.ssmv.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private SystemService systemService;

    /**
     * 根据id查询用户
     *
     */
    @RequestMapping("/getById")
    @ResponseBody
    public System getById(Long id) {
        return systemService.selectByPrimaryKey(id);
    }
    /**
     * 用户主界面
     */
    @RequestMapping("/index")
    public String index(Model model){
        return "system";
    }

    /**
     * 用户列表数据
     *
     */
    @RequestMapping("/list")
    @ResponseBody
    public PageResult list(SystemQuery qo){
        return systemService.selectAllForList(qo);
    }


    /**
     * 用户添加/修改
     */
    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult add(System system){
        try {
            if(system.getId() == null){
                systemService.insert(system);
            }else{
                systemService.updateByPrimaryKey(system);
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
                systemService.deleteByPrimaryKey(id);
            }
            return AjaxResult.success();
        } catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 用户删除
     */
    @RequestMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Long id){
        try {
            systemService.deleteByPrimaryKey(id);
            return AjaxResult.success();
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error("啊,系统异常啦,我们正在殴打程序员O(∩_∩)O~");
        }
    }
}
