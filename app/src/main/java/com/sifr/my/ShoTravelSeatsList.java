package com.sifr.my;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sifr.my.common.CommonChecks;
import com.sifr.my.retrofit.Model4VehicleSeatsLst;
import com.sifr.my.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sifr.my.ActiSelVeh2bokSeats.chosenDt;
import static com.sifr.my.AdapterForSeatsLst.ppModel4VehicleSeatsLst;
import static com.sifr.my.Whom2Work4.model4MXonly;
import static com.sifr.my.SeatBookerActivity.seatsBooked;

public class ShoTravelSeatsList extends AppCompatActivity {
    private final String TAG = "sfr ShoTravelSeatsList";
    private RecyclerView mact_trav_seats_lst_rc;
    private AdapterForSeatsLst adapterForSeatsLst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_seats_list);

        Button mact_trav_seats_lst_btn_book = findViewById(R.id.act_trav_seats_lst_btn_book);
        mact_trav_seats_lst_rc = findViewById(R.id.act_trav_seats_lst_rc);
        TextView mact_trav_seats_lst_cnt_tv = findViewById(R.id.act_trav_seats_lst_cnt_tv);
        mact_trav_seats_lst_cnt_tv.setVisibility(View.GONE);

        mact_trav_seats_lst_btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SeatBookerActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_loader);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
        dialog.setCancelable(false);

        CommonChecks CommonChks = new CommonChecks(getApplicationContext());
        if (CommonChks.isOnline()) {

            Model4VehicleSeatsLst model4VehicleSeatsLst = new Model4VehicleSeatsLst();
            model4VehicleSeatsLst.setVe(Integer.parseInt(ActiSelVeh2bokSeats.choModel4vehicleAvailableByDt.getI()));
            model4VehicleSeatsLst.setM(model4MXonly.getM());
            model4VehicleSeatsLst.setC(chosenDt);
            Call<Model4VehicleSeatsLst> call = RetrofitClient.getClient().retroInterfGetSeatLst(model4VehicleSeatsLst);

            call.enqueue(new Callback<Model4VehicleSeatsLst>() {
                @Override
                public void onResponse(Call<Model4VehicleSeatsLst> call, Response<Model4VehicleSeatsLst> response) {
                    dialog.dismiss();
                    if (response.body() != null) {
                        Log.d("loog", TAG + "137 lu_rcvd=" + response.body().toString());
                        if (response.isSuccessful()) {
                            if (response.body().getSuc()) {
                                VehicleStopListActivity.pModel4TravelStops = response.body().getTsLst();
                                adapterForSeatsLst = new AdapterForSeatsLst(ShoTravelSeatsList.this, response.body());
                                mact_trav_seats_lst_rc.setAdapter(adapterForSeatsLst);
                                GridLayoutManager manager = new GridLayoutManager(ShoTravelSeatsList.this, 5, GridLayoutManager.VERTICAL, false);
                                mact_trav_seats_lst_rc.setLayoutManager(manager);
                                Log.d("loog", TAG + "143 lu_rcvd=");
                            } else {
                                Toast.makeText(ShoTravelSeatsList.this, response.body().getMsg(), Toast.LENGTH_LONG).show();
                            }
                        } else {
                            if (response.body().getMsg() != null) {
                                Toast.makeText(ShoTravelSeatsList.this, "There was an error. " + response.body().getMsg(), Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(ShoTravelSeatsList.this, "Server error. ", Toast.LENGTH_LONG).show();
                            }
                        }
                    } else {
                        Toast.makeText(ShoTravelSeatsList.this, "Unable to get response. Contact administrator.", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Model4VehicleSeatsLst> call, Throwable t) {
                    dialog.dismiss();
                    Log.d("loog", TAG + "162 t=" + t.toString());
                }
            });
        } else {
            Toast.makeText(ShoTravelSeatsList.this, "Active internet connection is required.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (seatsBooked) {
            for (int i = 0; i < ppModel4VehicleSeatsLst.getVesLst().size(); i++) {
                if (ppModel4VehicleSeatsLst.getVesLst().get(i).getS() != null && ppModel4VehicleSeatsLst.getVesLst().get(i).getS().equals("1")) {
                    adapterForSeatsLst.updateList();
                }
            }
            seatsBooked = false;
        }
    }
}