package com.sifr.my;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.sifr.my.common.CommonChecks;
import com.sifr.my.retrofit.Model4lstXp;
import com.sifr.my.retrofit.Model4snglXp;
import com.sifr.my.retrofit.Model4successEid;
import com.sifr.my.retrofit.RetrofitClient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sifr.my.Adapter4lstSites.selectedModel4snglSite;
import static com.sifr.my.Adapter4lstXpsNms.selectedModel4snglXp;
import static com.sifr.my.Whom2Work4.model4MXonly;

public class Activity2crudXP extends AppCompatActivity {
    private final String TAG = "sfr Activity2crudXP";
    private DatePickerDialog mXpDatePicker;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private String dtXpChosn;
    private PopupWindow mPopupWindow;
    private LinearLayout mact_2crud_xp_lao;
    private RecyclerView mact_2crud_xp_ti_rv;
    private TextInputEditText mact_2crud_xp_ti_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2crud_xp);
        TextView mact_2crud_xp_tv_dt = findViewById(R.id.act_2crud_xp_tv_dt);
        TextView mact_2crud_xp_tv_tit = findViewById(R.id.act_2crud_xp_tv_tit);
        mact_2crud_xp_ti_tv = findViewById(R.id.act_2crud_xp_ti_tv);
        mact_2crud_xp_tv_tit.setText(selectedModel4snglSite.getN());
        TextInputEditText mact_2crud_xp_ti_q = findViewById(R.id.act_2crud_xp_ti_q);
        ImageView mact_2crud_xp_iv = findViewById(R.id.act_2crud_xp_iv);
        ImageView mact_2crud_xp_iv_sho = findViewById(R.id.act_2crud_xp_iv_sho);
        mact_2crud_xp_ti_rv = findViewById(R.id.act_2crud_xp_ti_rv);
        mact_2crud_xp_lao = findViewById(R.id.act_2crud_xp_lao);

        mact_2crud_xp_tv_tit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonShowPopupWindowClick();
