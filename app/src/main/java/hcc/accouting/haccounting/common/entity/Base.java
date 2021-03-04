package hcc.accouting.haccounting.common.entity;

import java.io.Serializable;

public abstract class Base  {
    private String cdate;
    private String udate;

    public Base() {
    }

    public Base(String cdate, String udate) {
        this.cdate = cdate;
        this.udate = udate;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public String getUdate() {
        return udate;
    }

    public void setUdate(String udate) {
        this.udate = udate;
    }
}
