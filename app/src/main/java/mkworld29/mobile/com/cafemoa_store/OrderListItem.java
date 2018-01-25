package mkworld29.mobile.com.cafemoa_store;


import mkworld29.mobile.com.cafemoa_store.Entity.Beverage;

/**
 * Created by parkjaemin on 2017. 11. 15..
 */

public class OrderListItem {

    private String content;
    private Beverage option;
    private int order_number;
    private int beverage_pk;

    public OrderListItem(String content, int order_number, Beverage option, int beverage_pk)
    {
        this.content = content;
        this.order_number = order_number;
        this.option = option;
        this.beverage_pk=beverage_pk;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Beverage getOption() {
        return option;
    }

    public void setOption(Beverage option) {
        this.option = option;
    }


    public int getOrder_number() {
        return order_number;
    }
    public int getBeverage_pk(){return beverage_pk;}

    public void setOrder_number(int order_number) {
        this.order_number = order_number;
    }
}
