package mkworld29.mobile.com.cafemoa_store.etc;

/**
 * Created by myhome on 2018-01-24.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by 건후 on 2015-08-25.
 */
public class MySQLiteHelper extends SQLiteOpenHelper{
    public MySQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BOOK_TABLE = "CREATE TABLE IF NOT EXISTS RECEIPT" +
                " ( id INTEGER PRIMARY KEY autoincrement, " +
                "item TEXT NOT NULL );";

        Log.d("SQLITE OnCreate", "Success");
        db.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS RECEIPT";
        db.execSQL(sql);
        onCreate(db);
    }
}
