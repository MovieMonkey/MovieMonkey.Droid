package gruppenprojekt.mobpro.hslu.moviemanager.DataAccesses;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class BasicDataAccess extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "movieManager.db";

    private static final int DATABASE_VERSION = 1;

    //Tables
    public static final String TABLE_MOVIES = "Movies";

    //------------ Columns ------------
    //Shared
    public static final String KEY_ID = "Id";
    public static final String KEY_NAME = "Name";

    public BasicDataAccess(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
    }

    //------------ SQL Queries ------------

    private static String CREATE_MOVIE_TABLE = "CREATE TABLE " + TABLE_MOVIES + "(" +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_NAME + " TEXT " +
            ")";
}