package nz.ac.unitec.restaurantordersystem.pojo;

import java.util.UUID;

/**
 * Created by Kay on 2016/9/22.
 */
public class Category {
    //--field--
    private UUID mId;
    private String mName;


    public Category(){
        mId = UUID.randomUUID();
        mName = "Dish test";
    }

    public Category(UUID id){
        mId = id;
        mName = "Dish test";
    }


    public UUID getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }


}
