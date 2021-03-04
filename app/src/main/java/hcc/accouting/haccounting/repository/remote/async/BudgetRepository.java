package hcc.accouting.haccounting.repository.remote.async;

import java.util.List;

import hcc.accouting.haccounting.common.entity.Acnt;
import hcc.accouting.haccounting.common.entity.Budget;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 예산 관련 레파지토리 레이어 인터페이스
 * <p>
 * 예산 내역 api url 함수 정보
 */
public interface BudgetRepository {

    @GET("getBudgetByCompCdAndDeptCdAndAcntCdAndPosDate.json")
    Call<Budget> getBudgetByCompCdAndDeptCdAndAcntCdAndPosDate(@Query("compCd") String compCd, @Query("deptCd") String deptCd, @Query("acntCd") String acntCd , @Query("posDate") String posDate);

    @GET("getBudgetsByCompCdAndDeptCdAndAcntCd.json")
    Call<List<Budget>> getBudgetsByCompCdAndDeptCdAndAcntCd(@Query("compCd") String compCd, @Query("deptCd") String deptCd, @Query("acntCd") String acntCd);

}
