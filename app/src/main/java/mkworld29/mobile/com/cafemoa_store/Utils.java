package mkworld29.mobile.com.cafemoa_store;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by parkjaemin on 2018. 1. 25..
 */

public class Utils {

    private static Utils mInstance;

    public Utils()
    {

    }

    public static Utils getInstance()
    {
        if(mInstance == null)
        {
            mInstance = new Utils();
            return mInstance;
        }
        else
            return mInstance;
    }

    public  void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            Log.d("TAG!TAG!!!!","RETURN!!!!");
            return;
        }

        int totalHeight = 0;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            //listItem.measure(0, 0);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += 350;
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight;
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
