package com.oneworldacademymz.owa;


import android.content.Context;
import android.os.Bundle;
import android.support.design.chip.Chip;
import android.support.design.chip.ChipGroup;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlunosFragment extends Fragment {


    public AlunosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_alunos, container, false);

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

        // TODO: 6/25/18 Limit to 3 students;
        mSingleCheckList.add(new Aluno("Monica Lopes"));


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
