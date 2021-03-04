package hcc.accouting.haccounting.common.entity;

import java.io.Serializable;

public class Dept implements Serializable {
    private long rownum;
    private String expObjmCd;
    private String objmNm;

    public Dept() {
    }

    public long getRownum() {
        return rownum;
    }

    public void setRownum(long rownum) {
        this.rownum = rownum;
    }

    public String getExpObjmCd() {
        return expObjmCd;
    }

    public void setExpObjmCd(String expObjmCd) {
        this.expObjmCd = expObjmCd;
    }

    public String getObjmNm() {
        return objmNm;
    }

    public void setObjmNm(String objmNm) {
        this.objmNm = objmNm;
    }
}
