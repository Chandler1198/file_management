package cn.charge.ssmv.web.controller;


import cn.charge.ssmv.domain.System;
import cn.charge.ssmv.domain.User;
import cn.charge.ssmv.service.SystemService;
import cn.charge.ssmv.util.AjaxResult;
import cn.charge.ssmv.util.CookieUtil;
import cn.charge.ssmv.util.SessionUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class LoginController {
    @Autowired
    private SystemService systemService;
    /**
     * 跳转到登录界面
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(Model model, HttpServletRequest req, HttpServletResponse response){
        //浏览器中获取cookie列表
        Cookie[] cookies = req.getCookies();
        String cookie_username = null;
        String cookie_password = null;
        //如果存在cookie
        if(cookies!=null){
            try {
                //遍历cookie列表
                for(Cookie cookie : cookies){
                    //获得名字是cookie_username和cookie_password
                    if("username".equals(cookie.getName())){
                        cookie_username = cookie.getValue();
                    }
                    if("password".equals(cookie.getName())){
                        cookie_password = cookie.getValue();
                    }
                }
                //如果cookie中有满足登陆的账户名以及密码，那么就自动登录
                if (cookie_username!=null && cookie_password!=null){
                    cookieLogin(cookie_username,cookie_password);
                    //查询系统配置 给系统赋予名字以及其他动态属性
                    System system = systemService.selectByPrimaryKey(1L);
                    req.getSession().setAttribute("system", system);
                    //动态获取时间
                    Date date = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String format = formatter.format(date);
                    req.getSession().setAttribute("roottime",format);

                    //从定向到首页
                    return "redirect:/main/index";
                }
            }catch (UnknownAccountException e) {
                return "login";
            } catch (IncorrectCredentialsException e) {
                return "login";
            } catch (Exception e) {
                e.printStackTrace();
                return "login";
            }
        }

        return "login";
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult login(String userName, String password, Model model, HttpServletResponse resp,HttpServletRequest req){
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
            Subject subject = SecurityUtils.getSubject();
            if(!subject.isAuthenticated()){//判断当前用户是否登录 布尔值  取反
                subject.login(token);//认证登陆
            }
            //将登陆的用户信息存入域对象之中
            User user = ((User) subject.getPrincipal());//获取当前用户
            SessionUtil.setSession(user);

            //创建2天的cookie
            CookieUtil.createCookie(userName,password,2,resp);
            //查询系统配置 给系统赋予名字以及其他动态属性
            System system = systemService.selectByPrimaryKey(1L);
            req.getSession().setAttribute("system", system);
            //动态获取时间
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = formatter.format(date);
            req.getSession().setAttribute("roottime",format);
            return AjaxResult.success();

        } catch (UnknownAccountException e) {
            e.printStackTrace();
            return AjaxResult.error("账户不存在");
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            return AjaxResult.error("密码错误");
        }catch (AuthenticationException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return AjaxResult.error("网络错误");
    }
    /*注销登陆
     * */
    @RequestMapping("/cookieclear")
    public String logout(HttpServletRequest request,HttpServletResponse response) {
        //清除浏览其中已有cookie
        CookieUtil.clearAll(request,response);
        //清空域对象值
        request.getSession().removeAttribute(SessionUtil.LOGINSESSION);
        /*要从定向得到shiro的注销方法去*/
        return "redirect:/logout";
    }
    /**
     * 认证
     */
    public AjaxResult cookieLogin(String username, String password) {
        //获取用户
        Subject subject = SecurityUtils.getSubject();
        //如果没有认证成功，则进行认证
        if (!subject.isAuthenticated()) {
            //把用户名和密码封装成一个token对象
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            //进行认证
            subject.login(token);
            //获取用户
            User employee = (User) subject.getPrincipal();
            //将用户放入Session
            SessionUtil.setSession(employee);
        }
        return AjaxResult.success();
    }

    /**
     * 人脸识别
     */
    @RequestMapping(value = "/face",method = RequestMethod.GET)
    public String face() {
        return "face";
    }
}
