package cn.charge.ssmv.service.impl;

import cn.charge.ssmv.domain.Archive;
import cn.charge.ssmv.domain.User;
import cn.charge.ssmv.mapper.ArchiveMapper;
import cn.charge.ssmv.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ArchiveServiceImpl extends BaseServiceImpl<Archive> implements ArchiveService {

    @Autowired
    private ArchiveMapper archiveMapper;

    @Override
    public User selectByRealName(String realName) {
        return archiveMapper.selectByRealName(realName);
    }
	
	
	    @Override
    public void updateState(Long id) {
        //查询对象
        Archive archive = archiveMapper.selectByPrimaryKey(id);
        //判断状态
        if(archive.getBorrowStatu() == 0){
            //修改状态
            archive.setBorrowStatu(1);
            archiveMapper.updateByPrimaryStatu(archive);
        }else if (archive.getBorrowStatu() == 1){
            archive.setBorrowStatu(0);
            archiveMapper.updateByPrimaryStatu(archive);
        }
    }
}
