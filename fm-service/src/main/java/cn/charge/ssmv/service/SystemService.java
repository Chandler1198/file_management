package cn.charge.ssmv.service;

import cn.charge.ssmv.domain.System;

public interface SystemService extends BaseService<System>{
    void updateStateToDisable(Long id);

}


