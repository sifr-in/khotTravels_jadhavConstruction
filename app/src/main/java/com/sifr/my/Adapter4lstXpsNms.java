package com.sifr.my;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sifr.my.retrofit.Model4snglXp;

import java.util.List;

public class Adapter4lstXpsNms extends RecyclerView.Adapter<Adapter4lstXpsNms.ViewHolder> {
    private final String TAG = "sfr Adapter4lstXpsNms";
    private final Activity acti;
    private final List<Model4snglXp> lstModel4snglXp;
    public static Model4snglXp selectedModel4snglXp;
    private final int inBtnID;

    public Adapter4lstXpsNms(Activity activityContext, List<Model4snglXp> pModel4snglXp, int inBtnID) {
        this.lstModel4snglXp = pModel4snglXp;
        this.acti = activityContext;
        this.inBtnID = inBtnID;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.layout_single_radio_line, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.mla_singl_rb_sel.setText(lstModel4snglXp.get(position).getN());
        holder.mla_singl_rb_sel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedModel4snglXp = lstModel4snglXp.get(position);
                Toast.makeText(acti, selectedModel4snglXp.getN() + " selected", Toast.LENGTH_LONG).show();
                if (inBtnID == 230) {
                    acti.finish();
                }
            }
        });
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
        RadioButton mla_singl_rb_sel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mla_singl_rb_sel = itemView.findViewById(R.id.la_singl_rb_sel);
        }
    }
}
