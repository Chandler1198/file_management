package cn.charge.ssmv.service.impl;


import cn.charge.ssmv.domain.User;
import cn.charge.ssmv.mapper.UserMapper;
import cn.charge.ssmv.service.UserService;
import cn.charge.ssmv.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceimpl extends BaseServiceImpl<User> implements UserService {
    @Autowired
    public UserMapper userMapper;


    @Override
    public int insert(User user) {
        if (user.getId()==null){
            user.setPassword(MD5Util.createMd5(user.getPassword()));
        }
        int insert = userMapper.insert(user);
        return insert;
    }

    @Override
    public void updateStateToDisable(Long id) {
        //1.查询对象
        User user = userMapper.selectByPrimaryKey(id);

        //判断状态
        if(user.getState() == User.STATE_NORMAL){
            //2.修改状态
            user.setState(User.STATE_DISABLED);
            updateByPrimaryKey(user);
        }
    }

    //覆写根据名字查询用户
    @Override
    public User findOneByName(String username) {
        return userMapper.findOneByName(username);
    }
}
