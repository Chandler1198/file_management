package cn.charge.ssmv.service;


import cn.charge.ssmv.domain.User;

public interface UserService extends BaseService<User>{
    void updateStateToDisable(Long id);

    User findOneByName(String username);

}
