package com.oneworldacademymz.owa;

import android.app.FragmentManager;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import static android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;


public class ProfileActivity extends AppCompatActivity {

    private FrameLayout fl_mainframe;
    private BottomNavigationView main_nav;

    private HomeFragment homeFragment;
    private ProfileFragment profileFragment;
    private AlunosFragment alunosFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        // Init basic views
        fl_mainframe = findViewById(R.id.fl_mainframe);
        main_nav = findViewById(R.id.main_nav);

        // Fragments:
        alunosFragment = new AlunosFragment();
        homeFragment = new HomeFragment();
        profileFragment = new ProfileFragment();

        // set Default Selected Fragment to Home
        main_nav.setSelectedItemId(R.id.nav_home);
        setFragment(homeFragment);


        main_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        setFragment(homeFragment);
                        return true;

                    case R.id.nav_meusalunos:
                        setFragment(alunosFragment);
                        return true;

                    case R.id.nav_profile:
                        setFragment(profileFragment);
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
    private void setFragment(Fragment fragmentName) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(fragmentName.toString());

        fragmentTransaction.replace(R.id.fl_mainframe, fragmentName);
        fragmentTransaction.commit();
    }


    @Override
    public void onBackPressed() {
        if (main_nav.getSelectedItemId() == R.id.nav_home) {
            getFragmentManager().popBackStack();
        } else {
            main_nav.setSelectedItemId(R.id.nav_home);
            setFragment(homeFragment);
            getFragmentManager().popBackStack();
        }
    }




}
