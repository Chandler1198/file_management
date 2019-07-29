package cn.charge.ssmv.mapper;

import cn.charge.ssmv.domain.Archive;
import cn.charge.ssmv.domain.User;

public interface ArchiveMapper extends BaseMapper<Archive>{


    User selectByRealName(String realName);
	
	
	int updateByPrimaryStatu(Archive archive);
}