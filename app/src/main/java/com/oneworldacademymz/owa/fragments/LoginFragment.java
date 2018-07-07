package com.oneworldacademymz.owa.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.oneworldacademymz.owa.activities.ProfileActivity;
import com.oneworldacademymz.owa.activities.SignInActivity;
import com.oneworldacademymz.owa.room.database.entities.entities.User;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rilixtech.materialfancybutton.MaterialFancyButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private MaterialEditText et_username, et_password;
    private MaterialFancyButton btn_login;
    private static String URL_LOGIN = "http://www.upmaxixe.ac.mz/fonts/login.php";
    // private static String URL_LOGIN = "http://www.oneworldacademymz.com/android-api/login.php";


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        // Init Shared Preferences
        final SharedPreferences status = ProfileActivity.status;


        // Init essentials
        et_username = view.findViewById(R.id.et_username);
        et_password = view.findViewById(R.id.et_password);
        btn_login   = view.findViewById(R.id.btn_signin);


        // [For Database] Create an object of the entity class on which the data will be stored (in this case it is User.class)
        final User user = new User();


        // What happens when login button is clicked
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // String username = et_username.getText().toString().trim();
                // String password = et_password.getText().toString().trim();
                String username = "antonioj2018";
                String password = "123";

                if (!username.isEmpty() && !password.isEmpty()){
                    Log.v("owa_response", "u: " + username + " –– p: " + password);
                    login(username, password, status, user);

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

        // Inflate the layout
        return view;
    }



    // Login Handler
    private void login(final String username, final String password, final SharedPreferences sharedPreferences, final User user){
        btn_login.setEnabled(false);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest userRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray successArray = jsonObject.getJSONArray("suc");

                            if (successArray.length() > 0){
                                for (int sc = 0; sc<successArray.length(); sc++){
                                    String successCode = successArray.getString(sc);

                                    // If USER PROFILE query was successful
                                    if (successCode.equals("100")){
                                        JSONArray userArray = jsonObject.getJSONArray("up"); // User Profile
                                        JSONObject object = userArray.getJSONObject(0);

                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putBoolean("isLoggedIn", true);
                                        editor.putString("id", object.getString("id"));
                                        editor.apply();

                                        user.setId(Integer.parseInt(object.getString("id")));
                                        user.setCurrent_grade(object.getString("grade").trim());
                                        user.setFirst_name(object.getString("first_name"));
                                        user.setLast_name(object.getString("last_name"));
                                        user.setUsername(object.getString("username"));

                                        ProfileActivity.myDatabase.myDao().addUser(user);
                                        Log.v("owa_database", "Success on Adding user to the database! (On LoginFragment)");

                                        // TODO: Replace the fragment to the profile one.
                                        ProfileActivity.fragmentManager.beginTransaction().remove(LoginFragment.this).commit();
                                        ProfileActivity.main_nav.setSelectedItemId(R.id.nav_home);
                                    }

                                    btn_login.setEnabled(true);

                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Error. \n\n" + e.toString(), Toast.LENGTH_LONG).show();
                            btn_login.setEnabled(true);
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Error Response. \n\n" + error.toString(), Toast.LENGTH_SHORT).show();
                        btn_login.setEnabled(true);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }

        };
        requestQueue.add(userRequest);
    }

}
