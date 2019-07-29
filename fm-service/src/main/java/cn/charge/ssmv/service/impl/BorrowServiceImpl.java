package cn.charge.ssmv.service.impl;


import cn.charge.ssmv.domain.Borrow;
import cn.charge.ssmv.domain.User;
import cn.charge.ssmv.mapper.BorrowMapper;
import cn.charge.ssmv.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wenbing
 * @Date: 2018/10/17 14:45
 * @Version 1.0
 */
@Service
public class BorrowServiceImpl extends BaseServiceImpl<Borrow> implements BorrowService {

    @Autowired
    private BorrowMapper borrowMapper;
    @Override
    public List<Borrow> selectAll() {
        return borrowMapper.selectAll();
    }


    @Override
    public User selectByRealName(String realName) {
        return borrowMapper.selectByRealName(realName);
    }



}
