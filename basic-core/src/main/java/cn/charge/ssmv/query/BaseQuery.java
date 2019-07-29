package cn.charge.ssmv.query;

/**
 * 查询条件 如果有特殊条件 自己写继承类
 */
public class BaseQuery {
    private Integer page = 1;
    private Integer rows = 10;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
