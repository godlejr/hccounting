package hcc.accouting.haccounting.repository.remote.interceptor;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import hcc.accouting.haccounting.common.entity.User;
import hcc.accouting.haccounting.repository.converter.NullOnEmptyConverterFactory;
import hcc.accouting.haccounting.repository.local.SharedPreferenceManager;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpInterceptor implements Interceptor {
//    private final String BASE_API = "http://172.16.2.52:8080/ham/";
//
  private final String BASE_API = "http://121.168.9.79:80/";

    // private final String BASE_API = "http://172.19.116.78:8089/";

    private final String USER_URL = "users/";
    private final String CARD_HISTORY_URL = "cardHistories/";
    private final String SMS_URL = "sms/";
    private final String ACNT_URL = "acnts/";
    private final String BUDGET_URL = "budgets/";
    private final String TRIP_URL = "trips/";
    private final String DEPT_URL = "depts/";
    private final String SLIP_HEADER_URL = "slipHeaders/";


    private Context mContext;

    private SharedPreferenceManager mSharedPreferenceManager;

    public Retrofit retrofit;
    private OkHttpClient okHttpClient;
    private Gson gson = new GsonBuilder().setLenient().create();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request newRequest = null;


        User user = this.mSharedPreferenceManager.getUser();

        if (user != null) {
            String accessToken = user.getAccessToken();
            if (accessToken != null && accessToken.length() > 0) {
                request = request.newBuilder()
                        .addHeader("Authorization", accessToken)
                        .build();
            }
        } else {
            request = request.newBuilder().build();
        }

        Response response = chain.proceed(request);

        int responseCode = response.code();
        if (responseCode == 401) {
            //authentication fail
            User modifiedUser = reLogin();

            String modifiedAccessToken = modifiedUser.getAccessToken();
            newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", modifiedAccessToken)
                    .build();
            return chain.proceed(newRequest);
        }

        return response;
    }

    public User reLogin() throws IOException {
        //RequestBody requestBody = RequestBody.create(null, new byte[0]);
        OkHttpClient client = new OkHttpClient();

        User user = this.mSharedPreferenceManager.getUser();

        String loginId = user.getLoginId();
        String loginPw = user.getLoginPw();

        Request request = new Request.Builder()
                .url(BASE_API + USER_URL + "login.json?" + "loginId=" + loginId + "&loginPw=" + loginPw)
                .method("GET", null)
                .build();

        Response response = client.newCall(request).execute();
        int responseCode = response.code();
        if (responseCode == 200) {
            // Get response
            final ResponseBody body = response.body();
            String jsonData = body.string();
            Gson gson = new Gson();
            User modifiedUser = gson.fromJson(jsonData, User.class);
            if (modifiedUser != null) {
                this.mSharedPreferenceManager.setUser(modifiedUser);

                user = modifiedUser;
            } else {
                user = null;
            }
            body.close();
        } else {
            user = null;
        }
        return user;
    }

    public String getAesKey() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(BASE_API + USER_URL + "aesKey.json")
                .method("GET", null)
                .build();

        Response response = client.newCall(request).execute();
        int responseCode = response.code();

        String aesKey = null;
        if (responseCode == 200) {
            // Get response
            final ResponseBody body = response.body();
            String jsonData = body.string();
            Gson gson = new Gson();
            aesKey = gson.fromJson(jsonData, String.class);

            body.close();
        }
        return aesKey;
    }


    public HttpInterceptor(Context context) {
        this.mContext = context;
        this.mSharedPreferenceManager = new SharedPreferenceManager(context);

        this.okHttpClient = new OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(this)
                .build();
    }

    public HttpInterceptor() {
        this.okHttpClient = new OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(this)
                .build();
    }


    public Retrofit getRetrofitForUserRepository() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_API + USER_URL).addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    public Retrofit getRetrofitForSmsRepository() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_API + SMS_URL).addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    public Retrofit getRetrofitForCardHistoryRepository() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_API + CARD_HISTORY_URL).addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    public Retrofit getRetrofitForAcntRepository() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_API + ACNT_URL).addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    public Retrofit getRetrofitForBudgetRepository() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_API + BUDGET_URL).addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    public Retrofit getRetrofitForTripRepository() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_API + TRIP_URL).addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    public Retrofit getRetrofitForDeptRepository() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_API + DEPT_URL).addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    public Retrofit getRetrofitForSlipHeaderRepository() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_API + SLIP_HEADER_URL).addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        return retrofit;
    }

}
