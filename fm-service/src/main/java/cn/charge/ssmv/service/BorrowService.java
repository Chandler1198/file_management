package cn.charge.ssmv.service;

import cn.charge.ssmv.domain.Borrow;
import cn.charge.ssmv.domain.User;

import java.util.List;

public interface BorrowService extends BaseService<Borrow> {

    List<Borrow> selectAll();



    User selectByRealName(String realName);
}
