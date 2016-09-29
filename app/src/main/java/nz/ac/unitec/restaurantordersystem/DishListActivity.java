package nz.ac.unitec.restaurantordersystem;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;

import nz.ac.unitec.restaurantordersystem.pojo.Category;
import nz.ac.unitec.restaurantordersystem.pojo.Dish;
import nz.ac.unitec.restaurantordersystem.service.DishDBManager;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by Kay on 27/07/2016.
 */
public class DishListActivity extends Activity{
    private ImageView imgCart;
    private ViewGroup anim_mask_layout;
    private RecyclerView rvType,rvSelected;
    private TextView tvCount,tvCost,tvSubmit,tvTips;
    private View bottomSheet;
    private StickyListHeadersListView listView;


    private CategoryLab typeList;
    private DishLab dataList;
    private SparseArray<Dish> selectedList;
    private SparseIntArray groupSelect;

//    private GoodsAdapter myAdapter;
//    private SelectAdapter selectAdapter;
//    private TypeAdapter typeAdapter;

    private NumberFormat nf;
    private Handler mHanlder;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        DishLab dishLab = DishLab.get(DishListActivity.this);
        Dish d = new Dish();
        dishLab.addDish(d);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                CategoryLab categoryLab = CategoryLab.get(DishListActivity.this);
//                Category c = new Category();
//                String result = DishDBManager.getMenu();
//                System.out.println(result);
//                try {
//                    JSONArray jAry = new JSONArray(result);
//                    for (int i = 0; i < jAry.length();i++){
//                        JSONObject jObj = jAry.getJSONObject(i);
//                    String categoryID = jObj.getString("categoryID");
//                    String name = jObj.getString("categoryName");
//                    c.setName(name);
//                        categoryLab.addCategory(c);
//                    System.out.println(categoryID);
//                    System.out.println(name);}
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

        initView();
    }

    private void initView() {
        tvCount = (TextView) findViewById(R.id.tvCount);
        tvCost = (TextView) findViewById(R.id.tvCost);
        tvTips = (TextView) findViewById(R.id.tvTips);
        tvSubmit = (TextView) findViewById(R.id.tvSubmit);
        rvType = (RecyclerView) findViewById(R.id.categoryRecyclerView);

        imgCart = (ImageView) findViewById(R.id.imgCart);
        anim_mask_layout = (RelativeLayout) findViewById(R.id.containerLayout);

        listView = (StickyListHeadersListView) findViewById(R.id.dish_sticky_view);

//        rvType.setLayoutManager(new LinearLayoutManager(this));
//        typeAdapter = new TypeAdapter(this, typeList);
//        rvType.setAdapter(typeAdapter);
//        rvType.addItemDecoration(new DividerDecoration(this));
//
//        myAdapter = new GoodsAdapter(dataList, this);
//        listView.setAdapter(myAdapter);

//        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                Dish item = dataList.getDishes().get(firstVisibleItem);
//                if (typeAdapter.selectTypeId != item.typeId) {
//                    typeAdapter.selectTypeId = item.typeId;
//                    typeAdapter.notifyDataSetChanged();
//                    rvType.smoothScrollToPosition(getSelectedGroupPosition(item.typeId));
//                }
//            }
//        });
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
