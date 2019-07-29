package cn.charge.ssmv.service;

import cn.charge.ssmv.domain.Archive;
import cn.charge.ssmv.domain.User;

public interface ArchiveService extends BaseService<Archive> {

    User selectByRealName(String realName);
	
	
	void updateState(Long id);
}
