package cn.charge.ssmv.service.impl;

import cn.charge.ssmv.domain.System;
import cn.charge.ssmv.service.SystemService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SystemServiceImpl extends BaseServiceImpl<System> implements SystemService {


    @Override
    public void updateStateToDisable(Long id) {

    }
}
