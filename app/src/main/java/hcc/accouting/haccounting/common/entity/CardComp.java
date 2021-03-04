package hcc.accouting.haccounting.common.entity;

import java.io.Serializable;

public class CardComp  extends Base implements Serializable {
    private String compCd;
    private String compNm;

    public CardComp() {
    }

    public String getCompCd() {
        return compCd;
    }

    public void setCompCd(String compCd) {
        this.compCd = compCd;
    }

    public String getCompNm() {
        return compNm;
    }

    public void setCompNm(String compNm) {
        this.compNm = compNm;
    }
}
