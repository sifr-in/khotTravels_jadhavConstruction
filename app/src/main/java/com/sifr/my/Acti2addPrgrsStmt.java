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

import com.google.android.material.textfield.TextInputEditText;
import com.sifr.my.common.CommonChecks;
import com.sifr.my.retrofit.Model4MXonly;
import com.sifr.my.retrofit.Model4lstPrgrsRpt;
import com.sifr.my.retrofit.Model4snglProgrsRprtOfSite;
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

import static com.sifr.my.Adapter4lstSites.selectedModel4snglSite;
import static com.sifr.my.Whom2Work4.model4MXonly;

public class Acti2addPrgrsStmt extends AppCompatActivity {
    private final String TAG = "sfr Acti2addPrgrsStmt";
    private TextView mact_2add_progress_rpt_tv;
    private TextInputEditText mact_2add_progress_rpt_ti;
    private DatePickerDialog mDatePicker;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static String chosnDt;
    private RecyclerView mact_2add_progress_rpt_rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2add_progress_report);
        mact_2add_progress_rpt_tv = findViewById(R.id.act_2add_progress_rpt_tv);
        mact_2add_progress_rpt_ti = findViewById(R.id.act_2add_progress_rpt_ti);
        Button mact_2add_progress_rpt_bt = findViewById(R.id.act_2add_progress_rpt_bt);
        Button mact_2add_progress_rpt_sho_bt = findViewById(R.id.act_2add_progress_rpt_sho_bt);
        mact_2add_progress_rpt_rv = findViewById(R.id.act_2add_progress_rpt_rv);

        mact_2add_progress_rpt_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                mDatePicker = new DatePickerDialog(Acti2addPrgrsStmt.this,
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

                                mact_2add_progress_rpt_tv.setText(year + "-" + strMnth + "-" + strDay);
                                chosnDt = mact_2add_progress_rpt_tv.getText().toString();
                            }
                        }, year, month, day);
                mDatePicker.show();
            }
        });

        mact_2add_progress_rpt_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Date strDate = sdf.parse(mact_2add_progress_rpt_tv.getText().toString());
                    final Calendar clndr = Calendar.getInstance();
                    clndr.setTime(strDate);
                    clndr.add(Calendar.DAY_OF_YEAR, +1);
                    if (new Date().before(clndr.getTime())) {
                        if (mact_2add_progress_rpt_ti.getText().toString().length() > 15) {

                            final Dialog dialog = new Dialog(Acti2addPrgrsStmt.this);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setContentView(R.layout.layout_loader);
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                            dialog.show();
                            dialog.setCancelable(false);

                            Model4lstPrgrsRpt mModel4lstPrgrsRpt = new Model4lstPrgrsRpt();
                            mModel4lstPrgrsRpt.setMx(model4MXonly);
                            Model4snglProgrsRprtOfSite pModel4snglProgrsRprtOfSite = new Model4snglProgrsRprtOfSite();
                            pModel4snglProgrsRprtOfSite.setG(selectedModel4snglSite.getD());
                            pModel4snglProgrsRprtOfSite.setA(chosnDt);
                            pModel4snglProgrsRprtOfSite.setN(mact_2add_progress_rpt_ti.getText().toString());
                            List<Model4snglProgrsRprtOfSite> lst1 = new ArrayList<>();
                            lst1.add(pModel4snglProgrsRprtOfSite);
                            mModel4lstPrgrsRpt.setLst1(lst1);

                            CommonChecks commonChks = new CommonChecks(getApplicationContext());
                            if (commonChks.isOnline()) {
                                Call<Model4successEid> call = RetrofitClient.getClient().retroInterfAddPrgrsStmt(mModel4lstPrgrsRpt);

                                call.enqueue(new Callback<Model4successEid>() {
                                    @Override
                                    public void onResponse(Call<Model4successEid> call, Response<Model4successEid> response) {
                                        dialog.dismiss();
                                        if (response.body() != null) {
                                            Log.d(TAG, "137 lu_rcvd=" + response.body().toString());
                                            if (response.isSuccessful()) {
                                                if (response.body().getSuc()) {
                                                    mact_2add_progress_rpt_bt.setEnabled(false);
                                                    Log.d(TAG, "143 lu_rcvd=");
                                                } else {
                                                    Toast.makeText(Acti2addPrgrsStmt.this, response.body().getMsg(), Toast.LENGTH_LONG).show();
                                                }
                                            } else {
                                                if (response.body().getMsg() != null) {
                                                    Toast.makeText(Acti2addPrgrsStmt.this, "There was an error. " + response.body().getMsg(), Toast.LENGTH_LONG).show();
                                                } else {
                                                    Toast.makeText(Acti2addPrgrsStmt.this, "Server error. ", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        } else {
                                            Toast.makeText(Acti2addPrgrsStmt.this, "Unable to get response. Contact administrator.", Toast.LENGTH_LONG).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Model4successEid> call, Throwable t) {
                                        dialog.dismiss();
                                        Log.d(TAG, "148 t=" + t.toString());
                                    }
                                });
                            } else {
                                Toast.makeText(Acti2addPrgrsStmt.this, "Active internet connection is required.", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(Acti2addPrgrsStmt.this, "155 there must be atleast 5 words of report" + clndr.get(Calendar.DAY_OF_MONTH), Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(Acti2addPrgrsStmt.this, "149 date must be today onwards " + clndr.get(Calendar.DAY_OF_MONTH), Toast.LENGTH_LONG).show();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    Toast.makeText(Acti2addPrgrsStmt.this, "153 date is incorrect", Toast.LENGTH_LONG).show();
                }
            }
        });

        mact_2add_progress_rpt_sho_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Date strDate = sdf.parse(mact_2add_progress_rpt_tv.getText().toString());
                    final Calendar clndr = Calendar.getInstance();
                    clndr.setTime(strDate);
                    clndr.add(Calendar.DAY_OF_YEAR, +1);
                    if (new Date().before(clndr.getTime())) {
                        Model4MXonly pModel4MXonly = model4MXonly;
                        pModel4MXonly.setIxd(selectedModel4snglSite.getD());
                        pModel4MXonly.setDt(chosnDt);

                        final Dialog dialog = new Dialog(Acti2addPrgrsStmt.this);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.layout_loader);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        dialog.show();
                        dialog.setCancelable(false);

                        CommonChecks commonChks = new CommonChecks(getApplicationContext());
                        if (commonChks.isOnline()) {
                            Call<Model4lstPrgrsRpt> call = RetrofitClient.getClient().retroInterfGetPrgrsRprtLst(pModel4MXonly);

                            call.enqueue(new Callback<Model4lstPrgrsRpt>() {
                                @Override
                                public void onResponse(Call<Model4lstPrgrsRpt> call, Response<Model4lstPrgrsRpt> response) {
                                    dialog.dismiss();
                                    if (response.body() != null) {
                                        Log.d(TAG, "202 lu_rcvd=" + response.body().toString());
                                        if (response.isSuccessful()) {
                                            if (response.body().getSuc()) {
                                                Adapter4lstProgrsRpt mAdapter4lstProgrsRpt = new Adapter4lstProgrsRpt(Acti2addPrgrsStmt.this, response.body().getLst1());
                                                mact_2add_progress_rpt_rv.setAdapter(mAdapter4lstProgrsRpt);
                                                Log.d(TAG, "205 lu_rcvd=");
                                            } else {
                                                Toast.makeText(Acti2addPrgrsStmt.this, response.body().getMsg(), Toast.LENGTH_LONG).show();
                                            }
                                        } else {
                                            if (response.body().getMsg() != null) {
                                                Toast.makeText(Acti2addPrgrsStmt.this, "There was an error. " + response.body().getMsg(), Toast.LENGTH_LONG).show();
                                            } else {
                                                Toast.makeText(Acti2addPrgrsStmt.this, "Server error. ", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    } else {
                                        Toast.makeText(Acti2addPrgrsStmt.this, "Unable to get response. Contact administrator.", Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Model4lstPrgrsRpt> call, Throwable t) {
                                    dialog.dismiss();
                                    Log.d(TAG, "224 t=" + t.toString());
                                }
                            });
                        } else {
                            Toast.makeText(Acti2addPrgrsStmt.this, "Active internet connection is required.", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(Acti2addPrgrsStmt.this, "149 date must be today onwards " + clndr.get(Calendar.DAY_OF_MONTH), Toast.LENGTH_LONG).show();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    Toast.makeText(Acti2addPrgrsStmt.this, "153 date is incorrect", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}