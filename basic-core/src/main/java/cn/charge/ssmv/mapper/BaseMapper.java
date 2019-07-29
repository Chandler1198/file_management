package cn.charge.ssmv.mapper;


import cn.charge.ssmv.query.BaseQuery;

import java.util.List;

public interface BaseMapper<T> {
    int deleteByPrimaryKey(Long id);

    int insert(T record);

    T selectByPrimaryKey(Long id);

    List<T> selectAllForList(BaseQuery baseQuery);

    int updateByPrimaryKey(T record);
}
