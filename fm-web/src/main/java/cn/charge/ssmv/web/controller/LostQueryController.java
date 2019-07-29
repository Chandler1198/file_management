package cn.charge.ssmv.web.controller;

import cn.charge.ssmv.domain.LostQuery;
import cn.charge.ssmv.query.LostQueryQuery;
import cn.charge.ssmv.service.LostQueryService;
import cn.charge.ssmv.util.AjaxResult;
import cn.charge.ssmv.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/lostquery")
public class LostQueryController {
    @Autowired
    private LostQueryService lostQueryService;

    @RequestMapping("/index")
    public String index() {
        return "lostquery";
    }

    @RequestMapping("/selectAllForList")
    @ResponseBody
    public PageResult list(LostQueryQuery lostqueryQuery){
        PageResult pageResult = lostQueryService.selectAllForList(lostqueryQuery);
//        System.out.println(pageResult);
        return  pageResult;
    }

    // lostquery/delete?id
    @RequestMapping("/delete")
    @ResponseBody//json
    public AjaxResult deleteOne(Long id) {
        try {
            lostQueryService.deleteByPrimaryKey(id);
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
                lostQueryService.deleteByPrimaryKey(id);
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
    public AjaxResult saveOrUpdate(LostQuery lostquery) {
        if (lostquery != null) {
            try {
                //判断是否有id,有就是修改,没有就是添加
                if (lostquery.getId() != null) {
                    //修改
                    lostQueryService.updateByPrimaryKey(lostquery);
                } else {
                    //添加
                    lostQueryService.insert(lostquery);
                }
                return AjaxResult.success();
            } catch (Exception e) {
                e.printStackTrace();
                return AjaxResult.error("操作失败:"+e.getMessage());
            }
        }
        return null;
    }

    @RequestMapping("/i")
    public String index2() {
        return "mainChars";
    }
}
