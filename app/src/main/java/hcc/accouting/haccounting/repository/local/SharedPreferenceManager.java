package hcc.accouting.haccounting.repository.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import hcc.accouting.haccounting.common.entity.User;
import hcc.accouting.haccounting.common.utils.AES256Util;
import hcc.accouting.haccounting.repository.remote.interceptor.HttpInterceptor;

public class SharedPreferenceManager {
    private Context context;

    private SharedPreferences sharedUserPreferences;
    private SharedPreferences sharedNotificationPreferences;

    public SharedPreferenceManager(Context context) {
        this.context = context;

        if (sharedUserPreferences == null) {
            sharedUserPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        }

        if (sharedNotificationPreferences == null) {
            sharedNotificationPreferences = context.getSharedPreferences("notification", Context.MODE_PRIVATE);
        }
    }


    public User getUser()  {
        Gson gson = new Gson();
        String json = sharedUserPreferences.getString("user", "");

        User user = gson.fromJson(json, User.class);
        if(user != null) {
            try {
                user.decrypt(new AES256Util());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    public void setUser(User user) {
        SharedPreferences.Editor editor = sharedUserPreferences.edit();

        Gson gson = new Gson();
        String stringUser = gson.toJson(user);
        editor.putString("user", stringUser).apply();
        editor.commit();
    }

    public void removeUser() {
        SharedPreferences.Editor editor = sharedUserPreferences.edit();
        editor.remove("user").apply();
        editor.commit();
    }

}
