package cn.charge.ssmv.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//cookie缓存类   保存账户信息天数(避免多次登录)
public class CookieUtil {

    private static String PATH = "/";

    public static void createCookie(String username,String password,Integer liveTime,HttpServletResponse response){
        //根据前台传递过来的正确数据设置cookie
        Cookie nameCookie = new Cookie("username", username);
        //设置Cookie的有效期
        nameCookie.setMaxAge(60 * 60 * 24 * liveTime);
        nameCookie.setPath(PATH);
        //密码
        Cookie pwdCookie = new Cookie("password", password);
        //设置Cookie的有效期
        pwdCookie.setMaxAge(60 * 60 * 24 * liveTime);
        pwdCookie.setPath(PATH);
        //添加cookie到浏览器
        response.addCookie(nameCookie);
        response.addCookie(pwdCookie);
    }

    /**
     * 清除所有cookie
     */
    public static void clearAll(HttpServletRequest req, HttpServletResponse resp){
        Cookie[] cookies = req.getCookies(); //获取浏览器中的所有cookie
        for (Cookie cookie:cookies){
            cookie.setMaxAge(0);  //设置存活时间为o
            cookie.setPath(PATH);
            resp.addCookie(cookie);
        }
    }
    /**
     * 清除单个cookie
     */
    public static void clear(String name, HttpServletRequest req, HttpServletResponse resp){

        Cookie newCookie=new Cookie("username",null); //假如要删除名称为username的Cookie

        newCookie.setMaxAge(0); //立即删除型

        newCookie.setPath(PATH); //项目所有目录均有效，这句很关键，否则不敢保证删除

        resp.addCookie(newCookie); //重新写入，将覆盖之前的
    }
}
