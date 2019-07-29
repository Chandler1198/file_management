package cn.charge.ssmv.domain;

public class Permission {
    //设置默认状态为开启状态
    public static final Integer OPen= 0 ;
    //设置状态为关闭状态
    public static final Integer Close = -1 ;
    private Long id;

    private String name;

    private String url;

    private String remarks;

    private Integer state=OPen;

    private Long menuId;

    private String sn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", remarks='" + remarks + '\'' +
                ", state=" + state +
                ", menuId=" + menuId +
                ", sn='" + sn + '\'' +
                '}';
    }
}