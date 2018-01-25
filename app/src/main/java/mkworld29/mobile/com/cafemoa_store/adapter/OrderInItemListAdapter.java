package mkworld29.mobile.com.cafemoa_store.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;

import java.util.ArrayList;

import mkworld29.mobile.com.cafemoa_store.Entity.Beverage;
import mkworld29.mobile.com.cafemoa_store.Entity.Order;
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
 * Created by parkjaemin on 2018. 1. 24..
 */

public class OrderInItemListAdapter extends BaseAdapter {
    private ArrayList<Beverage> listViewItemList ;

    public OrderInItemListAdapter() {

        listViewItemList = new ArrayList<Beverage>();
    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();
        OrderInItemListAdapter.ViewHolder holder = null;

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_order_list2, parent, false);


            holder = new ViewHolder();

            holder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            holder.tv_amount = (TextView) convertView.findViewById(R.id.tv_amount);
            holder.tv_isMulti = (TextView) convertView.findViewById(R.id.tv_isMulti);
            holder.tv_option = (TextView) convertView.findViewById(R.id.tv_option);

            convertView.setTag(holder);
        }else{
            holder = (OrderInItemListAdapter.ViewHolder) convertView.getTag();
        }

        final Beverage item = (Beverage) getItem(position);

        if(item!= null){

            String str_content = "sibal";
            String str_option="sibal";
            String str_amount = String.valueOf(item.getAmount());


            holder.tv_content.setText(str_content);
            holder.tv_option.setText(str_option);
            holder.tv_isMulti.setVisibility(View.VISIBLE);
            holder.tv_amount.setText(str_amount);
        }

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
    public void addItem(Beverage bev){
    }


    private class ViewHolder {
        private TextView tv_content, tv_isMulti, tv_amount, tv_option;
    }


}


