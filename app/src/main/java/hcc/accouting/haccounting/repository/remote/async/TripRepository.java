package hcc.accouting.haccounting.repository.remote.async;

import java.util.List;

import hcc.accouting.haccounting.common.entity.Trip;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 출장 관련 레파지토리 레이어 인터페이스
 * <p>
 * 출장 내역 api url 함수 정보
 */
public interface TripRepository {

    @GET("getTripsByCompCdAndEmpNoAndDate.json")
    Call<List<Trip>> getTripsByCompCdAndEmpNoAndDate(@Query("compCd") String compCd, @Query("empNo") String empNo, @Query("date") String date);


    @GET("getTripsByCompCdAndEmpNoAndTripNmAndOffsetAndLimit.json")
    Call<List<Trip>> getTripsByCompCdAndEmpNoAndTripNmAndOffsetAndLimit(@Query("compCd") String compCd, @Query("empNo") String empNo, @Query("tripNm") String tripNm, @Query("offset") long offset, @Query("limit") long limit);

    @GET("getTripByCompCdAndTripCdAndDeptCd.json")
    Call<Trip> getTripByCompCdAndTripCdAndDeptCd(@Query("compCd") String compCd, @Query("tripCd") String tripCd, @Query("deptCd") String deptCd);

    @GET("getRecentOneTripByCompCdAndEmpNo.json")
    Call<Trip> getRecentOneTripByCompCdAndEmpNo(@Query("compCd") String compCd, @Query("empNo") String empNo);

}
