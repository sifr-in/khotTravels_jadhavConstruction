package com.sifr.my;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sifr.my.retrofit.Model4snglSite;

import java.util.List;
import java.util.Random;

import static com.sifr.my.ActivityButtons.selectedModel4ButtonsEPermissions0;


public class Adapter4lstSites extends RecyclerView.Adapter<Adapter4lstSites.ViewHolder> {
    private final String TAG = "sfr Adapter4lstSites";
    private final Activity acti;
    private final List<Model4snglSite> lstModel4snglSite;
    public static Model4snglSite selectedModel4snglSite;
    public static Model4snglSite seltdLvl2Model4snglSite;
    private int lvl;

    public Adapter4lstSites(Activity activityContext, List<Model4snglSite> pModel4snglSite, int psiteLvl) {
        this.lstModel4snglSite = pModel4snglSite;
        this.acti = activityContext;
        this.lvl = psiteLvl;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.layout_single_site, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.mla_singl_site_tv_sr.setText(String.valueOf(lstModel4snglSite.get(position).getD()));
        holder.mla_singl_site_rb.setText(lstModel4snglSite.get(position).getN());
        holder.mla_singl_site_pb.setProgress(lstModel4snglSite.get(position).getP());
        holder.mla_singl_site_pb.getProgressDrawable().setColorFilter(
                getRandColor(), android.graphics.PorterDuff.Mode.SRC_IN);
        holder.mla_singl_site_tv_perc.setText("\u00A0" + lstModel4snglSite.get(position).getP() + "%");
        holder.mla_singl_site_iv_xpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.mla_singl_site_tv_actual_start_dtt.setText(lstModel4snglSite.get(position).getC());
                holder.mla_singl_site_tv_start_dtt.setText(lstModel4snglSite.get(position).getA());
                holder.mla_singl_site_tv_end_dtt.setText(lstModel4snglSite.get(position).getB());
                if (holder.mla_singl_site_ll_dtls.getVisibility() == View.VISIBLE)
                    holder.mla_singl_site_ll_dtls.setVisibility(View.GONE);
                else
                    holder.mla_singl_site_ll_dtls.setVisibility(View.VISIBLE);
            }
        });
        holder.mla_singl_site_rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lvl == 0) {
                    selectedModel4snglSite = lstModel4snglSite.get(position);
                    Toast.makeText(acti, "0 level", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(acti, ActivityButtons.class);
                    intent.putExtra("buttonLevel", 1);
                    intent.putExtra("inBtnID", selectedModel4ButtonsEPermissions0.getG());
                    acti.startActivity(intent);
                } else {
                    seltdLvl2Model4snglSite = lstModel4snglSite.get(position);
                    Toast.makeText(acti, "level 1", Toast.LENGTH_LONG).show();
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
        if (lstModel4snglSite != null)
            return lstModel4snglSite.size();
        else
            return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        RadioButton mla_singl_site_rb;
        ProgressBar mla_singl_site_pb;
        TextView mla_singl_site_tv_actual_start_dtt, mla_singl_site_tv_start_dtt,
                mla_singl_site_tv_end_dtt, mla_singl_site_tv_perc,
                mla_singl_site_tv_sr;
        LinearLayout mla_singl_site_ll_dtls;
        ImageView mla_singl_site_iv_xpa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mla_singl_site_rb = itemView.findViewById(R.id.la_singl_site_rb);
            mla_singl_site_pb = itemView.findViewById(R.id.la_singl_site_pb);
            mla_singl_site_tv_actual_start_dtt = itemView.findViewById(R.id.la_singl_site_tv_actual_start_dtt);
            mla_singl_site_tv_start_dtt = itemView.findViewById(R.id.la_singl_site_tv_start_dtt);
            mla_singl_site_tv_end_dtt = itemView.findViewById(R.id.la_singl_site_tv_end_dtt);
            mla_singl_site_tv_perc = itemView.findViewById(R.id.la_singl_site_tv_perc);
            mla_singl_site_tv_sr = itemView.findViewById(R.id.la_singl_site_tv_sr);
            mla_singl_site_ll_dtls = itemView.findViewById(R.id.la_singl_site_ll_dtls);
            mla_singl_site_ll_dtls.setVisibility(View.GONE);
            mla_singl_site_iv_xpa = itemView.findViewById(R.id.la_singl_site_iv_xpa);
        }
    }

    public int getRandColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}
