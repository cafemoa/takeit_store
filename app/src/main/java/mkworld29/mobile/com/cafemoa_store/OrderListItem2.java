package mkworld29.mobile.com.cafemoa_store;

/**
 * Created by parkjaemin on 2017. 11. 15..
 */

public class OrderListItem2 {

    private String content;
    private CoffeOption option;
    private int wait_time;
    private int order_number;
    private int pk;

    public OrderListItem2(String content, int wait_time, int order_number, CoffeOption option, int pk)
    {
        this.content = content;
        this.wait_time = wait_time;
        this.order_number = order_number;
        this.option = option;
        this.pk=pk;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CoffeOption getOption() {
        return option;
    }

    public void setOption(CoffeOption option) {
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

    public void setOrder_number(int order_number) {
        this.order_number = order_number;
    }

    public int getPk() {return pk;}
}
