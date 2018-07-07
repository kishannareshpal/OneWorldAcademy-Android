package com.oneworldacademymz.owa.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.chip.Chip;
import android.support.design.chip.ChipGroup;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.oneworldacademymz.owa.Aluno;
import com.oneworldacademymz.owa.AlunoAdapter;
import com.oneworldacademymz.owa.R;
import com.oneworldacademymz.owa.activities.ProfileActivity;
import com.oneworldacademymz.owa.room.database.entities.MyDatabase;
import com.oneworldacademymz.owa.room.database.entities.entities.User;

import java.util.ArrayList;
import java.util.List;


 /**
        ______/   __________
        |  Alunos Adapter  |
        –––––(0)–––(0)––––––
 **/


public class AlunosFragment extends Fragment {


    public AlunosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_alunos, container, false);

        SharedPreferences status = ProfileActivity.status;
        MyDatabase myDatabase = ProfileActivity.myDatabase;
        List<User> students = myDatabase.myDao().getUsers();


//        final List<Aluno> alunoList = new ArrayList<>();
//        final RecyclerView recyclerView_alunos    = view.findViewById(R.id.rv_alunos);
//        recyclerView_alunos.setHasFixedSize(false);
//        recyclerView_alunos.setLayoutManager(new AlunosFragment.CustomLinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false)); // set the list layout style to vertical.


//        alunoList.add(new Aluno("Kishan Nareshpal Jadav"));
//        alunoList.add(new Aluno("J Balvin"));


//        AlunoAdapter alunoAdapter = new AlunoAdapter(getActivity(), alunoList);
//        recyclerView_alunos.setAdapter(alunoAdapter);



        RecyclerView mRecyclerView = view.findViewById(R.id.rv_alunos);
        final List<Aluno> mSingleCheckList = new ArrayList<>();
        AlunoAdapter mAdapter;


        // TODO: 6/28/18 Limit to 4 students;
        for(User student : students){
            Log.v("owa_database", "Student List: " + student.getId() + " ––––– " + "Student Name: " + student.getFirst_name() + " " + student.getLast_name());
            mSingleCheckList.add(new Aluno(student.getFirst_name() + " " + student.getLast_name()));
        }


        mAdapter = new AlunoAdapter(getActivity(), mSingleCheckList);
        mRecyclerView.setLayoutManager(new CustomLinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false)); // set the list layout style to vertical.
        mRecyclerView.setAdapter(mAdapter);



        mAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

            }
        });


        return view;
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
