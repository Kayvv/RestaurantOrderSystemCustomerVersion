package nz.ac.unitec.restaurantordersystem;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.text.NumberFormat;
import java.util.List;

import nz.ac.unitec.restaurantordersystem.pojo.Dish;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by Kay on 27/07/2016.
 */
public class DishListFragment extends Fragment {
    private static final String TAG = "DishListFragment";

    private StickyListHeadersListView mDishRecyclerView;
    private DishAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_dish_list,container,false);
        mDishRecyclerView = (StickyListHeadersListView)view.findViewById(R.id.dish_sticky_view);

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_dish_list, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView =
                (SearchView) MenuItemCompat.getActionView(searchItem);

        // Configure the search info and add any event listeners...


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



    private void updateUI(){
        DishLab dishLab = DishLab.get(getActivity());
        List<Dish> dishes = dishLab.getDishes();
        if (mAdapter == null) {
            mAdapter = new DishAdapter(dishes);
            mDishRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setDishes(dishes);
            mAdapter.notifyDataSetChanged();
        }
    }


    private class DishAdapter extends BaseAdapter implements StickyListHeadersAdapter {

        private List<Dish> mDishes;

        private DishLab dataLab;
        private DishListActivity mContext;
        private NumberFormat nf;
        private LayoutInflater mInflater;

        public DishAdapter(DishLab dataLab, DishListActivity mContext) {
            this.dataLab = dataLab;
            this.mContext = mContext;
            nf = NumberFormat.getCurrencyInstance();
            nf.setMaximumFractionDigits(2);
            mInflater = LayoutInflater.from(mContext);
        }

        public DishAdapter(List<Dish> dishes){
            mDishes = dishes;
        }

        public void setDishes(List<Dish> dishes) {
            mDishes = dishes;
        }

        @Override
        public View getHeaderView(int position, View convertView, ViewGroup parent) {
            if(convertView==null) {
                convertView = mInflater.inflate(R.layout.item_header_view, parent, false);
            }
            ((TextView)(convertView)).setText(dataLab.getDishes().get(position).getCategoryId());
            return convertView;
        }

        @Override
        public long getHeaderId(int position) {
            return 0;
        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }

    //define the ViewHolder by inner class
    private class DishHolder implements View.OnClickListener{
        private TextView mTitleTextView;
        private TextView mPriceTextView;
        private ImageView mPhotoView;
        private File mPhotoFile;

        private Dish mDish;

        public DishHolder(View itemView) {
            itemView.setOnClickListener(this);

            mPhotoView = (ImageView)
                    itemView.findViewById(R.id.dish_photo);
            mTitleTextView = (TextView)
                    itemView.findViewById(R.id.dish_title);
            mPriceTextView = (TextView)
                    itemView.findViewById(R.id.dish_price);
        }

        public void bindDish(Dish dish) {
            mDish = dish;
            mPhotoFile = DishLab.get(getActivity()).getPhotoFile(mDish);
            mTitleTextView.setText(mDish.getName());
            mPriceTextView.setText(mDish.toString());
            updatePhotoView();
        }

        @Override
        public void onClick(View v) {
            Intent intent = DishPagerActivity.newIntent(getActivity(), mDish.getId());
            startActivity(intent);
        }

        private void updatePhotoView(){
            if(mPhotoFile == null|| !mPhotoFile.exists()){
                mPhotoView.setImageDrawable(null);
            }else{
                Bitmap bitmap = PictureUtils.getScaledBitmap(
                        mPhotoFile.getPath(),getActivity());
                mPhotoView.setImageBitmap(bitmap);
            }
        }
    }


}
