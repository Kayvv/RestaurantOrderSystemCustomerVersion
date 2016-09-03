package nz.ac.unitec.restaurantordersystem;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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
                String result = DishDBManager.getMenu();
                System.out.println(result);
            }
        }).start();
    }
}
