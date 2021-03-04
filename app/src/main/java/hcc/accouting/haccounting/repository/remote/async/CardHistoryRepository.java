package hcc.accouting.haccounting.repository.remote.async;

import java.util.List;

import hcc.accouting.haccounting.common.entity.CardHistory;
import hcc.accouting.haccounting.common.entity.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 카드 내역 관련 레파지토리 레이어 인터페이스
 * <p>
 * 카드 내역 api url 함수 정보
 */
public interface CardHistoryRepository {

    @GET("{status}.json")
    Call<List<CardHistory>> getCardHistoriesByStatusAndCompCdAndEmpNoAndOffsetAndLimitOrderByTransDateAndTransTimeDesc(@Path("status") String status, @Query("compCd") String compCd, @Query("empNo") String empNo, @Query("offset") long offset, @Query("limit") long limit);

    @POST("getCardHistoryByModifyingCardHistory.json")
    Call<CardHistory> getCardHistoryByModifyingCardHistory(@Body CardHistory cardHistory);

    @POST("getIsSuccessByDeletingCardHistoriesAndSlipHeaders.json")
    Call<Boolean> getIsSuccessByDeletingCardHistoriesAndSlipHeaders(@Body List<CardHistory> cardHistories);

    @POST("getIsSuccessByDeletingSlipHeadersAndModifyingCardHistories.json")
    Call<Boolean> getIsSuccessByDeletingSlipHeadersAndModifyingCardHistories(@Body List<CardHistory> cardHistories);

    @GET("{status}/count.json")
    Call<Integer> getCardHistoryCountByStatusAndEmpNo(@Path("status") String status, @Query("empNo") String empNo);

}