package com.sifr.my;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.hbb20.CountryCodePicker;
import com.sifr.my.common.CommonChecks;
import com.sifr.my.retrofit.Model4ButtonsEPermissions;
import com.sifr.my.retrofit.Model4MXeBtns;
import com.sifr.my.retrofit.Model4MXonly;
import com.sifr.my.retrofit.RetrofitClient;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sifr.my.RegisterActivity.setExpiry;
import static com.sifr.my.RegisterActivity.tmpMoNo;

public class Whom2Work4 extends AppCompatActivity {
    private final String TAG = "sfr Whom2Work4";
    public static Model4MXonly model4MXonly;
    public static List<Model4ButtonsEPermissions> mModel4BtnLstOnly;

    private RadioButton mact_for_whom2_rb_m, mact_for_whom2_rb_e, mact_for_whom2_rb_v, mact_for_whom2_rb_u;
    private CountryCodePicker mact_for_whom2_ccod_mfc, mact_for_whom2_ccod_vfc, mact_for_whom2_ccod_mc, mact_for_whom2_ccod_vc, mact_for_whom2_ccod_uc;
    private TextInputEditText mact_for_whom2_met_mfo, mact_for_whom2_met_vfo, mact_for_whom2_met_mo, mact_for_whom2_met_vo, mact_for_whom2_met_uo;
    private EditText mact_for_whom2_et_mfe, mact_for_whom2_et_vfg, mact_for_whom2_et_e, mact_for_whom2_et_g;
    private CheckBox mact_for_whom2_cb_remem, mact_for_whom2_cb_u;
    private LinearLayout mact_for_whom2_ll_vf, mact_for_whom2_ll_label, mact_for_whom2_ll_me, mact_for_whom2_ll_rb_m,
            mact_for_whom2_ll_mf, mact_for_whom2_ll_vco, mact_for_whom2_ll_uco;
    private ImageView mact_for_whom2_iv_mf, mact_for_whom2_iv_vf;
    private RadioGroup mact_for_whom2_rg_vf;
    private Button mact_for_whom2_bt;
    private boolean remem;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_whom_do_u_want_to_work);

        mact_for_whom2_cb_remem = findViewById(R.id.act_for_whom2_cb_remem);
        SharedPreferences saved_values = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = saved_values.edit();
        remem = saved_values.getBoolean("rememMX", true);//change this to save mx model (right now save only mo)
        mact_for_whom2_cb_remem.setChecked(remem);
        mact_for_whom2_cb_remem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //remember MX}
                editor.putBoolean("rememMX", mact_for_whom2_cb_remem.isChecked());//remember MX
                editor.apply();
            }
        });
        mact_for_whom2_ll_vf = findViewById(R.id.act_for_whom2_ll_vf);
        mact_for_whom2_ll_vf.setVisibility(View.GONE);
        mact_for_whom2_ll_me = findViewById(R.id.act_for_whom2_ll_me);
        mact_for_whom2_ll_label = findViewById(R.id.act_for_whom2_ll_label);

        mact_for_whom2_iv_mf = findViewById(R.id.act_for_whom2_iv_mf);
        mact_for_whom2_ll_mf = findViewById(R.id.act_for_whom2_ll_mf);
        mact_for_whom2_ll_mf.setVisibility(View.GONE);

        mact_for_whom2_rg_vf = findViewById(R.id.act_for_whom2_rg_vf);
        mact_for_whom2_rg_vf.setVisibility(View.GONE);
        mact_for_whom2_ll_rb_m = findViewById(R.id.act_for_whom2_ll_rb_m);

        mact_for_whom2_ccod_mfc = findViewById(R.id.act_for_whom2_ccod_mfc);
        mact_for_whom2_met_mfo = findViewById(R.id.act_for_whom2_met_mfo);
        mact_for_whom2_et_mfe = findViewById(R.id.act_for_whom2_et_mfe);

        mact_for_whom2_rb_m = findViewById(R.id.act_for_whom2_rb_m);
        mact_for_whom2_rb_m.setEnabled(false);
        mact_for_whom2_rb_e = findViewById(R.id.act_for_whom2_rb_e);
        mact_for_whom2_rb_e.setEnabled(false);

        mact_for_whom2_ccod_mc = findViewById(R.id.act_for_whom2_ccod_mc);
        mact_for_whom2_met_mo = findViewById(R.id.act_for_whom2_met_mo);
        mact_for_whom2_et_e = findViewById(R.id.act_for_whom2_et_e);

        mact_for_whom2_iv_vf = findViewById(R.id.act_for_whom2_iv_vf);
        mact_for_whom2_ccod_vfc = findViewById(R.id.act_for_whom2_ccod_vfc);
        mact_for_whom2_met_vfo = findViewById(R.id.act_for_whom2_met_vfo);
        mact_for_whom2_et_vfg = findViewById(R.id.act_for_whom2_et_vfg);

        mact_for_whom2_rb_v = findViewById(R.id.act_for_whom2_rb_v);
        mact_for_whom2_rb_v.setSelected(true);
        mact_for_whom2_rb_u = findViewById(R.id.act_for_whom2_rb_u);

        mact_for_whom2_ll_vco = findViewById(R.id.act_for_whom2_ll_vco);
        mact_for_whom2_ll_vco.setVisibility(View.GONE);

        mact_for_whom2_ccod_vc = findViewById(R.id.act_for_whom2_ccod_vc);
        mact_for_whom2_met_vo = findViewById(R.id.act_for_whom2_met_vo);

        mact_for_whom2_cb_u = findViewById(R.id.act_for_whom2_cb_u);
        mact_for_whom2_cb_u.setVisibility(View.GONE);

        mact_for_whom2_ll_uco = findViewById(R.id.act_for_whom2_ll_uco);
        mact_for_whom2_ll_uco.setVisibility(View.GONE);

        mact_for_whom2_ccod_uc = findViewById(R.id.act_for_whom2_ccod_uc);
        mact_for_whom2_met_uo = findViewById(R.id.act_for_whom2_met_uo);
        mact_for_whom2_et_g = findViewById(R.id.act_for_whom2_et_g);

        mact_for_whom2_bt = findViewById(R.id.act_for_whom2_bt);
        mact_for_whom2_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pullButtonLst();
            }
        });
