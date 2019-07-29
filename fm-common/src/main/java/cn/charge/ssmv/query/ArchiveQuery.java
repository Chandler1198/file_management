package cn.charge.ssmv.query;

public class ArchiveQuery extends BaseQuery {

    public void setCurrentPage(Integer currentPage){
        this.setPage(currentPage);
    }


    public void setPageSize(Integer pageSize){
        this.setRows(pageSize);
    }


}
