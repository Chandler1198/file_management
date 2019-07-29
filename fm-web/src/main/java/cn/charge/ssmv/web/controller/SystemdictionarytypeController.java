package cn.charge.ssmv.web.controller;

import cn.charge.ssmv.util.AjaxResult;
import cn.charge.ssmv.util.PageResult;
import cn.charge.ssmv.domain.Systemdictionarytype;
import cn.charge.ssmv.query.SystemdictionarytypeQuery;
import cn.charge.ssmv.service.SystemdictionarytypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: wenbing
 * @Date: 2018/10/17 22:52
 * @Version 1.0
 */
@Controller
@RequestMapping("/systemdictionarytype")
public class SystemdictionarytypeController {

    @Autowired
    private SystemdictionarytypeService systemdictionarytypeService;

    @RequestMapping("/index")
    public String index() {
        return "systemdictionarytype";
    }

    @RequestMapping("/selectAllForList")
    @ResponseBody
    public PageResult list(SystemdictionarytypeQuery systemdictionarytypeQuery){
            return systemdictionarytypeService.selectAllForList(systemdictionarytypeQuery);
     }

    // systemdictionarytype/delete?id
    @RequestMapping("/delete")
    @ResponseBody//json
    public AjaxResult delete(Long id) {
        try {
            systemdictionarytypeService.deleteByPrimaryKey(id);
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("删除失败:" + e.getMessage());
        }
    }

    // systemdictionarytype/saveOrUpdate
    @RequestMapping("/saveOrUpdate")
    @ResponseBody//json
    public AjaxResult saveOrUpdate(Systemdictionarytype systemdictionarytype) {
        if (systemdictionarytype != null) {
            try {
                //判断是否有id,有就是修改,没有就是添加
                if (systemdictionarytype.getId() != null) {
                    //修改
                    systemdictionarytypeService.updateByPrimaryKey(systemdictionarytype);
                } else {
                    //添加
                    systemdictionarytypeService.insert(systemdictionarytype);
                }
                return AjaxResult.success();
            } catch (Exception e) {
                e.printStackTrace();
                return AjaxResult.error("操作失败:"+e.getMessage());
            }
        }
        return null;
    }
}
