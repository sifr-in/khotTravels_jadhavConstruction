package com.sifr.my;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sifr.my.retrofit.Model4snglProgrsRprtOfSite;
import com.sifr.my.retrofit.Model4snglSite;

import java.util.List;
import java.util.Random;


public class Adapter4lstProgrsRpt extends RecyclerView.Adapter<Adapter4lstProgrsRpt.ViewHolder> {
    private final String TAG = "sfr Adapter4lstProgrsRpt";
    private final Activity acti;
    private final List<Model4snglProgrsRprtOfSite> lstModel4snglProgrsRprtOfSite;
    public static Model4snglProgrsRprtOfSite selectedModel4snglProgrsRprtOfSite;

    public Adapter4lstProgrsRpt(Activity activityContext, List<Model4snglProgrsRprtOfSite> pModel4snglProgrsRprtOfSite) {
        this.lstModel4snglProgrsRprtOfSite = pModel4snglProgrsRprtOfSite;
        this.acti = activityContext;
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
        holder.mla_singl_rb_sel.setText(lstModel4snglProgrsRprtOfSite.get(position).getN());
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (lstModel4snglProgrsRprtOfSite != null)
            return lstModel4snglProgrsRprtOfSite.size();
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