// TO DO
//        mact_btns_sho_food_menu = findViewById(R.id.act_btns_sho_food_menu);
//        mact_btns_show_seats = findViewById(R.id.act_btns_show_seats);

        mact_for_whom2_iv_mf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                introSoon();
                /*if (mact_for_whom2_ll_mf.getVisibility() == View.GONE) {
                    mact_for_whom2_ll_mf.setVisibility(View.VISIBLE);
                } else {
                    mact_for_whom2_ll_mf.setVisibility(View.GONE);
                }*/
            }
        });

        mact_for_whom2_iv_vf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                introSoon();
                /*if (mact_for_whom2_rg_vf.getVisibility() == View.VISIBLE) {
                    mact_for_whom2_rg_vf.setVisibility(View.GONE);
                    mact_for_whom2_ll_vco.setVisibility(View.GONE);
                } else {
                    mact_for_whom2_rg_vf.setVisibility(View.VISIBLE);
                    mact_for_whom2_ll_vco.setVisibility(View.VISIBLE);
                }*/
            }
        });

        mact_for_whom2_rb_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mact_for_whom2_et_e.setText("0");
            }
        });

        mact_for_whom2_rb_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mact_for_whom2_et_e.setText("1");
            }
        });

        mact_for_whom2_rb_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mact_for_whom2_ll_vco.setVisibility(View.VISIBLE);
                mact_for_whom2_ll_uco.setVisibility(View.GONE);
                mact_for_whom2_met_uo.setText(null);
            }
        });

        mact_for_whom2_rb_u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mact_for_whom2_ll_vco.setVisibility(View.GONE);
                mact_for_whom2_ll_uco.setVisibility(View.VISIBLE);
                mact_for_whom2_met_vo.setText(null);
            }
        });

        mact_for_whom2_ccod_mc.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                if (!mact_for_whom2_ccod_mc.getSelectedCountryCode().equals("91")) {
                    mact_for_whom2_bt.setEnabled(false);
                    Snackbar snackbar = Snackbar
                            .make(mact_for_whom2_ccod_mc, "only indian mobile number users can see their data.", Snackbar.LENGTH_LONG);
                    snackbar.show();
                } else {
                    mact_for_whom2_bt.setEnabled(checkValidation(true));
                }
            }
        });

        mact_for_whom2_cb_u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mact_for_whom2_cb_u.isChecked()) {
                    mact_for_whom2_ll_uco.setVisibility(View.VISIBLE);
                    mact_for_whom2_et_g.setText("1");
                } else {
                    mact_for_whom2_ll_uco.setVisibility(View.GONE);
                    mact_for_whom2_et_g.setText("0");
                }
            }
        });

        mact_for_whom2_met_vo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mact_for_whom2_met_mo.setText(mact_for_whom2_met_vo.getText());
            }
        });

        mact_for_whom2_met_uo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mact_for_whom2_met_mo.setText(mact_for_whom2_met_uo.getText());
            }
        });


        if (setExpiry) {
            mact_for_whom2_cb_remem.setChecked(true);
            mact_for_whom2_met_mo.setText(tmpMoNo);
            mact_for_whom2_met_mo.setEnabled(false);
            if (!getIntent().getBooleanExtra("backAgain", false)) {
                pullButtonLst();
            }
        } else if (remem) {
            String MXmo = saved_values.getString("MXmo", null);
            if (MXmo != null) {
                mact_for_whom2_met_mo.setText(MXmo);
                if (!getIntent().getBooleanExtra("backAgain", false)) {
                    pullButtonLst();
                }
            }
        }
    }

    private void pullButtonLst() {
        String formatRegx = "[0-9]{10}";
        if (Validation.hasMinLenEfrmat(mact_for_whom2_met_mo, "Incorrect mobile No.", 10, true, false, Whom2Work4.this, formatRegx)) {
            if (remem) {
                if (mact_for_whom2_met_mo.getText().toString().length() == 10) {
                    editor.putString("MXmo", Objects.requireNonNull(mact_for_whom2_met_mo.getText()).toString());
                    editor.apply();
                }
            }

            final CommonChecks commonChks = new CommonChecks(getApplicationContext());
            if (commonChks.isOnline()) {
                final Dialog dialog = new Dialog(Whom2Work4.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.layout_loader);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                dialog.setCancelable(false);

                if (mact_for_whom2_met_mfo.getText().toString().length() == 10) {
                    model4MXonly.setMfcl(new Model4MXonly());
                    model4MXonly.getMfcl().setMo(Long.parseLong(mact_for_whom2_met_mfo.getText().toString()));
                    model4MXonly.getMfcl().setMc(Integer.parseInt(mact_for_whom2_ccod_mfc.getSelectedCountryCode()));
                    model4MXonly.getMfcl().setE(Integer.parseInt(mact_for_whom2_et_mfe.getText().toString()));
                }

                if (mact_for_whom2_met_mo.getText().toString().length() == 10) {
                    model4MXonly.setMc(Integer.parseInt(mact_for_whom2_ccod_mc.getSelectedCountryCode()));
                    model4MXonly.setMo(Long.parseLong(mact_for_whom2_met_mo.getText().toString()));
                    model4MXonly.setE(Integer.parseInt(mact_for_whom2_et_e.getText().toString()));
                }

                if (mact_for_whom2_met_vfo.getText().toString().length() == 10) {
                    model4MXonly.setVfcl(new Model4MXonly());
                    model4MXonly.getVfcl().setVc(Integer.parseInt(mact_for_whom2_ccod_vfc.getSelectedCountryCode()));
                    model4MXonly.getVfcl().setVo(Long.parseLong(mact_for_whom2_met_vfo.getText().toString()));
                    model4MXonly.getVfcl().setG(Integer.parseInt(mact_for_whom2_et_vfg.getText().toString()));
                }

                if (mact_for_whom2_met_vo.getText().toString().length() == 10) {
                    model4MXonly.setVc(Integer.parseInt(mact_for_whom2_ccod_vc.getSelectedCountryCode()));
                    model4MXonly.setVo(Long.parseLong(mact_for_whom2_met_vo.getText().toString()));
                }

                if (mact_for_whom2_met_uo.getText().toString().length() == 10) {
                    model4MXonly.setUc(Integer.parseInt(mact_for_whom2_ccod_uc.getSelectedCountryCode()));
                    model4MXonly.setUo(Long.parseLong(mact_for_whom2_met_uo.getText().toString()));
                    model4MXonly.setG(Integer.parseInt(mact_for_whom2_et_g.getText().toString()));
                }

                Call<Model4MXeBtns> call = RetrofitClient.getClient().retroInterfSetMX(model4MXonly);
                call.enqueue(new Callback<Model4MXeBtns>() {
                    @Override
                    public void onResponse(Call<Model4MXeBtns> call, Response<Model4MXeBtns> response) {
                        dialog.dismiss();
                        if (response.body() != null) {
                            Log.d(TAG, "79 lu_rcvd=" + response.body().toString());
                            if (response.isSuccessful()) {
                                if (response.body().getSuc()) {
                                    model4MXonly = response.body().getMx();
                                    if (response.body().getBtn() != null && response.body().getBtn().size() > 0) {
                                        mModel4BtnLstOnly = response.body().getBtn();
                                        Log.d(TAG, "308 " + response.body());
                                        finish();
                                        Intent i = new Intent(Whom2Work4.this, ActivityButtons.class);
                                        startActivity(i);
                                    } else {
                                        Toast.makeText(Whom2Work4.this, "no work buttons are assigned to you.", Toast.LENGTH_LONG).show();
                                        Snackbar snackbar = Snackbar
                                                .make(mact_for_whom2_met_mo, "no work buttons are assigned to you.", Snackbar.LENGTH_LONG);
                                        snackbar.show();
                                    }
                                } else {
                                    Toast.makeText(Whom2Work4.this, "error. " + response.body().getMsg(), Toast.LENGTH_LONG).show();
                                }
                            } else {
                                if (response.body().getMsg() != null) {
                                    Toast.makeText(Whom2Work4.this, "There was an error. " + response.body().getMsg(), Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(Whom2Work4.this, "Server error. ", Toast.LENGTH_LONG).show();
                                }
                            }
                        } else {
                            Toast.makeText(Whom2Work4.this, "Unable to get response. Contact administrator.", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Model4MXeBtns> call, Throwable t) {
                        dialog.dismiss();
                        Log.d(TAG, "274 t=" + t.toString());
                    }
                });
            } else {
                Toast.makeText(Whom2Work4.this, "internet connection required.", Toast.LENGTH_LONG).show();
                Snackbar snackbar = Snackbar
                        .make(mact_for_whom2_bt, "internet connection required", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
    }

    private boolean checkValidation(Boolean shoErrMsg) {
//        return Validation.hasTextLength(mact_reg_yo, "mobile no. ", 10, shoErrMsg, false, this)
//                && Validation.hasTextLength(mact_reg_yn, "your name ", 3, shoErrMsg, false, this)
//                && Validation.hasNumberInIt(mact_reg_yn, "your name ", shoErrMsg, this);
        return
                (Validation.hasTextLength(mact_for_whom2_met_uo, "mobile no. ", 10, shoErrMsg, false, this)
                        ||
                        Validation.hasTextLength(mact_for_whom2_met_vo, "mobile no. ", 10, shoErrMsg, false, this)
                ) &&
                        Validation.hasTextLength(mact_for_whom2_met_mo, "mobile no. ", 10, shoErrMsg, false, this);
    }

    private void introSoon() {
        Toast.makeText(Whom2Work4.this, "this system will be introduced soon.", Toast.LENGTH_LONG).show();
        Snackbar snackbar = Snackbar
                .make(mact_for_whom2_met_mo, "this system will be introduced soon.", Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}