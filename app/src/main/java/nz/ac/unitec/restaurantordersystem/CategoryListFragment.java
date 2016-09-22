package nz.ac.unitec.restaurantordersystem;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import nz.ac.unitec.restaurantordersystem.pojo.Category;
import nz.ac.unitec.restaurantordersystem.pojo.Dish;

/**
 * Created by Kay on 27/07/2016.
 */
public class CategoryListFragment extends Fragment {
    private static final String TAG = "CategoryListFragment";

    private RecyclerView mDishRecyclerView;
    private TypeAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_dish_list,container,false);
        mDishRecyclerView = (RecyclerView)view.findViewById(R.id.dish_recycler_view);
        mDishRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

       // updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
      //  updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



//    private void updateUI(){
//        DishLab dishLab = DishLab.get(getActivity());
//        List<Dish> dishes = dishLab.getDishes();
//        if (mAdapter == null) {
//            mAdapter = new TypeAdapter(dishes);
//            mDishRecyclerView.setAdapter(mAdapter);
//        } else {
//            mAdapter.setDishes(dishes);
//            mAdapter.notifyDataSetChanged();
//        }
//    }


    public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.ViewHolder> {
        public int selectTypeId;
        public DishListActivity activity;
        public ArrayList<Category> dataList;

        public TypeAdapter(DishListActivity activity, ArrayList<Category> dataList) {
            this.activity = activity;
            this.dataList = dataList;
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type, parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Category item = dataList.get(position);

            holder.bindData(item);
        }

        @Override
        public int getItemCount() {
            if (dataList == null) {
                return 0;
            }
            return dataList.size();
        }

        //define the ViewHolder by inner class
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView tvCount, type;
            private Category item;

            public ViewHolder(View itemView) {
                super(itemView);
                tvCount = (TextView) itemView.findViewById(R.id.tvCount);
                type = (TextView) itemView.findViewById(R.id.type);
                itemView.setOnClickListener(this);
            }

            public void bindData(Category item) {
                this.item = item;
                type.setText(item.typeName);
                int count = activity.getSelectedGroupCountByTypeId(item.typeId);
                tvCount.setText(String.valueOf(count));
                if (count < 1) {
                    tvCount.setVisibility(View.GONE);
                } else {
                    tvCount.setVisibility(View.VISIBLE);
                }
                if (item.typeId == selectTypeId) {
                    itemView.setBackgroundColor(Color.WHITE);
                } else {
                    itemView.setBackgroundColor(Color.TRANSPARENT);
                }

            }

//        public void bindCategory(Dish dish) {
//            mDish = dish;
//            mPhotoFile = DishLab.get(getActivity()).getPhotoFile(mDish);
//            mTitleTextView.setText(mDish.getName());
//            mPriceTextView.setText(mDish.toString());
//        }

            @Override
            public void onClick(View v) {
                activity.onTypeClicked(item.typeId);
            }


        }
    }

}
