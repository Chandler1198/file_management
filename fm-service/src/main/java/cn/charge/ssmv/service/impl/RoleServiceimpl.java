package cn.charge.ssmv.service.impl;



import cn.charge.ssmv.domain.Permission;
import cn.charge.ssmv.domain.Role;
import cn.charge.ssmv.mapper.RoleMapper;
import cn.charge.ssmv.query.BaseQuery;
import cn.charge.ssmv.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceimpl extends BaseServiceImpl<Role> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;


    @Override
    public List<Role> selectAll(BaseQuery baseQuery) {
        return roleMapper.selectAll(baseQuery);
    }
    @Override
    public void updateZT(Long id) {
        Role role1 = roleMapper.selectByPrimaryKey(id);
        if (role1.getState().equals(Role.OPen)) {//r如果产品对象为开启状态
            role1.setState(Role.Close);
            roleMapper.updateByPrimaryKey(role1);
        }else if(role1.getState().equals(Role.Close)){//r如果产品对象为关闭状态
            role1.setState(Role.OPen);
            roleMapper.updateByPrimaryKey(role1);
        }
    }
}
