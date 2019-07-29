package cn.charge.ssmv.mapper;


import cn.charge.ssmv.domain.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User>{

    User findOneByName(String username);

     List<User> selectAll();


}