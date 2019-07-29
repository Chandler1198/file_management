package cn.charge.ssmv.util;

import cn.charge.ssmv.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class SessionUtil {
    public static final String LOGINSESSION = "loginuser";

    //将登陆对象存入域对象之中
    public static void setSession(User user){//将登陆用户存入域对象
        Subject subject = SecurityUtils.getSubject();//获取登陆对象
        subject.getSession().setAttribute(LOGINSESSION,user);
    }

    //Session中获取当前登陆对象
    public static User getSession(){//获取登陆对象域对象信息
        Subject subject = SecurityUtils.getSubject();//获取登陆对象
        return (User) subject.getSession().getAttribute(LOGINSESSION);
    }
}
