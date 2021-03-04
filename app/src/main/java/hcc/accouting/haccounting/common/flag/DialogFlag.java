package hcc.accouting.haccounting.common.flag;

public class DialogFlag {

    /**
     * Confirm Dialog
     * <p>
     * 액티비티 결과 코드
     */
    public static final int SLIP_TRANS_DELETE_CONFIRM = 1;
    public static final int CARD_HISTORY_DELETE_CONFIRM = 2;
    public static final int LOGOUT_CONFIRM = 10;


    /**
     * Confirm Dialog title
     * <p>
     * 다이어로그 제목
     */
    public static final String SLIP_TRANS_DELETE_CONFIRM_TITLE = "전송 취소 하시겠습니까?";
    public static final String CARD_HISTORY_DELETE_CONFIRM_TITLE = "삭제 하시겠습니까?";
    public static final String LOGOUT_CONFIRM_TITLE = "로그아웃 하시겠습니까?";


    /**
     * Search List Dialog
     * <p>
     * 액티비티 결과 코드
     */
    public static final int ACNT_SEARCH_LIST = 1;
    public static final int DEPT_SEARCH_LIST = 2;
    public static final int TRIP_SEARCH_LIST = 3;


    /**
     * Search List Dialog title
     * <p>
     * 다이어로그 제목
     */
    public static final String ACNT_SEARCH_LIST_TITLE = "계정을 선택하세요";
    public static final String DEPT_SEARCH_LIST_TITLE = "부서를 선택하세요";
    public static final String TRIP_SEARCH_LIST_TITLE = "출장품의를 선택하세요";

    /**
     * Search List Dialog offset limit
     * <p>
     * 오프셋 및 리밋
     */
    public static final long SEARCH_LIST_FRIST_OFFSET = 1;
    public static final long SEARCH_LIST_LIMIT = 6;

    /**
     * Search List Dialog status
     * <p>
     * 리스트 상태 (유지, 변화)
     */
    public static final int SEARCH_LIST_STATUS_STATELESS = 1;
    public static final int SEARCH_LIST_STATUS_CHANGED = 2;


}
