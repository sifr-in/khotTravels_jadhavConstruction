package com.sifr.my.common;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sifr.my.R;

import java.net.URLEncoder;

import static android.content.Intent.ACTION_VIEW;

public class CommonChecks {
    private final String TAG = "sfr CommonChecks";
    private static final int PERMISSION_REQUEST_CODE = 200;
    Context context;
    boolean resultSave, resultAdd, snores;
    private String classnm = " DialogBoxs ";

    public CommonChecks(Context c) {
        // TODO constructor
        context = c;
    }

    public void messageToast(String msg) {
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        //toast.setGravity(Gravity.TOP,0, 0);
        View view = toast.getView();
        view.setBackgroundColor(Color.RED);

        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void alertToast(String msg) {
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        View view = toast.getView();
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void messageDialog(String title, String msg) {
        Dialog d = new Dialog(context);
        d.setTitle(title);
        TextView tv = new TextView(context);
        tv.setText(msg);
        d.setContentView(tv);
        d.show();
    }

    public void okDialog(String title, String msg) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        // Setting LoginWithMobAndCCode Title
        alertDialog.setTitle(title);

        // Setting LoginWithMobAndCCode Message
        alertDialog.setMessage(msg);

        // Setting Icon to LoginWithMobAndCCode
        alertDialog.setIcon(R.drawable.ic_dashboard_black_24dp);

        // Setting Negative "NO" Button
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to invoke NO event
                //Toast.makeText(context, "You clicked on NO", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

    public void reset(Class<?> curClass) {
        try {
            Intent myIntent = new Intent(context, curClass);
//            myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(myIntent);
        } catch (Exception e) {
            Toast.makeText(context, "" + e, Toast.LENGTH_LONG);
        }
    }

    public void goToActivity(Class<?> targetClass) {
        try {
            Intent myIntent = new Intent(context, targetClass);
            //myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            //myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(myIntent);
        } catch (Exception e) {
            okDialog("Exception", e.toString());
        }
    }

    public Boolean isOnline() {
        /*boolean reachable = false;
        try {
            Process p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.com");
//            Process p1 = Runtime.getRuntime().exec("ping -c 1 www.sifr.in");
            int returnVal = p1.waitFor();
            reachable = (returnVal == 0);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return reachable;*/

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }


    public boolean isAppInstalled(Context ctx, String packageName) {
        PackageManager pm = ctx.getPackageManager();
        boolean app_installed;
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }

    public void sendWhatsApp(Context ctx, String moNo, String defMsg, String webPageLink, String appPackage) {
        try {
            //"https://api.whatsapp.com/send?phone=" + callModel.getNumber().substring(1) + "&text=" + URLEncoder.encode(defMsg + "\n" + webPageLink, "UTF-8")
            //"https://wa.me/"+callModel.getNumber().substring(1)+"?text="+URLEncoder.encode(defMsg + "\n" + webPageLink, "UTF-8")
            //String url = "https://api.whatsapp.com/send?phone=" + callModel.getNumber().substring(1) + "&text=" + URLEncoder.encode(defMsg + "\n" + webPageLink, "UTF-8");

//                String mobileNo = moNo.replace("+", "");
            /*String url = "https://api.whatsapp.com/send?phone=" + moNo + "&text=" + URLEncoder.encode(defMsg + "\n" + webPageLink, "UTF-8");
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setPackage(appPackage);
            Log.e(TAG, "with " + appPackage + " whatsApped to " + url);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse(url));
//            intent.setDataAndType(Uri.parse(url),"text/plain");
            ctx.startActivity(intent);
            Log.e(TAG, "430 wa sent by " + appPackage + " to " + url);*/

//            https://wa.me/1XXXXXXXXXX?text=I'm%20interested%20in%20your%20car%20for%20sale
            /*String url = "https://wa.me/" + moNo + "?text=" + URLEncoder.encode(defMsg + "\n" + webPageLink, "UTF-8");
            Intent i = new Intent(Intent.ACTION_SEND, Uri.parse(url));
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ctx.startActivity(i);
            Log.e(TAG, "437 wa sent by " + appPackage + " to " + url);*/

            /*//mns shows send to screen if number not found
            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.setType("text/plain");
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hi, This is it.");
            sendIntent.putExtra("jid", "+"+moNo + "@s.whatsapp.net"); //phone number without "+" prefix
            sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            sendIntent.setPackage(appPackage);
            if (sendIntent.resolveActivity(ctx.getPackageManager()) == null) {
                Toast.makeText(ctx, "Error/n", Toast.LENGTH_SHORT).show();
                return;
            }
            Log.e(TAG, "461 wa sent by " + appPackage + " to ulr=" + "null");
            ctx.startActivity(sendIntent);*/


            /*//mns shows dialog if number not found
            //mns but text message is blank
            Uri mUri = Uri.parse("smsto:+"+moNo);
            Intent mIntent = new Intent(Intent.ACTION_SENDTO, mUri);
            mIntent.setPackage(appPackage);
//            mIntent.putExtra("sms_body", "The text goes here");
            mIntent.putExtra("sms_body", URLEncoder.encode(defMsg + "\n" + webPageLink, "UTF-8"));
            mIntent.putExtra("chat",true);
            mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ctx.startActivity(mIntent);*/

            /*//mns message not shown in whatsApp
            String url = "https://api.whatsapp.com/send?phone=" + moNo;
            try {
                PackageManager pm = ctx.getPackageManager();
                pm.getPackageInfo(appPackage, PackageManager.GET_ACTIVITIES);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ctx.startActivity(i);
                Log.e(TAG, "484 wa sent by " + appPackage + " to " + url);
            } catch (PackageManager.NameNotFoundException e) {
                Toast.makeText(ctx, "486 Whatsapp app not installed in your phone",Toast.LENGTH_LONG).show();
                Log.e(TAG, "487 wa sent by " + appPackage + " to " + url);
                e.printStackTrace();
            }*/


            /*String url = "https://api.whatsapp.com/send?phone=919999999999";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            Intent intent = Intent.createChooser(i, "open with");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Log.e(TAG, "497 wa sent by " + appPackage + " to " + url);
            ctx.startActivity(intent);*/


            /*//mns says no application found that can
            String url = "https://wa.me/" + moNo + "?text=" + URLEncoder.encode(defMsg + "\n" + webPageLink, "UTF-8");
            Intent i = new Intent(Intent.ACTION_SEND, Uri.parse(url));
            i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Intent intent = Intent.createChooser(i, "open with");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ctx.startActivity(intent);
            Log.e(TAG, "508 wa sent by without package to " + url);*/


            //mns says no application found that can
            String url = "https://wa.me/" + moNo + "?text=" + URLEncoder.encode(defMsg + "\n" + webPageLink, "UTF-8");
            Intent i = new Intent(ACTION_VIEW, Uri.parse(url));
            i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Intent intent = Intent.createChooser(i, "open with");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ctx.startActivity(intent);
            Log.e(TAG, "508 wa sent by without package to " + url);


            /*String url = "https://wa.me/" + moNo + "?text=" + URLEncoder.encode(defMsg + "\n" + webPageLink, "UTF-8");
            Intent intent = new Intent(Intent.ACTION_SEND, Uri.parse(url));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setPackage("com.android.chrome");
            try {
                Log.e(TAG, "456 com.android.chrome url=" + url);
                ctx.startActivity(intent);
            } catch (Exception e) {
                Log.e(TAG, "459 exception " + e.toString() + " url " + url);
                intent.setPackage(null);
                Intent inte = Intent.createChooser(intent, "select browser");
                inte.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ctx.startActivity(inte);
            }*/











            /*//https://web.whatsapp.com/send?phone=6200000000&text=Hello%20Moxqitto
            String url = "https://web.whatsapp.com/send?phone=" + moNo + "?text=" + URLEncoder.encode(defMsg + "\n" + webPageLink, "UTF-8");
//            String url = "https://wa.me/" + moNo + "?text=" + URLEncoder.encode(defMsg + "\n" + webPageLink, "UTF-8");
            Intent browserIntent = new Intent(Intent.ACTION_SEND, Uri.parse("http://"));
            ResolveInfo resolveInfo = ctx.getPackageManager().resolveActivity(browserIntent, PackageManager.MATCH_DEFAULT_ONLY);
            String pkgName = resolveInfo.activityInfo.packageName;
            Log.e(TAG, "471 wa sent by " + pkgName + " to " + url);
            // Use the explicit browser package name
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            i.setPackage(pkgName);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ctx.startActivity(i);*/



            /*String url = "https://wa.me/" + moNo + "?text=" + URLEncoder.encode(defMsg + "\n" + webPageLink, "UTF-8");
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "try this fantastic app");
            shareIntent.putExtra(Intent.EXTRA_TEXT,  url);
            Intent inte = Intent.createChooser(shareIntent, "select browser");
            inte.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ctx.startActivity(inte);*/






/*//            String url = "https://www.google.com/";
            String url = "https://wa.me/" + moNo + "?text=" + URLEncoder.encode(defMsg + "\n" + webPageLink, "UTF-8");
            String query = Uri.encode(url, "UTF-8");
            Intent browserIntent = new Intent(CATEGORY_BROWSABLE, Uri.parse(Uri.decode(query)));
            browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            browserIntent.setPackage("com.android.chrome");
            browserIntent.setAction(ACTION_VIEW);
            ctx.startActivity(browserIntent);
            Log.e(TAG, "444 wa sent by " + appPackage + " to " + url);*/

//            whatsapp://send/?phone=&text&source&data
            /*String url = "whatsapp://send/?phone=" + moNo + "?text=" + URLEncoder.encode(defMsg + "\n" + webPageLink, "UTF-8");
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ctx.startActivity(i);
            Log.e(TAG, "451 wa sent by " + appPackage + " to " + url);*/

            /*Intent sendIntent = new Intent("android.intent.action.MAIN");
            sendIntent.setComponent(new ComponentName(appPackage, "com.whatsapp.Conversation"));
            sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Log.e(TAG, "non API " + appPackage + " whatsApped to " + url);
            sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(moNo) + "@s.whatsapp.net");
            intent.setDataAndType(Uri.parse(url),"text/plain");
//            sendIntent.setData(Uri.parse(url));
            ctx.startActivity(sendIntent);
            Log.e(TAG, "461 wa sent by " + appPackage + " to " + url);*/

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ctx, "459 errrrrrrrror sending whatsApp:" + e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e(TAG, e.getMessage());
        }
    }

    public String getMeaTXTfromCode(String b) {
        int i = 0;
        try {
            i = Integer.parseInt(b);
        } catch (Exception e) {
            Log.d(TAG, "338 e=" + e.toString());
        }
        String ret = null;
        switch (i) {
            case 1:
                ret = "hours";
                break;

            case 2:
                ret = "week";
                break;

            case 3:
                ret = "month";
                break;

            case 4:
                ret = "year";
                break;

            default:
                ret = "days";
                break;
        }
        return ret;
    }
}