package cn.charge.ssmv.query;

/**
 * @Author: wenbing
 * @Date: 2018/10/18 0:18
 * @Version 1.0
 */
public class ArcreturnQuery extends BaseQuery {

    public void setCurrentPage(Integer currentPage){
        this.setPage(currentPage);
    }


    public void setPageSize(Integer pageSize){
        this.setRows(pageSize);
    }


}
