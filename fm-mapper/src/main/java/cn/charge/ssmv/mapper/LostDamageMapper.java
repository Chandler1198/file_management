package cn.charge.ssmv.mapper;

import cn.charge.ssmv.domain.LostDamage;
import cn.charge.ssmv.domain.LostQuery;

import java.util.List;

public interface LostDamageMapper extends BaseMapper<LostDamage>{
    List<LostDamage> selectAll();
}