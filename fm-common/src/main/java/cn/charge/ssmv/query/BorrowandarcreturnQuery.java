package cn.charge.ssmv.query;


public class BorrowandarcreturnQuery extends BaseQuery {

    public void setCurrentPage(Integer currentPage){
        this.setPage(currentPage);
    }


    public void setPageSize(Integer pageSize){
        this.setRows(pageSize);
    }

}
