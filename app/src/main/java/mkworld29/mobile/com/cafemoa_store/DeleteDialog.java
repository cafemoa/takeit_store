package mkworld29.mobile.com.cafemoa_store;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.google.gson.Gson;

import mkworld29.mobile.com.cafemoa_store.Entity.Order;
import mkworld29.mobile.com.cafemoa_store.Entity.StoredOrder;
import mkworld29.mobile.com.cafemoa_store.etc.DBManager;
import okhttp3.internal.Util;

/**
 * Created by ABCla on 2017-11-14.
 */

public class DeleteDialog extends Activity implements View.OnClickListener{

    private String id;
    private int position;
    private StoredOrder item;
    private TextView tv_order_number, tv_order_content, tv_order_delete_cancel, tv_order_delete_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_bottom);

        Intent intent = getIntent();

        if(intent != null)
        {
            Gson gson  = new Gson();
            item = gson.fromJson(intent.getStringExtra("Item"),StoredOrder.class);
            position = intent.getIntExtra("Position",-1);
        }

        id=getIntent().getStringExtra("Content");

        tv_order_content = (TextView)findViewById(R.id.tv_order_content);
        tv_order_number = (TextView)findViewById(R.id.tv_order_number);
        tv_order_delete_cancel = (TextView)findViewById(R.id.tv_order_delete_cancel);
        tv_order_delete_ok = (TextView)findViewById(R.id.tv_order_delete_ok);

        tv_order_delete_cancel.setOnClickListener(this);
        tv_order_delete_ok.setOnClickListener(this);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()== MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == tv_order_delete_ok.getId())
        {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("Position", position);
            returnIntent.putExtra("Item", Utils.getInstance().OrderToString(item));
            setResult(Activity.RESULT_OK,returnIntent);
            finish();
        }
        else if(v.getId() == tv_order_delete_cancel.getId())
        {
            finish();
        }
    }
}
