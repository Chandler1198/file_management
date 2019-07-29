package cn.charge.ssmv.domain;



import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Archive {
    private Long id;

    private String arcNum;

    private String arcType;

    private String arcPath;

    private String arcTitle;

    private User arcUser;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")//页面写入数据库时格式化
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")//出参格式
    private Date createdDate;

    private String modifyUser;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")//页面写入数据库时格式化
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")//出参格式
    private Date modifyDate;

    private Integer borrowStatu;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")//页面写入数据库时格式化
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")//出参格式
    private Date expirationTime;

    private Integer expirationNotice;

    private Integer notificationDay;

    private String contentArchive;

    private String contentValidity;

    private Integer commonlyUsed;

    private Integer docNum;

    private String comDepart;

    private String publishUnit;

    private String publisher;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")//页面写入数据库时格式化
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")//出参格式
    private Date approvalTime;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")//页面写入数据库时格式化
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")//出参格式
    private Date identificaTime;

    private String productNum;

    private String rawNumber;

    private Integer number;

    private Integer page;

    private String patentNo;

    private String inspectPerson;

    private Integer state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArcNum() {
        return arcNum;
    }

    public void setArcNum(String arcNum) {
        this.arcNum = arcNum;
    }

    public String getArcType() {
        return arcType;
    }

    public void setArcType(String arcType) {
        this.arcType = arcType;
    }

    public String getArcPath() {
        return arcPath;
    }

    public void setArcPath(String arcPath) {
        this.arcPath = arcPath;
    }

    public String getArcTitle() {
        return arcTitle;
    }

    public void setArcTitle(String arcTitle) {
        this.arcTitle = arcTitle;
    }

    public User getArcUser() {
        return arcUser;
    }

    public void setArcUser(User arcUser) {
        this.arcUser = arcUser;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getBorrowStatu() {
        return borrowStatu;
    }

    public void setBorrowStatu(Integer borrowStatu) {
        this.borrowStatu = borrowStatu;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Integer getExpirationNotice() {
        return expirationNotice;
    }

    public void setExpirationNotice(Integer expirationNotice) {
        this.expirationNotice = expirationNotice;
    }

    public Integer getNotificationDay() {
        return notificationDay;
    }

    public void setNotificationDay(Integer notificationDay) {
        this.notificationDay = notificationDay;
    }

    public String getContentArchive() {
        return contentArchive;
    }

    public void setContentArchive(String contentArchive) {
        this.contentArchive = contentArchive;
    }

    public String getContentValidity() {
        return contentValidity;
    }

    public void setContentValidity(String contentValidity) {
        this.contentValidity = contentValidity;
    }

    public Integer getCommonlyUsed() {
        return commonlyUsed;
    }

    public void setCommonlyUsed(Integer commonlyUsed) {
        this.commonlyUsed = commonlyUsed;
    }

    public Integer getDocNum() {
        return docNum;
    }

    public void setDocNum(Integer docNum) {
        this.docNum = docNum;
    }

    public String getComDepart() {
        return comDepart;
    }

    public void setComDepart(String comDepart) {
        this.comDepart = comDepart;
    }

    public String getPublishUnit() {
        return publishUnit;
    }

    public void setPublishUnit(String publishUnit) {
        this.publishUnit = publishUnit;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
    }

    public Date getIdentificaTime() {
        return identificaTime;
    }

    public void setIdentificaTime(Date identificaTime) {
        this.identificaTime = identificaTime;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getRawNumber() {
        return rawNumber;
    }

    public void setRawNumber(String rawNumber) {
        this.rawNumber = rawNumber;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getPatentNo() {
        return patentNo;
    }

    public void setPatentNo(String patentNo) {
        this.patentNo = patentNo;
    }

    public String getInspectPerson() {
        return inspectPerson;
    }

    public void setInspectPerson(String inspectPerson) {
        this.inspectPerson = inspectPerson;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Archive{" +
                "id=" + id +
                ", arcNum='" + arcNum + '\'' +
                ", arcType='" + arcType + '\'' +
                ", arcPath='" + arcPath + '\'' +
                ", arcTitle='" + arcTitle + '\'' +
                ", arcUser=" + arcUser +
                ", createdDate=" + createdDate +
                ", modifyUser='" + modifyUser + '\'' +
                ", modifyDate=" + modifyDate +
                ", borrowStatu=" + borrowStatu +
                ", expirationTime=" + expirationTime +
                ", expirationNotice=" + expirationNotice +
                ", notificationDay=" + notificationDay +
                ", contentArchive='" + contentArchive + '\'' +
                ", contentValidity='" + contentValidity + '\'' +
                ", commonlyUsed=" + commonlyUsed +
                ", docNum=" + docNum +
                ", comDepart='" + comDepart + '\'' +
                ", publishUnit='" + publishUnit + '\'' +
                ", publisher='" + publisher + '\'' +
                ", approvalTime=" + approvalTime +
                ", identificaTime=" + identificaTime +
                ", productNum='" + productNum + '\'' +
                ", rawNumber='" + rawNumber + '\'' +
                ", number=" + number +
                ", page=" + page +
                ", patentNo='" + patentNo + '\'' +
                ", inspectPerson='" + inspectPerson + '\'' +
                ", state=" + state +
                '}';
    }
}