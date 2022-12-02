package com.sifr.my;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;
import com.sifr.my.common.CommonChecks;
import com.sifr.my.retrofit.Model4Y;
import com.sifr.my.retrofit.RetrofitClient;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.animation.Animation.RELATIVE_TO_SELF;
import static com.sifr.my.Adapter4listOfRelations.sltdModel4SnglRelationship;
import static com.sifr.my.MainActivity.lstModel4SnglRelationship;
import static com.sifr.my.MainActivity.pModel4Y4OTP;

public class RegisterActivity extends AppCompatActivity {
    private final String TAG = "sfr RegisterActivity";
    public final static boolean setExpiry = false;
    //TODO
    //when below date is changed don't forget to change the expiry date in q in permission table to set more than below end date;
    public final static String endDt = "2022-12-05";
    //    public final static String tmpMoNo = "9890421688";//prasad patil shree graphics
//        public final static String tmpMoNo = "9403453182";//s. v. jadhav constructions
    public final static String tmpMoNo = "9421146316";//khot travel
//        public final static String tmpMoNo = "9226771646";//prajyot patil constructions
    //    public final static String tmpMoNo = "9960706060";//mns demo

    private CountryCodePicker mact_reg_yc;
    private TextInputEditText mact_reg_yo, mact_reg_et_otp;
    private EditText mact_reg_tvpi;
    private TextInputLayout mact_reg_et_la_otp;
    private ProgressBar mact_reg_pb;
    private TextView mact_reg_tv_yc, mact_reg_tv_pb;
    private Button mact_reg_btn_get_otp, mact_reg_btn_veri_otp, mact_reg_btn_set_pi;
    private LinearLayout mact_reg_la_pb, mact_reg_la_vrfy, mact_reg_la_pi, mact_reg_la_get_otp;
    //    int myProgress = 0;
    int progress;
    CountDownTimer countDownTimer;
    int endSeconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        final CommonChecks CommonChks = new CommonChecks(getApplicationContext());
        mact_reg_yc = findViewById(R.id.act_reg_yc);//entity country code
        mact_reg_yo = findViewById(R.id.act_reg_yo);
        mact_reg_la_pb = findViewById(R.id.act_reg_la_pb);
        mact_reg_la_pb.setVisibility(View.GONE);
        mact_reg_la_vrfy = findViewById(R.id.act_reg_la_vrfy);
        mact_reg_la_pi = findViewById(R.id.act_reg_la_pi);
        mact_reg_la_pi.setVisibility(View.GONE);
        mact_reg_pb = findViewById(R.id.act_reg_pb);
        mact_reg_et_la_otp = findViewById(R.id.act_reg_et_la_otp);
        mact_reg_et_la_otp.setVisibility(View.GONE);
        mact_reg_et_otp = findViewById(R.id.act_reg_et_otp);
        mact_reg_tv_pb = findViewById(R.id.act_reg_tv_pb);
        mact_reg_tv_yc = findViewById(R.id.act_reg_tv_yc);
        mact_reg_tv_yc.setVisibility(View.GONE);
        mact_reg_btn_get_otp = findViewById(R.id.act_reg_btn_get_otp);
        mact_reg_btn_get_otp.setEnabled(false);
        mact_reg_la_get_otp = findViewById(R.id.act_reg_la_get_otp);
        mact_reg_btn_veri_otp = findViewById(R.id.act_reg_btn_veri_otp);
        mact_reg_btn_veri_otp.setVisibility(View.INVISIBLE);

        mact_reg_tvpi = findViewById(R.id.act_reg_tvpi);
        mact_reg_btn_set_pi = findViewById(R.id.act_reg_btn_set_pi);

        RecyclerView mact_reg_rc_profi = findViewById(R.id.act_reg_rc_profi);
        Adapter4listOfRelations mAdapter4listOfRelations = new Adapter4listOfRelations(RegisterActivity.this, lstModel4SnglRelationship);
        mact_reg_rc_profi.setAdapter(mAdapter4listOfRelations);

