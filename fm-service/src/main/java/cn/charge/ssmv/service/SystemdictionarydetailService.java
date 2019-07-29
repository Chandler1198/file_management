package cn.charge.ssmv.service;

import cn.charge.ssmv.domain.Systemdictionarydetail;

import java.util.List;

public interface SystemdictionarydetailService extends BaseService<Systemdictionarydetail> {
    List<Systemdictionarydetail> selectAllById(String ident);

}
