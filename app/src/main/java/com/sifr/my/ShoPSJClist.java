package com.sifr.my;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.sifr.my.common.CommonChecks;
import com.sifr.my.retrofit.Model4MeWithOrder;
import com.sifr.my.retrofit.Model4LstSPJC;
import com.sifr.my.retrofit.ModelSerProdJob;
import com.sifr.my.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sifr.my.AdapterForSPJCLst.seltdAdptorModelLstSerProdJob;
import static com.sifr.my.Whom2Work4.model4MXonly;

public class ShoPSJClist extends AppCompatActivity {
    //psj = product service job consumable
    private final String TAG = "sfr ShoPSJClist ";
    private RecyclerView mact_psjc_lst_rv;
    public static TextView mact_psjc_lst_tv_cnt;
    private AdapterForSPJCLst mAdapterForSPJCLst;
    private List<ModelSerProdJob> modelLstSerProdJob = new ArrayList<>();
    private Button mact_psjc_lst_btn_place_ord;
    private IntentIntegrator qrScan;
    private String tblqr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psjc_lst);
        // Delayed display of UI elements
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        mact_psjc_lst_rv = findViewById(R.id.act_psjc_lst_rv);
        mact_psjc_lst_tv_cnt = findViewById(R.id.act_psjc_lst_tv_cnt);
        mact_psjc_lst_btn_place_ord = findViewById(R.id.act_psjc_lst_btn_place_ord);
        mact_psjc_lst_btn_place_ord.setEnabled(false);
        ImageView mact_psjc_lst_iv_scan_todays_code = findViewById(R.id.act_psjc_lst_iv_scan_todays_code);

        mact_psjc_lst_btn_place_ord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Float.parseFloat(mact_psjc_lst_tv_cnt.getText().toString().trim()) > 0) {
                    if (tblqr.length() > 0) {
                        final Dialog dialog = new Dialog(ShoPSJClist.this);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.layout_loader);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        dialog.show();
                        dialog.setCancelable(false);

                        CommonChecks CommonChks = new CommonChecks(getApplicationContext());
                        Log.d(TAG, TAG + "70 seltdAdptorModelLstSerProdJob.size=" + seltdAdptorModelLstSerProdJob.size());
                        if (CommonChks.isOnline()) {
                            Model4MeWithOrder paramModel4MeWithOrder = new Model4MeWithOrder();
                            paramModel4MeWithOrder.setM(7844);
                            paramModel4MeWithOrder.setE(1);
                            paramModel4MeWithOrder.setV(99);
                            paramModel4MeWithOrder.setPsjLst(seltdAdptorModelLstSerProdJob);
                            Call<Model4MeWithOrder> call = RetrofitClient.getClient().retroInterfPlaceOrder(paramModel4MeWithOrder);

                            call.enqueue(new Callback<Model4MeWithOrder>() {
                                @Override
                                public void onResponse(Call<Model4MeWithOrder> call, Response<Model4MeWithOrder> response) {
                                    dialog.dismiss();
                                    if (response.body() != null) {
                                        Log.d(TAG, TAG + "79 lu_rcvd=" + response.body().toString());
                                        if (response.isSuccessful()) {
                                            if (response.body().getSuc()) {
                                                Log.d(TAG, TAG + "82 lu_rcvd=" + response.body());
                                                Intent intent = new Intent(ShoPSJClist.this, FullscreenActivityMenuOrdered.class);
                                                startActivity(intent);
                                            } else {
                                                Toast.makeText(ShoPSJClist.this, response.body().getMsg(), Toast.LENGTH_LONG).show();
                                            }
                                        } else {
                                            if (response.body().getMsg() != null) {
                                                Toast.makeText(ShoPSJClist.this, "There was an error. " + response.body().getMsg(), Toast.LENGTH_LONG).show();
                                            } else {
                                                Toast.makeText(ShoPSJClist.this, "Server error. ", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    } else {
                                        Toast.makeText(ShoPSJClist.this, "Unable to get response. Contact administrator.", Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Model4MeWithOrder> call, Throwable t) {
                                    dialog.dismiss();
                                    Log.d(TAG, TAG + "101 t=" + t.toString());
                                }
                            });
                        } else {
                            Toast.makeText(ShoPSJClist.this, "Active internet connection is required.", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Snackbar snackbar = Snackbar
                                .make(mact_psjc_lst_btn_place_ord, "table code necessary", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                } else {
                    Snackbar snackbar = Snackbar
                            .make(mact_psjc_lst_btn_place_ord, "atleast 1 order required", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });

        mact_psjc_lst_iv_scan_todays_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Float.parseFloat(mact_psjc_lst_tv_cnt.getText().toString().trim()) > 0) {
                    qrScan = new IntentIntegrator(ShoPSJClist.this);
                    qrScan.setOrientationLocked(false);
                    qrScan.initiateScan();
                } else {
                    Snackbar snackbar = Snackbar
                            .make(mact_psjc_lst_tv_cnt, "atleast 1 order required", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }

            }
        });


//        final ProgressDialog dialog = new ProgressDialog(ShoPSJClist.this);
//        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        dialog.setMessage("Loading...");
//        dialog.show();
//        dialog.setCancelable(false);
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_loader);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
        dialog.setCancelable(false);

        CommonChecks CommonChks = new CommonChecks(getApplicationContext());
        if (CommonChks.isOnline()) {
            Call<Model4LstSPJC> call = RetrofitClient.getClient().retroInterfGetSerProdJobConsumList(model4MXonly);

            call.enqueue(new Callback<Model4LstSPJC>() {
                @Override
                public void onResponse(Call<Model4LstSPJC> call, Response<Model4LstSPJC> response) {
                    dialog.dismiss();
                    if (response.body() != null) {
                        Log.d("loog", TAG + "137 lu_rcvd=" + response.body().toString());
                        if (response.isSuccessful()) {
                            if (response.body().getSuc()) {
                                modelLstSerProdJob = response.body().getDat1();
                                mAdapterForSPJCLst = new AdapterForSPJCLst(ShoPSJClist.this, modelLstSerProdJob);
                                mact_psjc_lst_rv.setAdapter(mAdapterForSPJCLst);
//                                GridLayoutManager manager = new GridLayoutManager(ShoPSJClist.this, 2, GridLayoutManager.VERTICAL, false);
//                                mact_sho_menu_rcy.setLayoutManager(manager);
                                Log.d("loog", TAG + "143 lu_rcvd=");
                            } else {
                                Toast.makeText(ShoPSJClist.this, response.body().getMsg(), Toast.LENGTH_LONG).show();
                            }
                        } else {
                            if (response.body().getMsg() != null) {
                                Toast.makeText(ShoPSJClist.this, "There was an error. " + response.body().getMsg(), Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(ShoPSJClist.this, "Server error. ", Toast.LENGTH_LONG).show();
                            }
                        }
                    } else {
                        Toast.makeText(ShoPSJClist.this, "Unable to get response. Contact administrator.", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Model4LstSPJC> call, Throwable t) {
                    dialog.dismiss();
                    Log.d(TAG, "202 t=" + t.toString());
                }
            });
        } else {
            Toast.makeText(ShoPSJClist.this, "Active internet connection is required.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "213 " + requestCode);
        if (result != null) {
            //if qrcode has nothing in it
            Log.d(TAG, "216 " + requestCode);
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                tblqr = data.getExtras().get("SCAN_RESULT").toString();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }


        /*switch (requestCode) {
            case (100):
                try {
                    Log.d(TAG, TAG + "541 " + requestCode);
                    if (resultCode == Activity.RESULT_OK) {
                        Log.d(TAG, TAG + "543 ");
                        Uri contactData = data.getData();
                        @SuppressWarnings("deprecation")
                        Cursor c = managedQuery(contactData, null, null, null, null);
                        String cNumber = "";
                        if (c.moveToFirst()) {
                            String id = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
                            String hasPhone = c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                            if (hasPhone.equalsIgnoreCase("1")) {
                                Cursor phones = getContentResolver()
                                        .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null);
                                phones.moveToFirst();
                                cNumber = phones.getString(phones.getColumnIndex("data1"));

                            }
                            String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                            String num = "", phNo = "";
                            if (name != null) {
                                String number = cNumber.replaceAll("\\s+", "");
                                Log.i("number", number);

                                int var = number.length();
                                int var1 = var - 10;
                                num = number.substring(var1, var);
                                phNo = num.replaceAll("[()\\-\\s]", "").trim();
//                                jtransaction_edt_moe.setText(phNo);
//                                jtransaction_edt_namee.setText(name);

                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "no contact Number found", Toast.LENGTH_SHORT).show();
                        }

                    }
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }*/
    }
}