package nz.ac.unitec.restaurantordersystem.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import nz.ac.unitec.restaurantordersystem.database.DishDbSchema.DishTable;
import nz.ac.unitec.restaurantordersystem.pojo.Category;

/**
 * Created by Kay on 2016/8/19.
 */
public class CategoryCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public CategoryCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Category getCategory() {
        String uuidString = getString(getColumnIndex(DishTable.Cols.UUID));
        String name = getString(getColumnIndex(DishTable.Cols.NAME));

        Category dish = new Category(UUID.fromString(uuidString));
        dish.setName(name);
        return dish;
    }
}
