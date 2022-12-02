package com.sifr.my;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.sifr.my.common.CommonChecks;
import com.sifr.my.retrofit.Model4MXonly;
import com.sifr.my.retrofit.Model4lstMch;
import com.sifr.my.retrofit.Model4snglMch;
import com.sifr.my.retrofit.Model4snglStr;
import com.sifr.my.retrofit.Model4successEid;
import com.sifr.my.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sifr.my.Adapter4lstMch.seltdModel4snglMch;
import static com.sifr.my.Adapter4lstSites.selectedModel4snglSite;
import static com.sifr.my.Adapter4lstSites.seltdLvl2Model4snglSite;
import static com.sifr.my.Whom2Work4.model4MXonly;

public class Activity4mchLst extends AppCompatActivity {
    private final String TAG = "sfr Activity4mchLst";
    private int inBtnID = 0;
    private LinearLayout mact_4mch_lst_ll_trns, mact_4mch_lst_ll_nw;
    private TextView mact_4mch_lst_trns_2_site;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4mch_lst);
        seltdLvl2Model4snglSite = null;
        seltdModel4snglMch = null;

        RecyclerView mact_4mch_lst_rv = findViewById(R.id.act_4mch_lst_rv);
        Button mact_4mch_lst_bt_adnw = findViewById(R.id.act_4mch_lst_bt_adnw);
        Button mact_4mch_lst_bt_trns = findViewById(R.id.act_4mch_lst_bt_trns);
        TextInputEditText mact_4mch_lst_ti = findViewById(R.id.act_4mch_lst_ti);
        TextInputEditText mact_4mch_lst_ti_qt = findViewById(R.id.act_4mch_lst_ti_qt);
        TextView mact_4mch_lst_tv_sitenm = findViewById(R.id.act_4mch_lst_tv_sitenm);
        mact_4mch_lst_tv_sitenm.setVisibility(View.GONE);
        mact_4mch_lst_trns_2_site = findViewById(R.id.act_4mch_lst_trns_2_site);
        mact_4mch_lst_trns_2_site.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activity4mchLst.this, Activity2selectSite.class);
                i.putExtra("siteLvl", 1);
                startActivity(i);
            }
        });
        mact_4mch_lst_ll_nw = findViewById(R.id.act_4mch_lst_ll_nw);
        mact_4mch_lst_ll_nw.setVisibility(View.GONE);
        mact_4mch_lst_ll_trns = findViewById(R.id.act_4mch_lst_ll_trns);
        mact_4mch_lst_ll_trns.setVisibility(View.GONE);
        ImageView mact_4mch_lst_iv_nwmch = findViewById(R.id.act_4mch_lst_iv_nwmch);

        inBtnID = getIntent().getIntExtra("inBtnID", 0);
        if (inBtnID == 230) {
            mact_4mch_lst_bt_trns.setText("transfer from " + selectedModel4snglSite.getN());
            mact_4mch_lst_ti.setVisibility(View.GONE);
            mact_4mch_lst_ti_qt.setVisibility(View.GONE);
            mact_4mch_lst_tv_sitenm.setVisibility(View.VISIBLE);
            mact_4mch_lst_tv_sitenm.setText(selectedModel4snglSite.getN());
            mact_4mch_lst_bt_adnw.setVisibility(View.GONE);
        } else {
            mact_4mch_lst_bt_trns.setText("add more");
            mact_4mch_lst_bt_adnw.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mact_4mch_lst_ll_nw.setVisibility(View.VISIBLE);
                    mact_4mch_lst_ll_trns.setVisibility(View.GONE);
                }
            });
        }

        mact_4mch_lst_bt_trns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mact_4mch_lst_ll_nw.setVisibility(View.GONE);
                if (seltdModel4snglMch != null) {
                    if (inBtnID == 230) {
                        mact_4mch_lst_trns_2_site.setText("transfer " + seltdModel4snglMch.getN());
                    } else {
                        mact_4mch_lst_trns_2_site.setText("add " + seltdModel4snglMch.getN());
                    }
                    mact_4mch_lst_ll_trns.setVisibility(View.VISIBLE);
                } else {
                    if (inBtnID == 230) {
                        Toast.makeText(Activity4mchLst.this, "select a machine to transfer", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Activity4mchLst.this, "select a machine to add count", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        TextInputEditText mact_4mch_lst_trns_ti_qt = findViewById(R.id.act_4mch_lst_trns_ti_qt);
        ImageView mact_4mch_lst_trns_iv_mch = findViewById(R.id.act_4mch_lst_trns_iv_mch);
        mact_4mch_lst_trns_iv_mch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (seltdLvl2Model4snglSite != null) {
                    if (mact_4mch_lst_trns_ti_qt.getText().toString().trim().length() > 0 && Float.parseFloat(mact_4mch_lst_trns_ti_qt.getText().toString().trim()) > 0) {
                        final Dialog dialog = new Dialog(Activity4mchLst.this);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.layout_loader);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        dialog.show();
                        dialog.setCancelable(false);

                        Model4snglMch pModel4snglMch = new Model4snglMch();
                        pModel4snglMch.setMx(model4MXonly);
                        if (inBtnID == 230) {
                            pModel4snglMch.setIxd(selectedModel4snglSite.getD());
                        } else {
                            pModel4snglMch.setIxd(0);
                        }
                        pModel4snglMch.setB(seltdModel4snglMch.getD());
                        pModel4snglMch.setC(seltdLvl2Model4snglSite.getD());
                        pModel4snglMch.setG(Integer.parseInt(mact_4mch_lst_trns_ti_qt.getText().toString().trim()));

                        CommonChecks commonChks = new CommonChecks(Activity4mchLst.this);
                        if (commonChks.isOnline()) {
                            Call<Model4successEid> call = RetrofitClient.getClient().retroInterfAddMch2Site(pModel4snglMch);

                            call.enqueue(new Callback<Model4successEid>() {
                                @Override
                                public void onResponse(Call<Model4successEid> call, Response<Model4successEid> response) {
                                    dialog.dismiss();
                                    if (response.body() != null) {
                                        Log.d(TAG, "127 " + response.body().toString());
                                        if (response.isSuccessful()) {
                                            if (response.body().getSuc()) {
                                                Toast.makeText(Activity4mchLst.this, "machine count was updated successfully. " + response.body().getMsg(), Toast.LENGTH_LONG).show();
                                            } else {
                                                Toast.makeText(Activity4mchLst.this, response.body().getMsg(), Toast.LENGTH_LONG).show();
                                            }
                                        } else {
                                            if (response.body().getMsg() != null) {
                                                Toast.makeText(Activity4mchLst.this, "There was an error. " + response.body().getMsg(), Toast.LENGTH_LONG).show();
                                            } else {
                                                Toast.makeText(Activity4mchLst.this, "Server error. ", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    } else {
                                        Toast.makeText(Activity4mchLst.this, "Unable to get response. Contact administrator.", Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Model4successEid> call, Throwable t) {
                                    dialog.dismiss();
                                    Log.d(TAG, "130 t=" + t.toString());
                                }
                            });
                        } else {
                            Toast.makeText(Activity4mchLst.this, "Active internet connection is required.", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(Activity4mchLst.this, "quantity must be greater than 0", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Activity4mchLst.this, "select site where u want to transfer", Toast.LENGTH_LONG).show();
                }
            }
        });

        CommonChecks commonChks = new CommonChecks(getApplicationContext());
        if (commonChks.isOnline()) {
            final Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.layout_loader);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.show();
            dialog.setCancelable(false);

            Call<Model4lstMch> call;
            if (inBtnID == 230) {
                model4MXonly.setIxd(selectedModel4snglSite.getD());//send the site id
                call = RetrofitClient.getClient().retroInterfGetMchLstOnSite(model4MXonly);
            } else {
                call = RetrofitClient.getClient().retroInterfGetMchLst(model4MXonly);
            }
            call.enqueue(new Callback<Model4lstMch>() {
                @Override
                public void onResponse(Call<Model4lstMch> call, Response<Model4lstMch> response) {
                    dialog.dismiss();
                    if (response.body() != null) {
                        Log.d(TAG, "65 lu_rcvd=" + response.body().toString());
                        if (response.isSuccessful()) {
                            if (response.body().getSuc()) {
                                Log.d(TAG, "68 lu_rcvd=");
                                RecyclerViewClickListener rc = new RecyclerViewClickListener() {
                                    @Override
                                    public void doSomething(View v, Object obj, int position, int index) {

                                    }
                                };
                                Adapter4lstMch mAdapter4LstMch = new Adapter4lstMch(Activity4mchLst.this, response.body().getLst1(), inBtnID, rc);
                                mact_4mch_lst_rv.setAdapter(mAdapter4LstMch);
                            } else {
                                Toast.makeText(Activity4mchLst.this, response.body().getMsg(), Toast.LENGTH_LONG).show();
                            }
                        } else {
                            if (response.body().getMsg() != null) {
                                Toast.makeText(Activity4mchLst.this, "There was an error. " + response.body().getMsg(), Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(Activity4mchLst.this, "Server error. ", Toast.LENGTH_LONG).show();
                            }
                        }
                    } else {
                        Toast.makeText(Activity4mchLst.this, "Unable to get response. Contact administrator.", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Model4lstMch> call, Throwable t) {
                    dialog.dismiss();
                    Log.d(TAG, "89 t=" + t.toString());
                }
            });
        } else {
            Toast.makeText(Activity4mchLst.this, "Active internet connection is required.", Toast.LENGTH_LONG).show();
        }

        mact_4mch_lst_iv_nwmch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mact_4mch_lst_ti.getText().toString().trim().length() > 4) {
                    if (mact_4mch_lst_ti_qt.getText().toString().trim().length() > 0 && Integer.parseInt(mact_4mch_lst_ti_qt.getText().toString().trim()) > 0) {
                        final Dialog dialog = new Dialog(Activity4mchLst.this);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.layout_loader);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        dialog.show();
                        dialog.setCancelable(false);

                        Model4MXonly mModel4MXonly = model4MXonly;
                        mModel4MXonly.setStr(mact_4mch_lst_ti.getText().toString().trim());
                        mModel4MXonly.setStr2(mact_4mch_lst_ti_qt.getText().toString().trim());

                        CommonChecks commonChks = new CommonChecks(getApplicationContext());
                        if (commonChks.isOnline()) {
                            Call<Model4successEid> call = RetrofitClient.getClient().retroInterfAddMchNm(mModel4MXonly);

                            call.enqueue(new Callback<Model4successEid>() {
                                @Override
                                public void onResponse(Call<Model4successEid> call, Response<Model4successEid> response) {
                                    dialog.dismiss();
                                    if (response.body() != null) {
                                        Log.d(TAG, "137 lu_rcvd=" + response.body().toString());
                                        if (response.isSuccessful()) {
                                            if (response.body().getSuc()) {
                                                mact_4mch_lst_iv_nwmch.setEnabled(false);
                                                Toast.makeText(Activity4mchLst.this, "machine added successfully", Toast.LENGTH_LONG).show();
                                                Snackbar snackbar = Snackbar
                                                        .make(mact_4mch_lst_iv_nwmch, "machine added successfully", Snackbar.LENGTH_LONG);
                                                snackbar.show();
                                            } else {
                                                Toast.makeText(Activity4mchLst.this, response.body().getMsg(), Toast.LENGTH_LONG).show();
                                            }
                                        } else {
                                            if (response.body().getMsg() != null) {
                                                Toast.makeText(Activity4mchLst.this, "There was an error. " + response.body().getMsg(), Toast.LENGTH_LONG).show();
                                            } else {
                                                Toast.makeText(Activity4mchLst.this, "Server error. ", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    } else {
                                        Toast.makeText(Activity4mchLst.this, "Unable to get response. Contact administrator.", Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Model4successEid> call, Throwable t) {
                                    dialog.dismiss();
                                    Log.d(TAG, "148 t=" + t.toString());
                                }
                            });
                        } else {
                            Toast.makeText(Activity4mchLst.this, "Active internet connection is required.", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(Activity4mchLst.this, "machine count cannot be 0", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Activity4mchLst.this, "machine name must be atleast 5 characters (without space in beginning or end)", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (seltdLvl2Model4snglSite != null) {
            mact_4mch_lst_trns_2_site.setText("transfer " + seltdModel4snglMch.getN() + " to " + seltdLvl2Model4snglSite.getN());
        }
    }
}