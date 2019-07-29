package cn.charge.ssmv.web.controller;

import cn.charge.ssmv.domain.Archive;
import cn.charge.ssmv.domain.Borrowandarcreturn;
import cn.charge.ssmv.query.BorrowandarcreturnQuery;
import cn.charge.ssmv.service.ArchiveService;
import cn.charge.ssmv.service.BorrowandarcreturnService;
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
@RequestMapping("/archivesstill")
public class BorrowandarcreturnController {

    @Autowired
    private BorrowandarcreturnService borrowandarcreturnService;
    @Autowired
    private ArchiveService archiveService;


    @RequestMapping("/index")
    public String index() {
        return "archivesstill";
    }

    @RequestMapping("/selectAllForList")
    @ResponseBody
    public PageResult list(BorrowandarcreturnQuery borrowandarcreturnQuery){

        PageResult pageResult = borrowandarcreturnService.selectAllForList(borrowandarcreturnQuery);
        return pageResult;

    }

    // borrowandarcreturn/delete?id
    @RequestMapping("/delete")
    @ResponseBody//json
    public AjaxResult delete(Long id,Long archiveid) {
        System.out.println("返回ID"+id);
        System.out.println("档案id"+archiveid);

        try {

            borrowandarcreturnService.deleteByPrimaryKey(id);
            Archive archive = archiveService.selectByPrimaryKey(archiveid);
            if (archive.getBorrowStatu()==1){
                archiveService.updateState(archiveid);
            }
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("删除失败:" + e.getMessage());
        }
    }

    // borrowandarcreturn/saveOrUpdate
    @RequestMapping("/saveOrUpdate")
    @ResponseBody//json
    public AjaxResult saveOrUpdate(Borrowandarcreturn borrowandarcreturn) {
        if (borrowandarcreturn != null) {
            try {
                //判断是否有id,有就是修改,没有就是添加
                if (borrowandarcreturn.getId() != null) {
                    //修改
                    borrowandarcreturnService.updateByPrimaryKey(borrowandarcreturn);
                } else {
                    //添加
                    borrowandarcreturnService.insert(borrowandarcreturn);
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
