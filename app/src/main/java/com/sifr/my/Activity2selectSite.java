package com.sifr.my;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.sifr.my.common.CommonChecks;
import com.sifr.my.retrofit.Model4lstSite;
import com.sifr.my.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sifr.my.Whom2Work4.model4MXonly;

public class Activity2selectSite extends AppCompatActivity {
    private final String TAG = "sfr Activity2selectSite";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2select_site);
        RecyclerView mact_2_selc_site_rc = findViewById(R.id.act_2_selc_site_rc);

        int siteLvl = getIntent().getIntExtra("siteLvl", 0);

        CommonChecks commonChks = new CommonChecks(getApplicationContext());
        if (commonChks.isOnline()) {
            final Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.layout_loader);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.show();
            dialog.setCancelable(false);

            Call<Model4lstSite> call = RetrofitClient.getClient().retroInterfGetSiteLst(model4MXonly);

            call.enqueue(new Callback<Model4lstSite>() {
                @Override
                public void onResponse(Call<Model4lstSite> call, Response<Model4lstSite> response) {
                    dialog.dismiss();
                    if (response.body() != null) {
                        Log.d(TAG, "65 lu_rcvd=" + response.body().toString());
                        if (response.isSuccessful()) {
                            if (response.body().getSuc()) {
                                Log.d(TAG, "68 lu_rcvd=");
                                Adapter4lstSites mAdapter4lstSites = new Adapter4lstSites(Activity2selectSite.this, response.body().getLst1(), siteLvl);
                                mact_2_selc_site_rc.setAdapter(mAdapter4lstSites);
                            } else {
                                Toast.makeText(Activity2selectSite.this, response.body().getMsg(), Toast.LENGTH_LONG).show();
                            }
                        } else {
                            if (response.body().getMsg() != null) {
                                Toast.makeText(Activity2selectSite.this, "There was an error. " + response.body().getMsg(), Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(Activity2selectSite.this, "Server error. ", Toast.LENGTH_LONG).show();
                            }
                        }
                    } else {
                        Toast.makeText(Activity2selectSite.this, "Unable to get response. Contact administrator.", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Model4lstSite> call, Throwable t) {
                    dialog.dismiss();
                    Log.d(TAG, "89 t=" + t.toString());
                }
            });
        } else {
            Toast.makeText(Activity2selectSite.this, "Active internet connection is required.", Toast.LENGTH_LONG).show();
        }
    }
}