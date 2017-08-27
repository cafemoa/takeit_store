package mkworld29.mobile.com.cafemoa_store.item;

/**
 * Created by parkjaemin on 2017. 8. 27..
 */

public class OrderItem {
    private int size, shots;
    private boolean is_whipping, is_cold;
    private String menu_name;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getShots() {
        return shots;
    }

    public void setShots(int shots) {
        this.shots = shots;
    }

    public boolean is_whipping() {
        return is_whipping;
    }

    public void setIs_whipping(boolean is_whipping) {
        this.is_whipping = is_whipping;
    }

    public boolean is_cold() {
        return is_cold;
    }

    public void setIs_cold(boolean is_cold) {
        this.is_cold = is_cold;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }
}
