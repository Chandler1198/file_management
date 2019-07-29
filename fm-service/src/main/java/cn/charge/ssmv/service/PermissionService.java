package cn.charge.ssmv.service;


import cn.charge.ssmv.domain.Permission;

import java.util.List;
import java.util.Set;

public interface PermissionService extends BaseService<Permission> {

    /*根据登陆对象查询他的对应资源权限*/

/*    @Select("    SELECT u.user_name,u.id,r.id as roleid,r.role_name,p.url,p.`name`\n" +
            "    FROM t_user u JOIN t_user_role ur ON u.id=ur.user_id\n" +
            "    JOIN t_role r ON ur.role_id=r.id\n" +
            "    JOIN t_role_permission rp ON r.id=rp.role_id\n" +
            "    JOIN t_permission p ON rp.permission_id=p.id WHERE u.id=1")*/
    Set<Permission> findPermissionByloginuser(Long id);
    //查询所有资源信息
    List<Permission> selectAll();

    void updateZT(Long id);

    //删除多条数据
    /*void moreDelete(List<Long> ids);*/
}
