package cn.charge.ssmv.realm;


import cn.charge.ssmv.domain.Permission;
import cn.charge.ssmv.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//-自定义过滤器 动态注入权限--
public class Powerfiltershiro {
    @Autowired
    private PermissionService permissionService;
    public Map<String,String>PowerMap(){
        List<Permission> permissions = permissionService.selectAll();
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/static/**", "anon");
        map.put("/login", "anon");
        map.put("/logout", "logout");
        for (Permission permission : permissions) {
            map.put(permission.getUrl(), "Perms[" + permission.getSn() + "]");
        }
        map.put("/**", "authc");
        return map;
    }
}
