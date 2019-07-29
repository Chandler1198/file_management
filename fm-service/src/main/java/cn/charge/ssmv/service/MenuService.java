package cn.charge.ssmv.service;

import cn.charge.ssmv.domain.Menu;

import java.util.List;

public interface MenuService extends BaseService<Menu> {
    List<Menu> selectAll();

    //根据登陆对象查询其对应的菜单
    List<Menu> findMenuByLoginuser(Long id);
}
