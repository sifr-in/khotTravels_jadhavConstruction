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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.sifr.my.common.CommonChecks;
import com.sifr.my.retrofit.Model4ButtonsEPermissions;
import com.sifr.my.retrofit.Model4LstTravelStops;
import com.sifr.my.retrofit.Model4VehicleSeatsLst;
import com.sifr.my.retrofit.Model4lstOfVehicle;
import com.sifr.my.retrofit.Model4successEid;
import com.sifr.my.retrofit.Model4vehicle;
import com.sifr.my.retrofit.Model4vehicleAvailableByDt;
import com.sifr.my.retrofit.RetrofitClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sifr.my.Adapter4lstOfVehicles.sltdModel4vehicle;
import static com.sifr.my.Adapter4travelStops.sltdModel4TravelStops;
import static com.sifr.my.Whom2Work4.mModel4BtnLstOnly;
import static com.sifr.my.Whom2Work4.model4MXonly;
import static com.sifr.my.VehicleStopListActivity.pModel4TravelStops;

public class Activity2addVehicle extends AppCompatActivity {
    private final String TAG = "sfr Activity2addVehicle";
    Adapter4lstOfVehicles mAdapter4lstOfVehicles;
    private List<Model4vehicle> mModel4vehicle;
    private DatePickerDialog mDatePicker;
    TextView mact_to_add_vehicle_tv_dt, mact_to_add_vehicle_tv_pckup, mact_to_add_vehicle_tv_drop;
    private int dropPoint, pickPoint;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private String chosenDt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2add_vehicle);

        TextInputEditText mact_to_add_vehicle_et = findViewById(R.id.act_to_add_vehicle_et);
        Button mact_to_add_vehicle_bt = findViewById(R.id.act_to_add_vehicle_bt);
        mact_to_add_vehicle_bt.setEnabled(false);
        Button mact_to_add_vehicle_bydt_bt = findViewById(R.id.act_to_add_vehicle_bydt_bt);
        LinearLayout mact_to_add_vehicle_ll_bydt_bt = findViewById(R.id.act_to_add_vehicle_ll_bydt_bt);
        mact_to_add_vehicle_tv_dt = findViewById(R.id.act_to_add_vehicle_tv_dt);
        mact_to_add_vehicle_tv_pckup = findViewById(R.id.act_to_add_vehicle_tv_pckup);
        mact_to_add_vehicle_tv_drop = findViewById(R.id.act_to_add_vehicle_tv_drop);

        mact_to_add_vehicle_tv_pckup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sltdModel4vehicle != null) {
                    if (!pModel4TravelStops.isEmpty()) {
                        opnActi(1);
                    } else {
                        fetchList(1);
                    }
                } else {
                    Toast.makeText(Activity2addVehicle.this, "select vehicle first", Toast.LENGTH_LONG).show();
                }
            }
        });
        mact_to_add_vehicle_tv_drop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sltdModel4vehicle != null) {
                    if (!pModel4TravelStops.isEmpty()) {
                        opnActi(2);
                    } else {
                        fetchList(2);
                    }
                } else {
                    Toast.makeText(Activity2addVehicle.this, "select vehicle first", Toast.LENGTH_LONG).show();
                }
            }
        });

        mact_to_add_vehicle_tv_dt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                mDatePicker = new DatePickerDialog(Activity2addVehicle.this,
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

                                mact_to_add_vehicle_tv_dt.setText(year + "-" + strMnth + "-" + strDay);
                                chosenDt = year + "-" + strMnth + "-" + strDay;
                            }
                        }, year, month, day);
                mDatePicker.show();
            }
        });

        mact_to_add_vehicle_ll_bydt_bt.setVisibility(View.GONE);
        RecyclerView act_to_add_vehicle_rv = findViewById(R.id.act_to_add_vehicle_rv);

        final Dialog dialog = new Dialog(Activity2addVehicle.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_loader);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
        dialog.setCancelable(false);

        boolean hasPerm = false;
        for (Model4ButtonsEPermissions btn : mModel4BtnLstOnly) {
            if (btn.getG() == 228)
                hasPerm = true;
        }
        if (hasPerm) {
            mact_to_add_vehicle_ll_bydt_bt.setVisibility(View.VISIBLE);
        }

        mact_to_add_vehicle_bydt_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chosenDt != null && chosenDt.length() > 0) {
                    try {
                        Date strDate = sdf.parse(chosenDt);
                        final Calendar clndr = Calendar.getInstance();
                        clndr.setTime(strDate);
                        clndr.add(Calendar.DAY_OF_YEAR, +1);
                        if (new Date().before(clndr.getTime())) {
                            if (pickPoint > 0) {
                                if (dropPoint > 0) {
                                    if (Integer.parseInt(sltdModel4vehicle.getD()) > 0) {
                                        Model4vehicleAvailableByDt pModel4vehicleAvailableByDt = new Model4vehicleAvailableByDt();
                                        pModel4vehicleAvailableByDt.setI(sltdModel4vehicle.getD());
                                        pModel4vehicleAvailableByDt.setC(chosenDt);
                                        pModel4vehicleAvailableByDt.setL(String.valueOf(pickPoint));
                                        pModel4vehicleAvailableByDt.setG(String.valueOf(dropPoint));
                                        pModel4vehicleAvailableByDt.setMx(model4MXonly);

                                        final Dialog dialog = new Dialog(Activity2addVehicle.this);
                                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                        dialog.setContentView(R.layout.layout_loader);
                                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                                        dialog.show();
                                        dialog.setCancelable(false);

                                        Call<Model4successEid> call = RetrofitClient.getClient().retroInterfAorUVecAvailByDt(pModel4vehicleAvailableByDt);
                                        call.enqueue(new Callback<Model4successEid>() {
                                            @Override
                                            public void onResponse(Call<Model4successEid> call, Response<Model4successEid> response) {
                                                dialog.dismiss();
                                                if (response.body() != null) {
                                                    Log.d(TAG, "79 lu_rcvd=" + response.body().toString());
                                                    if (response.isSuccessful()) {
                                                        if (response.body().getSuc()) {
                                                            Log.d(TAG, "57 " + response.body());
                                                            if (response.body().getSuc()) {
                                                                Toast.makeText(Activity2addVehicle.this, response.body().getMsg(), Toast.LENGTH_LONG).show();
                                                            }
                                                        }
                                                    } else {
                                                        if (response.body().getMsg() != null) {
                                                            Toast.makeText(Activity2addVehicle.this, "There was an error. " + response.body().getMsg(), Toast.LENGTH_LONG).show();
                                                        } else {
                                                            Toast.makeText(Activity2addVehicle.this, "Server error. ", Toast.LENGTH_LONG).show();
                                                        }
                                                    }
                                                } else {
                                                    Toast.makeText(Activity2addVehicle.this, "Unable to get response. Contact administrator.", Toast.LENGTH_LONG).show();
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<Model4successEid> call, Throwable t) {
                                                dialog.dismiss();
                                                Log.d(TAG, "101 t=" + t.toString());
                                            }
                                        });
                                    } else {
                                        Toast.makeText(Activity2addVehicle.this, "209 drop point must be selected ", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(Activity2addVehicle.this, "213 drop point must be selected ", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(Activity2addVehicle.this, "216 pickup point must be selected ", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(Activity2addVehicle.this, "219 date must be today onwards " + clndr.get(Calendar.DAY_OF_MONTH), Toast.LENGTH_LONG).show();
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                        Toast.makeText(Activity2addVehicle.this, "223 date is incorrect", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Activity2addVehicle.this, "226 date is incorrect", Toast.LENGTH_LONG).show();
                }
            }
        });

        Call<Model4lstOfVehicle> call = RetrofitClient.getClient().retroInterfGetVecLst(model4MXonly);
        call.enqueue(new Callback<Model4lstOfVehicle>() {
            @Override
            public void onResponse(Call<Model4lstOfVehicle> call, Response<Model4lstOfVehicle> response) {
                dialog.dismiss();
                if (response.body() != null) {
                    Log.d(TAG, "79 lu_rcvd=" + response.body().toString());
                    if (response.isSuccessful()) {
                        if (response.body().getSuc()) {
                            Log.d(TAG, "57 " + response.body());
                            mModel4vehicle = response.body().getLst1();
                            mAdapter4lstOfVehicles = new Adapter4lstOfVehicles(Activity2addVehicle.this, mModel4vehicle);
                            act_to_add_vehicle_rv.setAdapter(mAdapter4lstOfVehicles);
                            // TO DO
                            //                                        mact_for_whom_bt_sho_btns.setVisibility(View.GONE);
                            // mact_for_whom_bt_ll_main.setVisibility(View.VISIBLE);
                        }
                    } else {
                        if (response.body().getMsg() != null) {
                            Toast.makeText(Activity2addVehicle.this, "There was an error. " + response.body().getMsg(), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(Activity2addVehicle.this, "Server error. ", Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    Toast.makeText(Activity2addVehicle.this, "Unable to get response. Contact administrator.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Model4lstOfVehicle> call, Throwable t) {
                dialog.dismiss();
                Log.d(TAG, "101 t=" + t.toString());
            }
        });

        mact_to_add_vehicle_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Validation.isTextInGivenFormat(mact_to_add_vehicle_et, "incorrect format", true,
                        getApplicationContext(), "^[A-Z|a-z]{2}[0-9]{1,2}[A-Z|a-z]{0,3}[0-9]{1,4}$")) {
//                        getApplicationContext(), "^[A-Z|a-z]{2}\\-?[0-9]{1,2}\\-?[A-Z|a-z]{0,3}\\-?[0-9]{1,4}$")) {

                    final Dialog dialog = new Dialog(Activity2addVehicle.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.layout_loader);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    dialog.show();
                    dialog.setCancelable(false);

                    Model4vehicle pModel4vehicle = new Model4vehicle();
                    pModel4vehicle.setMx(model4MXonly);
                    pModel4vehicle.setN(mact_to_add_vehicle_et.getText().toString());

                    Call<Model4successEid> call = RetrofitClient.getClient().retroInterfAddVecInOurLst(pModel4vehicle);
                    call.enqueue(new Callback<Model4successEid>() {
                        @Override
                        public void onResponse(Call<Model4successEid> call, Response<Model4successEid> response) {
                            dialog.dismiss();
                            if (response.body() != null) {
                                Log.d(TAG, "79 lu_rcvd=" + response.body().toString());
                                if (response.isSuccessful()) {
                                    if (response.body().getSuc()) {
                                        Log.d(TAG, "57 " + response.body());
                                        if (response.body().getSuc()) {
                                            pModel4vehicle.setD(response.body().getD());
                                            mModel4vehicle.add(pModel4vehicle);
                                        }
                                        mAdapter4lstOfVehicles.updateList(mModel4vehicle);
                                        // TO DO
                                        //                                        mact_for_whom_bt_sho_btns.setVisibility(View.GONE);
                                        // mact_for_whom_bt_ll_main.setVisibility(View.VISIBLE);
                                    }
                                } else {
                                    if (response.body().getMsg() != null) {
                                        Toast.makeText(Activity2addVehicle.this, "There was an error. " + response.body().getMsg(), Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(Activity2addVehicle.this, "Server error. ", Toast.LENGTH_LONG).show();
                                    }
                                }
                            } else {
                                Toast.makeText(Activity2addVehicle.this, "Unable to get response. Contact administrator.", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Model4successEid> call, Throwable t) {
                            dialog.dismiss();
                            Log.d(TAG, "101 t=" + t.toString());
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "enter correct vehicle number with '-' in between", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (sltdModel4TravelStops != null && sltdModel4TravelStops.getD() > 0) {
            if (sltdModel4TravelStops.getPod() == 1) {
                pickPoint = sltdModel4TravelStops.getD();
                mact_to_add_vehicle_tv_pckup.setText(sltdModel4TravelStops.getN());
            } else {
                mact_to_add_vehicle_tv_drop.setText(sltdModel4TravelStops.getN());
                dropPoint = sltdModel4TravelStops.getD();
            }
        }
    }

    private void fetchList(int i) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_loader);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
        dialog.setCancelable(false);

        CommonChecks CommonChks = new CommonChecks(getApplicationContext());
        if (CommonChks.isOnline()) {

            Model4VehicleSeatsLst model4VehicleSeatsLst = new Model4VehicleSeatsLst();
            model4VehicleSeatsLst.setVe(Integer.parseInt(sltdModel4vehicle.getD()));
            model4VehicleSeatsLst.setM(model4MXonly.getM());
            model4VehicleSeatsLst.setC(chosenDt);//even if this date is 00 list of stops is returned
            Call<Model4LstTravelStops> call = RetrofitClient.getClient().retroInterfGetStopLst(model4VehicleSeatsLst);

            call.enqueue(new Callback<Model4LstTravelStops>() {
                @Override
                public void onResponse(Call<Model4LstTravelStops> call, Response<Model4LstTravelStops> response) {
                    dialog.dismiss();
                    if (response.body() != null) {
                        Log.d("loog", TAG + "137 lu_rcvd=" + response.body().toString());
                        if (response.isSuccessful()) {
                            if (response.body().getSuc()) {
                                pModel4TravelStops = response.body().getTsLst();
                                Log.d("loog", TAG + "143 lu_rcvd=");
                                opnActi(i);
                            } else {
                                Toast.makeText(Activity2addVehicle.this, response.body().getMsg(), Toast.LENGTH_LONG).show();
                            }
                        } else {
                            if (response.body().getMsg() != null) {
                                Toast.makeText(Activity2addVehicle.this, "There was an error. " + response.body().getMsg(), Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(Activity2addVehicle.this, "Server error. ", Toast.LENGTH_LONG).show();
                            }
                        }
                    } else {
                        Toast.makeText(Activity2addVehicle.this, "Unable to get response. Contact administrator.", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Model4LstTravelStops> call, Throwable t) {
                    dialog.dismiss();
                    Log.d("loog", TAG + "162 t=" + t.toString());
                }
            });
        } else {
            Toast.makeText(Activity2addVehicle.this, "Active internet connection is required.", Toast.LENGTH_LONG).show();
        }
    }

    ;

    private void opnActi(int i) {
        Intent intent = new Intent(Activity2addVehicle.this, VehicleStopListActivity.class);
        intent.putExtra("px_pod", i);
        startActivity(intent);
    }
}