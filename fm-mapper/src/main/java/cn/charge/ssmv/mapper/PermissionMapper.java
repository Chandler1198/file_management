package cn.charge.ssmv.mapper;

import cn.charge.ssmv.domain.Permission;

import java.util.List;
import java.util.Set;

public interface PermissionMapper extends BaseMapper<Permission> {
    Set<Permission> findPermissionByloginuser(Long id);
    //查询所有
    List<Permission> selectAll();

    //删除多条数据
   /* void moreDelete(List<Long> ids);*/
}