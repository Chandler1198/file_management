package cn.charge.ssmv.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainController {

    /**
     * 跳到主界面
     * @return
     */
    @RequestMapping("/index")
    public String main(){
        return "main";
    }


}
