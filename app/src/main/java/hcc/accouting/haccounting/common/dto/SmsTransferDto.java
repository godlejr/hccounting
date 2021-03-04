package hcc.accouting.haccounting.common.dto;

import java.io.Serializable;

public class SmsTransferDto implements Serializable {

    private String compCd;
    private String empNo;
    private String message;
    private String compPhone;

    public SmsTransferDto() {
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCompPhone() {
        return compPhone;
    }

    public void setCompPhone(String compPhone) {
        this.compPhone = compPhone;
    }
}
