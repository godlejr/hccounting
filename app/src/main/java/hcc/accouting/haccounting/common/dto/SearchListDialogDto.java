package hcc.accouting.haccounting.common.dto;

import java.io.Serializable;

public class SearchListDialogDto implements Serializable {

    private String vendorNm; //부서코드 : 계정을 위한
    private String date; // 전기일 : 출장 품의를 위한


    public SearchListDialogDto() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVendorNm() {
        return vendorNm;
    }

    public void setVendorNm(String vendorNm) {
        this.vendorNm = vendorNm;
    }
}
