package com.sifr.my;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.sifr.my.common.CommonChecks;
import com.sifr.my.retrofit.Model4MXonly;
import com.sifr.my.retrofit.Model4empPresencyLst;
import com.sifr.my.retrofit.Model4snglEmpPresency;
import com.sifr.my.retrofit.Model4snglStr;
import com.sifr.my.retrofit.Model4successEid;
import com.sifr.my.retrofit.RetrofitClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sifr.my.Adapter4lstOfEmpPresency.mModel4snglEmpPresency;
import static com.sifr.my.Adapter4lstSites.selectedModel4snglSite;
import static com.sifr.my.Whom2Work4.model4MXonly;

public class Acti4empPrsncy extends AppCompatActivity {
    private final String TAG = "sfr Acti4empPrsncy";
    private TextView mact_4emp_presency_tv, mact_4emp_presency_tv_tit;
    private DatePickerDialog mDatePicker;
    private RecyclerView mact_4emp_presency_rc;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private String dtChosn4presency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4emp_presency);
        mact_4emp_presency_tv = findViewById(R.id.act_4emp_presency_tv);
        mact_4emp_presency_tv_tit = findViewById(R.id.act_4emp_presency_tv_tit);
        mact_4emp_presency_tv_tit.setText(selectedModel4snglSite.getN());
        mact_4emp_presency_rc = findViewById(R.id.act_4emp_presency_rc);
        Button mact_4emp_presency_bt = findViewById(R.id.act_4emp_presency_bt);

        mact_4emp_presency_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                mDatePicker = new DatePickerDialog(Acti4empPrsncy.this,
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

                                mact_4emp_presency_tv.setText(year + "-" + strMnth + "-" + strDay);
                                getVecList(mact_4emp_presency_bt);
                            }
                        }, year, month, day);
                mDatePicker.show();
            }
        });

        mact_4emp_presency_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Model4snglEmpPresency> lst1 = new ArrayList<>();
                for (Model4snglEmpPresency mEmp : mModel4snglEmpPresency) {
                    if (mEmp.getChg() == 1) {
                        lst1.add(mEmp);
                    }
                }
                if (lst1.size() > 0) {
                    final Dialog dialog = new Dialog(Acti4empPrsncy.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.layout_loader);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    dialog.show();
                    dialog.setCancelable(false);
                    Model4MXonly pModel4MXonly = model4MXonly;
                    pModel4MXonly.setIxd(selectedModel4snglSite.getD());
                    pModel4MXonly.setDt(dtChosn4presency);
                    Model4empPresencyLst pModel4empPresencyLst = new Model4empPresencyLst();
                    pModel4empPresencyLst.setMx(pModel4MXonly);
                    pModel4empPresencyLst.setLst1(lst1);

                    CommonChecks commonChks = new CommonChecks(getApplicationContext());
                    if (commonChks.isOnline()) {
                        Call<Model4successEid> call = RetrofitClient.getClient().retroInterfUpInLabourPresency(pModel4empPresencyLst);

                        call.enqueue(new Callback<Model4successEid>() {
                            @Override
                            public void onResponse(Call<Model4successEid> call, Response<Model4successEid> response) {
                                dialog.dismiss();
                                if (response.body() != null) {
                                    Log.d(TAG, "122 lu_rcvd=" + response.body().toString());
                                    if (response.isSuccessful()) {
                                        if (response.body().getSuc()) {
                                            Toast.makeText(Acti4empPrsncy.this, "presency was updated successfully. " + response.body().getMsg(), Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(Acti4empPrsncy.this, response.body().getMsg(), Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        if (response.body().getMsg() != null) {
                                            Toast.makeText(Acti4empPrsncy.this, "There was an error. " + response.body().getMsg(), Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(Acti4empPrsncy.this, "Server error. ", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                } else {
                                    Toast.makeText(Acti4empPrsncy.this, "Unable to get response. Contact administrator.", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Model4successEid> call, Throwable t) {
                                dialog.dismiss();
                                Log.d(TAG, "146 t=" + t.toString());
                            }
                        });
                    } else {
                        Toast.makeText(Acti4empPrsncy.this, "151 Active internet connection is required.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Acti4empPrsncy.this, "154 no changes, no update.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void getVecList(Button mact_4emp_presency_bt) {
        try {
            Date strDate = sdf.parse(mact_4emp_presency_tv.getText().toString());
            final Calendar clndr = Calendar.getInstance();
            clndr.setTime(strDate);
            clndr.add(Calendar.DAY_OF_YEAR, +1);
            if (new Date().before(clndr.getTime())) {
                dtChosn4presency = mact_4emp_presency_tv.getText().toString();

                final Dialog dialog = new Dialog(this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.layout_loader);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();
                dialog.setCancelable(false);

                Model4MXonly pModel4MXonly = model4MXonly;
                pModel4MXonly.setDt(dtChosn4presency);
                pModel4MXonly.setIxd(selectedModel4snglSite.getD());

                CommonChecks commonChks = new CommonChecks(getApplicationContext());
                if (commonChks.isOnline()) {
                    Call<Model4empPresencyLst> call = RetrofitClient.getClient().retroInterfGetLabourLst(pModel4MXonly);

                    call.enqueue(new Callback<Model4empPresencyLst>() {
                        @Override
                        public void onResponse(Call<Model4empPresencyLst> call, Response<Model4empPresencyLst> response) {
                            dialog.dismiss();
                            if (response.body() != null) {
                                Log.d(TAG, "188 lu_rcvd=" + response.body().toString());
                                if (response.isSuccessful()) {
                                    if (response.body().getSuc()) {
                                        Log.d(TAG, "191 lu_rcvd=");
                                        Adapter4lstOfEmpPresency mAdapter4lstOfEmpPresency = new Adapter4lstOfEmpPresency(Acti4empPrsncy.this, response.body().getLst1());
                                        mact_4emp_presency_rc.setAdapter(mAdapter4lstOfEmpPresency);
                                    } else {
                                        Toast.makeText(Acti4empPrsncy.this, response.body().getMsg(), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    if (response.body().getMsg() != null) {
                                        Toast.makeText(Acti4empPrsncy.this, "There was an error. " + response.body().getMsg(), Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(Acti4empPrsncy.this, "Server error. ", Toast.LENGTH_LONG).show();
                                    }
                                }
                            } else {
                                Toast.makeText(Acti4empPrsncy.this, "Unable to get response. Contact administrator.", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Model4empPresencyLst> call, Throwable t) {
                            dialog.dismiss();
                            Log.d(TAG, "146 t=" + t.toString());
                        }
                    });
                } else {
                    Toast.makeText(Acti4empPrsncy.this, "216 Active internet connection is required.", Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(Acti4empPrsncy.this, "220 date must be today onwards " + clndr.get(Calendar.DAY_OF_MONTH), Toast.LENGTH_LONG).show();
            }
        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(Acti4empPrsncy.this, "224 date is incorrect", Toast.LENGTH_LONG).show();
        }
    }
}