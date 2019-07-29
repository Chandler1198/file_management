package cn.charge.ssmv.domain;

public class Department {
    private Long id;

// 部门标识
    private String departIden;
//部门名称
    private String departName;
//  电话
    private String phone;
//传真
    private String fax;
//部门路径
    private String departSrc;
//上级部门编号
    private Long superdepartId;
//部门主管
    private String departManager;


    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departIden='" + departIden + '\'' +
                ", departName='" + departName + '\'' +
                ", phone='" + phone + '\'' +
                ", fax='" + fax + '\'' +
                ", departSrc='" + departSrc + '\'' +
                ", superdepartId=" + superdepartId +
                ", departManager='" + departManager + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartIden() {
        return departIden;
    }

    public void setDepartIden(String departIden) {
        this.departIden = departIden;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getDepartSrc() {
        return departSrc;
    }

    public void setDepartSrc(String departSrc) {
        this.departSrc = departSrc;
    }

    public Long getSuperdepartId() {
        return superdepartId;
    }

    public void setSuperdepartId(Long superdepartId) {
        this.superdepartId = superdepartId;
    }

    public String getDepartManager() {
        return departManager;
    }

    public void setDepartManager(String departManager) {
        this.departManager = departManager;
    }


}