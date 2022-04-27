package com.example.hkinfosoft.Util;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.example.hkinfosoft.R;

public class CustomProgressDialog {
    private Context c;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    private ImageView progress_image;
    public AlertDialog showCustomProgressDialog() {

        builder = new AlertDialog.Builder(c, R.style.CustomAlertProgresssDialog);
        alertDialog = builder.create();
        //alertDialog.setCancelable(false);

        //set custom view
        View v = LayoutInflater.from(c).inflate(R.layout.progress_dailog_layout,null);
        alertDialog.setView(v);

        //bind controls
         progress_image= v.findViewById(R.id.progress_image);

        //rotate animation
        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f,          Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(1300);
        rotate.setRepeatCount(Animation.INFINITE);
        rotate.setInterpolator(new LinearInterpolator());
        progress_image.setAnimation(rotate);

        //show dialog
        alertDialog.show();

        //set width high dialog
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(alertDialog.getWindow().getAttributes());
        layoutParams.width = 350;
        layoutParams.height = 270;
        alertDialog.getWindow().setAttributes(layoutParams);
        return alertDialog;
    }

    public void dismissCustomProgressDialog()
    {
        // Glide.with(c).load(R.color.white).into(progress_image);
        alertDialog.dismiss();
    }
    public CustomProgressDialog(Context context) {
        c = context;
    }
}
