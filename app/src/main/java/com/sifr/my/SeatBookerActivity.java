package com.sifr.my;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;
import com.sifr.my.common.CommonChecks;
import com.sifr.my.retrofit.Model4SeatsDtls;
import com.sifr.my.retrofit.Model4VehicleSeatsLst;
import com.sifr.my.retrofit.Model4snglClient;
import com.sifr.my.retrofit.Model4snglLuggage;
import com.sifr.my.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.sifr.my.ActiSelVeh2bokSeats.chosenDt;
import static com.sifr.my.Adapter4travelStops.sltdModel4TravelStops;
import static com.sifr.my.AdapterForSeatsLst.ppModel4VehicleSeatsLst;
import static com.sifr.my.Whom2Work4.model4MXonly;

public class SeatBookerActivity extends AppCompatActivity {
    private final String TAG = "sfr SeatBookerActivity";
    private CountryCodePicker mact_seat_booker_cc_vc;
    private EditText mact_seat_booker_et_vo, mact_seat_booker_et_vn, mact_seat_booker_et_amt, mact_seat_booker_et_adva;
    private TextView mact_seat_booker_tv_pckup, mact_seat_booker_tv_drop, mact_seat_booker_tv_rmna;
    private int dropPoint, pickPoint;
    float amt = 0L;
    private EditText mact_seat_booker_tv_pakit, mact_seat_booker_tv_pishvi,
            mact_seat_booker_tv_box, mact_seat_booker_tv_gon, mact_seat_booker_tv_other,
            mact_seat_booker_tv_pakit_a, mact_seat_booker_tv_pishvi_a,
            mact_seat_booker_tv_box_a, mact_seat_booker_tv_gon_a, mact_seat_booker_tv_other_a;
    public static boolean seatsBooked = false;
    private RadioButton mact_for_whom_rb_v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_booker);
        final CommonChecks CommonChks = new CommonChecks(getApplicationContext());

        TextView mact_seat_booker_et_dt = findViewById(R.id.act_seat_booker_et_dt);
        mact_seat_booker_et_dt.setText(chosenDt);
        TextView mact_seat_booker_tv_seats = findViewById(R.id.act_seat_booker_tv_seats);
        TextView mact_seat_booker_tv_totseats = findViewById(R.id.act_seat_booker_tv_totseats);
        mact_seat_booker_tv_pckup = findViewById(R.id.act_seat_booker_tv_pckup);
        mact_seat_booker_tv_drop = findViewById(R.id.act_seat_booker_tv_drop);
        mact_seat_booker_tv_rmna = findViewById(R.id.act_seat_booker_tv_rmna);
        Button mact_seat_booker_bt_sav = findViewById(R.id.act_seat_booker_bt_sav);
        mact_seat_booker_et_amt = findViewById(R.id.act_seat_booker_et_amt);
        mact_seat_booker_et_adva = findViewById(R.id.act_seat_booker_et_adva);
        mact_seat_booker_et_adva.setEnabled(false);
        mact_seat_booker_cc_vc = findViewById(R.id.act_seat_booker_cc_vc);
        mact_seat_booker_et_vo = findViewById(R.id.act_seat_booker_et_vo);
        mact_seat_booker_et_vn = findViewById(R.id.act_seat_booker_et_vn);
        mact_seat_booker_tv_pakit = findViewById(R.id.act_seat_booker_tv_pakit);
        mact_seat_booker_tv_pakit_a = findViewById(R.id.act_seat_booker_tv_pakit_a);
        mact_seat_booker_tv_pishvi = findViewById(R.id.act_seat_booker_tv_pishvi);
        mact_seat_booker_tv_pishvi_a = findViewById(R.id.act_seat_booker_tv_pishvi_a);
        mact_seat_booker_tv_box = findViewById(R.id.act_seat_booker_tv_box);
        mact_seat_booker_tv_box_a = findViewById(R.id.act_seat_booker_tv_box_a);
        mact_seat_booker_tv_gon = findViewById(R.id.act_seat_booker_tv_gon);
        mact_seat_booker_tv_gon_a = findViewById(R.id.act_seat_booker_tv_gon_a);
        mact_seat_booker_tv_other = findViewById(R.id.act_seat_booker_tv_other);
        mact_seat_booker_tv_other_a = findViewById(R.id.act_seat_booker_tv_other_a);
        mact_for_whom_rb_v = findViewById(R.id.act_for_whom_rb_v);


        String strSeats = "";
        List<Model4SeatsDtls> seltdModel4SeatsDtls = new ArrayList<>();
        for (int i = 0; i < ppModel4VehicleSeatsLst.getVesLst().size(); i++) {
            if (ppModel4VehicleSeatsLst.getVesLst().get(i).getS() != null && ppModel4VehicleSeatsLst.getVesLst().get(i).getS().equals("1")) {
                seltdModel4SeatsDtls.add(ppModel4VehicleSeatsLst.getVesLst().get(i));
                strSeats = strSeats + ", " + ppModel4VehicleSeatsLst.getVesLst().get(i).getA();
            }
        }
        mact_seat_booker_tv_seats.setText(strSeats);
        mact_seat_booker_tv_totseats.setText(String.valueOf(seltdModel4SeatsDtls.size()));

        mact_seat_booker_tv_pckup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), VehicleStopListActivity.class);
                intent.putExtra("px_pod", 1);
                v.getContext().startActivity(intent);
            }
        });

        mact_seat_booker_tv_drop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), VehicleStopListActivity.class);
                intent.putExtra("px_pod", 2);
                v.getContext().startActivity(intent);
            }
        });

        mact_seat_booker_et_amt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    amt = Float.parseFloat(s.toString());
                    mact_seat_booker_et_adva.setEnabled(true);
                } catch (NumberFormatException e) {
                    mact_seat_booker_et_adva.setText("");
                    mact_seat_booker_et_adva.setEnabled(false);
                }
            }
        });

        mact_seat_booker_et_adva.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG, "135 s=" + s);
                try {
                    float amtMinus = Float.parseFloat(s.toString());
                    Log.d(TAG, "138 amtMinus=" + amtMinus);
                    float amtt = amt - amtMinus;
                    Log.d(TAG, "140 amt=" + amt + " amtt=" + amtt);
                    mact_seat_booker_tv_rmna.setText(String.valueOf(amtt));
                } catch (NumberFormatException e) {
                    mact_seat_booker_tv_rmna.setText("");
                    mact_seat_booker_tv_rmna.setEnabled(false);
                }
            }
        });

        mact_seat_booker_bt_sav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //regex for number format "\\d+(?:\\.\\d+)?"
                String formatRegx = "\\d+(?:\\.\\d+)?";
                List<Model4snglLuggage> lstModel4snglLuggage = new ArrayList<>();
                if (Validation.hasMinLenEfrmat(mact_seat_booker_tv_other, "", 1, false, false, SeatBookerActivity.this, formatRegx)) {
                    Model4snglLuggage mModel4snglLuggage = new Model4snglLuggage();
                    mModel4snglLuggage.setL(0);//luggage type
                    mModel4snglLuggage.setQ(Float.parseFloat(Objects.requireNonNull(mact_seat_booker_tv_other.getText()).toString()));//quantity
                    if (Validation.hasMinLenEfrmat(mact_seat_booker_tv_other_a, "", 1, false, false, SeatBookerActivity.this, formatRegx))
                        mModel4snglLuggage.setC(Float.parseFloat(Objects.requireNonNull(mact_seat_booker_tv_other_a.getText()).toString()));//amount
                    lstModel4snglLuggage.add(mModel4snglLuggage);
                }
                if (Validation.hasMinLenEfrmat(mact_seat_booker_tv_pakit, "", 1, false, false, SeatBookerActivity.this, formatRegx)) {
                    Model4snglLuggage mModel4snglLuggage = new Model4snglLuggage();
                    mModel4snglLuggage.setL(1);//luggage type
                    mModel4snglLuggage.setQ(Float.parseFloat(Objects.requireNonNull(mact_seat_booker_tv_pakit.getText()).toString()));//quantity
                    if (Validation.hasMinLenEfrmat(mact_seat_booker_tv_pakit_a, "", 1, false, false, SeatBookerActivity.this, formatRegx))
                        mModel4snglLuggage.setC(Float.parseFloat(Objects.requireNonNull(mact_seat_booker_tv_pakit_a.getText()).toString()));//amount
                    lstModel4snglLuggage.add(mModel4snglLuggage);
                }
                if (Validation.hasMinLenEfrmat(mact_seat_booker_tv_pishvi, "", 1, false, false, SeatBookerActivity.this, formatRegx)) {
                    Model4snglLuggage mModel4snglLuggage = new Model4snglLuggage();
                    mModel4snglLuggage.setL(2);//luggage type
                    mModel4snglLuggage.setQ(Float.parseFloat(Objects.requireNonNull(mact_seat_booker_tv_pishvi.getText()).toString()));//quantity
                    if (Validation.hasMinLenEfrmat(mact_seat_booker_tv_pishvi_a, "", 1, false, false, SeatBookerActivity.this, formatRegx))
                        mModel4snglLuggage.setC(Float.parseFloat(Objects.requireNonNull(mact_seat_booker_tv_pishvi_a.getText()).toString()));//amount
                    lstModel4snglLuggage.add(mModel4snglLuggage);
                }
                if (Validation.hasMinLenEfrmat(mact_seat_booker_tv_box, "", 1, false, false, SeatBookerActivity.this, formatRegx)) {
                    Model4snglLuggage mModel4snglLuggage = new Model4snglLuggage();
                    mModel4snglLuggage.setL(3);//luggage type
                    mModel4snglLuggage.setQ(Float.parseFloat(Objects.requireNonNull(mact_seat_booker_tv_box.getText()).toString()));//quantity
                    if (Validation.hasMinLenEfrmat(mact_seat_booker_tv_box_a, "", 1, false, false, SeatBookerActivity.this, formatRegx))
                        mModel4snglLuggage.setC(Float.parseFloat(Objects.requireNonNull(mact_seat_booker_tv_box_a.getText()).toString()));//amount
                    lstModel4snglLuggage.add(mModel4snglLuggage);
                }
                if (Validation.hasMinLenEfrmat(mact_seat_booker_tv_gon, "", 1, false, false, SeatBookerActivity.this, formatRegx)) {
                    Model4snglLuggage mModel4snglLuggage = new Model4snglLuggage();
                    mModel4snglLuggage.setL(4);//luggage type
                    mModel4snglLuggage.setQ(Float.parseFloat(Objects.requireNonNull(mact_seat_booker_tv_gon.getText()).toString()));//quantity
                    if (Validation.hasMinLenEfrmat(mact_seat_booker_tv_gon_a, "", 1, false, false, SeatBookerActivity.this, formatRegx))
                        mModel4snglLuggage.setC(Float.parseFloat(Objects.requireNonNull(mact_seat_booker_tv_gon_a.getText()).toString()));//amount
                    lstModel4snglLuggage.add(mModel4snglLuggage);
                }
                if (seltdModel4SeatsDtls.size() > 0 || lstModel4snglLuggage.size() > 0) {
                    if (Validation.hasTextLength(mact_seat_booker_et_vo, "mobile no. ", 10, true, false, SeatBookerActivity.this)) {
                        if (CommonChks.isOnline()) {
                            final Dialog dialog = new Dialog(SeatBookerActivity.this);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setContentView(R.layout.layout_loader);
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                            dialog.show();
                            dialog.setCancelable(false);

                            Model4VehicleSeatsLst pModel4VehicleSeatsLst = new Model4VehicleSeatsLst();
                            pModel4VehicleSeatsLst.setMx(model4MXonly);

                            Model4snglClient mModel4snglClient = new Model4snglClient();
                            mModel4snglClient.setVc(91);
                            mModel4snglClient.setVo(Long.parseLong(mact_seat_booker_et_vo.getText().toString()));
                            mModel4snglClient.setVn(mact_seat_booker_et_vn.getText().toString());
                            pModel4VehicleSeatsLst.setUv(mModel4snglClient);

                            pModel4VehicleSeatsLst.setVe(Integer.parseInt(ActiSelVeh2bokSeats.choModel4vehicleAvailableByDt.getI()));
                            pModel4VehicleSeatsLst.setM(model4MXonly.getM());
                            pModel4VehicleSeatsLst.setC(chosenDt);
                            pModel4VehicleSeatsLst.setVc(Integer.parseInt(mact_seat_booker_cc_vc.getSelectedCountryCode()));
                            pModel4VehicleSeatsLst.setVo(Long.parseLong(mact_seat_booker_et_vo.getText().toString()));
                            pModel4VehicleSeatsLst.setVn(mact_seat_booker_et_vn.getText().toString());
                            pModel4VehicleSeatsLst.setP(pickPoint);
                            pModel4VehicleSeatsLst.setR(dropPoint);
                            pModel4VehicleSeatsLst.setTk(MainActivity.tk);

                            pModel4VehicleSeatsLst.setVesLst(seltdModel4SeatsDtls);
                            pModel4VehicleSeatsLst.setLuLst(lstModel4snglLuggage);

                            if (mact_seat_booker_et_amt.getText().toString().length() > 0)
                                pModel4VehicleSeatsLst.setK(Float.parseFloat(mact_seat_booker_et_amt.getText().toString()));
                            else
                                pModel4VehicleSeatsLst.setK(0L);

                            if (mact_seat_booker_et_adva.getText().toString().length() > 0)
                                pModel4VehicleSeatsLst.setL(Float.parseFloat(mact_seat_booker_et_adva.getText().toString()));
                            else
                                pModel4VehicleSeatsLst.setL(0L);

                            if (mact_for_whom_rb_v.isChecked()) {
                                pModel4VehicleSeatsLst.setN(1);
                            } else {
                                pModel4VehicleSeatsLst.setN(0);
                            }

                            Call<Model4VehicleSeatsLst> call = RetrofitClient.getClient().retroInterfBookSeats(pModel4VehicleSeatsLst);

                            call.enqueue(new Callback<Model4VehicleSeatsLst>() {
                                @Override
                                public void onResponse(Call<Model4VehicleSeatsLst> call, Response<Model4VehicleSeatsLst> response) {
                                    dialog.dismiss();
                                    if (response.body() != null) {
                                        Log.d("loog", TAG + "79 lu_rcvd=" + response.body().toString());
                                        if (response.isSuccessful()) {
                                            if (response.body().getSuc()) {
                                                seatsBooked = true;
                                                Toast.makeText(SeatBookerActivity.this, "Seats/luggage booked. " + response.body().getMsg(), Toast.LENGTH_LONG).show();

                                                String appPackage = null;
                                                String whtasNu = "91" + mModel4snglClient.getVo();
                                                String defMsg = "*स्मार्ट बस मध्ये आपलं स्वागत आहे*" +
                                                        "\nपुढील तपशीलाप्रमाणे आपली बुकिंग निश्चित करण्यात आलेली आहे." +
                                                        "\n\nबुक करणाऱ्याचे नाव: *" + mact_seat_booker_et_vn.getText().toString() + "*" +
                                                        "\nगाडी नंबर: *" + ActiSelVeh2bokSeats.choModel4vehicleAvailableByDt.getVn() + "*" +
                                                        "\nबसणार: *" + mact_seat_booker_tv_pckup.getText().toString() + "*" +
                                                        "\nउतरणार: *" + mact_seat_booker_tv_drop.getText().toString() + "*" +
                                                        "\nप्रवासाची तारीख: *" + chosenDt + "*" +
                                                        "\nएकूण सीट: *" + mact_seat_booker_tv_totseats.getText().toString() + "*" +
                                                        "\nसीट नं: *" + mact_seat_booker_tv_seats.getText().toString() + "*" +
                                                        "\nएकूण रक्कम ₹: *" + pModel4VehicleSeatsLst.getK() + "*" +
                                                        "\nयेणे रक्कम ₹: *" + mact_seat_booker_tv_rmna.getText().toString() + "*" +
                                                        "\n\nआमचा गूगल पे नं 9420732715" +
                                                        "\nआपल्या सेवेसाठी सदैव तत्पर" +
                                                        "\nस्मार्ट बस" +
                                                        "\nधन्यवाद." +
                                                        "\n\nगाडीचं Live लोकेशन बघण्यासाठी खालील लिंक वर क्लीक करा";

                                                        /*"असं क्रमांक : " + mact_seat_booker_tv_seats.getText().toString() +
                                                        " तुमच्यासाठी आरक्षित केल्या गेल्या आहेत." +
                                                        "%0aएकूण रक्कम होते:     " + pModel4VehicleSeatsLst.getK() +
                                                        "%0aमिळाली रक्कम:       " + pModel4VehicleSeatsLst.getL() +
                                                        "%0aयेणे बाकी:          " + mact_seat_booker_tv_rmna.getText().toString();*/
                                                String webPageLink = "\nsifr.in";

                                                if (CommonChks.isAppInstalled(SeatBookerActivity.this, "com.whatsapp.w4b")) {
                                                    appPackage = "com.whatsapp.w4b";
                                                    CommonChks.sendWhatsApp(SeatBookerActivity.this, whtasNu, defMsg, webPageLink, appPackage);
                                                } else if (CommonChks.isAppInstalled(SeatBookerActivity.this, "com.whatsapp")) {
                                                    appPackage = "com.whatsapp";
                                                    CommonChks.sendWhatsApp(SeatBookerActivity.this, whtasNu, defMsg, webPageLink, appPackage);
                                                } else {
                                                    Toast.makeText(SeatBookerActivity.this, "362 whatsApp is not installed", Toast.LENGTH_LONG).show();
                                                }

                                                Handler handler = new Handler();
                                                handler.postDelayed(new Runnable() {
                                                    public void run() {
                                                        finish();
                                                    }
                                                }, 2000);
                                            }
                                        } else {
                                            if (response.body().getMsg() != null) {
                                                Toast.makeText(SeatBookerActivity.this, "There was an error. " + response.body().getMsg(), Toast.LENGTH_LONG).show();
                                            } else {
                                                Toast.makeText(SeatBookerActivity.this, "Server error. ", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    } else {
                                        Toast.makeText(SeatBookerActivity.this, "Unable to get response. Contact administrator.", Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Model4VehicleSeatsLst> call, Throwable t) {
                                    dialog.dismiss();
                                    Log.d("loog", TAG + "101 t=" + t.toString());
                                }
                            });
                        } else {
                            Toast.makeText(SeatBookerActivity.this, "internet connection required.", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(SeatBookerActivity.this, "mobile no. is compulsory.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(SeatBookerActivity.this, "seat or package required.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (sltdModel4TravelStops != null && sltdModel4TravelStops.getD() > 0) {
            if (sltdModel4TravelStops.getPod() == 1) {
                pickPoint = sltdModel4TravelStops.getD();
                mact_seat_booker_tv_pckup.setText(sltdModel4TravelStops.getN());
            } else {
                mact_seat_booker_tv_drop.setText(sltdModel4TravelStops.getN());
                dropPoint = sltdModel4TravelStops.getD();
            }
        }
    }
}