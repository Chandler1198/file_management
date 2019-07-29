package cn.charge.ssmv.web.controller;

import cn.charge.ssmv.domain.Archive;
import cn.charge.ssmv.domain.Borrow;
import cn.charge.ssmv.domain.Borrowandarcreturn;
import cn.charge.ssmv.domain.User;
import cn.charge.ssmv.query.ArchiveQuery;
import cn.charge.ssmv.query.BorrowQuery;
import cn.charge.ssmv.service.ArchiveService;
import cn.charge.ssmv.service.BorrowService;
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
@RequestMapping("/archivesbor")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @Autowired
    private ArchiveService archiveServiceImpl;
    @Autowired
    private BorrowandarcreturnService borrowandarcreturnService;

    @RequestMapping("/index")
    public String index() {
        return "archivesbor";
    }


    @RequestMapping("/selectAll")
    @ResponseBody
    public PageResult select(ArchiveQuery qo){
        PageResult pageResult = archiveServiceImpl.selectAllForList(qo);
        return pageResult;
    }

    @RequestMapping("/selectAllForList")
    @ResponseBody
    public PageResult list(BorrowQuery borrowQuery){
        PageResult pageResult = borrowService.selectAllForList(borrowQuery);
        return pageResult;
    }


    // borrow/delete?id
    @RequestMapping("/delete")
    @ResponseBody//json
    public AjaxResult delete(Long id) {
        try {
            borrowService.deleteByPrimaryKey(id);
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("删除失败:" + e.getMessage());
        }
    }

    // borrow/saveOrUpdate
    @RequestMapping("/saveOrUpdate")
    @ResponseBody//json
    public AjaxResult saveOrUpdate(Borrow borrow, Long id) {
        if (borrow != null) {
            try {
                //判断是否有id,有就是修改,没有就是添加
                if (borrow.getId() != null) {
                    //修改
                    borrowService.updateByPrimaryKey(borrow);
                } else {
                    //添加
                    borrowService.insert(borrow);
                }
                return AjaxResult.success();
            } catch (Exception e) {
                e.printStackTrace();
                return AjaxResult.error("操作失败:"+e.getMessage());
            }
        }
        return null;
    }

    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult save(Borrow borrow, Long id){
        String realName = borrow.getArcUser().getRealName();
        User user = borrowService.selectByRealName(realName);
        borrow.setArcUser(user);
        try {
            Archive archive = archiveServiceImpl.selectByPrimaryKey(id);
            if (archive.getBorrowStatu()==0){
                borrow.setBorrowState(1);
                borrowService.insert(borrow);
                Long id1 = borrow.getId();
                archiveServiceImpl.updateState(id);
                Borrowandarcreturn borrowandarcreturn = new Borrowandarcreturn();
                borrowandarcreturn.setArchiveId(id);
                borrowandarcreturn.setBorrowId(id1);
                borrowandarcreturnService.insert(borrowandarcreturn);

            }else if (archive.getBorrowStatu()==1){
                return AjaxResult.error("已经借阅了");


            }


            return AjaxResult.success();

        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error(e.getMessage());
        }
    }


}
