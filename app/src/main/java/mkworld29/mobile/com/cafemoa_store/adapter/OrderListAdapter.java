package mkworld29.mobile.com.cafemoa_store.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mkworld29.mobile.com.cafemoa_store.R;
import mkworld29.mobile.com.cafemoa_store.item.OrderItem;
import mkworld29.mobile.com.cafemoa_store.retrofit.RetrofitConnection;
import mkworld29.mobile.com.cafemoa_store.retrofit.RetrofitInstance;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by parkjaemin on 2017. 8. 27..
 */

public class OrderListAdapter extends BaseAdapter{
    private ArrayList<OrderItem> listViewItemList = new ArrayList<OrderItem>() ;
    // ListViewAdapter의 생성자
    public OrderListAdapter() {

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_order_list, parent, false);
        }

        TextView tv_menu_name = (TextView) convertView.findViewById(R.id.tv_menu_name) ;
        TextView tv_sizes = (TextView) convertView.findViewById(R.id.tv_size) ;
        TextView tv_shots = (TextView) convertView.findViewById(R.id.tv_shots) ;
        Button   btn_commit = (Button) convertView.findViewById(R.id.btn_commit);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        OrderItem listViewItem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        tv_menu_name.setText(listViewItem.getMenu_name());
        tv_sizes.setText(String.valueOf(listViewItem.getSize()));
        tv_shots.setText(String.valueOf(listViewItem.getShots())+"샷");
        final int pk=listViewItem.getPk();

        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Retrofit retrofit = RetrofitInstance.getInstance(view.getContext());
                RetrofitConnection.complete_order service = retrofit.create(RetrofitConnection.complete_order.class);


                final Call<ResponseBody> repos = service.repoContributors(pk);
                repos.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        Toast.makeText(view.getContext(), "음료가 준비가 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("TAG", t.getLocalizedMessage());
                    }
                });
            }
        });

        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(String menu_name, String sizes, String shots, String is_whipping, String is_cold,int pk) {
        OrderItem item = new OrderItem();

        item.setMenu_name(menu_name);
        item.setSize(sizes);
        item.setShots(shots);
        item.setIs_whipping(is_whipping);
        item.setIs_cold(is_cold);
        item.setPk(pk);

        listViewItemList.add(item);
    }


}


