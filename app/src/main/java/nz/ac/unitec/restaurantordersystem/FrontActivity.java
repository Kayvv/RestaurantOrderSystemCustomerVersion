package nz.ac.unitec.restaurantordersystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Kay on 2016/9/11.
 */
public class FrontActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);
        bindView();
    }

    public void bindView(){
        Button viewMenu = (Button)findViewById(R.id.view_menu);
        Button findUs = (Button)findViewById(R.id.view_menu);
        Button bookTable = (Button)findViewById(R.id.view_menu);
        Button contactUs = (Button)findViewById(R.id.view_menu);

        viewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FrontActivity.this, DishListActivity.class);
                startActivity(i);
            }
        });
    }
}
