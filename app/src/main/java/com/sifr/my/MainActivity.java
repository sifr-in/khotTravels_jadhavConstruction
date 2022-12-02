package com.sifr.my;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sifr.my.retrofit.Model4SnglRelationship;
import com.sifr.my.retrofit.Model4Y;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;
import java.util.List;

import static com.sifr.my.RegisterActivity.setExpiry;

public class MainActivity extends AppCompatActivity {
    public static String yn = "", tk = "";//tk=!!!
    public static int yc = 0, y = 0;//yd=online id as designation number of this user in [d_[]].
    public static long yo = 0L;
    public static List<Model4SnglRelationship> lstModel4SnglRelationship = null;
    public static Model4Y pModel4Y4OTP = new Model4Y();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        lstModel4SnglRelationship = new ArrayList<>();
        Model4SnglRelationship mModel4SnglRelationship = new Model4SnglRelationship();
        mModel4SnglRelationship.setD(0);
        mModel4SnglRelationship.setN("self = i am owner of mobile no.");
        lstModel4SnglRelationship.add(mModel4SnglRelationship);

        SharedPreferences saved_values = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        y = saved_values.getInt("y", 0);//relation id from table [wl_] online server

        Intent intent;
//        if (setExpiry != saved_values.getBoolean("setExpiry", true)) {
//            intent = new Intent(MainActivity.this, RegisterActivity.class);
//        } else
        if (y == 0) {
            intent = new Intent(MainActivity.this, RegisterActivity.class);
        } else {
            yc = saved_values.getInt("yc", 0);//country code of user
            yo = saved_values.getLong("yo", 0L);//mobile no. of user
            yn = saved_values.getString("yn", "");//name of user
            pModel4Y4OTP.setY(y);
            pModel4Y4OTP.setYc(yc);
            pModel4Y4OTP.setYo(yo);
            pModel4Y4OTP.setYn(yn);
            intent = new Intent(MainActivity.this, LoginWithPiActivity.class);
        }
        finish();
        startActivity(intent);
    }
}