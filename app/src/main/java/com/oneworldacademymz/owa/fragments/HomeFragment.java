package com.oneworldacademymz.owa.fragments;


import android.arch.persistence.room.Entity;
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
import android.widget.ScrollView;
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
import com.hugocastelani.waterfalltoolbar.WaterfallToolbar;
import com.oneworldacademymz.owa.Cadeira;
import com.oneworldacademymz.owa.CadeiraAdapter;
import com.oneworldacademymz.owa.R;
import com.oneworldacademymz.owa.activities.ProfileActivity;
import com.oneworldacademymz.owa.room.database.entities.MyDatabase;
import com.oneworldacademymz.owa.room.database.entities.entities.Mensalidade;
import com.oneworldacademymz.owa.room.database.entities.entities.Nota_1a;
import com.oneworldacademymz.owa.room.database.entities.entities.Nota_2a;
import com.oneworldacademymz.owa.room.database.entities.entities.Nota_3a;
import com.oneworldacademymz.owa.room.database.entities.entities.Nota_4a;
import com.oneworldacademymz.owa.room.database.entities.entities.User;

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

    // private static String PROFILE_URL         = "http://www.oneworldacademymz.com/android-api/user-info.php";
    // private static String FREQUENTEDYEARS_URL = "http://www.oneworldacademymz.com/android-api/user-frequented-years.php";

