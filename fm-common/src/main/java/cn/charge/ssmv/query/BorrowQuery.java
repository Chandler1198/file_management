package cn.charge.ssmv.query;

import org.springframework.util.StringUtils;

/**
 * @Author: wenbing
 * @Date: 2018/10/18 0:18
 * @Version 1.0
 */
public class BorrowQuery extends BaseQuery {

    //关键字查询
    private String keyword;

    public String getKeyword() {
        if(StringUtils.hasLength(keyword)){
            return keyword.trim();
        }
        return null;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


    public void setCurrentPage(Integer currentPage){
        this.setPage(currentPage);
    }


    public void setPageSize(Integer pageSize){
        this.setRows(pageSize);
    }


}
