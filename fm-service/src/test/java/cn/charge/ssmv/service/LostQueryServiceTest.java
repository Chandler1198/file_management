package cn.charge.ssmv.service;

import cn.charge.ssmv.domain.LostDamage;
import cn.charge.ssmv.domain.LostQuery;
import cn.charge.ssmv.mapper.LostDamageMapper;
import cn.charge.ssmv.mapper.LostQueryMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class LostQueryServiceTest {

    @Autowired
    private LostQueryMapper lostQueryMapper;
    @Test
    public void selectAll() {
        List<LostQuery> lostDamages = lostQueryMapper.selectAll();
        System.out.println(lostDamages);
    }
}