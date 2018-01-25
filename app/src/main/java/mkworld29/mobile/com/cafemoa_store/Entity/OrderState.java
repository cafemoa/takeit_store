package mkworld29.mobile.com.cafemoa_store.Entity;

/**
 * Created by parkjaemin on 2018. 1. 24..
 */

public enum OrderState {
    BEFORE(1), ING(2), AFTER(3);

    private int value;

    private OrderState(int value){
        this.value = value;
    }
}
