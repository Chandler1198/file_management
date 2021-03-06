package cn.charge.ssmv.query;

import org.springframework.util.StringUtils;

public class RoleQuery extends BaseQuery {
    //前台页面传入的值 查询的角色名）
    private String keyname;

    public String getKeyname() {
        if(StringUtils.hasLength(keyname)){
            return keyname.trim();
        }
        return null;
    }

    public void setKeyname(String keyname) {
        this.keyname = keyname;
    }
    public void setCurrentPage(Integer currentPage){
        this.setPage(currentPage);
    }


    public void setPageSize(Integer pageSize){
        this.setRows(pageSize);
    }
}
