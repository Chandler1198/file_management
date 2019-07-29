package cn.charge.ssmv.service.impl;


import cn.charge.ssmv.domain.Permission;
import cn.charge.ssmv.mapper.PermissionMapper;
import cn.charge.ssmv.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class PermissionServiceimpl extends BaseServiceImpl<Permission> implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Set<Permission> findPermissionByloginuser(Long id) {
        Set<Permission> permissionByloginuser = permissionMapper.findPermissionByloginuser(id);
        return permissionByloginuser;
    }

    @Override
    public List<Permission> selectAll() {
        return permissionMapper.selectAll();
    }
    @Override
    public void updateZT(Long id) {
        Permission per1 = permissionMapper.selectByPrimaryKey(id);
        if (per1.getState().equals(Permission.OPen)) {//r如果产品对象为开启状态
            per1.setState(Permission.Close);
            permissionMapper.updateByPrimaryKey(per1);
        }else if(per1.getState().equals(Permission.Close)){
            per1.setState(Permission.OPen);
            permissionMapper.updateByPrimaryKey(per1);
        }
    }
}
