package cn.charge.ssmv.realm;


import cn.charge.ssmv.domain.Permission;
import cn.charge.ssmv.domain.User;
import cn.charge.ssmv.service.PermissionService;
import cn.charge.ssmv.service.UserService;
import cn.charge.ssmv.util.MD5Util;
import cn.charge.ssmv.util.SessionUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class FmRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;

//    -----------------------shiro权限登录判断--------------------------------
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();//获取登录用户名字
        //System.out.println(username);
        User user = userService.findOneByName(username);
        //System.out.println(user);
        if(user == null){
            return null;
        }

        ByteSource salt = ByteSource.Util.bytes(MD5Util.SALT);
        return new SimpleAuthenticationInfo(user,user.getPassword(),salt,getName());
    }

    @Override   //---shiro授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = SessionUtil.getSession();//获取登录的账号信息
        //System.out.println("域对象中用户信息"+user);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //根据登录账户查出其对应权限
        Set<Permission> permissionByloginusers = permissionService.findPermissionByloginuser(user.getId());
        Set<String> set = new HashSet<>();
        for (Permission permissionByloginuser : permissionByloginusers) {
            set.add(permissionByloginuser.getSn());
        }
        /*System.out.println(permissionByloginusers);*/
        //授权
        info.setStringPermissions(set);
        return info;
    }
}
