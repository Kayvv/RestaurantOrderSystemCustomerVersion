package nz.ac.unitec.restaurantordersystem;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import nz.ac.unitec.restaurantordersystem.service.DishDBManager;

/**
 * Created by Kay on 27/07/2016.
 */
public abstract class BaseActivity extends AppCompatActivity {
    public abstract Fragment createFragment();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        FragmentManager fm = getFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                Dish d = new Dish();
                String result = DishDBManager.getMenu();
                System.out.println(result);
                try {
                    JSONObject jObj = new JSONObject(result);
                    String description = jObj.getString("message");
                    String name = jObj.getString("aa");
                    d.setName(name);
                    d.setDescription(description);
                    //dishLab.addDish(d);
                    System.out.println(description);
                    System.out.println(name);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
