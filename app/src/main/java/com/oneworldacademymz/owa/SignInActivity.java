package com.oneworldacademymz.owa;

import android.content.Context;
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
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rilixtech.materialfancybutton.MaterialFancyButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignInActivity extends AppCompatActivity {

    // admin: 98a7fec21e9a0fc357616a62f5e1ca4fd1254c22a3e8a4585ec3a23158b73bf2d51cf7575c1ef9b37e969630eb8b86fc9b82ae044078f58b507bf2cf8690ec18

    private MaterialEditText et_username, et_password;
    private MaterialFancyButton btn_login;
    private ProgressBar progressBar;

//    private static String URL_LOGIN = "http://www.upmaxixe.ac.mz/fonts/l.php";
    private static String URL_LOGIN = "http://www.oneworldacademymz.com/android-api/login.php";

//    SharedPreferences profile = context.getSharedPreferences("Student_Profiles", Context.MODE_PRIVATE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_signin);
        progressBar = findViewById(R.id.progressBar);



        // If the user clicks on the login button
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String username = et_username.getText().toString().trim();
//                String password = et_password.getText().toString().trim();
                String username = "antonioj2018";
                String password = "123";


                if (!username.isEmpty() && !password.isEmpty()){

                    Log.v("owa_response", "u: " + username + " –– p: " + password);
                    login(username, password);

                } else {
                    if (username.isEmpty()){
                        et_username.setError("Por favor insira o username.");
                    }
                    if (password.isEmpty()){
                        et_password.setError("Por favor insira a senha.");
                    }
                }

            }
        });
    }


    // Login Handler
    private void login(final String uname, final String pword){
        progressBar.setVisibility(View.VISIBLE);
        btn_login.setEnabled(false);

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        Log.v("owa_response", response);
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");

                        JSONArray jsonArray = jsonObject.getJSONArray("login");

                        if (success.equals("1")){
                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject object = jsonArray.getJSONObject(i);
//                                Toast.makeText(SignInActivity.this, "Success Login. \nYour Username is: " + object.getString("username").trim(), Toast.LENGTH_LONG).show();

                                SharedPreferences profile = getApplicationContext().getSharedPreferences("Student_Profiles", MODE_PRIVATE);
                                SharedPreferences.Editor editor = profile.edit();
                                editor.putBoolean("isLoggedIn", true);
                                editor.putString("Id", object.getString("id").trim());
                                editor.putString("Username", object.getString("username").trim());
                                editor.putString("Name", object.getString("first_name").trim() + object.getString("last_name").trim());
                                editor.putString("Classe", object.getString("grade").trim());
                                editor.apply();

                                startActivity(new Intent(SignInActivity.this, ProfileActivity.class));
                            }

                        } else {
                            Log.v("owa_responsee", "failed");
                        }

                        progressBar.setVisibility(View.GONE);
                        btn_login.setEnabled(true);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(SignInActivity.this, "Error. \n\n" + e.toString(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        btn_login.setEnabled(true);
                    }
                }
            },

            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(SignInActivity.this, "Error Response. \n\n" + error.toString(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    btn_login.setEnabled(true);
                }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", uname);
                params.put("password", pword);
                return params;
            }

        };


        requestQueue.add(stringRequest);
    }
}
