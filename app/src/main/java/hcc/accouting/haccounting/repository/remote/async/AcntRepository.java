package hcc.accouting.haccounting.repository.remote.async;

import java.util.List;

import hcc.accouting.haccounting.common.entity.Acnt;
import hcc.accouting.haccounting.common.entity.CardHistory;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 계정 관련 레파지토리 레이어 인터페이스
 * <p>
 * 계정 내역 api url 함수 정보
 */
public interface AcntRepository {

    @GET("getAcntsWithSuggestionsByCompCdAndEmpNoAndVendorNm.json")
    Call<List<Acnt>> getAcntsWithSuggestionsByCompCdAndEmpNoAndVendorNm(@Query("compCd") String compCd, @Query("empNo") String empNo, @Query("vendorNm") String vendorNm);


    @GET("getAcntsByCompCdAndAcntCdOrAcntNmAndOffsetAndLimit.json")
    Call<List<Acnt>> getAcntsByCompCdAndAcntCdOrAcntNmAndOffsetAndLimit(@Query("compCd") String compCd, @Query("acntCd") String acntCd, @Query("acntNm") String acntNm, @Query("offset") long offset, @Query("limit") long limit);

}
