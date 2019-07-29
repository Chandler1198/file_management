package cn.charge.ssmv.service.impl;

import cn.charge.ssmv.domain.Menu;
import cn.charge.ssmv.mapper.MenuMapper;
import cn.charge.ssmv.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MenuServiceimpl extends BaseServiceImpl<Menu> implements MenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Override
    public List<Menu> selectAll() {
        return menuMapper.selectAll();
    }

    @Override
    public List<Menu> findMenuByLoginuser(Long id) {

        //存放所有的一级菜单
        List<Menu> parents = new ArrayList<>();
        List<Menu> menuchildens = menuMapper.findMenuByLoginuser(id);
        for (Menu menu : menuchildens) {
            //获取一级菜单
            Menu father = menuMapper.selectByPrimaryKey(menu.getParentId());
            if(!parents.contains(father)){
                parents.add(father);
            }
            father.getChildren().add(menu);
        }
        return parents;
    }

}
