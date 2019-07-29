package cn.charge.ssmv.domain;

public class Borrowandarcreturn {
    private Long id;

    private Long archiveId;
    private Archive archive;

    private Long borrowId;
    private Borrow borrow;

    private Long arcreturnId;
    private Arcreturn arcreturn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArchiveId() {
        return archiveId;
    }

    public void setArchiveId(Long archiveId) {
        this.archiveId = archiveId;
    }

    public Long getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Long borrowId) {
        this.borrowId = borrowId;
    }

    public Long getArcreturnId() {
        return arcreturnId;
    }

    public void setArcreturnId(Long arcreturnId) {
        this.arcreturnId = arcreturnId;
    }

    public Archive getArchive() {
        return archive;
    }

    public void setArchive(Archive archive) {
        this.archive = archive;
    }

    public Borrow getBorrow() {
        return borrow;
    }

    public void setBorrow(Borrow borrow) {
        this.borrow = borrow;
    }

    public Arcreturn getArcreturn() {
        return arcreturn;
    }

    public void setArcreturn(Arcreturn arcreturn) {
        this.arcreturn = arcreturn;
    }

    @Override
    public String toString() {
        return "Borrowandarcreturn{" +
                "archiveId=" + archiveId +
                ", archive=" + archive +
                ", borrowId=" + borrowId +
                ", borrow=" + borrow +
                ", arcreturnId=" + arcreturnId +
                ", arcreturn=" + arcreturn +
                '}';
    }
}