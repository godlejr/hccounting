package hcc.accouting.haccounting.common.dto;

import java.io.Serializable;
import java.util.List;

import hcc.accouting.haccounting.common.entity.CardHistory;
import hcc.accouting.haccounting.common.entity.User;

public class ConfirmCancelDialogDto implements Serializable {

    private List<CardHistory> cardHistories; //전표 / 카드사용내역 용도
    private User user; //로그아웃 용도

    public ConfirmCancelDialogDto() {
    }

    public List<CardHistory> getCardHistories() {
        return cardHistories;
    }

    public void setCardHistories(List<CardHistory> cardHistories) {
        this.cardHistories = cardHistories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
