package com.sifr.my;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
   private final String TAG = "sfr Validation";

    public static boolean hasTextLength(EditText editText, String errStr, int i, Boolean shoErrMsg, Boolean correctORblank, Context ctx) {
        //e.g. if correctORblank true phone number can be blank

        String text = editText.getText().toString().trim();
        editText.setError(null);

        if (correctORblank) {
            // length 0 means there is no text
            if (text.length() == 0) {
                return true;
            } else {
                if (text.length() == (i)) {
                    return true;
                } else {
                    editText.requestFocus();
                    if (shoErrMsg) {
                        openSnackBar(editText, errStr + " mus be minimum " + i + " characters", ctx);
                        editText.setError(errStr + " mus be minimum " + i + " characters");
                    }
                    return false;
                }
            }
        } else {
            // length 0 means there is no text
            if (text.length() < (i)) {
                editText.requestFocus();
                if (shoErrMsg) {
                    openSnackBar(editText, errStr + " mus be minimum " + i + " characters", ctx);
                    editText.setError(errStr + " mus be minimum " + i + " characters");
                }
                return false;
            } else
                return true;
        }
    }


    public static boolean hasMinLenEfrmat(EditText editText, String errStr, int i, Boolean shoErrMsg,
                                          Boolean correctORblank, Context ctx, String formatRegx) {
        //e.g. if correctORblank true phone number can be blank
        boolean ret;
        String text = editText.getText().toString().trim();
        editText.setError(null);

        if (correctORblank) {
            // length 0 means there is no text
            if (text.length() == 0) {
                ret = true;
            } else {
                if (text.length() >= (i)) {
                    if (isTextInFormat(text, formatRegx)) {
                        ret = true;
                    } else {
                        editText.requestFocus();
                        if (shoErrMsg) {
                            openSnackBar(editText, errStr + " format not correct.", ctx);
                            editText.setError(errStr + "  format not correct.");
                        }
                        ret = false;
                    }
                } else {
                    editText.requestFocus();
                    if (shoErrMsg) {
                        openSnackBar(editText, errStr + " mus be minimum " + i + " characters", ctx);
                        editText.setError(errStr + " mus be minimum " + i + " characters");
                    }
                    ret = false;
                }
            }
        } else {
            // length 0 means there is no text
            if (text.length() < (i)) {
                editText.requestFocus();
                if (shoErrMsg) {
                    openSnackBar(editText, errStr + " mus be minimum " + i + " characters", ctx);
                    editText.setError(errStr + " mus be minimum " + i + " characters");
                }
                ret = false;
            } else {

                if (isTextInFormat(text, formatRegx)) {
                    ret = true;
                } else {
                    editText.requestFocus();
                    if (shoErrMsg) {
                        openSnackBar(editText, errStr + " format not correct.", ctx);
                        editText.setError(errStr + "  format not correct.");
                    }
                    ret = false;
                }
            }
        }
        return ret;
    }

    public static boolean hasNumberInIt(TextInputEditText txtInpEdTxt, String errStr, Boolean shoErrMsg, Context ctx) {
        String regex = "\\D{2,25}";//any character, except number
        String txt = txtInpEdTxt.getText().toString();
        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the string is empty
        // return false
        if (txt == null) {
            openSnackBar(txtInpEdTxt, errStr + " has number in it, which is not allowed.", ctx);
            txtInpEdTxt.requestFocus();
            if (shoErrMsg) {
                txtInpEdTxt.setError(errStr + " has number in it, which is not allowed.");
            }
            return false;
        }

        // Pattern class contains matcher()
        // method to find the matching
        // between the given string
        // and the regular expression.
        Matcher m = p.matcher(txt);

        // Return if the string
        // matched the ReGex
        if (!m.matches()) {
            openSnackBar(txtInpEdTxt, errStr + " has number in it, which is not allowed.", ctx);
            txtInpEdTxt.requestFocus();
            if (shoErrMsg) {
                txtInpEdTxt.setError(errStr + " has number in it, which is not allowed.");
            }
            return false;
        }
        return m.matches();
    }


    public static void openSnackBar(View view, String errMessage, Context context) {
        Snackbar snak = Snackbar.make(view, errMessage, Snackbar.LENGTH_LONG);
        View snackBarView = snak.getView();
        TextView textView = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setTextSize(14.0f);
        snackBarView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        snak.show();
//        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }

    public static boolean isTextInGivenFormat(TextInputEditText txtInpEdTxt, String errStr, Boolean shoErrMsg, Context ctx, String formatRegx) {
        String txt = Objects.requireNonNull(txtInpEdTxt.getText()).toString();
        // Compile the ReGex
        Pattern p = Pattern.compile(formatRegx);

        // Pattern class contains matcher()
        // method to find the matching
        // between the given string
        // and the regular expression.
        Matcher m = p.matcher(txt);

        // Return if the string
        // matched the ReGex
        if (!m.matches()) {
            //openSnackBar(txtInpEdTxt, errStr + " has number in it, which is not allowed.", ctx);
            txtInpEdTxt.requestFocus();
            if (shoErrMsg) {
                txtInpEdTxt.setError(errStr + " has number in it, which is not allowed.");
            }
            return false;
        }
        return m.matches();
    }


    public static boolean isTextInFormat(String str, String formatRegx) {
        String txt = Objects.requireNonNull(str);
        Pattern p = Pattern.compile(formatRegx);
        Matcher m = p.matcher(txt);
        if (!m.matches()) {
            return false;
        }
        return m.matches();
    }
}
