package com.sifr.my;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.sifr.my.common.CommonChecks;
import com.sifr.my.retrofit.Model4ButtonsEPermissions;
import com.sifr.my.retrofit.Model4MXeBtns;
import com.sifr.my.retrofit.Model4successEid;
import com.sifr.my.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sifr.my.Whom2Work4.model4MXonly;

public class Adptr4btnUsrsLst extends RecyclerView.Adapter<Adptr4btnUsrsLst.ViewHolder> {
    private final String TAG = "sfr Adptr4btnUsrsLst";
    private final Activity acti;
    private final List<Model4ButtonsEPermissions> p1Model4ButtonsEPermissions;
    private final int pageId;
    CommonChecks commonChks;

    public Adptr4btnUsrsLst(Activity activityContext, List<Model4ButtonsEPermissions> paramObj, int pageId) {
        this.p1Model4ButtonsEPermissions = paramObj;
        this.acti = activityContext;
        this.pageId = pageId;
        this.commonChks = new CommonChecks(acti);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.layout_sngl_button_user_dtls, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        if (p1Model4ButtonsEPermissions.get(position).getYo() > 0) {
            holder.mla_sngl_buttn_usr_dtls_tv_yn.setText(p1Model4ButtonsEPermissions.get(position).getYn());
            holder.mla_sngl_buttn_usr_dtls_tv_yc.setText("+91 ");
            holder.mla_sngl_buttn_usr_dtls_te_yo.setText(String.valueOf(p1Model4ButtonsEPermissions.get(position).getYo()));

            holder.mla_sngl_buttn_usr_dtls_prmc.setText(String.valueOf(p1Model4ButtonsEPermissions.get(position).getA()));
            holder.mla_sngl_buttn_usr_dtls_prmr.setText(String.valueOf(p1Model4ButtonsEPermissions.get(position).getI()));
            holder.mla_sngl_buttn_usr_dtls_prmu.setText(String.valueOf(p1Model4ButtonsEPermissions.get(position).getB()));
            holder.mla_sngl_buttn_usr_dtls_prmd.setText(String.valueOf(p1Model4ButtonsEPermissions.get(position).getC()));
        } else {
            holder.mla_sngl_buttn_usr_dtls_tv_yc.setText("+91 ");
            holder.mla_sngl_buttn_usr_dtls_tv_yn.setText("not yet assigned to anyone");
        }
        holder.mla_sngl_buttn_usr_dtls_main_tv_dt.setText("allowed to use this button till\n" + p1Model4ButtonsEPermissions.get(position).getQ());
        holder.mla_sngl_buttn_usr_dtls_main_tv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.mla_sngl_buttn_usr_dtls_te_yo.getText().toString().length() == 10) {
                    if (commonChks.isOnline()) {
                        final Dialog dialog = new Dialog(acti);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.layout_loader);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        dialog.show();
                        dialog.setCancelable(false);
                        Model4MXeBtns mModel4MXeBtns = new Model4MXeBtns();
                        mModel4MXeBtns.setMx(model4MXonly);
                        List<Model4ButtonsEPermissions> mModel4BtnLstOnly = new ArrayList<>();
                        p1Model4ButtonsEPermissions.get(position).setYc(Integer.parseInt(holder.mla_sngl_buttn_usr_dtls_tv_yc.getText().toString().replace("+", "").trim()));
                        p1Model4ButtonsEPermissions.get(position).setYo(Long.parseLong(holder.mla_sngl_buttn_usr_dtls_te_yo.getText().toString()));
                        p1Model4ButtonsEPermissions.get(position).setG(pageId);
                        p1Model4ButtonsEPermissions.get(position).setA(Integer.parseInt(holder.mla_sngl_buttn_usr_dtls_prmc.getText().toString()));
                        p1Model4ButtonsEPermissions.get(position).setI(Integer.parseInt(holder.mla_sngl_buttn_usr_dtls_prmr.getText().toString()));
                        p1Model4ButtonsEPermissions.get(position).setB(Integer.parseInt(holder.mla_sngl_buttn_usr_dtls_prmu.getText().toString()));
                        p1Model4ButtonsEPermissions.get(position).setC(Integer.parseInt(holder.mla_sngl_buttn_usr_dtls_prmd.getText().toString()));
                        mModel4BtnLstOnly.add(p1Model4ButtonsEPermissions.get(position));
                        mModel4MXeBtns.setBtn(new ArrayList<>());
                        mModel4MXeBtns.setBtn(mModel4BtnLstOnly);

                        Call<Model4successEid> call = RetrofitClient.getClient().retroInterfSetORupBtnPerm(mModel4MXeBtns);
                        call.enqueue(new Callback<Model4successEid>() {
                            @Override
                            public void onResponse(Call<Model4successEid> call, Response<Model4successEid> response) {
                                dialog.dismiss();
                                if (response.body() != null) {
                                    Log.d(TAG, "79 lu_rcvd=" + response.body().toString());
                                    if (response.isSuccessful()) {
                                        if (response.body().getSuc()) {
                                            holder.mla_sngl_buttn_usr_dtls_main_tv_update.setEnabled(false);
                                            Toast.makeText(acti, "allowed successfully.", Toast.LENGTH_LONG).show();
                                            Snackbar snackbar = Snackbar
                                                    .make(holder.mla_sngl_buttn_usr_dtls_main_tv_update, "allowed successfully.", Snackbar.LENGTH_LONG);
                                            snackbar.show();
                                        } else {
                                            Toast.makeText(acti, "error. " + response.body().getMsg(), Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        if (response.body().getMsg() != null) {
                                            Toast.makeText(acti, "There was an error. " + response.body().getMsg(), Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(acti, "Server error. ", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                } else {
                                    Toast.makeText(acti, "Unable to get response. Contact administrator.", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Model4successEid> call, Throwable t) {
                                dialog.dismiss();
                                Log.d(TAG, "101 t=" + t.toString());
                            }
                        });
                    } else {
                        Toast.makeText(acti, "internet connection required.", Toast.LENGTH_LONG).show();
                        Snackbar snackbar = Snackbar
                                .make(holder.mla_sngl_buttn_usr_dtls_main_tv_update, "internet connection required", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                } else {
                    Toast.makeText(acti, "10 digit mobile no. required.", Toast.LENGTH_LONG).show();
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
        if (p1Model4ButtonsEPermissions != null)
            return p1Model4ButtonsEPermissions.size();
        else
            return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextInputEditText mla_sngl_buttn_usr_dtls_te_yo,
                mla_sngl_buttn_usr_dtls_prmc, mla_sngl_buttn_usr_dtls_prmr, mla_sngl_buttn_usr_dtls_prmu, mla_sngl_buttn_usr_dtls_prmd;
        private final TextView mla_sngl_buttn_usr_dtls_tv_yn, mla_sngl_buttn_usr_dtls_tv_yc, mla_sngl_buttn_usr_dtls_main_tv_dt;
        private final Button mla_sngl_buttn_usr_dtls_main_tv_update;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mla_sngl_buttn_usr_dtls_tv_yn = itemView.findViewById(R.id.la_sngl_buttn_usr_dtls_tv_yn);
            this.mla_sngl_buttn_usr_dtls_tv_yc = itemView.findViewById(R.id.la_sngl_buttn_usr_dtls_tv_yc);
            this.mla_sngl_buttn_usr_dtls_te_yo = itemView.findViewById(R.id.la_sngl_buttn_usr_dtls_te_yo);
            this.mla_sngl_buttn_usr_dtls_prmc = itemView.findViewById(R.id.la_sngl_buttn_usr_dtls_prmc);
            this.mla_sngl_buttn_usr_dtls_prmr = itemView.findViewById(R.id.la_sngl_buttn_usr_dtls_prmr);
            this.mla_sngl_buttn_usr_dtls_prmu = itemView.findViewById(R.id.la_sngl_buttn_usr_dtls_prmu);
            this.mla_sngl_buttn_usr_dtls_prmd = itemView.findViewById(R.id.la_sngl_buttn_usr_dtls_prmd);
            this.mla_sngl_buttn_usr_dtls_main_tv_dt = itemView.findViewById(R.id.la_sngl_buttn_usr_dtls_main_tv_dt);
            this.mla_sngl_buttn_usr_dtls_main_tv_update = itemView.findViewById(R.id.la_sngl_buttn_usr_dtls_main_tv_update);
        }
    }
}
