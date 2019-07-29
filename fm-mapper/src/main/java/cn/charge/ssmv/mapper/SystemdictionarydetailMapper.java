package cn.charge.ssmv.mapper;

import cn.charge.ssmv.domain.Systemdictionarydetail;

import java.util.List;

public interface SystemdictionarydetailMapper extends BaseMapper<Systemdictionarydetail>{
    List<Systemdictionarydetail> selectAllById(String ident);
}