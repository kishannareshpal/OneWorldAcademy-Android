package com.oneworldacademymz.owa.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

//import com.google.android.gms.signin.SignIn;
import com.oneworldacademymz.owa.R;
import com.rilixtech.materialfancybutton.MaterialFancyButton;


public class MainActivity extends AppCompatActivity {

    public MaterialFancyButton signinbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signinbtn = findViewById(R.id.btn_signin);

        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });
    }
}
