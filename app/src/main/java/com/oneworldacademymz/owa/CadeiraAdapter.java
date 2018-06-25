package com.oneworldacademymz.owa;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 *
 * RecyclerView.Adapter
 * RecyclerView.ViewHolder
 *
 */

public class CadeiraAdapter extends RecyclerView.Adapter<CadeiraAdapter.CadeiraViewHolder>{

    private Context ctx;
    private List<Cadeira> cadeiraList;

    public CadeiraAdapter(Context ctx, List<Cadeira> cadeiraList) {
        this.ctx = ctx;
        this.cadeiraList = cadeiraList;
    }



    @NonNull
    @Override
    public CadeiraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.cadeira_list, parent, false);
        return new CadeiraViewHolder(view);
    }

    @SuppressLint("StringFormatMatches")
    @Override
    public void onBindViewHolder(@NonNull CadeiraViewHolder holder, int position) {
        Cadeira cadeira = cadeiraList.get(position);
        holder.tv_cadeiraName.setText(cadeira.getCadeira_name());
        holder.tv_1oTrim.setText(ctx.getString(R.string.prim_Trim, nullToHuman(cadeira.getPrim_trim())));
        holder.tv_2oTrim.setText(ctx.getString(R.string.seg_Trim, nullToHuman(cadeira.getSeg_trim())));
        holder.tv_3oTrim.setText(ctx.getString(R.string.ter_Trim, nullToHuman(cadeira.getTer_trim())));
        holder.tv_notafinal.setText(ctx.getString(R.string.nota_final, nullToHuman(cadeira.getNota_final())));
//        holder.cv_cadeiraCard.setCardBackgroundColor(Color.parseColor(cadeira.getCardColor()));
    }


    // K-Function: When certain nota is null, show empty on the nota placeholder instead of "null".
    private String nullToHuman(String nota){
        if (nota.equals("null")){
            return " ";
        } else {
            return nota;
        }
    }

    @Override
    public int getItemCount() {
        return cadeiraList.size();
    }





    class CadeiraViewHolder extends RecyclerView.ViewHolder {

        TextView tv_cadeiraName, tv_1oTrim, tv_2oTrim, tv_3oTrim, tv_notafinal;
        CardView cv_cadeiraCard;

        public CadeiraViewHolder(View itemView) {
            super(itemView);

            cv_cadeiraCard = itemView.findViewById(R.id.cv_cadeiraCard);
            tv_cadeiraName = itemView.findViewById(R.id.tv_cadeiraName);
            tv_1oTrim      = itemView.findViewById(R.id.tv_1oTrim);
            tv_2oTrim      = itemView.findViewById(R.id.tv_2oTrim);
            tv_3oTrim      = itemView.findViewById(R.id.tv_3oTrim);
            tv_notafinal   = itemView.findViewById(R.id.tv_notafinal);



        }
    }
}
