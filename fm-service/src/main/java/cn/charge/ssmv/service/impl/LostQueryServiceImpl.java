package cn.charge.ssmv.service.impl;

import cn.charge.ssmv.domain.LostQuery;
import cn.charge.ssmv.mapper.LostQueryMapper;
import cn.charge.ssmv.service.LostQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LostQueryServiceImpl extends BaseServiceImpl<LostQuery> implements LostQueryService {

    @Autowired
    private LostQueryMapper lostQueryMapper;

    @Override
    public List<LostQuery> selectAll() {
        List<LostQuery> lostQueries = lostQueryMapper.selectAll();
        return lostQueries;
    }
}
