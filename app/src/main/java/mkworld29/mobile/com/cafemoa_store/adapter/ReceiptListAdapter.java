package mkworld29.mobile.com.cafemoa_store.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import mkworld29.mobile.com.cafemoa_store.DeleteDialog;
import mkworld29.mobile.com.cafemoa_store.Entity.Order;
import mkworld29.mobile.com.cafemoa_store.Entity.OrderState;
import mkworld29.mobile.com.cafemoa_store.Entity.StoredOrder;
import mkworld29.mobile.com.cafemoa_store.R;
import mkworld29.mobile.com.cafemoa_store.Utils;
import mkworld29.mobile.com.cafemoa_store.etc.DBManager;
import mkworld29.mobile.com.cafemoa_store.retrofit.RetrofitConnection;
import mkworld29.mobile.com.cafemoa_store.retrofit.RetrofitInstance;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by parkjaemin on 2017. 11. 15..
 */

public class ReceiptListAdapter extends BaseAdapter {
    private ArrayList<Order> listViewItemList = new ArrayList<>();

    public ReceiptListAdapter()
    {
        listViewItemList = new ArrayList<>();

    }

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void remove(int position){
        listViewItemList.remove(listViewItemList.get(position));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final Context context = parent.getContext();
        final View _convertView;
        final ViewHolder holder;
        final Order item = (Order) getItem(position);

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_order_list2, parent, false);

            holder = new ViewHolder();

            holder.tv_number            =   (TextView)convertView.findViewById(R.id.tv_order_number);
            holder.ly_order_state       =   (LinearLayout) convertView.findViewById(R.id.ly_order_state);
            holder.lv_content           =   (ListView)  convertView.findViewById(R.id.lv_content);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        _convertView = convertView;

        if(item!= null){
            holder.lv_content.setAdapter(item.getAdapter());
            holder.tv_number.setText(String.valueOf(listViewItemList.get(position).getOrder_num()));
        }

        Utils.getInstance().setListViewHeightBasedOnChildren(holder.lv_content);

        return convertView;
    }

    public ArrayList<Order> getListViewItemList()
    {
        return listViewItemList;
    }

    public void addItem(Order item)
    {
        listViewItemList.add(item);
    }

    private class ViewHolder {
        private TextView tv_number, tv_order_state;
        private ListView lv_content;
        private LinearLayout ly_order_state;
    }
}
