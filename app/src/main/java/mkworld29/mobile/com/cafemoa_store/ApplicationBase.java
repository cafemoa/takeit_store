package mkworld29.mobile.com.cafemoa_store;

import android.app.Application;

import com.tsengvn.typekit.Typekit;

/**
 * Created by parkjaemin on 2018. 1. 24..
 */

public class ApplicationBase extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this,"SDSwagger.otf"))
                .addBold(Typekit.createFromAsset(this,"SDSwagger.otf"));
    }
}
