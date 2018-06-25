package com.oneworldacademymz.owa;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.chip.Chip;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private static String PROFILE_URL         = "http://www.oneworldacademymz.com/android-api/user-info.php";
    private static String MENSALIDADE_URL     = "http://www.oneworldacademymz.com/android-api/mensalidades.php";
    private static String NOTAS_URL           = "http://www.oneworldacademymz.com/android-api/notas.php";
    private static String FREQUENTEDYEARS_URL = "http://www.oneworldacademymz.com/android-api/user-frequented-years.php";

    String id;
    Chip chip;
    CardView cv_loadingCard;
    Spinner sp_anos, sp_anoMensalidade;
    TextView tv_studentName, tv_studentClasse, tv_profileError, tv_mensalidadeError, tv_notasError;
    ShimmerFrameLayout profileShimmer, notasShimmer, pautasAnosShimmer, mensalidadeAnosShimmer;
    CheckBox cb_fevereiro, cb_marco, cb_abril, cb_maio, cb_junho, cb_julho, cb_agosto, cb_setembro, cb_outubro, cb_novembro;



    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        if (getActivity() != null){



            SharedPreferences profile = this.getActivity().getSharedPreferences("Student_Profiles", Context.MODE_PRIVATE);

            // Initialize the views
            tv_studentName         = view.findViewById(R.id.tv_studentName);
            tv_studentClasse       = view.findViewById(R.id.tv_studentClasse);
            tv_notasError          = view.findViewById(R.id.tv_notasError);
            tv_profileError        = view.findViewById(R.id.tv_profileError);
            tv_mensalidadeError    = view.findViewById(R.id.tv_mensalidadeError);
            cv_loadingCard         = view.findViewById(R.id.cv_loadingCadeira);
            profileShimmer         = view.findViewById(R.id.profileShimmer);
            notasShimmer           = view.findViewById(R.id.notasShimmer);
            sp_anos                = view.findViewById(R.id.sp_anos);
            sp_anoMensalidade      = view.findViewById(R.id.sp_anoMensalidade);
            pautasAnosShimmer      = view.findViewById(R.id.pautaAnosShimmer);
            mensalidadeAnosShimmer = view.findViewById(R.id.mensalidadeAnosShimmer);


            // Mensalidades CheckBox
            cb_fevereiro = view.findViewById(R.id.cb_fevereiro);
            cb_marco     = view.findViewById(R.id.cb_marco);
            cb_abril     = view.findViewById(R.id.cb_abril);
            cb_maio      = view.findViewById(R.id.cb_maio);
            cb_junho     = view.findViewById(R.id.cb_junho);
            cb_julho     = view.findViewById(R.id.cb_julho);
            cb_agosto    = view.findViewById(R.id.cb_agosto);
            cb_setembro  = view.findViewById(R.id.cb_setembro);
            cb_outubro   = view.findViewById(R.id.cb_outubro);
            cb_novembro  = view.findViewById(R.id.cb_novembro);


            cb_fevereiro.setClickable(false);
            cb_marco.setClickable(false);
            cb_abril.setClickable(false);
            cb_maio.setClickable(false);
            cb_junho.setClickable(false);
            cb_julho.setClickable(false);
            cb_agosto.setClickable(false);
            cb_setembro.setClickable(false);
            cb_outubro.setClickable(false);
            cb_novembro.setClickable(false);


            // Basic Init
            id = profile.getString("Id", "");
            pautasAnosShimmer.startShimmer();
            profileShimmer.startShimmer();



            /* Notas */
            final List<Cadeira> cadeiraList          = new ArrayList<>();
            final ArrayList<String> frequented_anos  = new ArrayList<>();
            final ArrayList<String> mensalidade_anos = new ArrayList<>();

            final RecyclerView recyclerView_notas    = view.findViewById(R.id.rv_notas);
            recyclerView_notas.setHasFixedSize(false);
            recyclerView_notas.setLayoutManager(new CustomLinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false)); // set the list layout style to vertical.

            // init volley to make http requests to the server. mainly for retrieving mysql stuff
            final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());


            getProfile(requestQueue, profile);


            StringRequest yearsRequest = new StringRequest(Request.Method.POST, FREQUENTEDYEARS_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String success = jsonObject.getString("success");
                                final JSONArray jsonArray = jsonObject.getJSONArray("frequented_years");

                                pautasAnosShimmer.setVisibility(View.GONE);
                                mensalidadeAnosShimmer.setVisibility(View.GONE);

                                sp_anos.setVisibility(View.VISIBLE);
                                sp_anoMensalidade.setVisibility(View.VISIBLE);


                                if (success.equals("1")){
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        try {
                                            JSONObject object = jsonArray.getJSONObject(i);
                                            frequented_anos.add(object.getString("year") + "/" + object.getString("classe"));
                                            mensalidade_anos.add(object.getString("year"));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    sp_anos.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, frequented_anos));
                                    sp_anoMensalidade.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, mensalidade_anos));

                                } else {
                                    Log.v("owa_responsee", "failed");
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getActivity(), "Error. \n\n" + e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getActivity(), "Error Response. \n\n" + error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
            ){

                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("id", id);
                    return params;
                }
            };
            requestQueue.add(yearsRequest);


            // 3rd: Whenever an item is selected on the Notas [ANO] spinner, load the correspondent notas.
            sp_anos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    cv_loadingCard.setVisibility(View.VISIBLE);
                    notasShimmer.startShimmer();
                    cadeiraList.clear();
                    getNotas(String.valueOf(parent.getItemAtPosition(position)), requestQueue, cadeiraList, recyclerView_notas);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });



            // 4th: Whenever an item is selected on the Mensalidades [ANO] spinner, load the correspondent mensalidades.
            sp_anoMensalidade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    getMensalidades(String.valueOf(parent.getItemAtPosition(position)), requestQueue);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });





            return view;

        } else {
            return view;
        }
        // Inflate the layout for this fragment

    }



    // Because the server's mensalidade response comes in "Sim" or "Não" for each paid/notPaid month.
    private boolean simOuNao_to_bool(String simOuNao){
        boolean isPaid = false;
        switch (simOuNao){
            case "Sim":
                isPaid = true;
                break;

            case "Não":
                isPaid = false;
                break;
        }
        return isPaid;
    }



    // Get Profile
    /*
     * getMensalidades() Error Codes:
     * 0 -> Success. No Error. Response was perfect json with results;
     * 1 -> Backend code error. Request succeded but did't return desirable results;
     * 2 -> Response wasn't a JSON Object. Volley Request Failed. Probably requesting while on captive portal. So assume Internet Connection Problems;
     * 3 -> Phone is not connected to the internet.
     *
     * */

    private void getProfile(final RequestQueue request_queue, final SharedPreferences sp_profile){
        tv_profileError.setVisibility(View.GONE);

        // Get Profile Details
        StringRequest profileRequest = new StringRequest(Request.Method.POST, PROFILE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("profile_details");
//                    profile_errorcode = 0;

                    if (success.equals("1")){
                        for (int i = 0; i < jsonArray.length(); i++){
                            final JSONObject object = jsonArray.getJSONObject(i);
                            // Toast.makeText(ProfileActivity.this, "Success. \nYour Username is: " + object.getString("first_name").trim(), Toast.LENGTH_LONG).show();
                            profileShimmer.stopShimmer();
                            profileShimmer.setVisibility(View.GONE);

                            tv_studentName.setText(object.getString("first_name").trim() + " " + object.getString("last_name").trim());
                            tv_studentClasse.setText(sp_profile.getString("Classe", ""));

                        }

                    } else {
//                      profile_errorcode = 1;
                        tv_profileError.setVisibility(View.VISIBLE);
                        tv_profileError.setText("Ocorreu um problema com o nosso servidor. Tentar Novamente");

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    // profile_errorcode = 2;

                    tv_profileError.setVisibility(View.VISIBLE);
                    tv_profileError.setText("Por favor verifique a sua conexão com a internet e tente novamente. Tentar Novamente");

                    // Toast.makeText(ProfileActivity.this, "Error. \n\n" + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        profile_errorcode = 3;
                        tv_profileError.setVisibility(View.VISIBLE);
                        tv_profileError.setText("Por favor verifique a sua conexão com a internet e tente novamente. Tentar Novamente");

//                        Toast.makeText(ProfileActivity.this, "Error Response. \n\n" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                return params;
            }
        };
        request_queue.add(profileRequest);
//        return profile_errorcode;
    }



    // Get Mensalidades
    /*
     * getMensalidades() Error Codes:
     * 0 -> Success. No Error. Response was perfect json with results;
     * 1 -> Backend code error. Request succeded but did't return desirable results;
     * 2 -> Response wasn't a JSON Object. Volley Request Failed. Probably requesting while on captive portal. So assume Internet Connection Problems;
     * 3 -> Phone is not connected to the internet.
     * */

    private void getMensalidades(final String current_ano, final RequestQueue request_queue){
        tv_mensalidadeError.setVisibility(View.GONE);

        StringRequest mensalidadesRequest = new StringRequest(Request.Method.POST, MENSALIDADE_URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
//                            mensalidade_errorcode = 0;


                            JSONArray jsonArray = jsonObject.getJSONArray("profile_mensalidades");

                            if (success.equals("1")){
                                for (int i = 0; i < jsonArray.length(); i++){
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    // Toast.makeText(ProfileActivity.this, "Success. \nYour Username is: " + object.getString("first_name").trim(), Toast.LENGTH_LONG).show();

                                    cb_fevereiro.setChecked(simOuNao_to_bool(object.getString("fev")));
                                    cb_marco.setChecked(simOuNao_to_bool(object.getString("mar")));
                                    cb_abril.setChecked(simOuNao_to_bool(object.getString("abr")));
                                    cb_maio.setChecked(simOuNao_to_bool(object.getString("mai")));
                                    cb_junho.setChecked(simOuNao_to_bool(object.getString("jun")));
                                    cb_julho.setChecked(simOuNao_to_bool(object.getString("jul")));
                                    cb_agosto.setChecked(simOuNao_to_bool(object.getString("ago")));
                                    cb_setembro.setChecked(simOuNao_to_bool(object.getString("sete")));
                                    cb_outubro.setChecked(simOuNao_to_bool(object.getString("outu")));
                                    cb_novembro.setChecked(simOuNao_to_bool(object.getString("nov")));
//                                    mensalidadesShimmer.stopShimmer();
                                }

                            } else {
//                                mensalidade_errorcode = 1;
                                tv_mensalidadeError.setVisibility(View.VISIBLE);
                                tv_mensalidadeError.setText("Ocorreu um problema com o nosso servidor. Tentar Novamente");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
//                            mensalidade_errorcode = 2;
                            tv_mensalidadeError.setVisibility(View.VISIBLE);
                            tv_mensalidadeError.setText("Por favor verifique a sua conexão com a internet e tente novamente. Tentar Novamente");
                            Toast.makeText(getActivity(), "Error. \n\n" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        mensalidade_errorcode = 3;
                        tv_mensalidadeError.setVisibility(View.VISIBLE);
                        tv_mensalidadeError.setText("Por favor verifique a sua conexão com a internet e tente novamente. Tentar Novamente");
//                        Toast.makeText(ProfileActivity.this, "Error Response. \n\n" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ){

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("current_ano", current_ano);
                return params;
            }
        };
        request_queue.add(mensalidadesRequest);
    }



    // Get Notas
    /*
     * getNotas() Error Codes:
     * 0 -> Success. No Error. Response was perfect json with results;
     * 1 -> Backend code error;
     * 2 -> Response wasn't a JSON Object. Probably requesting while on captive portal. So assume Internet Connection Problems;
     * 3 -> Phone is not connected to the internet.
     * */

    private void getNotas(final String ano_classe, final RequestQueue request_queue, final List<Cadeira> cadeiraList, final RecyclerView recyclerView_notas){

        String[] separated = ano_classe.split("/");
        final String current_ano = separated[0];
        final String current_classe = separated[1];
        tv_notasError.setVisibility(View.GONE);

        StringRequest notasRequest = new StringRequest(Request.Method.POST, NOTAS_URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")){
                                final JSONArray jsonArray = jsonObject.getJSONArray("profile_notas");
//                                notas_errorcode = 0;

                                notasShimmer.stopShimmer();
                                cv_loadingCard.setVisibility(View.GONE);

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    try {
                                        JSONObject object = jsonArray.getJSONObject(i);
                                        cadeiraList.add(new Cadeira(object.getString("Português"), i, object.getString("prim_trim"), object.getString("seg_trim"), object.getString("ter_trim"), object.getString("notafinal")));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    CadeiraAdapter cadeiraAdapter = new CadeiraAdapter(getActivity(), cadeiraList);
                                    recyclerView_notas.setAdapter(cadeiraAdapter);
                                }

                            } else{
//                                notas_errorcode = 1;
                                tv_notasError.setVisibility(View.VISIBLE);
                                tv_notasError.setText("Ocorreu um problema com o nosso servidor. Tentar Novamente");
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
//                            notas_errorcode = 2;
                            tv_notasError.setVisibility(View.VISIBLE);
                            tv_notasError.setText("Por favor verifique a sua conexão com a internet e tente novamente. Tentar Novamente");

//                            Toast.makeText(ProfileActivity.this, "Error. \n\n" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        notas_errorcode = 3;
                        tv_notasError.setVisibility(View.VISIBLE);
                        tv_notasError.setText("Por favor verifique a sua conexão com a internet e tente novamente. Tentar Novamente");
//                        Toast.makeText(ProfileActivity.this, "Error Response. \n\n" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ){

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("current_ano", current_ano);
                params.put("current_classe", current_classe);
                return params;
            }

        };
        request_queue.add(notasRequest);
    }


    public class CustomLinearLayoutManager extends LinearLayoutManager {
        public CustomLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
            super(context, orientation, reverseLayout);
        }

        // it will always pass false to RecyclerView when calling "canScrollVertically()" method.
        @Override
        public boolean canScrollVertically() {
            return false;
        }
    }

}
