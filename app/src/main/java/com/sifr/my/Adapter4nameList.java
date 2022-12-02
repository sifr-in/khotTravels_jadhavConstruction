/*
package com.sifr.my;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sifr.my.retrofit.Model4names;

import java.util.List;

public class Adapter4nameList extends RecyclerView.Adapter<Adapter4nameList.ViewHolder> {
    private final String TAG = "sfr Adapter4nameList";
    private final Activity acti;
    private final List<Model4names> mModel4names;

    public Adapter4nameList(Activity activityContext, List<Model4names> paramObj) {
        this.mModel4names = paramObj;
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
        holder.mla_singl_rb_sel.setText(mModel4names.get(position).getN());
        holder.mla_singl_rb_sel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (mModel4names != null)
            return mModel4names.size();
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
*/
