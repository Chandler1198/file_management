package cn.charge.ssmv.web.controller;

import cn.charge.ssmv.domain.Arcreturn;
import cn.charge.ssmv.query.ArcreturnQuery;
import cn.charge.ssmv.query.BorrowQuery;
import cn.charge.ssmv.service.ArcreturnService;
import cn.charge.ssmv.service.BorrowService;
import cn.charge.ssmv.util.AjaxResult;
import cn.charge.ssmv.util.PageResult;
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
@RequestMapping("/borrec")
public class ArcreturnController {

    @Autowired
    private ArcreturnService arcreturnService;

    @Autowired
    private BorrowService borrowService;

    @RequestMapping("/index")
    public String index() {
        return "borrec";
    }



    @RequestMapping("/selectAll")
    @ResponseBody
    public PageResult list(BorrowQuery borrowQuery){
        PageResult pageResult = borrowService.selectAllForList(borrowQuery);
        return pageResult;
    }

    @RequestMapping("/selectAllForList")
    @ResponseBody
    public PageResult list(ArcreturnQuery arcreturnQuery){
        PageResult pageResult = arcreturnService.selectAllForList(arcreturnQuery);
        return pageResult;
    }



    // arcreturn/delete?id
    @RequestMapping("/delete")
    @ResponseBody//json
    public AjaxResult delete(Long id) {
        try {
            arcreturnService.deleteByPrimaryKey(id);
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("删除失败:" + e.getMessage());
        }
    }

    // arcreturn/saveOrUpdate
    @RequestMapping("/saveOrUpdate")
    @ResponseBody//json
    public AjaxResult saveOrUpdate(Arcreturn arcreturn) {
        if (arcreturn != null) {
            try {
                //判断是否有id,有就是修改,没有就是添加
                if (arcreturn.getId() != null) {
                    //修改
                    arcreturnService.updateByPrimaryKey(arcreturn);
                } else {
                    //添加
                    arcreturnService.insert(arcreturn);
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
