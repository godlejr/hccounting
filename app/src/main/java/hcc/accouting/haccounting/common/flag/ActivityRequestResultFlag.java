package hcc.accouting.haccounting.common.flag;

public class ActivityRequestResultFlag {

    /**
     *  request
     *
     *  액티비티 요청 코드
     *
     *  10010 - dialog
     *
     */
    public static final int SEARCH_LIST_DIALOG_ACNT_REQUEST = 10011;

    public static final int SEARCH_LIST_DIALOG_DEPT_REQUEST = 10012;

    public static final int SEARCH_LIST_DIALOG_TRIP_REQUEST = 10013;

    public static final int CONFIRM_CANCEL_DIALOG_SLIP_DELETE_REQUEST = 10014;

    public static final int CONFIRM_CANCEL_DIALOG_CARD_HISTORY_DELETE_REQUEST = 10015;

    public static final int CONFIRM_CANCEL_DIALOG_LOGOUT_REQUEST = 10020;




    /**
     * result
     * <p>
     * 액티비티 결과 코드
     */
    public static final int RESULT_OK = -1;
    public static final int RESULT_CANCEL = 0;
    public static final int RESULT_EDIT = 3;
    public static final int RESULT_DELETE = 4;


}
