package com.sifr.my;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sifr.my.retrofit.Model4ButtonsEPermissions;

import java.util.ArrayList;
import java.util.List;

import static com.sifr.my.Whom2Work4.mModel4BtnLstOnly;
import static com.sifr.my.Whom2Work4.model4MXonly;
import static com.sifr.my.ActivityButtons.selectedModel4ButtonsEPermissions0;
import static com.sifr.my.ActivityButtons.selectedModel4ButtonsEPermissions1;

public class Adapter4buttonsList extends RecyclerView.Adapter<Adapter4buttonsList.ViewHolder> {
    private final String TAG = "sfr Adapter4buttonsList";
    private final Activity acti;
    private final List<Model4ButtonsEPermissions> mModel4ButtonsEPermissions;
    private final int mButtonLevel;

    public Adapter4buttonsList(Activity activityContext, int pButtonLevel, List<Model4ButtonsEPermissions> paramObj) {
        this.mModel4ButtonsEPermissions = paramObj;
        this.acti = activityContext;
        this.mButtonLevel = pButtonLevel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.layout_single_button, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
//        if (mModel4ButtonsEPermissions.get(position).getY() == pModel4Y4OTP.getY()
//                && mModel4ButtonsEPermissions.get(position).getZ() == pModel4Y4OTP.getZ()) {
        holder.mla_single_btn_itm_tv_lb.setText(mModel4ButtonsEPermissions.get(position).getLb());
        holder.mla_single_btn_itm_iv_xpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Model4ButtonsEPermissions> ppModel4ButtonsEPermissions = new ArrayList<>();
                int pageId = mModel4ButtonsEPermissions.get(position).getG();
                int pageAsignableCount = mModel4ButtonsEPermissions.get(position).getN() - 1;//this -1 is necessary as this person himself is 1 count
                for (Model4ButtonsEPermissions btnperm : mModel4BtnLstOnly) {
//                        if (btnperm.getG() == pageId && btnperm.getY() == 0 && btnperm.getZ() == 0) {
//                            pageAsignableCount = btnperm.getN();
//                        } else {
                    if (btnperm.getG() == pageId && btnperm.getR() == model4MXonly.getY()) {
                        ppModel4ButtonsEPermissions.add(btnperm);
                    }
//                        }
                }
                int remnPageAsignableCount = pageAsignableCount - ppModel4ButtonsEPermissions.size();
                for (int ii = 0; ii < remnPageAsignableCount; ii++) {
                    ppModel4ButtonsEPermissions.add(new Model4ButtonsEPermissions());
                }
                holder.mla_single_btn_itm_tv_xp.setText("this button can be used till " + mModel4ButtonsEPermissions.get(position).getQ() + " and can be assigned to total " + pageAsignableCount + " mobile numbers.");
                holder.mla_single_btn_itm_tv_xp.setVisibility(View.VISIBLE);
                if (pageAsignableCount > 0) {
                    Adptr4btnUsrsLst mAdptr4BtnUsrsLst = new Adptr4btnUsrsLst(acti, ppModel4ButtonsEPermissions, pageId);
                    holder.mla_single_btn_itm_rv.setAdapter(mAdptr4BtnUsrsLst);
                } else {
                    Toast.makeText(acti, "68 nothing is there to show. you can't assign this button to anyone.", Toast.LENGTH_LONG).show();
                }
            }
        });
        holder.mla_single_btn_itm_ll_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mButtonLevel == 0) {
                    selectedModel4ButtonsEPermissions0 = mModel4ButtonsEPermissions.get(position);
                } else if (mButtonLevel == 1) {
                    selectedModel4ButtonsEPermissions1 = mModel4ButtonsEPermissions.get(position);
                }
                startSpecificActivity(mModel4ButtonsEPermissions.get(position).getG(), mModel4ButtonsEPermissions.get(position).getW());
            }
        });
//        } else {
//            holder.mla_single_btn_itm_iv_xpa.setVisibility(View.GONE);
//            holder.mla_single_btn_itm_ll_main.setVisibility(View.GONE);
//        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (mModel4ButtonsEPermissions != null)
            return mModel4ButtonsEPermissions.size();
        else
            return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mla_single_btn_itm_tv_lb, mla_single_btn_itm_tv_xp;
        private final ImageView mla_single_btn_itm_iv_xpa;
        private final LinearLayout mla_single_btn_itm_ll_main;
        private final RecyclerView mla_single_btn_itm_rv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mla_single_btn_itm_tv_lb = itemView.findViewById(R.id.la_single_btn_itm_tv_lb);
            this.mla_single_btn_itm_tv_xp = itemView.findViewById(R.id.la_single_btn_itm_tv_xp);
            this.mla_single_btn_itm_iv_xpa = itemView.findViewById(R.id.la_single_btn_itm_iv_xpa);
            this.mla_single_btn_itm_ll_main = itemView.findViewById(R.id.la_single_btn_itm_ll_main);
            this.mla_single_btn_itm_rv = itemView.findViewById(R.id.la_single_btn_itm_rv);
        }
    }

    private void startSpecificActivity(int g, int btnID) {
        Intent i = null;
        switch (g) {
            case 226:
                i = new Intent(acti, Activity2addVehicle.class);
                break;

            case 227:
                i = new Intent(acti, ActiSelVeh2bokSeats.class);
                break;

            case 229:
                i = new Intent(acti, ShoPSJClist.class);
                break;

            case 230:
                i = new Intent(acti, Activity2selectSite.class);
                break;

            case 231:
                i = new Intent(acti, Acti4empPrsncy.class);
                break;

            case 232:
                i = new Intent(acti, Activity2crudEmp.class);
                break;

            case 233:
                i = new Intent(acti, Acti2addPrgrsStmt.class);
                break;

            case 235:
                i = new Intent(acti, Acti4expensesLst.class);
                break;

            case 236:
//                i = new Intent(acti, Acti4expensesLst.class);
                i = new Intent(acti, Activity2crudXP.class);
                i.putExtra("inBtnID", btnID);
                break;

            case 237:
                i = new Intent(acti, Activity4mchLst.class);
                break;

            case 238:
                i = new Intent(acti, Activity4mchLst.class);
                i.putExtra("inBtnID", btnID);
                break;

            default:
                i = new Intent(acti, ActivityShowNoWorkPage.class);
                break;
        }
        acti.startActivity(i);
    }
}
