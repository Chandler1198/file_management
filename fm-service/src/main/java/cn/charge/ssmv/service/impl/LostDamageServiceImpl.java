package cn.charge.ssmv.service.impl;

import cn.charge.ssmv.domain.LostDamage;
import cn.charge.ssmv.mapper.LostDamageMapper;
import cn.charge.ssmv.service.LostDamageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LostDamageServiceImpl extends BaseServiceImpl<LostDamage> implements LostDamageService {

    @Autowired
    private LostDamageMapper lostDamageMapper;

    @Override
    public List<LostDamage> selectAll() {
        List<LostDamage> lostDamages = lostDamageMapper.selectAll();
        return lostDamages;
    }
}
