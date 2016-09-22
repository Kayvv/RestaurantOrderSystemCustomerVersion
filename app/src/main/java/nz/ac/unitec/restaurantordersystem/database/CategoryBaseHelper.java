package nz.ac.unitec.restaurantordersystem.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import nz.ac.unitec.restaurantordersystem.database.CategoryDbSchema.CategoryTable;

/**
 * Created by Kay on 16/08/2016.
 */
public class CategoryBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "dishBase.db";

    public CategoryBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ CategoryTable.NAME+"("+
                " _id integer primary key autoincrement, "+
                CategoryTable.Cols.UUID+", "+
                CategoryTable.Cols.NAME+")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
