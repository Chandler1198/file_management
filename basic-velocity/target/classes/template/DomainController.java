package cn.charge.ssmv.web.controller;

import cn.charge.ssmv.util.AjaxResult;
import cn.charge.ssmv.util.PageResult;
import cn.charge.ssmv.domain.${Domain};
import cn.charge.ssmv.query.${Domain}Query;
import cn.charge.ssmv.service.${Domain}Service;
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
@RequestMapping("/${domain}")
public class ${Domain}Controller {

    @Autowired
    private ${Domain}Service ${domain}Service;

    @RequestMapping("/index")
    public String index() {
        return "${domain}";
    }

    @RequestMapping("/selectAllForList")
    @ResponseBody
    public PageResult list(${Domain}Query ${domain}Query){
            return ${domain}Service.selectAllForList(${domain}Query);
     }

    // ${domain}/delete?id
    @RequestMapping("/delete")
    @ResponseBody//json
    public AjaxResult delete(Long id) {
        try {
            ${domain}Service.deleteByPrimaryKey(id);
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("删除失败:" + e.getMessage());
        }
    }

    // ${domain}/saveOrUpdate
    @RequestMapping("/saveOrUpdate")
    @ResponseBody//json
    public AjaxResult saveOrUpdate(${Domain} ${domain}) {
        if (${domain} != null) {
            try {
                //判断是否有id,有就是修改,没有就是添加
                if (${domain}.getId() != null) {
                    //修改
                    ${domain}Service.updateByPrimaryKey(${domain});
                } else {
                    //添加
                    ${domain}Service.insert(${domain});
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
