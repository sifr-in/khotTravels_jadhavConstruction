package com.sifr.my;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.sifr.my.retrofit.Model4snglStr;

import java.util.ArrayList;
import java.util.List;

public class Acti4LstStr extends AppCompatActivity {
    private final String TAG = "sfr Acti4LstStr";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_Dialog);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acti_4_lst_str);
        getWindow().setBackgroundDrawable(null);
        getWindow().setGravity(Gravity.BOTTOM);

        int idx = Integer.parseInt(getIntent().getStringExtra("idx"));
        RecyclerView macti_4_lst_str_rv = findViewById(R.id.acti_4_lst_str_rv);

        List<Model4snglStr> lst = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            Model4snglStr mModel4snglStr = new Model4snglStr();
//            lstModel4snglStr.add(mModel4snglStr);
//        }
//
//        lstModel4snglStr.get(0).setD(0);
//        lstModel4snglStr.get(0).setN("days");
//        lstModel4snglStr.get(1).setD(1);
//        lstModel4snglStr.get(1).setN("hours");
//        lstModel4snglStr.get(2).setD(2);
//        lstModel4snglStr.get(2).setN("weeks");
//        lstModel4snglStr.get(3).setD(3);
//        lstModel4snglStr.get(3).setN("months");
//        lstModel4snglStr.get(4).setD(4);
//        lstModel4snglStr.get(4).setN("years");


        RecyclerViewClickListener listner = new RecyclerViewClickListener() {
            @Override
            public void doSomething(View v, Object obj, int position, int index) {
                //Toast.makeText(Acti4LstStr.this, "tosatla re tosatla", Toast.LENGTH_LONG).show();
            }
        };

        Adapter4lstStr mAdapter4lstStr = new Adapter4lstStr(Acti4LstStr.this, lst, listner, idx);
        macti_4_lst_str_rv.setAdapter(mAdapter4lstStr);
    }
}