package com.oneworldacademymz.owa.activities;

import android.arch.persistence.room.Room;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.oneworldacademymz.owa.R;
import com.oneworldacademymz.owa.fragments.AlunosFragment;
import com.oneworldacademymz.owa.fragments.HomeFragment;
import com.oneworldacademymz.owa.fragments.LoginFragment;
import com.oneworldacademymz.owa.fragments.ProfileFragment;
import com.oneworldacademymz.owa.room.database.entities.MyDatabase;


public class ProfileActivity extends AppCompatActivity {

    public static BottomNavigationView main_nav;
    private HomeFragment homeFragment;
    private ProfileFragment profileFragment;
    private AlunosFragment alunosFragment;
    private LoginFragment loginFragment;
    public static MyDatabase myDatabase;
    public static SharedPreferences status;
    public static FragmentManager fragmentManager;

    public static int fl_rootframe, fl_mainframe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Init Shared Preferences
        status = getApplicationContext().getSharedPreferences("status", MODE_PRIVATE);
        fragmentManager = getSupportFragmentManager();


        // Init database
        myDatabase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "studentDB").allowMainThreadQueries().build();

        // Init basic views
        main_nav = findViewById(R.id.main_nav);

        // Fragments:
        alunosFragment = new AlunosFragment();
        homeFragment = new HomeFragment();
        profileFragment = new ProfileFragment();
        loginFragment = new LoginFragment();

        // Frame Layouts
        fl_rootframe = R.id.fl_rootframe;
        fl_mainframe = R.id.fl_mainframe;


        Boolean isLoggedIn = status.getBoolean("isLoggedIn", false);

        if (isLoggedIn){
            // set Default Selected Fragment to Home
            main_nav.setSelectedItemId(R.id.nav_home);
            setFragment(fl_mainframe, homeFragment);

        } else {
            // show the login screen
            setFragment(fl_rootframe, loginFragment);
        }





        main_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        setFragment(fl_mainframe, homeFragment);
                        return true;

                    case R.id.nav_meusalunos:
                        setFragment(fl_mainframe, alunosFragment);
                        return true;

                    case R.id.nav_profile:
                        setFragment(fl_mainframe, profileFragment);
                        return true;

                    default:
                        return false;
                }
            }
        });




    }


    /*
    *
    * HELPER METHODS
    *
    **/

    // for selecting/changing fragments
    public static void setFragment(int frameLayout, Fragment fragmentName) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().addToBackStack(fragmentName.toString());
        fragmentTransaction.replace(frameLayout, fragmentName);
        fragmentTransaction.commit();
    }


    @Override
    public void onBackPressed() {
        if (main_nav.getSelectedItemId() == R.id.nav_home) {
            getFragmentManager().popBackStack();
        } else {
            main_nav.setSelectedItemId(R.id.nav_home);
            setFragment(fl_mainframe, homeFragment);
            getFragmentManager().popBackStack();
        }
    }


}
