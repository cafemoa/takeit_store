package mkworld29.mobile.com.cafemoa_store.etc;

/**
 * Created by myhome on 2018-01-24.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by 건후 on 2015-08-25.
 */
public class DBManager {

    public static DBManager mInstance;
    public MySQLiteHelper helper;
    public SQLiteDatabase db;
    public static boolean state = false;

    public static DBManager getInstance(){
        if(mInstance == null){
            mInstance = new DBManager();
            state = true;
        }
        return mInstance;
    }

    public void initHelper(Context context) {
        if(helper == null) {
            helper = new MySQLiteHelper(context, "takeit.db", null, 1);
            Log.d("Init Helper Success", "GOODD!!!");
        }
        else {
            return;
        }

    }

    public void insert(String item)
    {
        String table = "RECEIPT";
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("item", item);
        db.insert(table,null, values);
    }

    public void update(int target, String text)
    {
//        db = helper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("text", text);
//        db.update("STELLA",
//                values,
//                "num=?",
//                new String[]{String.valueOf(target)});
//        Log.d("Update " + target + "Success", text);
    }

    public void delete(int target)
    {
//        db = helper.getWritableDatabase();
//        db.delete("STELLA",
//                "day=?",
//                new String[]{String.valueOf(target)});
//        Log.d("SQLITE DELETE", String.valueOf(target));
    }

//    public ArrayList<String> getReceipt()
//    {
//        db = helper.getReadableDatabase();
//        Cursor c = db.query("RECEIPT",
//                null,
//                null,
//                null,
//                null,
//                null,
//                null);
//
//        ArrayList<String[]> result=new ArrayList<String[]>();
//
//        while(c.moveToNext())
//        {
//            String item = c.getString(0);
//            String time = c.getString(1);
//            String temp[]={item,time};
//            result.add(temp);
//        }
//
//        return result;
//    }

    public ArrayList<String> getReceiptList()
    {
        db = helper.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT item FROM RECEIPT order by id DESC", null);

        ArrayList<String> receiptList=new ArrayList<String>();

        result.moveToFirst();
        while (!result.isAfterLast()){
            String item = result.getString(1);
            receiptList.add(item);
            result.moveToNext();
        }

        result.close();
        return receiptList;
    }
}
