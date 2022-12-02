package com.sifr.my;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.textfield.TextInputEditText;
import com.sifr.my.retrofit.ModelSerProdJob;

import java.util.ArrayList;
import java.util.List;

import static com.sifr.my.ShoPSJClist.mact_psjc_lst_tv_cnt;

public class AdapterForSPJCLst extends RecyclerView.Adapter<AdapterForSPJCLst.ViewHolder> {
    private final String TAG = "sfr AdapterForSPJCLst";
    private Activity acti;
    private View listItem;
    private List<ModelSerProdJob> adptorModelLstSerProdJob;
    public static List<ModelSerProdJob> seltdAdptorModelLstSerProdJob;

    public AdapterForSPJCLst(Activity activityContext, List<ModelSerProdJob> paramLst) {
        this.adptorModelLstSerProdJob = paramLst;
        this.acti = activityContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        listItem = layoutInflater.inflate(R.layout.layout_psjc_item, parent, false);
        return new AdapterForSPJCLst.ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.mlay_menu_itm_nm.setText(adptorModelLstSerProdJob.get(position).getN());
        holder.mlay_menu_itm_h.setText(adptorModelLstSerProdJob.get(position).getH());
        holder.mlay_menu_itm_h.setPaintFlags(holder.mlay_menu_itm_h.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.mlay_menu_itm_ta.setText(adptorModelLstSerProdJob.get(position).getTa());
        holder.mlay_menu_itm_up.setText(adptorModelLstSerProdJob.get(position).getUp());
        holder.mlay_menu_itm_me.setText(adptorModelLstSerProdJob.get(position).getLm() + " in " + adptorModelLstSerProdJob.get(position).getMe());
        holder.mlay_menu_itm_descr.setText(adptorModelLstSerProdJob.get(position).getDc());
        holder.mlay_menu_itm_descr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FullTextActivity.class);
                intent.putExtra("param_txt", adptorModelLstSerProdJob.get(position).getDc());
                v.getContext().startActivity(intent);
            }
        });
        Glide.with(listItem.getContext())
                .load("https://sifr.in/" + adptorModelLstSerProdJob.get(position).getC())
                .apply(new RequestOptions().override(200, 200))
                .into(holder.mlay_menu_itm_img);
        holder.mlay_menu_itm_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                funcChooseImgFile2Upload();
            }
        });
        holder.mlay_menu_btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float prsntQuantity = 0;
                if (holder.mlay_menu_tx_q.getText().toString().trim().length() > 0)
                    prsntQuantity = Float.parseFloat(holder.mlay_menu_tx_q.getText().toString().trim());
                if (prsntQuantity > 1) {
                    holder.mlay_menu_tx_q.setText(String.valueOf(prsntQuantity - 1));
                    holder.mlay_menu_tx_tot.setText(String.valueOf((prsntQuantity - 1) * Float.parseFloat(adptorModelLstSerProdJob.get(position).getTa())));
                    adptorModelLstSerProdJob.get(position).setSeltdSPJ(true);
                } else {
                    holder.mlay_menu_tx_q.setText(null);
                    holder.mlay_menu_tx_tot.setText(null);
                    adptorModelLstSerProdJob.get(position).setSeltdSPJ(false);
                }
                int cnt = countSelected();
//                Toast.makeText(acti, "Total selected items=" + cnt, Toast.LENGTH_LONG).show();
                mact_psjc_lst_tv_cnt.setText(String.valueOf(cnt));
                adptorModelLstSerProdJob.get(position).setQty(cnt);
            }
        });
        holder.mlay_menu_btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float prsntQuantity = 0;
                if (holder.mlay_menu_tx_q.getText().toString().trim().length() > 0)
                    prsntQuantity = Float.parseFloat(holder.mlay_menu_tx_q.getText().toString().trim());

                holder.mlay_menu_tx_q.setText(String.valueOf(prsntQuantity + 1));
                holder.mlay_menu_tx_tot.setText(String.valueOf((prsntQuantity + 1) * Float.parseFloat(adptorModelLstSerProdJob.get(position).getTa())));
                adptorModelLstSerProdJob.get(position).setSeltdSPJ(true);
                int cnt = countSelected();
