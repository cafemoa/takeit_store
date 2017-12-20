package mkworld29.mobile.com.cafemoa_store;


import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.RequiresPermission;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().getDecorView().setBackgroundResource(R.drawable.store_splash);

        SharedPreferences pref = getSharedPreferences("VER", 0);

        try {
            PackageManager pm = this.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(getPackageName(), 0);
            int version = pi.versionCode;
            int old_ver = pref.getInt("version", 0);

            if(true) {
                SharedPreferences.Editor edit = pref.edit();
                edit.putInt("version",  version);
                edit.commit();
                Intent it = new Intent(this, TutorialActivity.class);
                startActivity(it);
                finish();
            } else{
                startSplash();
            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void startSplash()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, 3000);
    }

}
