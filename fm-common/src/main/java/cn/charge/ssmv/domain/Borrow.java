package cn.charge.ssmv.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Borrow {

     private Long id;
     private String borrowName;
     private String borrowNumber;
     private String borrowPhone;
     private User arcUser;
    @DateTimeFormat(pattern="yyyy-MM-dd")//页面写入数据库时格式化
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")//出参格式
     private Date borrowDate;
    @DateTimeFormat(pattern="yyyy-MM-dd")//页面写入数据库时格式化
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")//出参格式
     private Date returnDate;
     private String borrowRemarks;
     private Integer borrowState;
     private String systemdictionary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBorrowName() {
        return borrowName;
    }

    public void setBorrowName(String borrowName) {
        this.borrowName = borrowName;
    }

    public String getBorrowNumber() {
        return borrowNumber;
    }

    public void setBorrowNumber(String borrowNumber) {
        this.borrowNumber = borrowNumber;
    }

    public String getBorrowPhone() {
        return borrowPhone;
    }

    public void setBorrowPhone(String borrowPhone) {
        this.borrowPhone = borrowPhone;
    }

    public User getArcUser() {
        return arcUser;
    }

    public void setArcUser(User arcUser) {
        this.arcUser = arcUser;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getBorrowRemarks() {
        return borrowRemarks;
    }

    public void setBorrowRemarks(String borrowRemarks) {
        this.borrowRemarks = borrowRemarks;
    }

    public Integer getBorrowState() {
        return borrowState;
    }

    public void setBorrowState(Integer borrowState) {
        this.borrowState = borrowState;
    }

    public String getSystemdictionary() {
        return systemdictionary;
    }

    public void setSystemdictionary(String systemdictionary) {
        this.systemdictionary = systemdictionary;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "id=" + id +
                ", borrowName='" + borrowName + '\'' +
                ", borrowNumber='" + borrowNumber + '\'' +
                ", borrowPhone='" + borrowPhone + '\'' +
                ", arcUser=" + arcUser +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                ", borrowRemarks='" + borrowRemarks + '\'' +
                ", borrowState='" + borrowState + '\'' +
                ", systemdictionary='" + systemdictionary + '\'' +
                '}';
    }
}
