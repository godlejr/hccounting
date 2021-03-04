package hcc.accouting.haccounting.common.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

import hcc.accouting.haccounting.common.utils.AES256Util;

public class User extends Base implements Serializable {

    private String compCd;
    private String empNo;
    private String loginId;
    private String loginPw;
    private String compNm;
    private String empNm;
    private String startDate;
    private String endDate;
    private String siteCd;
    private String siteNm;
    private String deptCd;
    private String deptNm;
    private String erpId;
    private String erpVendorId;
    private String accessToken;
    private String deviceId;
    private int loginStatus;


    public User() {
    }

    public String getCompCd() {
        return compCd;
    }

    public void setCompCd(String compCd) {
        this.compCd = compCd;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginPw() {
        return loginPw;
    }

    public void setLoginPw(String loginPw) {
        this.loginPw = loginPw;
    }

    public String getCompNm() {
        return compNm;
    }

    public void setCompNm(String compNm) {
        this.compNm = compNm;
    }

    public String getEmpNm() {
        return empNm;
    }

    public void setEmpNm(String empNm) {
        this.empNm = empNm;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSiteCd() {
        return siteCd;
    }

    public void setSiteCd(String siteCd) {
        this.siteCd = siteCd;
    }

    public String getSiteNm() {
        return siteNm;
    }

    public void setSiteNm(String siteNm) {
        this.siteNm = siteNm;
    }

    public String getDeptCd() {
        return deptCd;
    }

    public void setDeptCd(String deptCd) {
        this.deptCd = deptCd;
    }

    public String getDeptNm() {
        return deptNm;
    }

    public void setDeptNm(String deptNm) {
        this.deptNm = deptNm;
    }

    public String getErpId() {
        return erpId;
    }

    public void setErpId(String erpId) {
        this.erpId = erpId;
    }

    public String getErpVendorId() {
        return erpVendorId;
    }

    public void setErpVendorId(String erpVendorId) {
        this.erpVendorId = erpVendorId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(int loginStatus) {
        this.loginStatus = loginStatus;
    }


    @Override
    public String toString() {
        return "User{" +
                "compCd='" + compCd + '\'' +
                ", empNo='" + empNo + '\'' +
                ", loginId='" + loginId + '\'' +
                ", loginPw='" + loginPw + '\'' +
                ", compNm='" + compNm + '\'' +
                ", empNm='" + empNm + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", siteCd='" + siteCd + '\'' +
                ", siteNm='" + siteNm + '\'' +
                ", deptCd='" + deptCd + '\'' +
                ", deptNm='" + deptNm + '\'' +
                ", erpId='" + erpId + '\'' +
                ", erpVendorId='" + erpVendorId + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", loginStatus=" + loginStatus +
                '}';
    }

    public void encrypt(AES256Util aes256Util) {
        try {

            this.compCd = aes256Util.encrypt(compCd);
            this.empNo = aes256Util.encrypt(empNo);
            this.compNm = aes256Util.encrypt(compNm);
            this.empNm = aes256Util.encrypt(empNm);
            this.startDate = aes256Util.encrypt(startDate);
            this.endDate = aes256Util.encrypt(endDate);
            this.siteCd = aes256Util.encrypt(siteCd);
            this.siteNm = aes256Util.encrypt(siteNm);
            this.deptCd = aes256Util.encrypt(deptCd);
            this.deptNm = aes256Util.encrypt(deptNm);
            this.erpId = aes256Util.encrypt(erpId);
            this.erpVendorId = aes256Util.encrypt(erpVendorId);
            this.deviceId = aes256Util.encrypt(deviceId);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    public void decrypt(AES256Util aes256Util) {
        try {

            this.compCd = aes256Util.decrypt(compCd);
            this.empNo = aes256Util.decrypt(empNo);
            this.compNm = aes256Util.decrypt(compNm);
            this.empNm = aes256Util.decrypt(empNm);
            this.startDate = aes256Util.decrypt(startDate);
            this.endDate = aes256Util.decrypt(endDate);
            this.siteCd = aes256Util.decrypt(siteCd);
            this.siteNm = aes256Util.decrypt(siteNm);
            this.deptCd = aes256Util.decrypt(deptCd);
            this.deptNm = aes256Util.decrypt(deptNm);
            this.erpId = aes256Util.decrypt(erpId);
            this.erpVendorId = aes256Util.decrypt(erpVendorId);
            this.deviceId = aes256Util.decrypt(deviceId);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }
}
