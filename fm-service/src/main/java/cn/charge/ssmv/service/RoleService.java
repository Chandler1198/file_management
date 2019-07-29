package cn.charge.ssmv.service;


import cn.charge.ssmv.domain.Role;
import cn.charge.ssmv.query.BaseQuery;

import java.util.List;

public interface RoleService extends BaseService<Role> {
    //查詢所有角色
    List<Role> selectAll(BaseQuery baseQuery);
    //删除角色  （修改角色状态为禁用
    void updateZT(Long id);
}
