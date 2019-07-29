package cn.charge.ssmv.web.controller;

import cn.charge.ssmv.domain.Filelist;
import cn.charge.ssmv.realm.GetAllFiles;
import cn.charge.ssmv.realm.SystemlogUtil;
import cn.charge.ssmv.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/datarecovery")
public class SystemlogController {
    @Autowired
    private SystemlogUtil systemlogUtil;

    @RequestMapping("/index")
    public String index() {
        return "systemlog";
    }

    @RequestMapping("/back")
    @ResponseBody
    public AjaxResult back() {
        try {
            systemlogUtil.back();
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("保存失败:" + e.getMessage());
        }
    }

    @RequestMapping("/restore")
    @ResponseBody
    public AjaxResult restore(String fileName) {
        try {
            systemlogUtil.restore(fileName);
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("保存失败:" + e.getMessage());
        }
    }
    @RequestMapping("/getfile")
    @ResponseBody
    public List<Filelist> getFileList() {
        //获取所有sql备份文件名
        List<Filelist> fileList = GetAllFiles.getFileList();
        return fileList;
    }
}
