package mkworld29.mobile.com.cafemoa_store;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import mkworld29.mobile.com.cafemoa_store.adapter.OrderListAdapter;
import mkworld29.mobile.com.cafemoa_store.retrofit.RetrofitConnection.*;
import mkworld29.mobile.com.cafemoa_store.retrofit.RetrofitConnection;
import mkworld29.mobile.com.cafemoa_store.retrofit.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


import java.util.List;

public class OrderActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private ListView lv_order_list;
    private Retrofit retrofit;
    private OrderListAdapter adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        retrofit= RetrofitInstance.getInstance(getApplicationContext());

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);


        lv_order_list = (ListView) findViewById(R.id.lv_order_list);

        onRefresh();
    }

    @Override
    public void onRefresh() {
        // 새로고침 소스
        adapter = new OrderListAdapter();

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
                        List<Option> options=order.options;
                        int option_num=options.size();
                        for(int j=0; j<option_num; j++){
                            Option option=options.get(j);
                            String order_num="주문번호 : "+order.order_num;
                            String shots="샷 : "+option.shot_num;
                            String is_ice="얼음 : "+option.is_ice;
                            String whipping_cream="휘핑크림 : "+option.whipping_cream;

                            adapter.addItem(option.beverage_name,order_num,shots,is_ice,whipping_cream);
                        }
                    }
                    lv_order_list.setAdapter(adapter);
                }
                else{
                    Toast.makeText(getApplicationContext(), "아이디와 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Log.d("TAG", t.getLocalizedMessage());
            }
        });
        mSwipeRefreshLayout.setRefreshing(false);
    }


}
