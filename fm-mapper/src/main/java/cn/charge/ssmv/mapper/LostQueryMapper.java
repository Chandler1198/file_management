package cn.charge.ssmv.mapper;

import cn.charge.ssmv.domain.LostQuery;
import java.util.List;

public interface LostQueryMapper extends BaseMapper<LostQuery> {

    List<LostQuery> selectAll();

}