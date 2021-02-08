package com.example.splashscreen;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

public class Services extends AppCompatActivity {
    private ViewFlipper flipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        int images[] = {R.drawable.vc, R.drawable.vct, R.drawable.images, R.drawable.oavct_tabarre_2};
        flipper = (ViewFlipper) findViewById(R.id.viewFliper);

        for (int image: images){
            flipers(image);
        }
    }

    public void flipers(int images){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(images);
        flipper.addView(imageView);
        flipper.setFlipInterval(4000);
        flipper.setAutoStart(true);

        flipper.setInAnimation(this, android.R.anim.slide_in_left);
        flipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }
}