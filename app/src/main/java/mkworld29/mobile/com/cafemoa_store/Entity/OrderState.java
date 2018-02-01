package mkworld29.mobile.com.cafemoa_store.Entity;

/**
 * Created by parkjaemin on 2018. 1. 24..
 */

public enum OrderState {
    READY(0), MAKING(1), DONE(2), END(3);

    private int value;
    private OrderState(int value){
        this.value = value;
    }
}
