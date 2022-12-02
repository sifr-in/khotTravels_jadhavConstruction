package com.sifr.my;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sifr.my.retrofit.Model4VehicleSeatsLst;

import static com.sifr.my.ActivityButtons.selectedModel4ButtonsEPermissions0;

public class AdapterForSeatsLst extends RecyclerView.Adapter<AdapterForSeatsLst.ViewHolder> {
    private final String TAG = "sfr AdapterForSeatsLst";
    private View listItem;
    public static Model4VehicleSeatsLst ppModel4VehicleSeatsLst;
    private Activity acti;

    public AdapterForSeatsLst(Activity activityContext, Model4VehicleSeatsLst paramObj) {
        this.acti = activityContext;
        ppModel4VehicleSeatsLst = paramObj;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        listItem = layoutInflater.inflate(R.layout.layout_seat_item, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        if (ppModel4VehicleSeatsLst.getVesLst().get(position).getB() != null && ppModel4VehicleSeatsLst.getVesLst().get(position).getB().equals("0")) {
            if (ppModel4VehicleSeatsLst.getVesLst().get(position).getR() != null && ppModel4VehicleSeatsLst.getVesLst().get(position).getR().equals("1")) {
                //        android:background="#55fa555a"
                if (ppModel4VehicleSeatsLst.getVesLst().get(position).getG() == 2) {
                    holder.mla_seat_itm_iv.setBackgroundColor(Color.parseColor("#FFC0CB"));
                } else if (ppModel4VehicleSeatsLst.getVesLst().get(position).getG() == 1) {
                    holder.mla_seat_itm_iv.setBackgroundColor(Color.parseColor("#FFD000"));
                } else {
                    holder.mla_seat_itm_iv.setBackgroundColor(Color.GRAY);
                }
                holder.mla_seat_itm_cb_booked.setChecked(true);
                if (selectedModel4ButtonsEPermissions0.getC() == 3) {
                    holder.mla_seat_itm_cb_booked.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!holder.mla_seat_itm_cb_booked.isChecked()) {
                                yesno(holder, position, "are u sure?", "are u sure, u want to cancell this booked seat?");
                            }
                        }
                    });
                } else {
                    holder.mla_seat_itm_cb_booked.setEnabled(false);
                }
            } else {
                holder.mla_seat_itm_cb_booked.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (holder.mla_seat_itm_cb_booked.isChecked())
                            ppModel4VehicleSeatsLst.getVesLst().get(position).setS("1");
                        else
                            ppModel4VehicleSeatsLst.getVesLst().get(position).setS("0");
                    }
                });
            }
            holder.mla_seat_itm_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ActivitySingleSeat.class);
                    intent.putExtra("param_posi", position);
                    v.getContext().startActivity(intent);
                }
            });
            holder.mla_seat_itm_cb_booked.setText(ppModel4VehicleSeatsLst.getVesLst().get(position).getA());
        } else {
            holder.mla_seat_itm_ll_main.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return ppModel4VehicleSeatsLst.getVesLst().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mla_seat_itm_ll_main;
        private ImageView mla_seat_itm_iv;
        private CheckBox mla_seat_itm_cb_booked;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mla_seat_itm_ll_main = itemView.findViewById(R.id.la_seat_itm_ll_main);
            this.mla_seat_itm_iv = itemView.findViewById(R.id.la_seat_itm_iv);
            this.mla_seat_itm_cb_booked = itemView.findViewById(R.id.la_seat_itm_cb_booked);
        }
    }

    public void updateList() {
        this.notifyDataSetChanged();
    }

    private void yesno(ViewHolder holder, int position, String ttl, String msg) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(acti);

        // Setting LoginWithMobAndCCode Title
        alertDialog.setTitle(ttl);
        // Setting LoginWithMobAndCCode Message
        alertDialog.setMessage(msg);
        // Setting Icon to LoginWithMobAndCCode
//        alertDialog.setIcon(R.drawable.circle);

        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                holder.mla_seat_itm_cb_booked.setChecked(true);
                dialog.cancel();
            }
        });
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(acti, ActivitySingleSeat.class);
                intent.putExtra("param_posi", position);
                intent.putExtra("cancel", true);
                acti.startActivity(intent);
                dialog.cancel();
                acti.finish();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
}
