package hcc.accouting.haccounting.common.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CardHistory extends Base implements Serializable {
    private long rownum;
    private String compCd;
    private String transDate;
    private String transTime;
    private String cardNo;
    private long seqNo;


    @SerializedName("cardCompDao")
    private CardComp cardComp;
    private float amtTot;
    private String currCd;
    private String abroadYn;
    private String cancPartYn;
    private String empNo;
    private String mngNoMobile;
    private String vendorNm;
    private String status;
    private String vanMappingYn;

    public long getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(long seqNo) {
        this.seqNo = seqNo;
    }

    private boolean isChecked;

    public CardHistory() {
        this.isChecked = false;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public long getRownum() {
        return rownum;
    }

    public void setRownum(long rownum) {
        this.rownum = rownum;
    }

    public String getCompCd() {
        return compCd;
    }

    public void setCompCd(String compCd) {
        this.compCd = compCd;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    public String getTransTime() {
        return transTime;
    }

    public void setTransTime(String transTime) {
        this.transTime = transTime;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public CardComp getCardComp() {
        return cardComp;
    }

    public void setCardComp(CardComp cardComp) {
        this.cardComp = cardComp;
    }

    public float getAmtTot() {
        return amtTot;
    }

    public void setAmtTot(float amtTot) {
        this.amtTot = amtTot;
    }

    public String getCurrCd() {
        return currCd;
    }

    public void setCurrCd(String currCd) {
        this.currCd = currCd;
    }

    public String getAbroadYn() {
        return abroadYn;
    }

    public void setAbroadYn(String abroadYn) {
        this.abroadYn = abroadYn;
    }

    public String getCancPartYn() {
        return cancPartYn;
    }

    public void setCancPartYn(String cancPartYn) {
        this.cancPartYn = cancPartYn;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getMngNoMobile() {
        return mngNoMobile;
    }

    public void setMngNoMobile(String mngNoMobile) {
        this.mngNoMobile = mngNoMobile;
    }

    public String getVendorNm() {
        return vendorNm;
    }

    public void setVendorNm(String vendorNm) {
        this.vendorNm = vendorNm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVanMappingYn() {
        return vanMappingYn;
    }

    public void setVanMappingYn(String vanMappingYn) {
        this.vanMappingYn = vanMappingYn;
    }
}
