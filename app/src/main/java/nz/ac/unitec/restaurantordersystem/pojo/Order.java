package nz.ac.unitec.restaurantordersystem.pojo;

import java.util.ArrayList;
import java.util.UUID;

import nz.ac.unitec.restaurantordersystem.pojo.Dish;

/**
 * Created by Kay on 27/07/2016.
 */
public class Order {
    //--field--
    private UUID mId;
    private ArrayList<Dish> mOrderedDish;
    private float mTotaPrice;
    private String mState;
    private Boolean mPaid;


}
