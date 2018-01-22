package mkworld29.mobile.com.cafemoa_store;

import java.util.List;

/**
 * Created by parkjaemin on 2017. 11. 15..
 */
public class CoffeeOption{
    public int size;
    public int shot_num;
    public String beverage_name;
    public List<Selection> options;

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
