package cn.charge.ssmv.service;


import cn.charge.ssmv.domain.Department;

public interface DepartmentService extends BaseService<Department>{
    void updateStateToDisable(Long id);

}
