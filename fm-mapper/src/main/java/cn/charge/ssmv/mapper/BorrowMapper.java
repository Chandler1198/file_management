package cn.charge.ssmv.mapper;

import cn.charge.ssmv.domain.Borrow;
import cn.charge.ssmv.domain.User;

import java.util.List;

public interface BorrowMapper extends BaseMapper<Borrow> {

    List<Borrow> selectAll();


    User selectByRealName(String realName);
}