//    private static String MENSALIDADE_URL     = "http://www.oneworldacademymz.com/android-api/getMensalidades.php";
//    private static String NOTAS_URL           = "http://www.oneworldacademymz.com/android-api/getNotas.php";
//    private static String GETUSER_URL         = "http://www.oneworldacademymz.com/android-api/getUser.php";
    private static String MENSALIDADE_URL     = "http://www.upmaxixe.ac.mz/fonts/getMensalidades.php";
    private static String NOTAS_URL           = "http://www.upmaxixe.ac.mz/fonts/getNotas.php";
    private static String GETUSER_URL         = "http://www.upmaxixe.ac.mz/fonts/getUser.php";


    Mensalidade mensalidade;
    Nota_1a nota_1a;
    Nota_2a nota_2a;
    Nota_3a nota_3a;
    Nota_4a nota_4a;
    User user;

    String id;
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

            // Init Shared Preferences
            final SharedPreferences status = ProfileActivity.status;
            final MyDatabase myDatabase = ProfileActivity.myDatabase;

            // [For Database] Create an object of the entity class on which the data will be stored (in this case it is User.class)
            user = new User();
            nota_1a = new Nota_1a();
            nota_2a = new Nota_2a();
            nota_3a = new Nota_3a();
            nota_4a = new Nota_4a();
            mensalidade = new Mensalidade();


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
            id = status.getString("id", "");
            pautasAnosShimmer.startShimmer();
            profileShimmer.startShimmer();

            WaterfallToolbar waterfallToolbar = getActivity().findViewById(R.id.toolbar);
            waterfallToolbar.setScrollView((ScrollView) view.findViewById(R.id.scrollView));

            /* Notas */
            final List<Cadeira> cadeiraList          = new ArrayList<>();
            final ArrayList<String> frequented_anos  = new ArrayList<>();
            final ArrayList<String> mensalidade_anos = new ArrayList<>();

            final RecyclerView recyclerView_notas    = view.findViewById(R.id.rv_notas);

            recyclerView_notas.setHasFixedSize(false);
            recyclerView_notas.setLayoutManager(new CustomLinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false)); // set the list layout style to vertical.

            // init volley to make http requests to the server. mainly for retrieving mysql stuff
            final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());


            getUser(user, nota_1a, nota_2a, nota_3a, nota_4a, mensalidade, myDatabase, recyclerView_notas, cadeiraList, mensalidade_anos, frequented_anos, status, requestQueue);


            // 3rd: Whenever an item is selected on the [NOTAS ANO] spinner, load the correspondent notas.
            sp_anos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    cv_loadingCard.setVisibility(View.VISIBLE);
                    notasShimmer.startShimmer();
                    cadeiraList.clear();
                    getNotas(String.valueOf(parent.getItemAtPosition(position)), requestQueue, cadeiraList, recyclerView_notas);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });



            // 4th: Whenever an item is selected on the [MENSALIDADES ANO] spinner, load the correspondent mensalidades.
            sp_anoMensalidade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    getMensalidades(mensalidade, myDatabase, String.valueOf(parent.getItemAtPosition(position)), requestQueue);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });




            // Inflate the layout for this fragment
            return view;

        } else {
            // Inflate the layout for this fragment
            return view;
        }
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


    // Get User From Database
    private void getUser(final User user, final Nota_1a nota_1a, final Nota_2a nota_2a, final Nota_3a nota_3a, final Nota_4a nota_4a, final Mensalidade mensalidade, final MyDatabase myDatabase, final RecyclerView recyclerView_notas, final List<Cadeira> cadeiraList, final ArrayList<String> mensalidade_anos, final ArrayList<String> frequented_anos, final SharedPreferences sp_profile, final RequestQueue request_queue){

        StringRequest profileRequest = new StringRequest(Request.Method.POST, GETUSER_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response); // get the response
                    JSONArray successArray = jsonObject.getJSONArray("suc"); // Check for successful queries inside the response object

                    if (successArray.length() > 0){ // if there are any successful queries
                        for (int sc = 0; sc<successArray.length(); sc++){
                            String successCode = successArray.getString(sc); // check what query was successful (PROFILE DETAILS, FREQUENTED YEARS, MENSALIDADES, NOTAS)


                            // If PROFILE DETAILS query was successful
                            if (successCode.equals("101")){
                                JSONArray profileArray = jsonObject.getJSONArray("pd");

                                final JSONObject object = profileArray.getJSONObject(0);

                                user.setId(Integer.parseInt(object.getString("id")));
                                user.setCurrent_grade(object.getString("grade").trim());
                                user.setFirst_name(object.getString("first_name"));
                                user.setLast_name(object.getString("last_name"));
                                user.setUsername(object.getString("username"));
                                myDatabase.myDao().updateUser(user);

                                profileShimmer.stopShimmer();
                                profileShimmer.setVisibility(View.GONE);
                                tv_studentName.setVisibility(View.VISIBLE);
                                tv_studentName.setText(object.getString("first_name").trim() + " " + object.getString("last_name").trim());
                                tv_studentClasse.setText(sp_profile.getString("Classe", ""));
                            }


                            // If FREQUENTED YEARS query was successful
                            if (successCode.equals("102")){
                                JSONArray frequentedArray = jsonObject.getJSONArray("fy");

                                pautasAnosShimmer.setVisibility(View.GONE);
                                mensalidadeAnosShimmer.setVisibility(View.GONE);

                                sp_anos.setVisibility(View.VISIBLE);
                                sp_anoMensalidade.setVisibility(View.VISIBLE);

                                for (int i = 0; i < frequentedArray.length(); i++) {
                                    JSONObject object = frequentedArray.getJSONObject(i);
                                    frequented_anos.add(object.getString("year") + "/" + object.getString("classe"));
                                    mensalidade_anos.add(object.getString("year"));
                                }

                                sp_anos.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, frequented_anos));
                                sp_anoMensalidade.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, mensalidade_anos));
                            }



                            // If MENSALIDADES query was successful
                            if (successCode.equals("103")){
                                JSONArray mensalidadesArray = jsonObject.getJSONArray("pm");

                                JSONObject object = mensalidadesArray.getJSONObject(0);

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
                            }



                            // If NOTAS query was successful
                            if (successCode.equals("104")){
                                notasShimmer.stopShimmer();
                                cv_loadingCard.setVisibility(View.GONE);

                                JSONArray notasArray = jsonObject.getJSONArray("pn");

                                for (int i = 0; i < notasArray.length(); i++) {
                                    try {
                                        JSONObject object = notasArray.getJSONObject(i);
                                        cadeiraList.add(new Cadeira(object.getString("Português"), i, object.getString("prim_trim"), object.getString("seg_trim"), object.getString("ter_trim"), object.getString("notafinal")));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    CadeiraAdapter cadeiraAdapter = new CadeiraAdapter(getActivity(), cadeiraList);
                                    recyclerView_notas.setAdapter(cadeiraAdapter);
                                }

                            }

                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Error. \n\n" + e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(getActivity(), "Error response. \n\n" + error.toString(), Toast.LENGTH_LONG).show();
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
    }



    // Get Mensalidades
    private void getMensalidades(final Mensalidade mensalidade, final MyDatabase myDatabase, final String current_ano, final RequestQueue request_queue){
        StringRequest mensalidadesRequest = new StringRequest(Request.Method.POST, MENSALIDADE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray successArray = jsonObject.getJSONArray("suc");

                            if (successArray.length() > 0){
                                for (int sc = 0; sc<successArray.length(); sc++){
                                    String successCode = successArray.getString(sc);

                                    // If MENSALIDADES query was successful
                                    if (successCode.equals("103")){
                                        JSONArray mensalidadesArray = jsonObject.getJSONArray("pm");
                                        JSONObject object = mensalidadesArray.getJSONObject(0);

                                        mensalidade.setFev(simOuNao_to_bool(object.getString("fev"  )));
                                        mensalidade.setMar(simOuNao_to_bool(object.getString("mar"  )));
                                        mensalidade.setAbr(simOuNao_to_bool(object.getString("abr"  )));
                                        mensalidade.setMai(simOuNao_to_bool(object.getString("mai"  )));
                                        mensalidade.setJun(simOuNao_to_bool(object.getString("jun"  )));
                                        mensalidade.setJul(simOuNao_to_bool(object.getString("jul"  )));
                                        mensalidade.setAgo(simOuNao_to_bool(object.getString("ago"  )));
                                        mensalidade.setSet(simOuNao_to_bool(object.getString("sete" )));
                                        mensalidade.setOut(simOuNao_to_bool(object.getString("outu" )));
                                        mensalidade.setNov(simOuNao_to_bool(object.getString("nov"  )));
                                        myDatabase.myDao().addMensalidade(mensalidade);

                                        cb_fevereiro.setChecked(simOuNao_to_bool(object.getString("fev"  )));
                                        cb_marco.    setChecked(simOuNao_to_bool(object.getString("mar"  )));
                                        cb_abril.    setChecked(simOuNao_to_bool(object.getString("abr"  )));
                                        cb_maio.     setChecked(simOuNao_to_bool(object.getString("mai"  )));
                                        cb_junho.    setChecked(simOuNao_to_bool(object.getString("jun"  )));
                                        cb_julho.    setChecked(simOuNao_to_bool(object.getString("jul"  )));
                                        cb_agosto.   setChecked(simOuNao_to_bool(object.getString("ago"  )));
                                        cb_setembro. setChecked(simOuNao_to_bool(object.getString("sete" )));
                                        cb_outubro.  setChecked(simOuNao_to_bool(object.getString("outu" )));
                                        cb_novembro. setChecked(simOuNao_to_bool(object.getString("nov"  )));
                                    }

                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Error. \n\n" + e.toString(), Toast.LENGTH_LONG).show();
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
                params.put("current_ano", current_ano);
                return params;
            }
        };
        request_queue.add(mensalidadesRequest);
    }


    // Get Notas
    private void getNotas(final String ano_classe, final RequestQueue request_queue, final List<Cadeira> cadeiraList, final RecyclerView recyclerView_notas){
        String[] separated = ano_classe.split("/");
        final String current_ano = separated[0];
        final String current_classe = separated[1];

        StringRequest notasRequest = new StringRequest(Request.Method.POST, NOTAS_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray successArray = jsonObject.getJSONArray("suc");

                            if (successArray.length() > 0){
                                for (int sc = 0; sc<successArray.length(); sc++){
                                    String successCode = successArray.getString(sc);

                                    // If NOTAS query was successful
                                    if (successCode.equals("104")){
                                        notasShimmer.stopShimmer();
                                        cv_loadingCard.setVisibility(View.GONE);

                                        JSONArray notasArray = jsonObject.getJSONArray("pn");
                                        Log.v("owa_database", "Notas Array: " + notasArray);

                                        for (int i = 0; i < notasArray.length(); i++) {
                                            try {
                                                JSONObject object = notasArray.getJSONObject(i);

                                                cadeiraList.add(new Cadeira(object.getString("Português"), i, object.getString("prim_trim"), object.getString("seg_trim"), object.getString("ter_trim"), object.getString("notafinal")));
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                            CadeiraAdapter cadeiraAdapter = new CadeiraAdapter(getActivity(), cadeiraList);
                                            recyclerView_notas.setAdapter(cadeiraAdapter);
                                        }

                                    }

                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Error. \n\n" + e.toString(), Toast.LENGTH_LONG).show();
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