//                Toast.makeText(acti, "Total selected items=" + cnt, Toast.LENGTH_LONG).show();
                mact_psjc_lst_tv_cnt.setText(String.valueOf(cnt));
                adptorModelLstSerProdJob.get(position).setQty(cnt);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    private int countSelected() {
        seltdAdptorModelLstSerProdJob = new ArrayList<>();
        for (int i = 0; i < adptorModelLstSerProdJob.size(); i++) {
            if (adptorModelLstSerProdJob.get(i).isSeltdSPJ())
                seltdAdptorModelLstSerProdJob.add(adptorModelLstSerProdJob.get(i));
        }
        return seltdAdptorModelLstSerProdJob.size();
    }

    @Override
    public int getItemCount() {
        return adptorModelLstSerProdJob.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mlay_menu_itm_nm, mlay_menu_itm_h, mlay_menu_itm_ta, mlay_menu_itm_descr, mlay_menu_itm_up, mlay_menu_itm_me, mlay_menu_tx_tot;
        private ImageView mlay_menu_itm_img, mlay_menu_btn_sub, mlay_menu_btn_add;
        private TextInputEditText mlay_menu_tx_q;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mlay_menu_itm_img = itemView.findViewById(R.id.lay_menu_itm_img);
            this.mlay_menu_itm_nm = itemView.findViewById(R.id.lay_menu_itm_nm);
            this.mlay_menu_itm_h = itemView.findViewById(R.id.lay_menu_itm_h);
            this.mlay_menu_itm_ta = itemView.findViewById(R.id.lay_menu_itm_ta);
            this.mlay_menu_itm_me = itemView.findViewById(R.id.lay_menu_itm_me);
            this.mlay_menu_itm_up = itemView.findViewById(R.id.lay_menu_itm_up);
            this.mlay_menu_itm_descr = itemView.findViewById(R.id.lay_menu_itm_descr);
            this.mlay_menu_tx_tot = itemView.findViewById(R.id.lay_menu_tx_tot);
            this.mlay_menu_btn_sub = itemView.findViewById(R.id.lay_menu_btn_sub);
            this.mlay_menu_tx_q = itemView.findViewById(R.id.lay_menu_tx_q);
            this.mlay_menu_btn_add = itemView.findViewById(R.id.lay_menu_btn_add);
        }
    }

    private void funcChooseImgFile2Upload() {
        Toast.makeText(acti, "multiple images of this item will be shown.", Toast.LENGTH_LONG).show();
        /*
        ImageView imgbtGallery, imgbtCamera;
        // custom dialog
        final android.app.Dialog dialog = new Dialog(acti);
        dialog.setContentView(R.layout.dialog_choose_photo_or_take_photo);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);

        // set the custom dialog components - text, image and button
        imgbtGallery = dialog.findViewById(R.id.dg_galry_or_camera_iv_galry);
        imgbtCamera = dialog.findViewById(R.id.dg_galry_or_camera_iv_camera);

        imgbtCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                //image_uri = generateTimeStampPhotoFileUri(AddEventTagActivity.this);
                if (checkPermission()) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    acti.startActivityForResult(intent, REQUEST_CAMERA);
                } else {
                    Toast.makeText(acti, "Allow the app to access camera.", Toast.LENGTH_LONG).show();
                    requestPermission();
                    if (checkPermission()) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        acti.startActivityForResult(intent, REQUEST_CAMERA);
                    }
                }
            }
        });
        imgbtGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

//                Intent intent = new Intent(
//                        Intent.ACTION_PICK,
//                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(intent, REQUEST_FILES_IMAGES);

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                acti.startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_FILES_IMAGES);


//                Intent i = new Intent();
//                i.setType("image/*");
//                i.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(i, "Select Picture"), REQUEST_FILES_IMAGES);
            }
        });
        dialog.show();
    */
    }

    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(acti, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        return true;
    }

    private void requestPermission() {/*
        ActivityCompat.requestPermissions(acti,
                new String[]{Manifest.permission.CAMERA},
                PERMISSION_REQUEST_CODE);
    */
    }

}
