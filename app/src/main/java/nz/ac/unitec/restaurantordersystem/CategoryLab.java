package nz.ac.unitec.restaurantordersystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import nz.ac.unitec.restaurantordersystem.database.CategoryBaseHelper;
import nz.ac.unitec.restaurantordersystem.database.CategoryCursorWrapper;
import nz.ac.unitec.restaurantordersystem.database.CategoryDbSchema.CategoryTable;
import nz.ac.unitec.restaurantordersystem.pojo.Category;

/**
 * Created by Kay on 27/07/2016.
 */
public class CategoryLab {
    private static CategoryLab sCategoryLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    private CategoryLab(Context appContext) {
        mContext = appContext.getApplicationContext();
        mDatabase = new CategoryBaseHelper(mContext).getWritableDatabase();
    }

    public static CategoryLab get(Context context){
        if(sCategoryLab == null){
            sCategoryLab = new CategoryLab(context.getApplicationContext());
        }
        return sCategoryLab;
    }

    public void addCategory(Category d){
        ContentValues values = getContentValues(d);
        mDatabase.insert(CategoryTable.NAME,null,values);
    }

    public List<Category> getCategories(){
        List<Category> Categories = new ArrayList<>();
        CategoryCursorWrapper cursor = queryCategoryes(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Categories.add(cursor.getCategory());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return Categories;
    }

    public Category getCategory(UUID id){
        CategoryCursorWrapper cursor = queryCategoryes(
                CategoryTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getCategory();
        } finally {
            cursor.close();
        }
    }


    public void updateCategory(Category Category) {
        String uuidString = Category.getId().toString();
        ContentValues values = getContentValues(Category);
        mDatabase.update(CategoryTable.NAME, values,
                CategoryTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

    private static ContentValues getContentValues(Category Category){
        ContentValues values = new ContentValues();
        values.put(CategoryTable.Cols.UUID,Category.getId().toString());
        values.put(CategoryTable.Cols.NAME,Category.getName());

        return values;
    }


    private CategoryCursorWrapper queryCategoryes(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                CategoryTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new CategoryCursorWrapper(cursor);
    }
}
