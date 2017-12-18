package mkworld29.mobile.com.cafemoa_store;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import mkworld29.mobile.com.cafemoa_store.retrofit.RetrofitConnection;
import mkworld29.mobile.com.cafemoa_store.retrofit.RetrofitInstance;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView lv_order = null;
    OrderListAdapter adapter = null;
    Retrofit retrofit;
    EditText et_min_time;
    Button closeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        Intent intent=getIntent();
        int minTime=intent.getIntExtra("MinTime",0);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                getData();
            }
        },500,10000);

        closeButton=(Button)findViewById(R.id.set_open);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitConnection.set_Open service = retrofit.create(RetrofitConnection.set_Open.class);
                Call<ResponseBody> repos = service.repoContributors(0);
                repos.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.code() == 200) {
                            Intent intent=new Intent(MainActivity.this,StartActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "통신 에러 발생", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("TAG", t.getLocalizedMessage());
                    }
                });
            }
        });

        et_min_time=(EditText)findViewById(R.id.et_min_time);
        et_min_time.setText(""+minTime);

        et_min_time.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    RetrofitConnection.set_minTime service = retrofit.create(RetrofitConnection.set_minTime.class);
                    Call<ResponseBody> repos = null;
                    try{
                        repos = service.repoContributors(Integer.parseInt(et_min_time.getText().toString()));
                    }catch (NumberFormatException e){
                        e.printStackTrace();
                    }
                    repos.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.code() == 200) {
                                Toast.makeText(getApplicationContext(), "성공적으로 설정 되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "통신 에러 발생", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.d("TAG", t.getLocalizedMessage());
                        }
                    });
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(et_min_time.getWindowToken(), 0);

                    return true;
                }
                return false;
            }
        });


        lv_order = (ListView) findViewById(R.id.lv_order);

        lv_order.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        new Timer().cancel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        new Timer().cancel();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    private void getData()
    {
        adapter = new OrderListAdapter();

        retrofit= RetrofitInstance.getInstance(getApplicationContext());
        RetrofitConnection.get_orders service = retrofit.create(RetrofitConnection.get_orders.class);

        final Call<List<RetrofitConnection.Order>> repos = service.repoContributors();
        repos.enqueue(new Callback<List<RetrofitConnection.Order>>() {
            @Override
            public void onResponse(Call<List<RetrofitConnection.Order>> call, Response<List<RetrofitConnection.Order>> response) {
                if (response.code() == 200) {

                    List<RetrofitConnection.Order> orders= response.body();
                    int orders_num=orders.size();

                    for(int i=0; i<orders_num; i++){
                        RetrofitConnection.Order order=orders.get(i);

                        List<RetrofitConnection.Option> options=order.options;
                        int option_num=options.size();

                        for(int j=0; j<option_num; j++){
                            RetrofitConnection.Option toption=options.get(j);
                            CoffeOption option = new CoffeOption(toption.shot_num,toption.size,toption.is_ice,toption.whipping_cream);

                            adapter.addItem(toption.beverage_name, order.get_time, order.order_num,option,order.pk);
                        }
                    }
                    lv_order.setAdapter(adapter);
                }
                else{
                    Toast.makeText(getApplicationContext(), "통신 에러 발생", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<RetrofitConnection.Order>> call, Throwable t) {
                Log.d("TAG", t.getLocalizedMessage());
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if(adapter != null)
            adapter.saveStates(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Only if you need to restore open/close state when
        // the orientation is changed
        if (adapter != null) {
            adapter.restoreStates(savedInstanceState);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private Boolean isNetWork(){
        ConnectivityManager manager = (ConnectivityManager) getSystemService (Context.CONNECTIVITY_SERVICE);
        boolean isMobileAvailable = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isAvailable();
        boolean isMobileConnect = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
        boolean isWifiAvailable = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isAvailable();
        boolean isWifiConnect = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();

        if ((isWifiAvailable && isWifiConnect) || (isMobileAvailable && isMobileConnect)){
            return true;
        }else{
            return false;
        }
    }

}