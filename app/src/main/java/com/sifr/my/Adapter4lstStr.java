package com.sifr.my;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sifr.my.retrofit.Model4snglStr;

import java.util.List;

public class Adapter4lstStr extends RecyclerView.Adapter<Adapter4lstStr.ViewHolder> {
    private final String TAG = "sfr Adapter4lstStr";
    private final Activity acti;
    private final List<Model4snglStr> lstModel4snglStr;
    public static Model4snglStr sltdModel4snglStr;
    private final RecyclerViewClickListener mListener;
    public static int idxx = 0;

    public Adapter4lstStr(Activity activityContext, List<Model4snglStr> pModel4snglStr, RecyclerViewClickListener rc, int idx) {
        this.lstModel4snglStr = pModel4snglStr;
        this.acti = activityContext;
        this.mListener = rc;
        idxx = idx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.lao_sngl_str, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.bind(lstModel4snglStr.get(position), mListener, acti);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (lstModel4snglStr != null)
            return lstModel4snglStr.size();
        else
            return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mlao_sngl_str_tv_sr;
        RadioButton mlao_sngl_str_rb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mlao_sngl_str_tv_sr = itemView.findViewById(R.id.lao_sngl_str_tv_sr);
            mlao_sngl_str_rb = itemView.findViewById(R.id.lao_sngl_str_rb);
        }

        public void bind(Model4snglStr mModel4snglStr, RecyclerViewClickListener mListener, Activity acti) {
            mlao_sngl_str_tv_sr.setText(String.valueOf(mModel4snglStr.getD()));
            mlao_sngl_str_rb.setText(mModel4snglStr.getN());
            mlao_sngl_str_rb.setOnClickListener(v -> {
                sltdModel4snglStr=mModel4snglStr;
//                acti.finish();
                mListener.doSomething(v, mModel4snglStr, idxx, 0);
            });
        }
    }
}
