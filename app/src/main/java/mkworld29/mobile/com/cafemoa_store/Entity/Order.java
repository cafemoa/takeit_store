package mkworld29.mobile.com.cafemoa_store.Entity;

import java.util.List;

import mkworld29.mobile.com.cafemoa_store.adapter.OrderInItemListAdapter;
import mkworld29.mobile.com.cafemoa_store.adapter.ReceiptInItemListAdapter;
import mkworld29.mobile.com.cafemoa_store.adapter.ReceiptListAdapter;

/**
 * Created by ABCla on 2018-01-22.
 */

public class Order{
    public int pk;
    public String order_time;
    public int order_num;
    public int payment_type;
    public String orderer_username;
    public List<Beverage> beverages;
    public OrderInItemListAdapter adapter;
    public ReceiptInItemListAdapter receiptInItemListAdapter;

    public int state;


    public Order()
    {

    }



    public Order(int pk, String order_time, int order_num, int payment_type, String orderer_username, List<Beverage> beverages, OrderInItemListAdapter adapter) {
        this.pk = pk;
        this.order_time = order_time;
        this.order_num = order_num;
        this.payment_type = payment_type;
        this.orderer_username = orderer_username;
        this.beverages = beverages;
        this.adapter = adapter;
    }

    public Order(StoredOrder storedOrder){
        this.pk=storedOrder.pk;
        this.order_time=storedOrder.order_time;
        this.order_num=storedOrder.order_num;
        this.payment_type=storedOrder.payment_type;
        this.orderer_username=storedOrder.orderer_username;
        this.beverages=storedOrder.beverages;
    }

    public OrderInItemListAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(OrderInItemListAdapter adapter) {
        this.adapter = adapter;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public int getOrder_num() {
        return order_num;
    }

    public void setOrder_num(int order_num) {
        this.order_num = order_num;
    }

    public int getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(int payment_type) {
        this.payment_type = payment_type;
    }

    public String getOrderer_username() {
        return orderer_username;
    }

    public void setOrderer_username(String orderer_username) {
        this.orderer_username = orderer_username;
    }

    public List<Beverage> getBeverages() {
        return beverages;
    }

    public void setBeverages(List<Beverage> beverages) {
        this.beverages = beverages;
    }
    public int getState(){ return state; }
}
