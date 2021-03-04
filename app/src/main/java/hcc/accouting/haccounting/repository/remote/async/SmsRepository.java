package hcc.accouting.haccounting.repository.remote.async;

import hcc.accouting.haccounting.common.dto.SmsTransferDto;
import hcc.accouting.haccounting.common.entity.CardHistory;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * SMS 처리 관련 레파지토리 레이어 인터페이스
 * <p>
 * SMS 처리 api url 함수 정보
 */
public interface SmsRepository {

    @POST("getCardHistoryBySavingCardHistoryWithML.json")
    Call<CardHistory> getCardHistoryByProcessingSMSWithML(@Body SmsTransferDto smsTransferDto);
}
