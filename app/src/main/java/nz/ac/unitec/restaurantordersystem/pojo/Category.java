package nz.ac.unitec.restaurantordersystem.pojo;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Kay on 2016/9/22.
 */
public class Category {
    public int id;
    public int typeId;
    public int rating;
    public String name;
    public String typeName;
    public double price;
    public int count;

    public Category(int id, double price, String name, int typeId, String typeName) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.typeId = typeId;
        this.typeName = typeName;
        rating = new Random().nextInt(5)+1;
    }

    private static ArrayList<Category> goodsList;
    private static ArrayList<Category> typeList;

    private static void initData(){
        goodsList = new ArrayList<>();
        typeList = new ArrayList<>();
        Category item = null;
        for(int i=1;i<15;i++){
            for(int j=1;j<10;j++){
                item = new Category(100*i+j,Math.random()*100,"商品"+(100*i+j),i,"种类"+i);
                goodsList.add(item);
            }
            typeList.add(item);
        }
    }

    public static ArrayList<Category> getGoodsList(){
        if(goodsList==null){
            initData();
        }
        return goodsList;
    }
    public static ArrayList<Category> getTypeList(){
        if(typeList==null){
            initData();
        }
        return typeList;
    }
}
