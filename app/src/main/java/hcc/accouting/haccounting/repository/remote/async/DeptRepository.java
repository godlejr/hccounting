package hcc.accouting.haccounting.repository.remote.async;

import java.util.List;

import hcc.accouting.haccounting.common.entity.Dept;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 부서 관련 레파지토리 레이어 인터페이스
 * <p>
 * 부서 내역 api url 함수 정보
 */
public interface DeptRepository {

    @GET("getDeptsByCompCdAndEmpNoAndDeptNmAndOffsetAndLimit.json")
    Call<List<Dept>> getDeptsByCompCdAndEmpNoAndDeptNmAndOffsetAndLimit(@Query("compCd") String compCd, @Query("empNo") String empNo, @Query("deptNm") String deptNm, @Query("offset") long offset, @Query("limit") long limit);

}
