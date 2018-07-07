package com.oneworldacademymz.owa.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.oneworldacademymz.owa.R;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rilixtech.materialfancybutton.MaterialFancyButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignInActivity extends AppCompatActivity {
    // for password: 123
    // hashed password ^: 8122dcdc0a46555f5aa602500f42cd1586435c4d99f7543637ef7c2ed04f98ece232ef8ff3b8d8398a05c46d31e19606298fec8509f94f9d95293b2a18b20aef
    // private static String URL_LOGIN = "http://www.upmaxixe.ac.mz/fonts/l.php";
    // SharedPreferences profile = context.getSharedPreferences("Student_Profiles", Context.MODE_PRIVATE);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);






    }



}
