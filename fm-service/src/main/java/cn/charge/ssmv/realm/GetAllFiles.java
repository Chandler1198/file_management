package cn.charge.ssmv.realm;

import cn.charge.ssmv.domain.Filelist;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GetAllFiles {

    public static List<Filelist> getFileList() {
        File file = new File("D:\\");
        File[] fileList = file.listFiles();
        List<Filelist> list = new ArrayList<>();
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isFile()) {
                String fileName = fileList[i].getName();
                if(".sql".equals(fileName.substring(fileName.lastIndexOf(".")))){
                    Filelist filelist = new Filelist();
                    filelist.setFlieName(fileName);
                    list.add(filelist);
                }
            }
        }
        return list;
    }
}
