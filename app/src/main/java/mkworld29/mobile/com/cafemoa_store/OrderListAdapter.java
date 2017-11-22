package mkworld29.mobile.com.cafemoa_store;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by parkjaemin on 2017. 11. 15..
 */

public class OrderListAdapter extends BaseAdapter {
    private final ViewBinderHelper binderHelper;
    public ArrayList<OrderListItem> listViewItemList = new ArrayList<>();


    public OrderListAdapter()
    {
        listViewItemList = new ArrayList<>();
        binderHelper = new ViewBinderHelper();
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

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();
        ViewHolder holder;

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_order_list2, parent, false);

            holder = new ViewHolder();

            holder.tv_content           =   (TextView)convertView.findViewById(R.id.tv_content);
            holder.tv_option            =   (TextView)convertView.findViewById(R.id.tv_option);
            holder.tv_wait_time         =   (TextView)convertView.findViewById(R.id.tv_wait_time);
            holder.tv_number            =   (TextView)convertView.findViewById(R.id.tv_order_number);
            holder.swipeLayout          =   (SwipeRevealLayout)convertView.findViewById(R.id.srl_order);
            holder.deleteView           =   convertView.findViewById(R.id.delete_view);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        final OrderListItem item = (OrderListItem) getItem(position);

        if(item!= null){
            binderHelper.bind(holder.swipeLayout, item.toString());

            CoffeOption option = listViewItemList.get(position).getOption();
            String str_option = String.valueOf(option.getSize()) + "/";

            if(option.getSize() == 0)
                str_option += "샷 추가 없음";
            else
                str_option += String.valueOf(option.getSize()) + "샷";

            str_option += (option.is_whipping())?"휘핑 추가" : "";

            String str_content = listViewItemList.get(position).getContent();

            if(option.is_cold())
                str_content += " (ICE)";
            else str_content += " (HOT)";

            holder.tv_content.setText(str_content);
            holder.tv_number.setText(String.valueOf(listViewItemList.get(position).getOrder_number()));
            holder.tv_option.setText(str_option);
            holder.tv_wait_time.setText(String.valueOf(listViewItemList.get(position).getWait_time()));

            holder.deleteView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    remove(position);
                    notifyDataSetChanged();
                }
            });
        }



        return convertView;
    }

    public ArrayList<OrderListItem> getListViewItemList()
    {
        return listViewItemList;
    }

    public void addItem(String content, int wait_time, int order_number, CoffeOption option)
    {
        OrderListItem item = new OrderListItem(content, wait_time, order_number, option);
        listViewItemList.add(item);
    }


    public void addItem(OrderListItem item)
    {
        listViewItemList.add(item);
    }


    public void saveStates(Bundle outState) {
        binderHelper.saveStates(outState);
    }

    /**
     * Only if you need to restore open/close state when the orientation is changed.
     * Call this method in {@link android.app.Activity#onRestoreInstanceState(Bundle)}
     */
    public void restoreStates(Bundle inState) {
        binderHelper.restoreStates(inState);
    }

    private class ViewHolder {
        private TextView tv_content, tv_option, tv_wait_time, tv_number;
        private View deleteView;
        private SwipeRevealLayout swipeLayout;
    }
}
