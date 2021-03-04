package hcc.accouting.haccounting.common.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SlipHeader implements Serializable {
    private String compCd;
    private String mngNoMobile;
    private String posDate;
    private String eviDate;
    private String textH;
    private String siteCd;
    private Float currExp;
    private String currCd;
    private String bankType;
    private String busiTripId;
    private String status;
    private String approveId;
    private String mngNo;
    private String webIfYn;
    private String writeUser;
    private String writeDate;
    private String writeTime;
    private String changeUser;
    private String changeDate;
    private String changeTime;

    private List<SlipDetail> slipDetails = new ArrayList<>();

    public SlipHeader() {
    }

    public String getCompCd() {
        return compCd;
    }

    public void setCompCd(String compCd) {
        this.compCd = compCd;
    }

    public String getMngNoMobile() {
        return mngNoMobile;
    }

    public void setMngNoMobile(String mngNoMobile) {
        this.mngNoMobile = mngNoMobile;
    }

    public String getPosDate() {
        return posDate;
    }

    public void setPosDate(String posDate) {
        this.posDate = posDate;
    }

    public String getEviDate() {
        return eviDate;
    }

    public void setEviDate(String eviDate) {
        this.eviDate = eviDate;
    }

    public String getTextH() {
        return textH;
    }

    public void setTextH(String textH) {
        this.textH = textH;
    }

    public String getSiteCd() {
        return siteCd;
    }

    public void setSiteCd(String siteCd) {
        this.siteCd = siteCd;
    }

    public Float getCurrExp() {
        return currExp;
    }

    public void setCurrExp(Float currExp) {
        this.currExp = currExp;
    }

    public String getCurrCd() {
        return currCd;
    }

    public void setCurrCd(String currCd) {
        this.currCd = currCd;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getBusiTripId() {
        return busiTripId;
    }

    public void setBusiTripId(String busiTripId) {
        this.busiTripId = busiTripId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApproveId() {
        return approveId;
    }

    public void setApproveId(String approveId) {
        this.approveId = approveId;
    }

    public String getMngNo() {
        return mngNo;
    }

    public void setMngNo(String mngNo) {
        this.mngNo = mngNo;
    }

    public String getWebIfYn() {
        return webIfYn;
    }

    public void setWebIfYn(String webIfYn) {
        this.webIfYn = webIfYn;
    }

    public String getWriteUser() {
        return writeUser;
    }

    public void setWriteUser(String writeUser) {
        this.writeUser = writeUser;
    }

    public String getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }

    public String getWriteTime() {
        return writeTime;
    }

    public void setWriteTime(String writeTime) {
        this.writeTime = writeTime;
    }

    public String getChangeUser() {
        return changeUser;
    }

    public void setChangeUser(String changeUser) {
        this.changeUser = changeUser;
    }

    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
    }

    public String getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
    }

    public List<SlipDetail> getSlipDetails() {
        return slipDetails;
    }

    public void setSlipDetails(List<SlipDetail> slipDetails) {
        this.slipDetails = slipDetails;
    }
}
