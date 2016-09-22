package nz.ac.unitec.restaurantordersystem;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import nz.ac.unitec.restaurantordersystem.pojo.Category;
import nz.ac.unitec.restaurantordersystem.service.DishDBManager;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by Kay on 27/07/2016.
 */
public class DishListActivity extends Activity{
    private SparseArray<Category> selectedList;
    private SparseIntArray groupSelect;
    private StickyListHeadersListView listView;
    private ArrayList<Category> dataList,typeList;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        new Thread(new Runnable() {
            @Override
            public void run() {
                CategoryLab categoryLab = CategoryLab.get(DishListActivity.this);
                Category c = new Category();
                String result = DishDBManager.getMenu();
                System.out.println(result);
                try {
                    JSONArray jAry = new JSONArray(result);
                    for (int i = 0; i < jAry.length();i++){
                        JSONObject jObj = jAry.getJSONObject(i);
                    String description = jObj.getString("categoryID");
                    String name = jObj.getString("categoryName");
                    c.setName(name);
                        categoryLab.addCategory(c);
                    System.out.println(description);
                    System.out.println(name);}
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.fragment_dish_list, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView =
                (SearchView) MenuItemCompat.getActionView(searchItem);

        // Configure the search info and add any event listeners...
        return true;
    }


}
