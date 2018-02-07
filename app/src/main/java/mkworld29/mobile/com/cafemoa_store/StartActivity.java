package mkworld29.mobile.com.cafemoa_store;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import mkworld29.mobile.com.cafemoa_store.retrofit.RetrofitConnection;
import mkworld29.mobile.com.cafemoa_store.retrofit.RetrofitInstance;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class StartActivity extends AppCompatActivity {

    private TextView tv_start;
    Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_start);

        retrofit= RetrofitInstance.getInstance(getApplicationContext());
        RetrofitConnection.getCafeInfo service = retrofit.create(RetrofitConnection.getCafeInfo.class);
        Call<RetrofitConnection.Cafe> repos = service.repoContributors();
        repos.enqueue(new Callback<RetrofitConnection.Cafe>() {
            @Override
            public void onResponse(Call<RetrofitConnection.Cafe> call, Response<RetrofitConnection.Cafe> response) {
                if (response.code() == 200)
                {
                    RetrofitConnection.Cafe cafe=response.body();
                    final Intent intent = new Intent(StartActivity.this, MainActivity.class);

                    intent.putExtra("MinTime", cafe.min_time);

                    if(cafe.is_open)
                    {
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        tv_start = (TextView) findViewById(R.id.btn_start);
                        tv_start.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                RetrofitConnection.set_Open service = retrofit.create(RetrofitConnection.set_Open.class);
                                Call<ResponseBody> repos = service.repoContributors(1);
                                repos.enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        if (response.code() == 200) {
                                            startActivity(intent);
                                            finish();
                                        }
                                        else{
                                            Toast.makeText(getApplicationContext(), "(Start1) 통신 에러 발생", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                                        Log.d("TAG", t.getLocalizedMessage());
                                    }
                                });

                            }
                        });
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "(Start2) 통신 에러 발생", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();

                }
            }

            @Override
            public void onFailure(Call<RetrofitConnection.Cafe> call, Throwable t) {
                Log.d("TAG", t.getLocalizedMessage());
            }
        });
    }
}
