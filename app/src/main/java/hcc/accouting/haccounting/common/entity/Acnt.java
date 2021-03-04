package hcc.accouting.haccounting.common.entity;

import java.io.Serializable;

public class Acnt implements Serializable {
    private long rownum;
    private String acntCd;
    private String acntNm;
    private String acntRate;

    public Acnt() {
    }

    public long getRownum() {
        return rownum;
    }

    public void setRownum(long rownum) {
        this.rownum = rownum;
    }

    public String getAcntCd() {
        return acntCd;
    }

    public void setAcntCd(String acntCd) {
        this.acntCd = acntCd;
    }

    public String getAcntNm() {
        return acntNm;
    }

    public void setAcntNm(String acntNm) {
        this.acntNm = acntNm;
    }

    public String getAcntRate() {
        return acntRate;
    }

    public void setAcntRate(String acntRate) {
        this.acntRate = acntRate;
    }
}
