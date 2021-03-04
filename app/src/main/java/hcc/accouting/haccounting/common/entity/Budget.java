package hcc.accouting.haccounting.common.entity;

public class Budget {
    private String compCd;
    private String expRvsnDept;
    private String objmNm;
    private String acntCd;
    private String acntNm;

    private Float udpBdget;
    private Float actBdget;
    private Float wrtBdget;
    private Float remBdget;

    public Budget() {
    }

    public String getCompCd() {
        return compCd;
    }

    public void setCompCd(String compCd) {
        this.compCd = compCd;
    }

    public String getExpRvsnDept() {
        return expRvsnDept;
    }

    public void setExpRvsnDept(String expRvsnDept) {
        this.expRvsnDept = expRvsnDept;
    }

    public String getObjmNm() {
        return objmNm;
    }

    public void setObjmNm(String objmNm) {
        this.objmNm = objmNm;
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

    public Float getUdpBdget() {
        return udpBdget;
    }

    public void setUdpBdget(Float udpBdget) {
        this.udpBdget = udpBdget;
    }

    public Float getActBdget() {
        return actBdget;
    }

    public void setActBdget(Float actBdget) {
        this.actBdget = actBdget;
    }

    public Float getWrtBdget() {
        return wrtBdget;
    }

    public void setWrtBdget(Float wrtBdget) {
        this.wrtBdget = wrtBdget;
    }

    public Float getRemBdget() {
        return remBdget;
    }

    public void setRemBdget(Float remBdget) {
        this.remBdget = remBdget;
    }
}
