package cn.charge.ssmv.service;

import cn.charge.ssmv.domain.LostDamage;

import java.util.List;

public interface LostDamageService extends BaseService<LostDamage> {
    List<LostDamage> selectAll();
}
