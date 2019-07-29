package cn.charge.ssmv.mapper;

import cn.charge.ssmv.domain.LostQuery;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;


public class LostQueryMapperTest {

    @Autowired
    private LostQueryMapper lostQueryMapper;

    @Test
    public void selectAll() {
        List<LostQuery> lostQueries = lostQueryMapper.selectAll();
        System.out.println(lostQueries);


    }
}