package nz.ac.unitec.restaurantordersystem.pojo;

import java.util.UUID;

/**
 * Created by Kay on 27/07/2016.
 */
public class Dish {
    //--field--
    private UUID mId;
    private int id;
    private String mName;
    private String mDescription;
    private float mPrice;
    private String mImage;
    private int categoryId;


    public Dish(){
        mId = UUID.randomUUID();
        mName = "Dish test";
        mDescription = "Description test";
        categoryId = 1;
    }

    public Dish(UUID id){
        mId = id;
        mName = "Dish test";
        mDescription = "Description test";
        categoryId = 22222222;
    }


    public UUID getId() {
        return mId;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public float getPrice() {
        return mPrice;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setPrice(float price) {
        mPrice = price;
    }

    public String getPhotoFilename() {
        return "IMG_" + getId().toString() + ".jpg";
    }

    public String toString(){
        return "12.5";
    }
}
