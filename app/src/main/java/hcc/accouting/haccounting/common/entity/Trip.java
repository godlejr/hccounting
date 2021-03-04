package hcc.accouting.haccounting.common.entity;

import java.io.Serializable;

public class Trip implements Serializable {
    private long rownum;
    private String tripCd;
    private String tripNm;
    private String tripGb;
    private String acntCd;
    private String acntNm;
    private Integer tripAmt;

    private Integer roomAmt;
    private Integer dailyAmt;
    private Integer transAmt;
    private Integer etcAmt;

    public Trip() {
    }

    public long getRownum() {
        return rownum;
    }

    public void setRownum(long rownum) {
        this.rownum = rownum;
    }

    public String getTripCd() {
        return tripCd;
    }

    public void setTripCd(String tripCd) {
        this.tripCd = tripCd;
    }

    public String getTripNm() {
        return tripNm;
    }

    public void setTripNm(String tripNm) {
        this.tripNm = tripNm;
    }

    public String getTripGb() {
        return tripGb;
    }

    public void setTripGb(String tripGb) {
        this.tripGb = tripGb;
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

    public Integer getTripAmt() {
        return tripAmt;
    }

    public void setTripAmt(Integer tripAmt) {
        this.tripAmt = tripAmt;
    }

    public Integer getRoomAmt() {
        return roomAmt;
    }

    public void setRoomAmt(Integer roomAmt) {
        this.roomAmt = roomAmt;
    }

    public Integer getDailyAmt() {
        return dailyAmt;
    }

    public void setDailyAmt(Integer dailyAmt) {
        this.dailyAmt = dailyAmt;
    }

    public Integer getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(Integer transAmt) {
        this.transAmt = transAmt;
    }

    public Integer getEtcAmt() {
        return etcAmt;
    }

    public void setEtcAmt(Integer etcAmt) {
        this.etcAmt = etcAmt;
    }
}
