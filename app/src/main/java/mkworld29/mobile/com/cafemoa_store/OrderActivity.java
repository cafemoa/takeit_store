package mkworld29.mobile.com.cafemoa_store;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import mkworld29.mobile.com.cafemoa_store.adapter.OrderListAdapter;

public class OrderActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private ListView lv_order_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        SwipeRefreshLayout mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);


        lv_order_list = (ListView) findViewById(R.id.lv_order_list);

        OrderListAdapter adapter = new OrderListAdapter();

        adapter.addItem("아메리카노",3,2,true,true );
        adapter.addItem("아메리카노",2,4,true,false );
        adapter.addItem("아메리카노",2,4,true,false );

        lv_order_list.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        // 새로고침 소스
    }
}
