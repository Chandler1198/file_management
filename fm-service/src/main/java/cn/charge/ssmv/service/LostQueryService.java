package cn.charge.ssmv.service;

import cn.charge.ssmv.domain.LostQuery;

import java.util.List;

public interface LostQueryService extends BaseService<LostQuery> {
    List<LostQuery> selectAll();
}
