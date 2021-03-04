package hcc.accouting.haccounting.repository.remote.async;

import java.util.List;

import hcc.accouting.haccounting.common.entity.BudgetCheck;
import hcc.accouting.haccounting.common.entity.SlipHeader;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 전표 헤더 관련 레파지토리 레이어 인터페이스
 * <p>
 *
 * 전표 헤더 내역 api url 함수 정보
 */
public interface SlipHeaderRepository {

    @POST("getSlipHeaderBySavingSlipHeaderAndSlipDetail.json")
    Call<SlipHeader> getSlipHeaderBySavingSlipHeaderAndSlipDetail(@Body SlipHeader slipHeader);

    @POST("getSlipHeaderByModifyingSlipHeaderAndSlipDetail.json")
    Call<SlipHeader> getSlipHeaderByModifyingSlipHeaderAndSlipDetail(@Body SlipHeader slipHeader);

    @POST("getBudgetCheckByTransferringSlipHeaders.json")
    Call<BudgetCheck> getBudgetCheckByTransferringSlipHeaders(@Body List<SlipHeader> slipHeaders);

    @GET("getSlipHeaderByMngNoMobile.json")
    Call<SlipHeader> getSlipHeaderByMngNoMobile(@Query("mngNoMobile") String mngNoMobile);

}
