package hcc.accouting.haccounting.common.dto;

import java.io.Serializable;

import hcc.accouting.haccounting.common.entity.Acnt;
import hcc.accouting.haccounting.common.entity.Dept;
import hcc.accouting.haccounting.common.entity.Trip;

public class SearchListDialogResultOkDto implements Serializable {
    private Acnt anct;
    private Trip trip;
    private Dept dept;

    public SearchListDialogResultOkDto() {
    }

    public Acnt getAnct() {
        return anct;
    }

    public void setAnct(Acnt anct) {
        this.anct = anct;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }
}
