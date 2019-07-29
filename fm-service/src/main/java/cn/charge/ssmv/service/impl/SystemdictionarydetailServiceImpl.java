package cn.charge.ssmv.service.impl;


import cn.charge.ssmv.domain.Systemdictionarydetail;
import cn.charge.ssmv.mapper.SystemdictionarydetailMapper;
import cn.charge.ssmv.service.SystemdictionarydetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wenbing
 * @Date: 2018/10/17 14:45
 * @Version 1.0
 */
@Service
public class SystemdictionarydetailServiceImpl extends BaseServiceImpl<Systemdictionarydetail> implements SystemdictionarydetailService {
    @Autowired
    private SystemdictionarydetailMapper systemdictionarydetailMapper;
    @Override
    public List<Systemdictionarydetail> selectAllById(String ident) {
        return systemdictionarydetailMapper.selectAllById(ident);
    }
}
