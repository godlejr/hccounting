package hcc.accouting.haccounting.common.utils;

import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.annotation.Annotation;

import hcc.accouting.haccounting.common.dto.HttpErrorDto;
import hcc.accouting.haccounting.repository.local.SharedPreferenceManager;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class HttpErrorUtil {
    public HttpErrorUtil() {
    }

    public HttpErrorDto responseHandler(Response response) {

        int responseCode = response.code();
        String errorMessage = null;
        switch (responseCode) {
            case 200:   // success
                break;
            case 401:   // Unauthorized : access_token 오류 (token update)
                break;
            case 403:   // Forbidden
                errorMessage = "서비스에 대한 호출 권한이 없습니다.";
                break;
            case 400:   // Bad Request : 쿼리 이상
            case 404:   // Not Found
            case 405:   // Method Not Allowed
            case 406:   // Not Acceptable : 요청한 페이지가 요청한 콘텐츠 특성으로 응답할 수 없음
            case 444:   // nginx 응답없음
                errorMessage = "잘못된 접근입니다.";
                break;
            case 408:
                errorMessage = "요청 시간이 초과되었습니다.";
                break;
            case 500:   // Internal Server Error
            case 502:   // Bad Gateway : nginx가 잘못된 서버로 요청함
            case 503:   // Service Unavailable : Server overload or down
            case 504:   // Gateway Time Out
                errorMessage = "일시적인 서버 장애입니다. 다시 시도해주세요.";

                break;
            default:
            errorMessage = "(" + responseCode + ") 일시적인 서버 장애입니다. 다시 시도해주세요.";
            break;
        }

        showHttpResponseCodeMessage(responseCode, errorMessage);
        return new HttpErrorDto(responseCode, errorMessage);
    }

    public void showHttpResponseCodeMessage(int responseCode, String message ){
        Log.e("RESPONSE_CODE 정보 ::", "(" + responseCode + ")  " + message);
    }
}
