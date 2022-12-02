package com.sifr.my;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.sifr.my.retrofit.Model4ButtonsEPermissions;

import java.util.ArrayList;
import java.util.List;

import static com.sifr.my.MainActivity.pModel4Y4OTP;
import static com.sifr.my.Whom2Work4.mModel4BtnLstOnly;
import static com.sifr.my.Whom2Work4.model4MXonly;

public class ActivityButtons extends AppCompatActivity {
    private final String TAG = "sfr ActivityButtons";
    public static Model4ButtonsEPermissions selectedModel4ButtonsEPermissions0;//level of selected buttons
    public static Model4ButtonsEPermissions selectedModel4ButtonsEPermissions1;//level of selected buttons

    private Integer backToExitPressedCounter = 0;
    private int inBtnID = 0;
    private TextView mact_btn_tv_ttl;
    private RecyclerView mact_btn_btns_rv;
    Adapter4buttonsList mAdapter4buttonsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons);

        mact_btn_tv_ttl = findViewById(R.id.act_btn_tv_ttl);
        mact_btn_tv_ttl.setText("welcome +" + pModel4Y4OTP.getYc() + " " + pModel4Y4OTP.getYo() + "\ntouch to work for someone else");
        mact_btn_btns_rv = findViewById(R.id.act_btn_btns_rv);

        mact_btn_tv_ttl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityButtons.this, Whom2Work4.class);
                i.putExtra("backAgain", true);
                startActivity(i);
            }
        });

        if (mModel4BtnLstOnly != null) {
            inBtnID = getIntent().getIntExtra("inBtnID", 0);
            int pButtonLevel = getIntent().getIntExtra("buttonLevel", 0);
            if (inBtnID > 0 && pButtonLevel == 1) {
                showFilteredBtnLst(mModel4BtnLstOnly, inBtnID, pButtonLevel);
            } else {
                showFilteredBtnLst(mModel4BtnLstOnly, inBtnID, 0);
            }
        } else {
            mact_btn_btns_rv.setVisibility(View.GONE);
            mact_btn_tv_ttl.setTextColor(Color.parseColor("#ff0000"));
            mact_btn_tv_ttl.setText("no buttons assigned to you\ntouch here to change mobile no.");
        }
    }

    private void showFilteredBtnLst(List<Model4ButtonsEPermissions> mModel4BtnLstOnly, int btnID, int pButtonLevel) {
        List<Model4ButtonsEPermissions> mOKFilteredModel4BtnLst = new ArrayList<>();
        for (Model4ButtonsEPermissions btnperm : mModel4BtnLstOnly) {
            if (btnperm.getW() == btnID
                    && btnperm.getDi() == 1 && btnperm.getY() == model4MXonly.getY()) {
                mOKFilteredModel4BtnLst.add(btnperm);
            }
        }
        if (mOKFilteredModel4BtnLst.size() > 0) {
            mAdapter4buttonsList = new Adapter4buttonsList(ActivityButtons.this, pButtonLevel, mOKFilteredModel4BtnLst);
            mact_btn_btns_rv.setAdapter(mAdapter4buttonsList);
            mact_btn_btns_rv.setVisibility(View.VISIBLE);
        } else {
            mact_btn_btns_rv.setVisibility(View.GONE);
            mact_btn_tv_ttl.setTextColor(Color.parseColor("#ff0000"));
            mact_btn_tv_ttl.setText("no buttons assigned to you\ntouch here to change mobile no.");

            Toast.makeText(ActivityButtons.this, "no work buttons are assigned to you.", Toast.LENGTH_LONG).show();
            Snackbar snackbar = Snackbar
                    .make(mact_btn_tv_ttl, "no work buttons are assigned to you.", Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }

    @Override
    public void onBackPressed() {
        if (inBtnID > 0) {
            super.onBackPressed();
        } else {
            Integer backToExitlimit = 1;
            if (backToExitPressedCounter == backToExitlimit) {
                super.onBackPressed();
                backToExitPressedCounter = 0;
            } else {
                Toast.makeText(this, "Press back " + (backToExitlimit - backToExitPressedCounter) + "more time(s) to exit", Toast.LENGTH_SHORT).show();
                backToExitPressedCounter++;
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    backToExitPressedCounter = 0;
                }
            }, 2000);
        }
    }
}