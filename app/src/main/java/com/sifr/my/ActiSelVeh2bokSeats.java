package com.sifr.my;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.sifr.my.common.CommonChecks;
import com.sifr.my.retrofit.Model4lstVehicleAvailableByDt;
import com.sifr.my.retrofit.Model4vehicleAvailableByDt;
import com.sifr.my.retrofit.RetrofitClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sifr.my.Whom2Work4.model4MXonly;

public class ActiSelVeh2bokSeats extends AppCompatActivity {
    private final String TAG = "sfr ActiSelVeh2bokSeats";
    private TextView mact_sel_veh_2book_tv;
    private DatePickerDialog mDatePicker;
    private RecyclerView mact_sel_veh_2book_rc;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static Model4vehicleAvailableByDt choModel4vehicleAvailableByDt;
    public static String chosenDt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2select_vehicle2book_seats);
        mact_sel_veh_2book_tv = findViewById(R.id.act_sel_veh_2book_tv);
        mact_sel_veh_2book_rc = findViewById(R.id.act_sel_veh_2book_rc);
        Button mact_sel_veh_2book_bt = findViewById(R.id.act_sel_veh_2book_bt);

        mact_sel_veh_2book_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                mDatePicker = new DatePickerDialog(ActiSelVeh2bokSeats.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                String strMnth = "", strDay = "";

                                if ((monthOfYear + 1) < 10)
                                    strMnth = ("0" + (monthOfYear + 1));
                                else
                                    strMnth = (String.valueOf(monthOfYear + 1));

                                if ((dayOfMonth) < 10)
                                    strDay = ("0" + (dayOfMonth));
                                else
                                    strDay = (String.valueOf(dayOfMonth));

                                mact_sel_veh_2book_tv.setText(year + "-" + strMnth + "-" + strDay);
                                getVecList(mact_sel_veh_2book_bt);
                            }
                        }, year, month, day);
                mDatePicker.show();
            }
        });

        mact_sel_veh_2book_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (choModel4vehicleAvailableByDt != null) {
                    Intent intent = new Intent(ActiSelVeh2bokSeats.this, ShoTravelSeatsList.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(ActiSelVeh2bokSeats.this, "146 select a vehicle", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void getVecList(Button mact_sel_veh_2book_bt) {
        try {
            Date strDate = sdf.parse(mact_sel_veh_2book_tv.getText().toString());
            final Calendar clndr = Calendar.getInstance();
            clndr.setTime(strDate);
            clndr.add(Calendar.DAY_OF_YEAR, +1);
            if (new Date().before(clndr.getTime())) {
                chosenDt = mact_sel_veh_2book_tv.getText().toString();

                final Dialog dialog = new Dialog(this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.layout_loader);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();
                dialog.setCancelable(false);

                Model4vehicleAvailableByDt pModel4vehicleAvailableByDt = new Model4vehicleAvailableByDt();
                pModel4vehicleAvailableByDt.setM(String.valueOf(model4MXonly.getM()));
                pModel4vehicleAvailableByDt.setC(chosenDt);

                CommonChecks commonChks = new CommonChecks(getApplicationContext());
                if (commonChks.isOnline()) {
                    Call<Model4lstVehicleAvailableByDt> call = RetrofitClient.getClient().retroInterfGetVehListOfDt(pModel4vehicleAvailableByDt);

                    call.enqueue(new Callback<Model4lstVehicleAvailableByDt>() {
                        @Override
                        public void onResponse(Call<Model4lstVehicleAvailableByDt> call, Response<Model4lstVehicleAvailableByDt> response) {
                            dialog.dismiss();
                            if (response.body() != null) {
                                Log.d(TAG, "137 lu_rcvd=" + response.body().toString());
                                if (response.isSuccessful()) {
                                    if (response.body().getSuc()) {
                                        Log.d(TAG, "143 lu_rcvd=");
                                        Adapter4lstVehicleByDt mAdapter4lstVehicleByDt = new Adapter4lstVehicleByDt(ActiSelVeh2bokSeats.this, response.body().getLst1());
                                        mact_sel_veh_2book_rc.setAdapter(mAdapter4lstVehicleByDt);
                                    } else {
                                        Toast.makeText(ActiSelVeh2bokSeats.this, response.body().getMsg(), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    if (response.body().getMsg() != null) {
                                        Toast.makeText(ActiSelVeh2bokSeats.this, "There was an error. " + response.body().getMsg(), Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(ActiSelVeh2bokSeats.this, "Server error. ", Toast.LENGTH_LONG).show();
                                    }
                                }
                            } else {
                                Toast.makeText(ActiSelVeh2bokSeats.this, "Unable to get response. Contact administrator.", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Model4lstVehicleAvailableByDt> call, Throwable t) {
                            dialog.dismiss();
                            Log.d(TAG, "162 t=" + t.toString());
                        }
                    });
                } else {
                    Toast.makeText(ActiSelVeh2bokSeats.this, "Active internet connection is required.", Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(ActiSelVeh2bokSeats.this, "149 date must be today onwards " + clndr.get(Calendar.DAY_OF_MONTH), Toast.LENGTH_LONG).show();
            }
        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(ActiSelVeh2bokSeats.this, "153 date is incorrect", Toast.LENGTH_LONG).show();
        }
    }
}