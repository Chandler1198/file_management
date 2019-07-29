package cn.charge.ssmv.mapper;

import cn.charge.ssmv.domain.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {


    List<Menu> selectAll();

    //根据登陆对象查询其对应的菜单
    List<Menu> findMenuByLoginuser(Long id);

}