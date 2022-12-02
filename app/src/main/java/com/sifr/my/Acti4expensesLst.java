package com.sifr.my;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.sifr.my.common.CommonChecks;
import com.sifr.my.retrofit.Model4MXonly;
import com.sifr.my.retrofit.Model4lstXp;
import com.sifr.my.retrofit.Model4successEid;
import com.sifr.my.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sifr.my.Whom2Work4.model4MXonly;

public class Acti4expensesLst extends AppCompatActivity {
    private final String TAG = "sfr Acti4expensesLst";
    private int inBtnID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4expenses_lst);
        RecyclerView mact_4exp_lst_rv = findViewById(R.id.act_4exp_lst_rv);
        Button mact_4exp_lst_bt = findViewById(R.id.act_4exp_lst_bt);
        TextInputEditText mact_4exp_lst_ti = findViewById(R.id.act_4exp_lst_ti);

        inBtnID = getIntent().getIntExtra("inBtnID", 0);
        if(inBtnID==230){
            mact_4exp_lst_bt.setVisibility(View.GONE);
            mact_4exp_lst_ti.setVisibility(View.GONE);
        }

        CommonChecks commonChks = new CommonChecks(getApplicationContext());
        if (commonChks.isOnline()) {
            final Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.layout_loader);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.show();
            dialog.setCancelable(false);

            Call<Model4lstXp> call = RetrofitClient.getClient().retroInterfGetXpLst(model4MXonly);

            call.enqueue(new Callback<Model4lstXp>() {
                @Override
                public void onResponse(Call<Model4lstXp> call, Response<Model4lstXp> response) {
                    dialog.dismiss();
                    if (response.body() != null) {
                        Log.d(TAG, "65 lu_rcvd=" + response.body().toString());
                        if (response.isSuccessful()) {
                            if (response.body().getSuc()) {
                                Log.d(TAG, "68 lu_rcvd=");
                                Adapter4lstXpsNms mAdapter4LstXpsNms = new Adapter4lstXpsNms(Acti4expensesLst.this, response.body().getLst1(), inBtnID);
                                mact_4exp_lst_rv.setAdapter(mAdapter4LstXpsNms);
                            } else {
                                Toast.makeText(Acti4expensesLst.this, response.body().getMsg(), Toast.LENGTH_LONG).show();
                            }
                        } else {
                            if (response.body().getMsg() != null) {
                                Toast.makeText(Acti4expensesLst.this, "There was an error. " + response.body().getMsg(), Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(Acti4expensesLst.this, "Server error. ", Toast.LENGTH_LONG).show();
                            }
                        }
                    } else {
                        Toast.makeText(Acti4expensesLst.this, "Unable to get response. Contact administrator.", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Model4lstXp> call, Throwable t) {
                    dialog.dismiss();
                    Log.d(TAG, "89 t=" + t.toString());
                }
            });
        } else {
            Toast.makeText(Acti4expensesLst.this, "Active internet connection is required.", Toast.LENGTH_LONG).show();
        }

        mact_4exp_lst_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mact_4exp_lst_ti.getText().toString().trim().length() > 5) {
                    final Dialog dialog = new Dialog(Acti4expensesLst.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.layout_loader);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    dialog.show();
                    dialog.setCancelable(false);

                    Model4MXonly mModel4MXonly = model4MXonly;
                    mModel4MXonly.setStr(mact_4exp_lst_ti.getText().toString().trim());

                    CommonChecks commonChks = new CommonChecks(getApplicationContext());
                    if (commonChks.isOnline()) {
                        Call<Model4successEid> call = RetrofitClient.getClient().retroInterfAddXpNm(mModel4MXonly);

                        call.enqueue(new Callback<Model4successEid>() {
                            @Override
                            public void onResponse(Call<Model4successEid> call, Response<Model4successEid> response) {
                                dialog.dismiss();
                                if (response.body() != null) {
                                    Log.d(TAG, "137 lu_rcvd=" + response.body().toString());
                                    if (response.isSuccessful()) {
                                        if (response.body().getSuc()) {
                                            mact_4exp_lst_bt.setEnabled(false);
                                            Toast.makeText(Acti4expensesLst.this, "expenditure added successfully", Toast.LENGTH_LONG).show();
                                            Snackbar snackbar = Snackbar
                                                    .make(mact_4exp_lst_bt, "expenditure added successfully", Snackbar.LENGTH_LONG);
                                            snackbar.show();
                                        } else {
                                            Toast.makeText(Acti4expensesLst.this, response.body().getMsg(), Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        if (response.body().getMsg() != null) {
                                            Toast.makeText(Acti4expensesLst.this, "There was an error. " + response.body().getMsg(), Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(Acti4expensesLst.this, "Server error. ", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                } else {
                                    Toast.makeText(Acti4expensesLst.this, "Unable to get response. Contact administrator.", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Model4successEid> call, Throwable t) {
                                dialog.dismiss();
                                Log.d(TAG, "148 t=" + t.toString());
                            }
                        });
                    } else {
                        Toast.makeText(Acti4expensesLst.this, "Active internet connection is required.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Acti4expensesLst.this, "exense name must be atleast 5 characters (without space in beginning or end)", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}