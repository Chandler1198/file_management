package cn.charge.ssmv.domain;

public class Systemdictionarydetail {
    private Long id;

    private String detailName;

    private Long typeId;

    private Long catalogNum;

    private Integer state = 0;

    private Systemdictionarytype systemdictionarytype;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetailName() {
        return detailName;
    }

    public void setDetailName(String detailName) {
        this.detailName = detailName;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getCatalogNum() {
        return catalogNum;
    }

    public void setCatalogNum(Long catalogNum) {
        this.catalogNum = catalogNum;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Systemdictionarytype getSystemdictionarytype() {
        return systemdictionarytype;
    }

    public void setSystemdictionarytype(Systemdictionarytype systemdictionarytype) {
        this.systemdictionarytype = systemdictionarytype;
    }
}