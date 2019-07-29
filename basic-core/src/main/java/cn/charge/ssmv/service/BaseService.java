package cn.charge.ssmv.service;

import cn.charge.ssmv.query.BaseQuery;
import cn.charge.ssmv.util.PageResult;

import java.util.List;

public interface BaseService<T> {
    
    int deleteByPrimaryKey(Long id);

    int insert(T record);

    T selectByPrimaryKey(Long id);

    PageResult selectAllForList(BaseQuery baseQuery);

    int updateByPrimaryKey(T record);
}
