package com.sifr.my;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sifr.my.retrofit.Model4vehicle;

import java.util.List;

public class Adapter4lstOfVehicles extends RecyclerView.Adapter<Adapter4lstOfVehicles.ViewHolder> {
    private final String TAG = "sfr Adapter4lstOfVehicles ";
    private final Activity acti;
    private List<Model4vehicle> mModel4vehicle;
    public static Model4vehicle sltdModel4vehicle;

    public Adapter4lstOfVehicles(Activity activityContext, List<Model4vehicle> paramObj) {
        this.mModel4vehicle = paramObj;
        this.acti = activityContext;
        sltdModel4vehicle=null;
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
        holder.mla_singl_rb_sel.setText(mModel4vehicle.get(position).getN());
        holder.mla_singl_rb_sel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sltdModel4vehicle=mModel4vehicle.get(position);
                Toast.makeText(acti, mModel4vehicle.get(position).getN()+ "is selected", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (mModel4vehicle != null)
            return mModel4vehicle.size();
        else
            return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final RadioButton mla_singl_rb_sel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mla_singl_rb_sel = itemView.findViewById(R.id.la_singl_rb_sel);
        }
    }

    public void updateList(List<Model4vehicle> list) {
        this.mModel4vehicle = list;
        this.notifyDataSetChanged();
    }
}
