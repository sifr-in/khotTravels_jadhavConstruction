package com.sifr.my;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sifr.my.retrofit.Model4TravelStops;

import java.util.List;

public class Adapter4travelStops extends RecyclerView.Adapter<Adapter4travelStops.ViewHolder> {
    private final String TAG = "sfr Adapter4travelStops";
    private final Activity acti;
    private final int rp_pod;
    public static Model4TravelStops sltdModel4TravelStops;
    private List<Model4TravelStops> pModel4TravelStops;

    public Adapter4travelStops(Activity activityContext, int pp_pod, List<Model4TravelStops> pModel4TravelStops) {
        this.acti = activityContext;
        this.rp_pod = pp_pod;
        this.pModel4TravelStops=pModel4TravelStops;
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
        holder.mla_singl_rb_sel.setText(pModel4TravelStops.get(position).getN());
        holder.mla_singl_rb_sel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sltdModel4TravelStops = pModel4TravelStops.get(position);
                sltdModel4TravelStops.setPod(rp_pod);
                acti.finish();
//                Toast.makeText(acti, mModel4TravelStops.getD() + " Chosen.", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return pModel4TravelStops.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final RadioButton mla_singl_rb_sel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mla_singl_rb_sel = itemView.findViewById(R.id.la_singl_rb_sel);
        }
    }

    public void updateList(List<Model4TravelStops> list){
        pModel4TravelStops.clear();
        pModel4TravelStops.addAll(list);
        this.notifyDataSetChanged();
    }
}
