package cn.charge.ssmv.domain;

import java.util.ArrayList;
import java.util.List;

public class Role {
    //设置默认状态为开启状态
    public static final Integer OPen= 0 ;
    //设置状态为关闭状态
    public static final Integer Close = -1 ;
    private Long id;

    private Integer roleId;

    private String roleName;

    private String remarks;

    private Integer state=OPen;

    //多对多 集合 多个角色对应多个资源
    private List<Permission> permissions = new ArrayList<>();


    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", remarks='" + remarks + '\'' +
                ", state=" + state +
                ", permissions=" + permissions +
                '}';
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
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
}