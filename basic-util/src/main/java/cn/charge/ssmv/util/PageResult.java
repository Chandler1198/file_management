package cn.charge.ssmv.util;

import java.util.ArrayList;
import java.util.List;

public class PageResult {
    private Long total = 0L;
    private Integer pageNum;
    private List rows = new ArrayList();

    public PageResult() {
    }

    public PageResult(Long total, Integer pageNum, List rows) {
        this.total = total;
        this.pageNum = pageNum;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
