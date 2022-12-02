package com.sifr.my;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.sifr.my.retrofit.Model4vehicleAvailableByDt;

import java.util.List;

public class Adapter4lstVehicleByDt extends RecyclerView.Adapter<Adapter4lstVehicleByDt.ViewHolder> {
    private final String TAG = "sfr Adapter4lstVehicleByDt";
    private final Activity acti;
    private final List<Model4vehicleAvailableByDt> mModel4vehicleAvailableByDt;

    public Adapter4lstVehicleByDt(Activity activityContext, List<Model4vehicleAvailableByDt> pModel4vehicleAvailableByDt) {
        this.mModel4vehicleAvailableByDt = pModel4vehicleAvailableByDt;
        this.acti = activityContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.layout_single_vehicle, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.mla_singl_vec_tv_start.setText(mModel4vehicleAvailableByDt.get(position).getLn());
        holder.mla_singl_vec_tv_end.setText(mModel4vehicleAvailableByDt.get(position).getGn());
        holder.mla_singl_vec_sel.setText(mModel4vehicleAvailableByDt.get(position).getVn());
        holder.mla_singl_vec_sel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActiSelVeh2bokSeats.choModel4vehicleAvailableByDt = mModel4vehicleAvailableByDt.get(position);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (mModel4vehicleAvailableByDt != null)
            return mModel4vehicleAvailableByDt.size();
        else
            return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        RadioButton mla_singl_vec_sel;
        TextView mla_singl_vec_tv_start, mla_singl_vec_tv_end;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mla_singl_vec_sel = itemView.findViewById(R.id.la_singl_vec_sel);
            mla_singl_vec_tv_start = itemView.findViewById(R.id.la_singl_vec_tv_start);
            mla_singl_vec_tv_end = itemView.findViewById(R.id.la_singl_vec_tv_end);
        }
    }
}
