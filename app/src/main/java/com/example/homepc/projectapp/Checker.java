package com.example.homepc.projectapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.graphics.PorterDuff;

import org.w3c.dom.Text;

public class Checker extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5,t6;
    ProgressBar p1,p2,p3,p4,p5;
    CardView c1,c2,c3,c4,c5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_main);


        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_action_name);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setTitle("Dashboard");

        final String s = getIntent().getStringExtra("device_id");
        t6 = (TextView) findViewById(R.id.device);
        t6.setText(s);

        final float watertemp = 100 - Float.parseFloat(getIntent().getStringExtra("WaterTemperature")) * 100;
        final float turbidity = 100 - Float.parseFloat(getIntent().getStringExtra("Turbidity")) * 100;
        final float waveheight = 100 - Float.parseFloat(getIntent().getStringExtra("WaveHeight")) * 100;
        final float waveperiod = 100 - Float.parseFloat(getIntent().getStringExtra("WavePeriod")) * 100;
        final float battery = 100 - Float.parseFloat(getIntent().getStringExtra("BatteryLife")) * 100;


        final float watertemp_th = 100 - Float.parseFloat(getIntent().getStringExtra("WaterTemperature_th")) * 100;
        final float turbidity_th = 100 - Float.parseFloat(getIntent().getStringExtra("Turbidity_th")) * 100;
        final float waveheight_th = 100 - Float.parseFloat(getIntent().getStringExtra("WaveHeight_th")) * 100;
        final float waveperiod_th = 100 - Float.parseFloat(getIntent().getStringExtra("WavePeriod_th")) * 100;
        final float battery_th = 100 - Float.parseFloat(getIntent().getStringExtra("BatteryLife_th")) * 100;

        System.out.println(watertemp);
        System.out.println(watertemp_th);

        p1 = (ProgressBar) findViewById(R.id.progressBar1);
        p2 = (ProgressBar) findViewById(R.id.progressBar2);
        p3 = (ProgressBar) findViewById(R.id.progressBar3);
        p4 = (ProgressBar) findViewById(R.id.progressBar4);
        p5 = (ProgressBar) findViewById(R.id.progressBar5);

        t1 = (TextView) findViewById(R.id.myTextProgress1);
        t2 = (TextView) findViewById(R.id.myTextProgress2);
        t3 = (TextView) findViewById(R.id.myTextProgress3);
        t4 = (TextView) findViewById(R.id.myTextProgress4);
        t5 = (TextView) findViewById(R.id.myTextProgress5);

        t1.setText(String.format("%.2f", watertemp));
        t2.setText(String.format("%.2f", turbidity));
        t3.setText(String.format("%.2f", waveheight));
        t4.setText(String.format("%.2f", waveperiod));
        t5.setText(String.format("%.2f", battery));

        p1.setProgress((int)watertemp);
        p2.setProgress((int)turbidity);
        p3.setProgress((int)waveheight);
        p4.setProgress((int)waveperiod);
        p5.setProgress((int)battery);

        c1 = (CardView) findViewById(R.id.card1);
        c2 = (CardView) findViewById(R.id.card2);
        c3 = (CardView) findViewById(R.id.card3);
        c4 = (CardView) findViewById(R.id.card4);
        c5 = (CardView) findViewById(R.id.card5);

        if(watertemp >= watertemp_th)
            c1.setCardBackgroundColor(Color.rgb(45, 171, 11));
        else {
            c1.setCardBackgroundColor(Color.rgb(255,140,3));
            p1.getProgressDrawable().setColorFilter(Color.parseColor("#E10A00"), PorterDuff.Mode.SRC_IN);
        }

        if(turbidity >= turbidity_th)
            c2.setCardBackgroundColor(Color.rgb(45, 171, 11));
        else {
            c2.setCardBackgroundColor(Color.rgb(255,140,3));
            p2.getProgressDrawable().setColorFilter(Color.parseColor("#E10A00"), PorterDuff.Mode.SRC_IN);
        }

        if(waveheight >= waveheight_th)
            c3.setCardBackgroundColor(Color.rgb(45, 171, 11));
        else {
            c3.setCardBackgroundColor(Color.rgb(255,140,3));
            p3.getProgressDrawable().setColorFilter(Color.parseColor("#E10A00"), PorterDuff.Mode.SRC_IN);
        }

        if(waveperiod >= waveperiod_th)
            c4.setCardBackgroundColor(Color.rgb(45, 171, 11));
        else {
            c4.setCardBackgroundColor(Color.rgb(255,140,3));
            p4.getProgressDrawable().setColorFilter(Color.parseColor("#E10A00"), PorterDuff.Mode.SRC_IN);
        }

        if(battery >= battery_th)
            c5.setCardBackgroundColor(Color.rgb(45, 171, 11));
        else {
            c5.setCardBackgroundColor(Color.rgb(255,140,3));
            p5.getProgressDrawable().setColorFilter(Color.parseColor("#E10A00"), PorterDuff.Mode.SRC_IN);
        }

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("You clicked card 1");
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("You clicked card 2");
            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("You clicked card 3");
            }
        });

        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("You clicked card 4");
            }
        });

        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("You clicked card 5");
            }
        });
    }
}
