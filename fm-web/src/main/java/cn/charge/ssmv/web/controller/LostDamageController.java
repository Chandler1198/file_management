package cn.charge.ssmv.web.controller;

import cn.charge.ssmv.domain.LostDamage;
import cn.charge.ssmv.domain.LostQuery;
import cn.charge.ssmv.query.LostDamageQuery;
import cn.charge.ssmv.query.LostQueryQuery;
import cn.charge.ssmv.service.LostDamageService;
import cn.charge.ssmv.service.LostQueryService;
import cn.charge.ssmv.util.AjaxResult;
import cn.charge.ssmv.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/lostdamage")
public class LostDamageController {
    @Autowired
    private LostDamageService lostDamageService;

    @RequestMapping("/index")
    public String index() {
        return "lostdamage";
    }

    @RequestMapping("/selectAllForList")
    @ResponseBody
    public PageResult list(LostDamageQuery lostdamageQuery){
        PageResult pageResult = lostDamageService.selectAllForList(lostdamageQuery);
//        System.out.println(pageResult);
        return  pageResult;
    }

    // lostdamage/delete?id
    @RequestMapping("/delete")
    @ResponseBody//json
    public AjaxResult deleteOne(Long id) {
        try {
            lostDamageService.deleteByPrimaryKey(id);
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("删除失败:" + e.getMessage());
        }
    }

    //批量删除
    @RequestMapping("/deleteList")
    @ResponseBody
    //传入选中的数组ids
    public AjaxResult deleteList(Long[] ids){
        try {
            //通过ids中的数据执行循环删除
            for (Long id : ids) {
                lostDamageService.deleteByPrimaryKey(id);
            }
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("删除失败:" + e.getMessage());
        }
    }


    // lostquery/saveOrUpdate
    @RequestMapping("/saveOrUpdate")
    @ResponseBody//json
    public AjaxResult saveOrUpdate(LostDamage lostDamage) {
        if (lostDamage != null) {
            try {
                //判断是否有id,有就是修改,没有就是添加
                if (lostDamage.getId() != null) {
                    //修改
                    lostDamageService.updateByPrimaryKey(lostDamage);
                } else {
                    //添加
                    lostDamageService.insert(lostDamage);
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
