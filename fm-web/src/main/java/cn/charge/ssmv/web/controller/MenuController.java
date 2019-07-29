package cn.charge.ssmv.web.controller;


import cn.charge.ssmv.domain.Menu;
import cn.charge.ssmv.domain.User;
import cn.charge.ssmv.service.MenuService;
import cn.charge.ssmv.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    //跳转制菜单主页
    @RequestMapping("/index")
    public String index(){
        return "main";
    }

    //根据登陆角色动态获取菜单
    @RequestMapping("/findMenuByLoginUser")
    @ResponseBody
    public List<Menu> findMenuByLoginUser(){
        User user = SessionUtil.getSession();
        return menuService.findMenuByLoginuser(user.getId());
    }
}
