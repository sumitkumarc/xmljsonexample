package com.skyzone18.Raghukulvidyalay.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.skyzone18.Raghukulvidyalay.R;

public class SlashActvity extends AppCompatActivity {
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_slash);
       // Logocopyanimation();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SlashActvity.this,MainActivity.class));
                finish();
            }
        },3000);
    }
//    private void Logocopyanimation() {
//        Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim);
//        anim = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
//        anim.reset();
//        image = (ImageView) findViewById(R.id.image);
//        try {
//            image.clearAnimation();
//            image.startAnimation(anim);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
