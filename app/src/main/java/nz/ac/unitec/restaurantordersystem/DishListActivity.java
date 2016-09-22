package nz.ac.unitec.restaurantordersystem;

import android.app.Fragment;
import android.util.SparseArray;
import android.util.SparseIntArray;

import nz.ac.unitec.restaurantordersystem.pojo.Category;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by Kay on 27/07/2016.
 */
public class DishListActivity extends BaseActivity{
    private SparseArray<Category> selectedList;
    private SparseIntArray groupSelect;
    private StickyListHeadersListView listView;

    @Override
    public Fragment createFragment() {
        groupSelect = new SparseIntArray();
        Fragment fg = new DishListFragment();
        return fg;


    }

    //根据类别Id获取属于当前类别的数量
    public int getSelectedGroupCountByTypeId(int typeId){
        return groupSelect.get(typeId);
    }

    public int getSelectedItemCountById(int id){
        Category temp = selectedList.get(id);
        if(temp==null){
            return 0;
        }
        return temp.count;
    }

    public void onTypeClicked(int typeId){
        listView.setSelection(getSelectedPosition(typeId));
    }
}
