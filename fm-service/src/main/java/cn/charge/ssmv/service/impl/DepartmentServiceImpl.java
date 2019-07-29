package cn.charge.ssmv.service.impl;


import cn.charge.ssmv.domain.Department;
import cn.charge.ssmv.mapper.DepartmentMapper;
import cn.charge.ssmv.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;


    @Override
    public void updateStateToDisable(Long id) {

    }
}