//                showLstPopup();
            }
        });

        mact_2crud_xp_tv_dt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                mXpDatePicker = new DatePickerDialog(Activity2crudXP.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                String strMnth = "", strDay = "";

                                if ((monthOfYear + 1) < 10)
                                    strMnth = ("0" + (monthOfYear + 1));
                                else
                                    strMnth = (String.valueOf(monthOfYear + 1));

                                if ((dayOfMonth) < 10)
                                    strDay = ("0" + (dayOfMonth));
                                else
                                    strDay = (String.valueOf(dayOfMonth));

                                mact_2crud_xp_tv_dt.setText(year + "-" + strMnth + "-" + strDay);
                                dtXpChosn = year + "-" + strMnth + "-" + strDay;
                                getXpLst();
                            }
                        }, year, month, day);
                mXpDatePicker.show();
            }
        });

        mact_2crud_xp_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dtXpChosn != null && dtXpChosn.length() == 10) {
                    if (mact_2crud_xp_ti_q.getText().toString().trim().length() > 0 && Float.parseFloat(mact_2crud_xp_ti_q.getText().toString().trim()) > 0) {
                        if (mact_2crud_xp_ti_tv.getText().toString().trim().length() > 4) {
                            final Dialog dialog = new Dialog(Activity2crudXP.this);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setContentView(R.layout.layout_loader);
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                            dialog.show();
                            dialog.setCancelable(false);

                            Model4snglXp pModel4snglXp = new Model4snglXp();
                            pModel4snglXp.setMx(model4MXonly);
                            pModel4snglXp.setA(dtXpChosn);
                            pModel4snglXp.setN(mact_2crud_xp_ti_tv.getText().toString());
                            pModel4snglXp.setC(selectedModel4snglSite.getD());
                            pModel4snglXp.setG(Float.parseFloat(mact_2crud_xp_ti_q.getText().toString()));

                            CommonChecks commonChks = new CommonChecks(getApplicationContext());
                            if (commonChks.isOnline()) {
                                Call<Model4successEid> call = RetrofitClient.getClient().retroInterfAddXp(pModel4snglXp);

                                call.enqueue(new Callback<Model4successEid>() {
                                    @Override
                                    public void onResponse(Call<Model4successEid> call, Response<Model4successEid> response) {
                                        dialog.dismiss();
                                        if (response.body() != null) {
                                            Log.d(TAG, "122 lu_rcvd=" + response.body().toString());
                                            if (response.isSuccessful()) {
                                                if (response.body().getSuc()) {
                                                    Toast.makeText(Activity2crudXP.this, "expenditure was updated successfully. " + response.body().getMsg(), Toast.LENGTH_LONG).show();
                                                } else {
                                                    Toast.makeText(Activity2crudXP.this, response.body().getMsg(), Toast.LENGTH_LONG).show();
                                                }
                                            } else {
                                                if (response.body().getMsg() != null) {
                                                    Toast.makeText(Activity2crudXP.this, "There was an error. " + response.body().getMsg(), Toast.LENGTH_LONG).show();
                                                } else {
                                                    Toast.makeText(Activity2crudXP.this, "Server error. ", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        } else {
                                            Toast.makeText(Activity2crudXP.this, "Unable to get response. Contact administrator.", Toast.LENGTH_LONG).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Model4successEid> call, Throwable t) {
                                        dialog.dismiss();
                                        Log.d(TAG, "130 t=" + t.toString());
                                    }
                                });
                            } else {
                                Toast.makeText(Activity2crudXP.this, "Active internet connection is required.", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(Activity2crudXP.this, "expenditure name must be atleast 4 chars", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(Activity2crudXP.this, "quantity must be greater than 0", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Activity2crudXP.this, "date must be selected", Toast.LENGTH_LONG).show();
                }
            }
        });

        mact_2crud_xp_iv_sho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activity2crudXP.this, Acti4expensesLst.class);
                int inBtnID = getIntent().getIntExtra("inBtnID", 0);
                i.putExtra("inBtnID", inBtnID);
                startActivity(i);
            }
        });
    }

    private void onButtonShowPopupWindowClick() {
        Point size = new Point();
        int width = size.x;
        int height = size.y;

        // Initialize a new instance of LayoutInflater service
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);

        // Inflate the custom layout/view
        View customView = inflater.inflate(R.layout.popup_window, null);

                /*
                    public PopupWindow (View contentView, int width, int height)
                        Create a new non focusable popup window which can display the contentView.
                        The dimension of the window must be passed to this constructor.

                        The popup does not provide any background. This should be handled by
                        the content view.

                    Parameters
                        contentView : the popup's content
                        width : the popup's width
                        height : the popup's height
                */
        // Initialize a new instance of popup window
        mPopupWindow = new PopupWindow(
                customView,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        mPopupWindow.setWidth(width - 20);
        mPopupWindow.setHeight(height - 20);

        // Set an elevation value for popup window
        // Call requires API level 21
        if (Build.VERSION.SDK_INT >= 21) {
            mPopupWindow.setElevation(5.0f);
        }

        // Get a reference for the custom view close button
        ImageView closeButton = customView.findViewById(R.id.pop_win_iv);

        // Set a click listener for the popup window close button
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dismiss the popup window
                mPopupWindow.dismiss();
            }
        });

        /*
            public void showAtLocation (View parent, int gravity, int x, int y)
                Display the content view in a popup window at the specified location. If the
                popup window cannot fit on screen, it will be clipped.
                Learn WindowManager.LayoutParams for more information on how gravity and the x
                and y parameters are related. Specifying a gravity of NO_GRAVITY is similar
                to specifying Gravity.LEFT | Gravity.TOP.

            Parameters
                parent : a parent view to get the getWindowToken() token from
                gravity : the gravity which controls the placement of the popup window
                x : the popup's x location offset
                y : the popup's y location offset
        */
        // Finally, show the popup window at the center location of root relative layout
        mPopupWindow.showAtLocation(mact_2crud_xp_lao, Gravity.CENTER, 0, 0);
    }

    private void showLstPopup() {

        ArrayList<String> alist = new ArrayList<String>();
        alist.add("Steve");
        alist.add("Tim");
        alist.add("Lucy");
        alist.add("Pat");
        alist.add("Angela");
        alist.add("Tom");

        ListPopupWindow window;
        window = new ListPopupWindow(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.delete, alist);
        /* use ur custom layout which has only TextView along with style required*/
        window.setAdapter(adapter);
        window.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        window.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        //window.getListView();
        window.setModal(false);
        window.setAnchorView(mact_2crud_xp_lao);/*it will be the overflow view of yours*/
        window.setHorizontalOffset(-430);
        window.setContentWidth(500);/* set width based on ur requirement*/
        window.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        window.show();
    }

    private void getXpLst() {
        CommonChecks commonChks = new CommonChecks(getApplicationContext());
        if (commonChks.isOnline()) {
            final Dialog dialog = new Dialog(Activity2crudXP.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.layout_loader);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.show();
            dialog.setCancelable(false);
            model4MXonly.setIxd(selectedModel4snglSite.getD());
            model4MXonly.setStr(dtXpChosn);

            Call<Model4lstXp> call = RetrofitClient.getClient().retroInterfGetLstXpQty(model4MXonly);

            call.enqueue(new Callback<Model4lstXp>() {
                @Override
                public void onResponse(Call<Model4lstXp> call, Response<Model4lstXp> response) {
                    dialog.dismiss();
                    if (response.body() != null) {
                        Log.d(TAG, "65 lu_rcvd=" + response.body().toString());
                        if (response.isSuccessful()) {
                            if (response.body().getSuc()) {
                                Log.d(TAG, "68 lu_rcvd=");
                                Adapter4lstQtyXps mAdapter4lstQtyXps = new Adapter4lstQtyXps(Activity2crudXP.this, response.body().getLst1());
                                mact_2crud_xp_ti_rv.setAdapter(mAdapter4lstQtyXps);
                            } else {
                                Toast.makeText(Activity2crudXP.this, response.body().getMsg(), Toast.LENGTH_LONG).show();
                            }
                        } else {
                            if (response.body().getMsg() != null) {
                                Toast.makeText(Activity2crudXP.this, "There was an error. " + response.body().getMsg(), Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(Activity2crudXP.this, "Server error. ", Toast.LENGTH_LONG).show();
                            }
                        }
                    } else {
                        Toast.makeText(Activity2crudXP.this, "Unable to get response. Contact administrator.", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Model4lstXp> call, Throwable t) {
                    dialog.dismiss();
                    Log.d(TAG, "89 t=" + t.toString());
                }
            });
        } else {
            Toast.makeText(Activity2crudXP.this, "Active internet connection is required.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (selectedModel4snglXp != null) {
            mact_2crud_xp_ti_tv.setText(selectedModel4snglXp.getN());
        }
    }
}