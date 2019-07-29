package cn.charge.ssmv.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class LostQuery {
    private Long id;

    private Long sn;

    private Archive archive;

    private User user;

    private Date date =new Date();

    private String remake;

    private Systemdictionarydetail type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSn() {
        return sn;
    }

    public void setSn(Long sn) {
        this.sn = sn;
    }

    public Archive getArchive() {
        return archive;
    }

    public void setArchive(Archive archive) {
        this.archive = archive;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getDate() {
        return date;
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public void setDate(Date date) {
        this.date = date;
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake;
    }

    public Systemdictionarydetail getType() {
        return type;
    }

    public void setType(Systemdictionarydetail type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "LostQuery{" +
                "id=" + id +
                ", sn=" + sn +
                ", archive=" + archive +
                ", user=" + user +
                ", date=" + date +
                ", remake='" + remake + '\'' +
                ", type=" + type +
                '}';
    }
}