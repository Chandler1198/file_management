package cn.charge.ssmv.mapper;

import cn.charge.ssmv.domain.Role;
import cn.charge.ssmv.query.BaseQuery;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
    //查詢所有角色
    List<Role> selectAll(BaseQuery baseQuery);
/*
      <!--根据登陆用户查询权限-->
  <select id="findPermissionByloginuser" resultType="java.lang.String" parameterType="cn.leilei.domain.User">
    SELECT u.user_name,u.id,r.id as roleid,r.role_name,p.url,p.`name`
    FROM t_user u JOIN t_user_role ur ON u.id=ur.user_id J
    OIN t_role r ON ur.role_id=r.id
    JOIN t_role_permission rp ON r.id=rp.role_id
    JOIN t_permission p ON rp.permission_id=p.id WHERE u.id=1
  </select>*/

}