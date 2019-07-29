package cn.charge.ssmv.web.controller;

import cn.charge.ssmv.domain.Archive;
import cn.charge.ssmv.domain.User;
import cn.charge.ssmv.query.ArchiveQuery;
import cn.charge.ssmv.service.ArchiveService;
import cn.charge.ssmv.service.impl.ArchiveServiceImpl;
import cn.charge.ssmv.util.AjaxResult;
import cn.charge.ssmv.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/archive")
public class ArchiveController {

    @Autowired
    private ArchiveService archiveServiceImpl;

    @RequestMapping("/index")
    public String index(){
        return "archive";
    }

    @RequestMapping("/selectAll")
    @ResponseBody
    public PageResult select(ArchiveQuery qo){
        PageResult pageResult = archiveServiceImpl.selectAllForList(qo);
        return pageResult;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public AjaxResult delete(Long id){
        try {
            archiveServiceImpl.deleteByPrimaryKey(id);
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error(e.getMessage());
        }
    }

    @RequestMapping("/batchDelete")
    @ResponseBody
    public AjaxResult batchDelete(Long[] ids){
        try {
            for (Long id : ids) {
                archiveServiceImpl.deleteByPrimaryKey(id);
            }
            return AjaxResult.success();
        } catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error(e.getMessage());
        }
    }


    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult save(Archive archive){
        System.out.println(archive);
        String realName = archive.getArcUser().getRealName();
        System.out.println(realName);
        User user = archiveServiceImpl.selectByRealName(realName);
        archive.setArcUser(user);
        System.out.println(archive);
        try {
            if(archive.getId() == null){
                archiveServiceImpl.insert(archive);
            }else{
                archiveServiceImpl.updateByPrimaryKey(archive);
            }
            return AjaxResult.success();
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error(e.getMessage());
        }
    }
}
