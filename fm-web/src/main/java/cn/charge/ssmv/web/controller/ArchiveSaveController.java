package cn.charge.ssmv.web.controller;

import cn.charge.ssmv.query.ArchiveQuery;
import cn.charge.ssmv.service.ArchiveService;
import cn.charge.ssmv.util.AjaxResult;
import cn.charge.ssmv.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/archivesin")
public class ArchiveSaveController {

    @Autowired
    private ArchiveService archiveServiceImpl;

    @RequestMapping("/index")
    public String index(){
        return "archive_save";
    }

}
