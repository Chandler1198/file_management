package cn.charge.ssmv.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class LostDamage {
    private Long id;

    private Integer sn;

    private Archive archive;

    private Systemdictionarydetail type;

    private User user;

    private Date date =new Date();

    private String remake;

    @Override
    public String toString() {
        return "LostDamage{" +
                "id=" + id +
                ", sn=" + sn +
                ", archive=" + archive +
                ", type=" + type +
                ", user=" + user +
                ", date=" + date +
                ", remake='" + remake + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSn() {
        return sn;
    }

    public void setSn(Integer sn) {
        this.sn = sn;
    }

    public Archive getArchive() {
        return archive;
    }

    public void setArchive(Archive archive) {
        this.archive = archive;
    }

    public Systemdictionarydetail getType() {
        return type;
    }

    public void setType(Systemdictionarydetail type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")//页面写入数据库时格式化
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")//出参格式
    public Date getDate() {
        return date;
    }

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")//页面写入数据库时格式化
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")//出参格式
    public void setDate(Date date) {
        this.date = date;
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake;
    }
}