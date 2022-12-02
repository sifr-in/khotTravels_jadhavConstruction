package com.sifr.my;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.sifr.my.common.CommonChecks;
import com.sifr.my.retrofit.Model2addEmp2Site;
import com.sifr.my.retrofit.Model4MXonly;
import com.sifr.my.retrofit.Model4successEid;
import com.sifr.my.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sifr.my.Adapter4lstSites.selectedModel4snglSite;
import static com.sifr.my.Whom2Work4.model4MXonly;

public class Activity2crudEmp extends AppCompatActivity {
    private final String TAG = "sfr Activity2crudEmp";
    private TextInputEditText mact_2crud_emp_met_vo, mact_2crud_emp_met_vn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2crud_emp);
        RadioButton mact_2crud_emp_rb_v = findViewById(R.id.act_2crud_emp_rb_v);
        mact_2crud_emp_rb_v.setChecked(true);
        RadioButton mact_2crud_emp_rb_u = findViewById(R.id.act_2crud_emp_rb_u);
        mact_2crud_emp_met_vo = findViewById(R.id.act_2crud_emp_met_vo);
        mact_2crud_emp_met_vn = findViewById(R.id.act_2crud_emp_met_vn);
        CheckBox mact_2crud_emp_cb_u = findViewById(R.id.act_2crud_emp_cb_u);
        mact_2crud_emp_cb_u.setVisibility(View.GONE);
        LinearLayout mact_2crud_emp_ll_uco = findViewById(R.id.act_2crud_emp_ll_uco);
        mact_2crud_emp_ll_uco.setVisibility(View.GONE);
        Button mact_2crud_emp_bt = findViewById(R.id.act_2crud_emp_bt);

        mact_2crud_emp_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Validation.hasTextLength(mact_2crud_emp_met_vo, "mobile no. ", 10, true, false, Activity2crudEmp.this)) {
                    String formatRegx = "[a-zA-Z\\s]+";
                    if (Validation.hasMinLenEfrmat(mact_2crud_emp_met_vn, "", 1, false, false, Activity2crudEmp.this, formatRegx)) {
                        if (selectedModel4snglSite.getD() > 0) {
                            Model4MXonly pModel4MXonly = model4MXonly;
                            pModel4MXonly.setVc(Integer.parseInt("91"));
                            pModel4MXonly.setVo(Long.parseLong(mact_2crud_emp_met_vo.getText().toString()));
                            pModel4MXonly.setVn(mact_2crud_emp_met_vn.getText().toString());
                            Model2addEmp2Site pModel2addEmp2Site = new Model2addEmp2Site();
                            pModel2addEmp2Site.setD(selectedModel4snglSite.getD());
                            pModel2addEmp2Site.setMx(pModel4MXonly);

                            final Dialog dialog = new Dialog(Activity2crudEmp.this);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setContentView(R.layout.layout_loader);
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                            dialog.show();
                            dialog.setCancelable(false);

                            CommonChecks commonChks = new CommonChecks(getApplicationContext());
                            if (commonChks.isOnline()) {
                                Call<Model4successEid> call = RetrofitClient.getClient().retroInterfCRUDemp(pModel2addEmp2Site);

                                call.enqueue(new Callback<Model4successEid>() {
                                    @Override
                                    public void onResponse(Call<Model4successEid> call, Response<Model4successEid> response) {
                                        dialog.dismiss();
                                        if (response.body() != null) {
                                            Log.d(TAG, "81 =" + response.body().toString());
                                            if (response.isSuccessful()) {
                                                if (response.body().getSuc()) {
                                                    Toast.makeText(Activity2crudEmp.this, "person added successfully " + response.body().getMsg(), Toast.LENGTH_LONG).show();
                                                } else {
                                                    Toast.makeText(Activity2crudEmp.this, response.body().getMsg(), Toast.LENGTH_LONG).show();
                                                }
                                            } else {
                                                if (response.body().getMsg() != null) {
                                                    Toast.makeText(Activity2crudEmp.this, "There was an error. " + response.body().getMsg(), Toast.LENGTH_LONG).show();
                                                } else {
                                                    Toast.makeText(Activity2crudEmp.this, "Server error. ", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        } else {
                                            Toast.makeText(Activity2crudEmp.this, "Unable to get response. Contact administrator.", Toast.LENGTH_LONG).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Model4successEid> call, Throwable t) {
                                        dialog.dismiss();
                                        Log.d(TAG, "103 t=" + t.toString());
                                    }
                                });
                            } else {
                                Toast.makeText(Activity2crudEmp.this, "107 Active internet connection is required.", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(Activity2crudEmp.this, "110 please go back and select site first.", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(Activity2crudEmp.this, "113 correct employee name necessary.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Activity2crudEmp.this, "116 correct contact number necessary.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}