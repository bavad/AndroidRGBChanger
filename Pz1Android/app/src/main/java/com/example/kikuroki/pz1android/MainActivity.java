package com.example.kikuroki.pz1android;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, LinearLayout.OnLayoutChangeListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar seekBar = findViewById(R.id.seekBar1);
        seekBar.setOnSeekBarChangeListener(this);

        seekBar = findViewById(R.id.seekBar2);
        seekBar.setOnSeekBarChangeListener(this);

        seekBar = findViewById(R.id.seekBar3);
        seekBar.setOnSeekBarChangeListener(this);

        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        linearLayout.addOnLayoutChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        SeekBar seekBar1 = findViewById(R.id.seekBar1);
        String hexProgress1 = Integer.toHexString(seekBar1.getProgress() / 16) + Integer.toHexString(seekBar1.getProgress() % 16);

        SeekBar seekBar2 = findViewById(R.id.seekBar2);
        String hexProgress2 = Integer.toHexString(seekBar2.getProgress()/16) + Integer.toHexString(seekBar2.getProgress() % 16);

        SeekBar seekBar3 = findViewById(R.id.seekBar3);
        String hexProgress3 = Integer.toHexString(seekBar3.getProgress() / 16) + Integer.toHexString(seekBar3.getProgress() % 16);

        View view = findViewById(R.id.view);
        String color =  "#" + hexProgress1 + hexProgress2 + hexProgress3;
        color.toUpperCase();
        view.setBackgroundColor(Color.parseColor(color));
        view.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom,
                               int leftWas, int topWas, int rightWas, int bottomWas ){
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int rotation = display.getRotation();

        if(rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180){
            LinearLayout linearLayout = findViewById(R.id.linearLayout);
            View view = findViewById(R.id.view);

            int viewHeight = linearLayout.getHeight() * 2 / 3;
            view.getLayoutParams().height = viewHeight;
            view.requestLayout();

            int seekBarHeight = (linearLayout.getHeight() - viewHeight) / 3;

            SeekBar seekBar = findViewById(R.id.seekBar1);
            seekBar.getLayoutParams().height = seekBarHeight;
            seekBar.requestLayout();

            seekBar = findViewById(R.id.seekBar2);
            seekBar.getLayoutParams().height = seekBarHeight;
            seekBar.requestLayout();

            seekBar = findViewById(R.id.seekBar3);
            seekBar.getLayoutParams().height = seekBarHeight;
            seekBar.requestLayout();
        }
        else
        {
            LinearLayout linearLayout = findViewById(R.id.linearLayout);
            View view = findViewById(R.id.view);

            int viewWidth = linearLayout.getWidth() * 2 / 3;
            view.getLayoutParams().width = viewWidth;
            view.requestLayout();

            int seekBarHeight = linearLayout.getHeight() / 3;

            SeekBar seekBar = findViewById(R.id.seekBar1);
            seekBar.getLayoutParams().height = seekBarHeight;
            seekBar.requestLayout();

            seekBar = findViewById(R.id.seekBar2);
            seekBar.getLayoutParams().height = seekBarHeight;
            seekBar.requestLayout();

            seekBar = findViewById(R.id.seekBar3);
            seekBar.getLayoutParams().height = seekBarHeight;
            seekBar.requestLayout();
        }
    }
}
