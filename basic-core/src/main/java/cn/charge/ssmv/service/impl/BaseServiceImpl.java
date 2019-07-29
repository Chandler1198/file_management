package cn.charge.ssmv.service.impl;

import cn.charge.ssmv.mapper.BaseMapper;
import cn.charge.ssmv.query.BaseQuery;
import cn.charge.ssmv.service.BaseService;
import cn.charge.ssmv.util.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    private BaseMapper<T> baseMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return baseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(T record) {
        return baseMapper.insert(record);
    }

    @Override
    public T selectByPrimaryKey(Long id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult selectAllForList(BaseQuery baseQuery) {
        PageHelper.startPage(baseQuery.getPage(), baseQuery.getRows());
        Page page = (Page) baseMapper.selectAllForList(baseQuery);
        return new PageResult(page.getTotal(),page.getPageNum(),page.getResult());
    }

    @Override
    public int updateByPrimaryKey(T record) {
        return baseMapper.updateByPrimaryKey(record);
    }
}
