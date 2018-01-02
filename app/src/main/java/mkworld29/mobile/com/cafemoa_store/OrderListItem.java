package mkworld29.mobile.com.cafemoa_store;

/**
 * Created by parkjaemin on 2017. 11. 15..
 */

public class OrderListItem {

    private String content;
    private CoffeeOption option;
    private int wait_time;
    private int order_number;
    private int beverage_pk;

    public OrderListItem(String content, int wait_time, int order_number, CoffeeOption option, int beverage_pk)
    {
        this.content = content;
        this.wait_time = wait_time;
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

    public CoffeeOption getOption() {
        return option;
    }

    public void setOption(CoffeeOption option) {
        this.option = option;
    }

    public int getWait_time() {
        return wait_time;
    }

    public void setWait_time(int wait_time) {
        this.wait_time = wait_time;
    }

    public int getOrder_number() {
        return order_number;
    }
    public int getBeverage_pk(){return beverage_pk;}

    public void setOrder_number(int order_number) {
        this.order_number = order_number;
    }
}
