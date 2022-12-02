package com.sifr.my;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.sifr.my.retrofit.Model4snglXp;

import java.util.List;


public class Adapter4lstQtyXps extends RecyclerView.Adapter<Adapter4lstQtyXps.ViewHolder> {
    private final String TAG = "sfr Adapter4lstQtyXps";
    private final Activity acti;
    private final List<Model4snglXp> lstModel4snglXp;

    public Adapter4lstQtyXps(Activity activityContext, List<Model4snglXp> pModel4snglXp) {
        this.lstModel4snglXp = pModel4snglXp;
        this.acti = activityContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.layout_single_xpn, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.mact_2qty_xp_ti_tv.setText(lstModel4snglXp.get(position).getN());
        holder.mact_2qty_xp_ti_q.setText(String.valueOf(lstModel4snglXp.get(position).getG()));
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (lstModel4snglXp != null)
            return lstModel4snglXp.size();
        else
            return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mact_2qty_xp_ti_tv;
        TextInputEditText mact_2qty_xp_ti_q;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mact_2qty_xp_ti_tv = itemView.findViewById(R.id.act_2qty_xp_ti_tv);
            mact_2qty_xp_ti_q = itemView.findViewById(R.id.act_2qty_xp_ti_q);
        }
    }
}
