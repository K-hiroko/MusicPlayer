package jp.techinstitute.ti_021.musicplayer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by TI-021 on 2015/01/24.
 */
public class CreateProductHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    public CreateProductHelper(Context context) {
        super(context, DBConst.DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