        /*Animation*/
        RotateAnimation makeVertical = new RotateAnimation(0, -90, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        makeVertical.setFillAfter(true);
        mact_reg_pb.startAnimation(makeVertical);
        mact_reg_pb.setSecondaryProgress(endSeconds);
        mact_reg_pb.setProgress(0);


        SharedPreferences saved_values = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        long tmp = saved_values.getLong("odtt", 0);
        if (tmp > 0) {
            fn_countdown(tmp, saved_values.getInt("yc", 0), saved_values.getLong("yo", 0));
        } else {

            mact_reg_yo.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (checkValidation(false))
                        mact_reg_btn_get_otp.setEnabled(true);
                    else
                        mact_reg_btn_get_otp.setEnabled(false);
                }
            });

            mact_reg_yc.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
                @Override
                public void onCountrySelected() {
                    if (!mact_reg_yc.getSelectedCountryCode().equals("91")) {
                        mact_reg_btn_get_otp.setEnabled(false);
                        mact_reg_tv_yc.setVisibility(View.VISIBLE);
                        Snackbar snackbar = Snackbar
                                .make(mact_reg_yc, "only indian numbers can register", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    } else {
                        if (checkValidation(true)) {
                            mact_reg_btn_get_otp.setEnabled(true);
                        } else {
                            mact_reg_btn_get_otp.setEnabled(false);
                        }
                        mact_reg_tv_yc.setVisibility(View.GONE);
                    }
                }
            });

            mact_reg_btn_get_otp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkValidation(true)) {
                        if (CommonChks.isOnline()) {
                            final Dialog dialog = new Dialog(RegisterActivity.this);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setContentView(R.layout.layout_loader);
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                            dialog.show();
                            dialog.setCancelable(false);

//                        pModel4Y4OTP.setYc(Integer.parseInt(mact_reg_yc.getSelectedCountryCode()));
//                        pModel4Y4OTP.setYo(Float.parseFloat(mact_reg_yo.getText().toString()));
//                        Call<Model4Y> call = RetrofitClient.getClient().retroInterfGetOTP(pModel4Y4OTP);

                            RequestBody yc = RequestBody.create(MediaType.parse("text/plain"), mact_reg_yc.getSelectedCountryCode() + "");
                            RequestBody yo = RequestBody.create(MediaType.parse("text/plain"), mact_reg_yo.getText().toString() + "");
                            Call<Model4Y> call;
                            if (setExpiry) {
                                call = RetrofitClient.getClient().retroInterfGetOTPtmp(yc, yo);
                            } else {
                                call = RetrofitClient.getClient().retroInterfGetOTP(yc, yo);
                            }

                            call.enqueue(new Callback<Model4Y>() {
                                @Override
                                public void onResponse(Call<Model4Y> call, Response<Model4Y> response) {
                                    dialog.dismiss();
                                    if (response.body() != null) {
                                        Log.d(TAG, "79 lu_rcvd=" + response.body().toString());
                                        if (response.isSuccessful()) {
                                            if (response.body().getSuc()) {
                                                SharedPreferences saved_values = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                                SharedPreferences.Editor editor = saved_values.edit();
                                                editor.putInt("yc", Integer.parseInt(mact_reg_yc.getSelectedCountryCode()));
                                                editor.putLong("yo", Long.parseLong(mact_reg_yo.getText().toString()));
                                                long millis = System.currentTimeMillis() + (100 * 1000);
                                                editor.putLong("odtt", millis);//otp must be verified till given time;
                                                editor.apply();

                                                fn_countdown(millis, Integer.parseInt(mact_reg_yc.getSelectedCountryCode()), Long.parseLong(mact_reg_yo.getText().toString()));

                                                Toast.makeText(RegisterActivity.this, response.body().getMsg(), Toast.LENGTH_LONG).show();
                                                Snackbar snackbar = Snackbar
                                                        .make(mact_reg_btn_get_otp, response.body().getMsg(), Snackbar.LENGTH_LONG);
                                                snackbar.show();
                                            } else {
                                                Toast.makeText(RegisterActivity.this, response.body().getMsg(), Toast.LENGTH_LONG).show();
                                            }
                                        } else {
                                            if (response.body().getMsg() != null) {
                                                Toast.makeText(RegisterActivity.this, "There was an error. " + response.body().getMsg(), Toast.LENGTH_LONG).show();
                                            } else {
                                                Toast.makeText(RegisterActivity.this, "Server error. ", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Unable to get response. Contact administrator.", Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Model4Y> call, Throwable t) {
                                    dialog.dismiss();
                                    Log.d(TAG, "101 t=" + t.toString());
                                }
                            });
                        } else {
                            Toast.makeText(RegisterActivity.this, "internet connection required.", Toast.LENGTH_LONG).show();
                            Snackbar snackbar = Snackbar
                                    .make(mact_reg_btn_get_otp, "internet connection required", Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }
                    }
                }
            });
        }

        mact_reg_btn_veri_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Validation.hasTextLength(mact_reg_et_otp, "mobile no. ", 4, true, false, RegisterActivity.this)) {
                    if (CommonChks.isOnline()) {
                        final Dialog dialog = new Dialog(RegisterActivity.this);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.layout_loader);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        dialog.show();
                        dialog.setCancelable(false);

//                        pModel4Y4OTP.setOt(Integer.parseInt(mact_reg_et_otp.getText().toString()));
//                        Call<Model4Y> call = RetrofitClient.getClient().retroInterfVeriOTP(pModel4Y4OTP);
                        RequestBody yc = RequestBody.create(MediaType.parse("text/plain"), pModel4Y4OTP.getYc() + "");
                        RequestBody yo = RequestBody.create(MediaType.parse("text/plain"), pModel4Y4OTP.getYo() + "");
                        RequestBody ot = RequestBody.create(MediaType.parse("text/plain"), mact_reg_et_otp.getText().toString() + "");
                        Call<Model4Y> call = RetrofitClient.getClient().retroInterfVeriOTP(yc, yo, ot);

                        call.enqueue(new Callback<Model4Y>() {
                            @Override
                            public void onResponse(Call<Model4Y> call, Response<Model4Y> response) {
                                dialog.dismiss();
                                if (response.body() != null) {
                                    Log.d(TAG, "79 lu_rcvd=" + response.body().toString());
                                    if (response.isSuccessful()) {
                                        if (response.body().getSuc()) {
                                            mact_reg_la_pi.setVisibility(View.VISIBLE);
                                            mact_reg_la_vrfy.setVisibility(View.GONE);
                                            Log.d(TAG, "244 lu_rcvd=" + response.body());
                                        } else {
                                            Toast.makeText(RegisterActivity.this, "error.\n" + response.body().getMsg(), Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        if (response.body().getMsg() != null) {
                                            Toast.makeText(RegisterActivity.this, "There was an error. " + response.body().getMsg(), Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(RegisterActivity.this, "Server error. ", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                } else {
                                    Toast.makeText(RegisterActivity.this, "Unable to get response. Contact administrator.", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Model4Y> call, Throwable t) {
                                dialog.dismiss();
                                Log.d(TAG, "101 t=" + t.toString());
                            }
                        });
                    } else {
                        Toast.makeText(RegisterActivity.this, "internet connection required.", Toast.LENGTH_LONG).show();
                        Snackbar snackbar = Snackbar
                                .make(mact_reg_btn_get_otp, "internet connection required", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                }
            }
        });

        mact_reg_btn_set_pi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sltdModel4SnglRelationship != null && sltdModel4SnglRelationship.getD() > -1) {
                    if (Validation.hasTextLength(mact_reg_tvpi, "pin number ", 4, true, false, RegisterActivity.this)) {
                        if (Validation.hasTextLength(mact_reg_et_otp, "mobile no. ", 4, true, false, RegisterActivity.this)) {
                            if (CommonChks.isOnline()) {
                                final Dialog dialog = new Dialog(RegisterActivity.this);
                                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                dialog.setContentView(R.layout.layout_loader);
                                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                                dialog.show();
                                dialog.setCancelable(false);

//                        pModel4Y4OTP.setOt(Integer.parseInt(mact_reg_et_otp.getText().toString()));
//                        Call<Model4Y> call = RetrofitClient.getClient().retroInterfVeriOTP(pModel4Y4OTP);
                                RequestBody yc = RequestBody.create(MediaType.parse("text/plain"), pModel4Y4OTP.getYc() + "");
                                RequestBody yo = RequestBody.create(MediaType.parse("text/plain"), pModel4Y4OTP.getYo() + "");
                                RequestBody ot = RequestBody.create(MediaType.parse("text/plain"), mact_reg_et_otp.getText().toString() + "");
                                RequestBody pi = RequestBody.create(MediaType.parse("text/plain"), mact_reg_tvpi.getText().toString() + "");
                                Call<Model4Y> call = RetrofitClient.getClient().retroInterfSetPw(yc, yo, ot, pi);

                                call.enqueue(new Callback<Model4Y>() {
                                    @Override
                                    public void onResponse(Call<Model4Y> call, Response<Model4Y> response) {
                                        dialog.dismiss();
                                        if (response.body() != null) {
                                            Log.d(TAG, "79 lu_rcvd=" + response.body().toString());
                                            if (response.isSuccessful()) {
                                                if (response.body().getSuc()) {
                                                    pModel4Y4OTP = response.body();
                                                    SharedPreferences saved_values = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                                    SharedPreferences.Editor editor = saved_values.edit();
                                                    editor.putInt("y", response.body().getY());
                                                    editor.putInt("yc", response.body().getYc());
                                                    editor.putLong("yo", response.body().getYo());
                                                    editor.putString("yn", response.body().getYn());
                                                    editor.putBoolean("setExpiry",  setExpiry);
                                                    editor.apply();
                                                    pModel4Y4OTP.setY(response.body().getY());
                                                    pModel4Y4OTP.setYc(response.body().getYc());
                                                    pModel4Y4OTP.setYo(response.body().getYo());
                                                    pModel4Y4OTP.setYn(response.body().getYn());
                                                    finish();
                                                    Intent intent = new Intent(RegisterActivity.this, LoginWithPiActivity.class);
                                                    startActivity(intent);
                                                    Log.d(TAG, "319 lu_rcvd=" + response.body());
                                                } else {
                                                    Toast.makeText(RegisterActivity.this, "error. " + response.body().getMsg(), Toast.LENGTH_LONG).show();
                                                }
                                            } else {
                                                if (response.body().getMsg() != null) {
                                                    Toast.makeText(RegisterActivity.this, "There was an error. " + response.body().getMsg(), Toast.LENGTH_LONG).show();
                                                } else {
                                                    Toast.makeText(RegisterActivity.this, "Server error. ", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        } else {
                                            Toast.makeText(RegisterActivity.this, "Unable to get response. Contact administrator.", Toast.LENGTH_LONG).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Model4Y> call, Throwable t) {
                                        dialog.dismiss();
                                        Log.d(TAG, "101 t=" + t.toString());
                                    }
                                });
                            } else {
                                Toast.makeText(RegisterActivity.this, "internet connection required.", Toast.LENGTH_LONG).show();
                                Snackbar snackbar = Snackbar
                                        .make(mact_reg_btn_get_otp, "internet connection required", Snackbar.LENGTH_LONG);
                                snackbar.show();
                            }
                        }
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "select relation with mobile no. owner.", Toast.LENGTH_LONG).show();
                    Snackbar snackbar = Snackbar
                            .make(mact_reg_btn_get_otp, "select relation with mobile no. owner.", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });
    }

    private boolean checkValidation(Boolean shoErrMsg) {
//        return Validation.hasTextLength(mact_reg_yo, "mobile no. ", 10, shoErrMsg, false, this)
//                && Validation.hasTextLength(mact_reg_yn, "your name ", 3, shoErrMsg, false, this)
//                && Validation.hasNumberInIt(mact_reg_yn, "your name ", shoErrMsg, this);
        return Validation.hasTextLength(mact_reg_yo, "mobile no. ", 10, shoErrMsg, false, this);
    }

    private void fn_countdown(long millis, int yc, long yo) {
        mact_reg_yc.setCountryForPhoneCode(yc);
        mact_reg_yo.setText(String.valueOf(yo));
        pModel4Y4OTP.setYc(yc);
        pModel4Y4OTP.setYo(yo);
        mact_reg_la_pb.setVisibility(View.VISIBLE);

        mact_reg_la_get_otp.setVisibility(View.GONE);
        mact_reg_btn_veri_otp.setVisibility(View.VISIBLE);
        mact_reg_yo.setEnabled(false);
        mact_reg_et_otp.requestFocus();
        mact_reg_et_la_otp.setVisibility(View.VISIBLE);

//        myProgress = 0;
        try {
            countDownTimer.cancel();
        } catch (Exception e) {
            Log.d(TAG, "403 e=" + e.toString());
        }

//        String timeInterval = "100";
        progress = (100 - (int) (millis - System.currentTimeMillis()) / 1000) + 1;
//        endTime = Integer.parseInt(timeInterval); // up to finish time
        endSeconds = (int) (millis - System.currentTimeMillis()) / 1000;

        countDownTimer = new CountDownTimer(endSeconds * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                setProgress(progress, endSeconds);
                progress = progress + 1;
                int seconds = (int) (millisUntilFinished / 1000) % 60;
                int minutes = (int) ((millisUntilFinished / (1000 * 60)) % 60);
                int hours = (int) ((millisUntilFinished / (1000 * 60 * 60)) % 24);
                String newtime = hours + ":" + minutes + ":" + seconds;

                if (newtime.equals("0:0:0")) {
                    mact_reg_tv_pb.setText("00:00:00");
                } else if ((String.valueOf(hours).length() == 1) && (String.valueOf(minutes).length() == 1) && (String.valueOf(seconds).length() == 1)) {
                    mact_reg_tv_pb.setText("0" + hours + ":0" + minutes + ":0" + seconds);
                } else if ((String.valueOf(hours).length() == 1) && (String.valueOf(minutes).length() == 1)) {
                    mact_reg_tv_pb.setText("0" + hours + ":0" + minutes + ":" + seconds);
                } else if ((String.valueOf(hours).length() == 1) && (String.valueOf(seconds).length() == 1)) {
                    mact_reg_tv_pb.setText("0" + hours + ":" + minutes + ":0" + seconds);
                } else if ((String.valueOf(minutes).length() == 1) && (String.valueOf(seconds).length() == 1)) {
                    mact_reg_tv_pb.setText(hours + ":0" + minutes + ":0" + seconds);
                } else if (String.valueOf(hours).length() == 1) {
                    mact_reg_tv_pb.setText("0" + hours + ":" + minutes + ":" + seconds);
                } else if (String.valueOf(minutes).length() == 1) {
                    mact_reg_tv_pb.setText(hours + ":0" + minutes + ":" + seconds);
                } else if (String.valueOf(seconds).length() == 1) {
                    mact_reg_tv_pb.setText(hours + ":" + minutes + ":0" + seconds);
                } else {
                    mact_reg_tv_pb.setText(hours + ":" + minutes + ":" + seconds);
                }
            }

            @Override
            public void onFinish() {
                setProgress(progress, endSeconds);
                mact_reg_btn_veri_otp.setEnabled(false);
                mact_reg_et_otp.setEnabled(false);
                mact_reg_btn_set_pi.setEnabled(false);

                SharedPreferences saved_values = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = saved_values.edit();
                editor.putLong("odtt", 0);
                editor.apply();
            }
        };
        countDownTimer.start();
    }

    public void setProgress(int startTime, int endTime) {
        mact_reg_pb.setMax(endTime);
        mact_reg_pb.setSecondaryProgress(endTime);
        mact_reg_pb.setProgress(startTime);
    }
}