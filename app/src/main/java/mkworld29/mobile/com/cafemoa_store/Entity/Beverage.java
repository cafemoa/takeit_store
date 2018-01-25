package mkworld29.mobile.com.cafemoa_store.Entity;

import java.util.List;
import mkworld29.mobile.com.cafemoa_store.Entity.Selection;

/**
 * Created by parkjaemin on 2017. 11. 15..
 */
public class Beverage{
    public int size;
    public int amount;
    public int shot_num;
    public String beverage_name;
    public List<Selection> options;


    public Beverage()
    {
        this(0,0,0,null);
    }

    public Beverage(int size, int amount, int shot_num, String beverage_name) {
        this.size = size;
        this.amount = amount;
        this.shot_num = shot_num;
        this.beverage_name = beverage_name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getShot_num() {
        return shot_num;
    }
    public void setShot_num(int shot_num) {
        this.shot_num = shot_num;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public void setBeverage_name(String beverage_name){
        this.beverage_name=beverage_name;
    }
}
