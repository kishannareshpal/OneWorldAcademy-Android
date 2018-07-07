package com.oneworldacademymz.owa;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.oneworldacademymz.owa.activities.ProfileActivity;
import com.robertlevonyan.views.chip.Chip;

import java.util.List;

public class AlunoAdapter extends RecyclerView.Adapter<AlunoAdapter.AlunoViewHolder> {

    private int mSelectedItem = -1;
    private List<Aluno> mSingleCheckList;
    private Context mContext;
    private AdapterView.OnItemClickListener onItemClickListener;

    private SharedPreferences status;
    private int id;

    public AlunoAdapter(Context context, List<Aluno> listItems) {
        mContext = context;
        mSingleCheckList = listItems;
    }

    @Override
    public AlunoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        final View view = inflater.inflate(R.layout.alunos_list, viewGroup, false);
        status = ProfileActivity.status;
        return new AlunoViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(AlunoViewHolder viewHolder, final int position) {
        Aluno item = mSingleCheckList.get(position);
        try {
            viewHolder.setStudentToList(item, position);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mSingleCheckList.size();
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public void onItemHolderClick(AlunoViewHolder holder) {
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(null, holder.itemView, holder.getAdapterPosition(), holder.getItemId());
        }
    }

    class AlunoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private AlunoAdapter mAdapter;
        private RadioButton mRadio;
        private TextView mText;

        public AlunoViewHolder(View itemView, final AlunoAdapter mAdapter) {
            super(itemView);
            this.mAdapter = mAdapter;

            mText = itemView.findViewById(R.id.text);
            mRadio = itemView.findViewById(R.id.radio);
            itemView.setOnClickListener(this);
            mRadio.setOnClickListener(this);
        }

        public void setStudentToList(Aluno item, int position) {
            id = Integer.parseInt(status.getString("id", ""));
            int idFromDatabase = ProfileActivity.myDatabase.myDao().getIdFromFullName_User(item.getStudent_name());
//            Toast.makeText(mContext, "sp_id = " + id + " ––– db_name = " + idFromDatabase, Toast.LENGTH_LONG).show();
            // mRadio.setChecked(position == mSelectedItem);
            mRadio.setChecked(idFromDatabase == id);
            mText.setText(item.getStudent_name());
        }

        @Override
        public void onClick(View v) {
            mSelectedItem = getAdapterPosition();
            notifyItemRangeChanged(0, mSingleCheckList.size());
            mAdapter.onItemHolderClick(AlunoViewHolder.this);
        }
    }
}













































//////////////////////////////////////////////////////////////////////////////////////////////////////////Adapter - Original.///////
//    private Context ctx;
//    private List<Aluno> alunoList;
//
//    public AlunoAdapter(Context ctx, List<Aluno> alunoList) {
//        this.ctx = ctx;
//        this.alunoList = alunoList;
//    }
//
//
//
//    @NonNull
//    @Override
//    public AlunoAdapter.AlunoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(ctx);
//        View view = inflater.inflate(R.layout.alunos_list, parent, false);
//        return new AlunoAdapter.AlunoViewHolder(view);
//    }
//
//    @SuppressLint("StringFormatMatches")
//    @Override
//    public void onBindViewHolder(@NonNull AlunoAdapter.AlunoViewHolder holder, int position) {
//        Aluno aluno = alunoList.get(position);
////        holder.tv_stName.setText(aluno.getStudent_name());
//        holder.tv_stName.setChipText(aluno.getStudent_name());
//
//    }
//
//
//    // K-Function: When certain nome is null, show empty on the nota placeholder instead of "null".
//    private String nullToHuman(String nota){
//        if (nota.equals("null")){
//            return " ";
//        } else {
//            return nota;
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return alunoList.size();
//    }
//
//
//
//
//
//    class AlunoViewHolder extends RecyclerView.ViewHolder {
//
//        // TextView tv_stName;
//        Chip tv_stName;
//        CardView cv_alunoCard;
//
//        public AlunoViewHolder(View itemView) {
//            super(itemView);
//
//            tv_stName = itemView.findViewById(R.id.tv_stName);
//
//        }
//    }
//}
