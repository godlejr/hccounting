package hcc.accouting.haccounting.repository.remote.async;


import hcc.accouting.haccounting.common.entity.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * 유저 관련 레파지토리 레이어 인터페이스
 * <p>
 * 유저 관련 api url 함수 정보
 */
public interface UserRepository {

    @GET("login.json")
    Call<User> getUserByIdAndPassword(@Query("loginId") String loginId, @Query("loginPw") String loginPw);

    @PUT("edit")
    Call<User> editAndGetUser(@Body User user);

    @GET("aesKey.json")
    Call<String> getAesKey();
}
