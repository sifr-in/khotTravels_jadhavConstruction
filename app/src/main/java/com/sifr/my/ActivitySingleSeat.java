package com.sifr.my;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.sifr.my.common.CommonChecks;
import com.sifr.my.retrofit.Model4SeatsDtls;
import com.sifr.my.retrofit.Model4VehicleSeatsLst;
import com.sifr.my.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sifr.my.ActiSelVeh2bokSeats.chosenDt;
import static com.sifr.my.AdapterForSeatsLst.ppModel4VehicleSeatsLst;
import static com.sifr.my.Whom2Work4.model4MXonly;

public class ActivitySingleSeat extends AppCompatActivity {
    private final String TAG = "sfr ActivitySingleSeat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_Dialog);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_seat);
        getWindow().setBackgroundDrawable(null);
        getWindow().setGravity(Gravity.BOTTOM);
//        getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        int pPosi = getIntent().getIntExtra("param_posi", 0);

        if (pPosi > -1) {
            TextInputEditText mla_seat_itm_et_ocupier_n = findViewById(R.id.la_seat_itm_et_ocupier_n);
            mla_seat_itm_et_ocupier_n.setText("seat no.:\n" + ppModel4VehicleSeatsLst.getVesLst().get(pPosi).getA());//seat number
            RadioButton mla_seat_itm_rb_male = findViewById(R.id.la_seat_itm_rb_male);
            mla_seat_itm_rb_male.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ppModel4VehicleSeatsLst.getVesLst().get(pPosi).setG(1);
                    mla_seat_itm_et_ocupier_n.setBackgroundColor(Color.parseColor("#808080"));
                }
            });
            RadioButton mla_seat_itm_rb_fmale = findViewById(R.id.la_seat_itm_rb_fmale);
            mla_seat_itm_rb_fmale.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ppModel4VehicleSeatsLst.getVesLst().get(pPosi).setG(2);
                    mla_seat_itm_et_ocupier_n.setBackgroundColor(Color.parseColor("#FFC0CB"));
                }
            });

            if (ppModel4VehicleSeatsLst.getVesLst().get(pPosi).getG() == 1) {
                mla_seat_itm_et_ocupier_n.setBackgroundColor(Color.parseColor("#808080"));
                mla_seat_itm_rb_male.setChecked(true);
            } else if (ppModel4VehicleSeatsLst.getVesLst().get(pPosi).getG() == 2) {
                mla_seat_itm_et_ocupier_n.setBackgroundColor(Color.parseColor("#FFC0CB"));
                mla_seat_itm_rb_fmale.setChecked(true);
            }

            boolean forCancel = getIntent().getBooleanExtra("cancel", false);
            Button mla_seat_itm_bt_cancel = findViewById(R.id.la_seat_itm_bt_cancel);
            if (forCancel) {
                mla_seat_itm_bt_cancel.setVisibility(View.VISIBLE);
                mla_seat_itm_bt_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final Dialog dialog = new Dialog(ActivitySingleSeat.this);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.layout_loader);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        dialog.show();
                        dialog.setCancelable(false);

                        CommonChecks CommonChks = new CommonChecks(getApplicationContext());
                        if (CommonChks.isOnline()) {

                            Model4VehicleSeatsLst pModel4VehicleSeatsLst = new Model4VehicleSeatsLst();
                            pModel4VehicleSeatsLst.setC(chosenDt);
                            pModel4VehicleSeatsLst.setM(model4MXonly.getM());
                            pModel4VehicleSeatsLst.setTk(model4MXonly.getTk());
                            pModel4VehicleSeatsLst.setVe(Integer.parseInt(ActiSelVeh2bokSeats.choModel4vehicleAvailableByDt.getI()));
                            List<Model4SeatsDtls> vesLst = new ArrayList<>();
                            vesLst.add(ppModel4VehicleSeatsLst.getVesLst().get(pPosi));
                            pModel4VehicleSeatsLst.setVesLst(vesLst);

                            Call<Model4VehicleSeatsLst> call = RetrofitClient.getClient().retroInterfCancelSeat(pModel4VehicleSeatsLst);
                            call.enqueue(new Callback<Model4VehicleSeatsLst>() {
                                @Override
                                public void onResponse(Call<Model4VehicleSeatsLst> call, Response<Model4VehicleSeatsLst> response) {
                                    dialog.dismiss();
                                    if (response.body() != null) {
                                        Log.d(TAG, "110 lu_rcvd=" + response.body().toString());
                                        if (response.isSuccessful()) {
                                            if (response.body().getSuc()) {
                                                Toast.makeText(ActivitySingleSeat.this, "seat successfully cancelled " + response.body().getMsg(), Toast.LENGTH_LONG).show();

                                                Handler handler = new Handler();
                                                handler.postDelayed(new Runnable() {
                                                    public void run() {
                                                        finish();
                                                    }
                                                }, 2000);
                                            } else {
                                                Toast.makeText(ActivitySingleSeat.this, response.body().getMsg(), Toast.LENGTH_LONG).show();
                                            }
                                        } else {
                                            if (response.body().getMsg() != null) {
                                                Toast.makeText(ActivitySingleSeat.this, "There was an error. " + response.body().getMsg(), Toast.LENGTH_LONG).show();
                                            } else {
                                                Toast.makeText(ActivitySingleSeat.this, "Server error. ", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    } else {
                                        Toast.makeText(ActivitySingleSeat.this, "Unable to get response. Contact administrator.", Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Model4VehicleSeatsLst> call, Throwable t) {
                                    dialog.dismiss();
                                    Log.d(TAG, "134 t=" + t.toString());
                                }
                            });
                        } else {
                            Toast.makeText(ActivitySingleSeat.this, "Active internet connection is required.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            } else {
                mla_seat_itm_bt_cancel.setVisibility(View.GONE);
            }
        } else {
            Toast.makeText(ActivitySingleSeat.this, "you cannot use this activity without seat number", Toast.LENGTH_LONG).show();
        }
    }
}