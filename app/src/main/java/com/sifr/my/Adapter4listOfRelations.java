package com.sifr.my;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sifr.my.retrofit.Model4ButtonsEPermissions;
import com.sifr.my.retrofit.Model4SnglRelationship;
import com.sifr.my.retrofit.Model4TravelStops;

import java.util.List;

public class Adapter4listOfRelations extends RecyclerView.Adapter<Adapter4listOfRelations.ViewHolder> {
    private final String TAG = "sfr Adapter4listOfRelations";
    private final Activity acti;
    public static Model4SnglRelationship sltdModel4SnglRelationship;
    private final List<Model4SnglRelationship> mModel4SnglRelationship;

    public Adapter4listOfRelations(Activity activityContext, List<Model4SnglRelationship> paramObj) {
        this.mModel4SnglRelationship = paramObj;
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
        holder.mla_singl_rb_sel.setText(mModel4SnglRelationship.get(position).getN());
        holder.mla_singl_rb_sel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sltdModel4SnglRelationship = mModel4SnglRelationship.get(position);

                switch (mModel4SnglRelationship.get(position).getD()) {
                    case 1:
                        holder.mla_singl_rb_sel.setBackgroundColor(Color.parseColor("#4dfd81"));
                        break;

                    default:
                        holder.mla_singl_rb_sel.setBackgroundColor(Color.parseColor("#fdad5c"));
                        break;
                }
                //Toast.makeText(acti, mModel4SnglRelationship.get(position).getN() + " Chosen.", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (mModel4SnglRelationship != null)
            return mModel4SnglRelationship.size();
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
}
