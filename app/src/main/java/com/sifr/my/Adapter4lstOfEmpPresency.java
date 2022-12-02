package com.sifr.my;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.motion.widget.MotionScene;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Slide;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;

import com.sifr.my.common.CommonChecks;
import com.sifr.my.retrofit.Model4snglEmpPresency;
import com.sifr.my.retrofit.Model4snglStr;

import java.util.ArrayList;
import java.util.List;

public class Adapter4lstOfEmpPresency extends RecyclerView.Adapter<Adapter4lstOfEmpPresency.ViewHolder> {
    private final String TAG = "sfr Adapter4lstOfEmpPresency";
    private final Activity acti;
    public static List<Model4snglEmpPresency> mModel4snglEmpPresency;
    private CommonChecks comnChks;

    public Adapter4lstOfEmpPresency(Activity activityContext, List<Model4snglEmpPresency> pModel4snglEmpPresency) {
        this.mModel4snglEmpPresency = pModel4snglEmpPresency;
        this.acti = activityContext;
        this.comnChks = new CommonChecks(acti);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.layout_single_emp_presency, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.mla_singl_emp_presen_tv_sr.setText(mModel4snglEmpPresency.get(position).getD());
        holder.mla_singl_emp_presen_rb.setText(mModel4snglEmpPresency.get(position).getVn());
        if (mModel4snglEmpPresency.get(position).getB() == null) {
            mModel4snglEmpPresency.get(position).setB("0");
        }
        holder.mla_singl_emp_presen_iv_xpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.mla_singl_emp_presen_tv_mono.setText("+" + mModel4snglEmpPresency.get(position).getVc() + " " +
                        mModel4snglEmpPresency.get(position).getVo());
                if (holder.mla_singl_emp_presen_tv_mono.getVisibility() == View.VISIBLE) {
                    holder.mla_singl_emp_presen_tv_mono.setVisibility(View.GONE);
                } else {
                    holder.mla_singl_emp_presen_tv_mono.setVisibility(View.VISIBLE);
                }
            }
        });

        holder.mla_singl_emp_presen_unit.setText(comnChks.getMeaTXTfromCode(mModel4snglEmpPresency.get(position).getB()));
        holder.mla_singl_emp_presen_unit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.shoRv(position, acti);
//                Intent i = new Intent(acti, Acti4LstStr.class);
//                i.putExtra("idx", mModel4snglEmpPresency.get(position).getD());
//                acti.startActivity(i);
            }
        });
        holder.mla_singl_emp_presen_qty.setText(mModel4snglEmpPresency.get(position).getC());
        holder.mla_singl_emp_presen_qty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mModel4snglEmpPresency.get(position).setChg(1);
                if (holder.mla_singl_emp_presen_qty.getText().toString().length() > 0)
                    mModel4snglEmpPresency.get(position).setC(holder.mla_singl_emp_presen_qty.getText().toString());
                else
                    mModel4snglEmpPresency.get(position).setC("0");
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (mModel4snglEmpPresency != null)
            return mModel4snglEmpPresency.size();
        else
            return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private String TAG = "sfr viewHolderPrsncy";
        RadioButton mla_singl_emp_presen_rb;
        TextView mla_singl_emp_presen_unit, mla_singl_emp_presen_qty,
                mla_singl_emp_presen_tv_mono, mla_singl_emp_presen_tv_sr;
        ImageView mla_singl_emp_presen_iv_xpa;
        RecyclerView mla_singl_emp_presen_rv;
        LinearLayout mla_singl_emp_presen_llao;
        Transition transition;
        boolean show;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mla_singl_emp_presen_rb = itemView.findViewById(R.id.la_singl_emp_presen_rb);
            mla_singl_emp_presen_unit = itemView.findViewById(R.id.la_singl_emp_presen_unit);
            mla_singl_emp_presen_qty = itemView.findViewById(R.id.la_singl_emp_presen_qty);
            mla_singl_emp_presen_tv_sr = itemView.findViewById(R.id.la_singl_emp_presen_tv_sr);
            mla_singl_emp_presen_tv_mono = itemView.findViewById(R.id.la_singl_emp_presen_tv_mono);
            mla_singl_emp_presen_tv_mono.setVisibility(View.GONE);
            mla_singl_emp_presen_iv_xpa = itemView.findViewById(R.id.la_singl_emp_presen_iv_xpa);
            mla_singl_emp_presen_rv = itemView.findViewById(R.id.la_singl_emp_presen_rv);
            mla_singl_emp_presen_rv.setVisibility(View.GONE);
            transition = new Slide(Gravity.BOTTOM);
            transition.setDuration(2000);
            transition.addTarget(R.id.la_singl_emp_presen_rv);
            mla_singl_emp_presen_llao = itemView.findViewById(R.id.la_singl_emp_presen_llao);
        }

        private void shoRv(int position, Activity acti) {
            List<Model4snglStr> lstModel4snglStr = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                Model4snglStr mModel4snglStr = new Model4snglStr();
                lstModel4snglStr.add(mModel4snglStr);
            }

            lstModel4snglStr.get(0).setD(0);
            lstModel4snglStr.get(0).setN("days");
            lstModel4snglStr.get(1).setD(1);
            lstModel4snglStr.get(1).setN("hours");
            lstModel4snglStr.get(2).setD(2);
            lstModel4snglStr.get(2).setN("weeks");
            lstModel4snglStr.get(3).setD(3);
            lstModel4snglStr.get(3).setN("months");
            lstModel4snglStr.get(4).setD(4);
            lstModel4snglStr.get(4).setN("years");

            mla_singl_emp_presen_rv.setVisibility(View.VISIBLE);

            show = !show;
            TransitionManager.beginDelayedTransition(mla_singl_emp_presen_llao, transition);
            mla_singl_emp_presen_rv.setVisibility(show ? View.VISIBLE : View.GONE);

            RecyclerViewClickListener rc = new RecyclerViewClickListener() {

                @Override
                public void doSomething(View v, Object obj, int position, int index) {
                    Model4snglStr mModel4snglStr = (Model4snglStr) obj;
                    mla_singl_emp_presen_unit.setText(mModel4snglStr.getN());
                    mla_singl_emp_presen_rv.setVisibility(View.GONE);
                    mModel4snglEmpPresency.get(position).setB(String.valueOf(mModel4snglStr.getD()));
                    mModel4snglEmpPresency.get(position).setChg(1);
                }
            };

            Adapter4lstStr mAdapter4lstStr = new Adapter4lstStr(acti, lstModel4snglStr, rc, position);
            mla_singl_emp_presen_rv.setAdapter(mAdapter4lstStr);
        }
    }
}
