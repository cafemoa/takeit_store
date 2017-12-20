package mkworld29.mobile.com.cafemoa_store;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;

import mkworld29.mobile.com.cafemoa_store.retrofit.RetrofitConnection;
import mkworld29.mobile.com.cafemoa_store.retrofit.RetrofitInstance;
import mkworld29.mobile.com.cafemoa_store.retrofit.SharedPreference;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by ABCla on 2017-08-30.
 */

public class LoginActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Retrofit retrofit;
    CheckBox chk_auto_login;
    SharedPreference sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseApp.initializeApp(getApplicationContext());
        FirebaseInstanceId.getInstance().getToken();

        int color = Color.parseColor("#ff0000");

        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        chk_auto_login = (CheckBox) findViewById(R.id.chk_auto_login);

        username.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        password.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        retrofit= RetrofitInstance.getInstance(getApplicationContext());
        sp= SharedPreference.getInstance(getApplicationContext());
    }

    public void loginClick(View v){
        String username_str=username.getText().toString();
        String password_str=password.getText().toString();

        RetrofitConnection.login service = retrofit.create(RetrofitConnection.login.class);

        final Call<RetrofitConnection.Token> repos = service.repoContributors(username_str,password_str);
        repos.enqueue(new Callback<RetrofitConnection.Token>() {
            @Override
            public void onResponse(Call<RetrofitConnection.Token> call, Response<RetrofitConnection.Token> response) {
                if (response.code() == 200) {
                    sp.put("Authorization", response.body().token);
                    if(chk_auto_login.isChecked()) sp.put("Auto_Login", "y");
                    else sp.put("Auto_Login","n");
                    send_fcmtoken();
                }
                else{
                    Toast.makeText(getApplicationContext(), "아이디와 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RetrofitConnection.Token> call, Throwable t) {
                Log.d("TAG", t.getLocalizedMessage());
            }
        });
    }
    public void send_fcmtoken(){
        RetrofitConnection.fcm_register service = retrofit.create(RetrofitConnection.fcm_register.class);
        final String deviceID = android.provider.Settings.Secure.getString(getBaseContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        final String fcm_token=sp.get("FCM_TOKEN");
        final Call<ResponseBody> repos=service.repoContributors(deviceID,fcm_token,true);
        repos.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Intent i=new Intent(LoginActivity.this, StartActivity.class);
                startActivity(i);
                finish();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("TAG", t.getLocalizedMessage());
            }
        });
    }
}