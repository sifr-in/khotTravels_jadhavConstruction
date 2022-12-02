package com.sifr.my;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.sifr.my.retrofit.Model4snglMch;

import java.util.List;

public class Adapter4lstMch extends RecyclerView.Adapter<Adapter4lstMch.ViewHolder> {
    private final String TAG = "sfr Adapter4lstMch";
    private final Activity acti;
    private final List<Model4snglMch> lstModel4snglMch;
    public static Model4snglMch seltdModel4snglMch;
    public final int inBtnID;
    private final RecyclerViewClickListener mListener;
    private int pCheckedPostion = -1;

    public Adapter4lstMch(Activity activityContext, List<Model4snglMch> mModel4snglMch, int inBtnID, RecyclerViewClickListener listener) {
        this.lstModel4snglMch = mModel4snglMch;
        this.acti = activityContext;
        this.inBtnID = inBtnID;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.layout_single_mch, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.mla_sngl_mch_nm_rb.setText(lstModel4snglMch.get(position).getN());
        holder.mla_sngl_mch_nm_rb.setChecked(position == pCheckedPostion);

        holder.mla_sngl_mch_nm_rb.setOnClickListener(v -> {

            pCheckedPostion = holder.getAdapterPosition();
            notifyItemRangeChanged(0, lstModel4snglMch.size());

            seltdModel4snglMch = lstModel4snglMch.get(position);
            Toast.makeText(acti, seltdModel4snglMch.getN() + " selected", Toast.LENGTH_LONG).show();
            mListener.doSomething(v, lstModel4snglMch.get(position), 0, 0);
//                if (inBtnID == 230) {
//                    Intent i = new Intent(acti, Activity2addMch.class);
//                    acti.startActivity(i);
//                }
        });
        holder.bind(lstModel4snglMch, position, inBtnID);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (lstModel4snglMch != null)
            return lstModel4snglMch.size();
        else
            return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        RadioButton mla_sngl_mch_nm_rb;
        TextInputEditText mla_sngl_mch_ti_qty, mla_sngl_mch_ti_g_qty;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mla_sngl_mch_nm_rb = itemView.findViewById(R.id.la_sngl_mch_nm_rb);
            mla_sngl_mch_ti_qty = itemView.findViewById(R.id.la_sngl_mch_ti_qty);
            mla_sngl_mch_ti_g_qty = itemView.findViewById(R.id.la_sngl_mch_ti_g_qty);
        }

        public void bind(List<Model4snglMch> mModel4snglMch, int position, int inBtnID) {
            mla_sngl_mch_ti_qty.setText(String.valueOf(mModel4snglMch.get(position).getA()));
            if (inBtnID == 230) {
                mla_sngl_mch_ti_qty.setEnabled(false);
                mla_sngl_mch_ti_g_qty.setText(String.valueOf(mModel4snglMch.get(position).getG()));
            } else {
                mla_sngl_mch_ti_g_qty.setVisibility(View.GONE);
            }
        }
    }
}
