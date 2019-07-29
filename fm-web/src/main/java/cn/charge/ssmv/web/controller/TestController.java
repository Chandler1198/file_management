package cn.charge.ssmv.web.controller;

import cn.charge.ssmv.domain.Systemdictionarydetail;
import cn.charge.ssmv.query.SystemdictionarydetailQuery;
import cn.charge.ssmv.service.SystemdictionarydetailService;
import cn.charge.ssmv.util.AjaxResult;
import cn.charge.ssmv.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private SystemdictionarydetailService systemdictionarydetailService;

    @RequestMapping("/index")
    public String index() {
        return "test";
    }

    @RequestMapping("/selectAllForList")
    @ResponseBody
    public PageResult list(SystemdictionarydetailQuery systemdictionarydetailQuery){
        return systemdictionarydetailService.selectAllForList(systemdictionarydetailQuery);
    }

    // systemdictionarydetail/delete?id
    @RequestMapping("/delete")
    @ResponseBody//json
    public AjaxResult delete(Long id) {
        try {
            systemdictionarydetailService.deleteByPrimaryKey(id);
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("删除失败:" + e.getMessage());
        }
    }

    // systemdictionarydetail/saveOrUpdate
    @RequestMapping("/saveOrUpdate")
    @ResponseBody//json
    public AjaxResult saveOrUpdate(Systemdictionarydetail systemdictionarydetail) {
        if (systemdictionarydetail != null) {
            try {
                //判断是否有id,有就是修改,没有就是添加
                if (systemdictionarydetail.getId() != null) {
                    //修改
                    systemdictionarydetailService.updateByPrimaryKey(systemdictionarydetail);
                } else {
                    //添加
                    systemdictionarydetailService.insert(systemdictionarydetail);
                }
                return AjaxResult.success();
            } catch (Exception e) {
                e.printStackTrace();
                return AjaxResult.error("操作失败:"+e.getMessage());
            }
        }
        return null;
    }

    // systemdictionarydetail/saveOrUpdate
    @RequestMapping("/selectAllById")
    @ResponseBody//json
    public List<Systemdictionarydetail> selectAllById(String ident) {
        return systemdictionarydetailService.selectAllById(ident);
    }
}
