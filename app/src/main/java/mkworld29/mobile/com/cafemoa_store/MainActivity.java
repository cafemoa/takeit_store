package mkworld29.mobile.com.cafemoa_store;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.tsengvn.typekit.TypekitContextWrapper;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import mkworld29.mobile.com.cafemoa_store.Entity.Beverage;
import mkworld29.mobile.com.cafemoa_store.Entity.Order;
import mkworld29.mobile.com.cafemoa_store.Entity.StoredOrder;
import mkworld29.mobile.com.cafemoa_store.adapter.OrderInItemListAdapter;
import mkworld29.mobile.com.cafemoa_store.adapter.OrderListAdapter;
import mkworld29.mobile.com.cafemoa_store.retrofit.RetrofitConnection;
import mkworld29.mobile.com.cafemoa_store.retrofit.RetrofitInstance;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView lv_order = null;
    public static Activity mInstance;
    OrderListAdapter adapter = null;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        startNavigation();

        Intent intent=getIntent();

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                getData();
            }
        },500,10000);


        lv_order = (ListView) findViewById(R.id.lv_order);

        Utils.getInstance().setListViewHeightBasedOnChildren(lv_order);

        mInstance = this;
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


    private void startNavigation()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.getMenu().close();
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(false);
        ab.setDisplayHomeAsUpEnabled(false);
        ab.setDisplayShowCustomEnabled(false);
        ab.setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //drawer.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.menu_bar,getTheme()));
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);


    }


    private void getData()
    {
        adapter = new OrderListAdapter();

        retrofit= RetrofitInstance.getInstance(getApplicationContext());
        RetrofitConnection.get_orders service = retrofit.create(RetrofitConnection.get_orders.class);

        final Call<List<Order>> repos = service.repoContributors();
        repos.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (response.code() == 200) {
                    List<Order> orders= response.body();
                    int orders_num=orders.size();

                    for(int i=0; i<orders_num; i++){
                        Order order=orders.get(i);
                        order.adapter = new OrderInItemListAdapter();

                        List<Beverage> beverages=order.getBeverages();

                        adapter.addItem(order);

                        for(Beverage bev : beverages)
                        {
                            Log.d("MainActivity TAG",  bev.beverage_name);
                            order.adapter.addItem(bev);
                        }

                        Log.d("MainActivty TAG", "Order Fin" + order.getOrder_num());
                    }
                    lv_order.setAdapter(adapter);


                }
                else{
                    Toast.makeText(getApplicationContext(), "통신 에러 발생", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Log.d("TAG", t.getLocalizedMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
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

        if (id == R.id.nav_quit) {
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
        } else if (id == R.id.nav_help) {

        } else if(id==R.id.nav_receipt){
            Intent intent=new Intent(MainActivity.this,ReceiptActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("TAG","되나안되나보자444");
        if(adapter != null)
            adapter.onActivityResult(requestCode,resultCode,data);

        if (requestCode == 0) {
            if(resultCode == Activity.RESULT_OK){
                int position = data.getIntExtra("Position",-1);
                if(position != -1) {
                    adapter.remove(position);
                    adapter.notifyDataSetChanged();
                    Log.d("TAG","되나안되나보자");
                    // DB Manager
                    //data.getStringExtra("Item")

                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
            }
        }
        else if(requestCode == -1)
        {
            Log.d("TAG","되나안되나보자222");
        }
        else
        {
            Log.d("TAG","되나안되나보자");
        }
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


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }


    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            //listItem.measure(0, 0);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight;
        listView.setLayoutParams(params);
        listView.requestLayout();
    }


}