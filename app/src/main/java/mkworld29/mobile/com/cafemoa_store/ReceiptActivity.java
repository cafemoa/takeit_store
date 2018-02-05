package mkworld29.mobile.com.cafemoa_store;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import mkworld29.mobile.com.cafemoa_store.Entity.Beverage;
import mkworld29.mobile.com.cafemoa_store.Entity.Order;
import mkworld29.mobile.com.cafemoa_store.Entity.StoredOrder;
import mkworld29.mobile.com.cafemoa_store.adapter.ReceiptInItemListAdapter;
import mkworld29.mobile.com.cafemoa_store.adapter.ReceiptListAdapter;
import mkworld29.mobile.com.cafemoa_store.etc.DBManager;

/**
 * Created by myhome on 2018-02-01.
 */

public class ReceiptActivity extends AppCompatActivity {

    private ListView listView;
    private ImageView iv_back;

    private ArrayList<Order> receiptList;

    ReceiptListAdapter receiptListAdapter=null;

    private ListView lv_receipt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_order_receipt);

        lv_receipt=(ListView) findViewById(R.id.lv_receipt_list);

        DBManager.getInstance().initHelper(getApplicationContext());

        ArrayList<String> list = DBManager.getInstance().getReceiptList();
        receiptList = new ArrayList<Order>();

        receiptListAdapter=new ReceiptListAdapter();

        for (int i = 0; i < list.size(); i++) {
            Order order=new Order(Utils.getInstance().StringToOrder(list.get(i)));

            order.receiptInItemListAdapter=new ReceiptInItemListAdapter();

            receiptList.add(order);


            List<Beverage> beverages = receiptList.get(i).getBeverages();


            receiptListAdapter.addItem(order);

            for (Beverage bev : beverages) {
                Log.d("MainActivity TAG", bev.beverage_name);
                receiptList.get(i).receiptInItemListAdapter.addItem(bev);
            }
        }
        lv_receipt.setAdapter(receiptListAdapter);

        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
