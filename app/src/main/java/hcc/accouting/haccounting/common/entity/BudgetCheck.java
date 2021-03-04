package hcc.accouting.haccounting.common.entity;

import java.io.Serializable;

public class BudgetCheck implements Serializable {
    private String returnCd;
    private Integer budgetAmt;
    private String returnMsg;
    private Boolean isSuccess;

    public BudgetCheck() {
        super();
    }

    public String getReturnCd() {
        return returnCd;
    }

    public void setReturnCd(String returnCd) {
        this.returnCd = returnCd;
    }

    public Integer getBudgetAmt() {
        return budgetAmt;
    }

    public void setBudgetAmt(Integer budgetAmt) {
        this.budgetAmt = budgetAmt;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }


}
