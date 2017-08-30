package mkworld29.mobile.com.cafemoa_store;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import mkworld29.mobile.com.cafemoa_store.retrofit.RetrofitConnection;
import mkworld29.mobile.com.cafemoa_store.retrofit.RetrofitInstance;
import mkworld29.mobile.com.cafemoa_store.retrofit.SharedPreference;
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
    SharedPreference sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        retrofit= RetrofitInstance.getInstance(getApplicationContext());
        sp= SharedPreference.getInstance(getApplicationContext());
    }
    void loginClick(View v){
        String username_str=username.getText().toString();
        String password_str=password.getText().toString();

        RetrofitConnection.login service = retrofit.create(RetrofitConnection.login.class);

        final Call<RetrofitConnection.Token> repos = service.repoContributors(username_str,password_str);
        repos.enqueue(new Callback<RetrofitConnection.Token>() {
            @Override
            public void onResponse(Call<RetrofitConnection.Token> call, Response<RetrofitConnection.Token> response) {
                if (response.code() == 200) {
                    sp.put("Authorization", response.body().token);
                    Intent i=new Intent(LoginActivity.this, OrderActivity.class);
                    startActivity(i);
                    finish();
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
}