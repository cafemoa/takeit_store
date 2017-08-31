package mkworld29.mobile.com.cafemoa_store.item;

/**
 * Created by parkjaemin on 2017. 8. 27..
 */

public class OrderItem {
    private String size, shots;
    private String is_whipping, is_cold;
    private String menu_name;
    private int pk;

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getShots() {
        return shots;
    }

    public void setShots(String shots) {
        this.shots = shots;
    }

    public String is_whipping() {
        return is_whipping;
    }

    public void setIs_whipping(String is_whipping) {
        this.is_whipping = is_whipping;
    }

    public String is_cold() {
        return is_cold;
    }

    public void setIs_cold(String is_cold) {
        this.is_cold = is_cold;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }
}
