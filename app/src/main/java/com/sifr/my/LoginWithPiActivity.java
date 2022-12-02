package com.sifr.my;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.sifr.my.common.CommonChecks;
import com.sifr.my.retrofit.Model4MXonly;
import com.sifr.my.retrofit.Model4successEid;
import com.sifr.my.retrofit.RetrofitClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sifr.my.Adapter4listOfRelations.sltdModel4SnglRelationship;
import static com.sifr.my.Whom2Work4.model4MXonly;
import static com.sifr.my.MainActivity.lstModel4SnglRelationship;
import static com.sifr.my.MainActivity.pModel4Y4OTP;
import static com.sifr.my.RegisterActivity.setExpiry;
import static com.sifr.my.RegisterActivity.endDt;

public class LoginWithPiActivity extends AppCompatActivity {
    private final String TAG = "sfr LoginWithPiActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_e_pi);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date strDate = null;
        try {
            strDate = sdf.parse(endDt);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        TextView mact_lgi_tv = findViewById(R.id.act_lgi_tv);
        if (setExpiry) {
            mact_lgi_tv.setText("for security this demo will stop working\nbefore: " + endDt);
            if (new Date().before(strDate)) {
                init();
            } else {
                Toast.makeText(LoginWithPiActivity.this, "Testing period expired.",
                        Toast.LENGTH_LONG).show();
            }
        } else {
            mact_lgi_tv.setVisibility(View.GONE);
            init();
        }
    }

    private void init() {
        final CommonChecks CommonChks = new CommonChecks(getApplicationContext());
        sltdModel4SnglRelationship = null;

        EditText mact_log_tvpi = findViewById(R.id.act_log_tvpi);
        RecyclerView mact_log_rc_profi = findViewById(R.id.act_log_rc_profi);
        Button mact_log_btn_chk_pi_get_tk = findViewById(R.id.act_log_btn_chk_pi_get_tk);

        Adapter4listOfRelations mAdapter4listOfRelations = new Adapter4listOfRelations(LoginWithPiActivity.this, lstModel4SnglRelationship);
        mact_log_rc_profi.setAdapter(mAdapter4listOfRelations);

        mact_log_btn_chk_pi_get_tk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sltdModel4SnglRelationship != null && sltdModel4SnglRelationship.getD() > -1) {
                    if (Validation.hasTextLength(mact_log_tvpi, "pin number ", 4, true, false, LoginWithPiActivity.this)) {
                        if (CommonChks.isOnline()) {
                            final Dialog dialog = new Dialog(LoginWithPiActivity.this);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setContentView(R.layout.layout_loader);
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                            dialog.show();
                            dialog.setCancelable(false);
                            pModel4Y4OTP.setPi(mact_log_tvpi.getText().toString());

                            Call<Model4successEid> call = RetrofitClient.getClient().retroInterfChkPiGetTk(pModel4Y4OTP);

                            call.enqueue(new Callback<Model4successEid>() {
                                @Override
                                public void onResponse(Call<Model4successEid> call, Response<Model4successEid> response) {
                                    dialog.dismiss();
                                    if (response.body() != null) {
                                        Log.d(TAG, "79 lu_rcvd=" + response.body().toString());
                                        if (response.isSuccessful()) {
                                            if (response.body().getSuc()) {
                                                MainActivity.tk = response.body().getTk();
                                                model4MXonly = new Model4MXonly();
                                                model4MXonly.setTk(response.body().getTk());
                                                model4MXonly.setY(Integer.parseInt(response.body().getD()));
                                                finish();
                                                Intent intent = new Intent(LoginWithPiActivity.this, Whom2Work4.class);
                                                startActivity(intent);
                                                Log.d(TAG, "91 lu_rcvd=" + response.body());
                                            } else {
                                                Toast.makeText(LoginWithPiActivity.this, "can't login. error. " + response.body().getMsg(), Toast.LENGTH_LONG).show();
                                            }
                                        } else {
                                            if (response.body().getMsg() != null) {
                                                Toast.makeText(LoginWithPiActivity.this, "There was an error. " + response.body().getMsg(), Toast.LENGTH_LONG).show();
                                            } else {
                                                Toast.makeText(LoginWithPiActivity.this, "Server error. ", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    } else {
                                        Toast.makeText(LoginWithPiActivity.this, "Unable to get response. Contact administrator.", Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Model4successEid> call, Throwable t) {
                                    dialog.dismiss();
                                    Log.d(TAG, "101 t=" + t.toString());
                                }
                            });
                        } else {
                            Toast.makeText(LoginWithPiActivity.this, "internet connection required.", Toast.LENGTH_LONG).show();
                            Snackbar snackbar = Snackbar
                                    .make(mact_log_btn_chk_pi_get_tk, "internet connection required", Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }
                    }
                } else {
                    Toast.makeText(LoginWithPiActivity.this, "select relation with mobile no. owner.", Toast.LENGTH_LONG).show();
                    Snackbar snackbar = Snackbar
                            .make(mact_log_btn_chk_pi_get_tk, "select relation with mobile no. owner.", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });
    }
}