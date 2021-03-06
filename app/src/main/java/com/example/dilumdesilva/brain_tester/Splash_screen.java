package com.example.dilumdesilva.brain_tester;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Splash_screen extends AppCompatActivity {

//    declaring splash screen related components
    //private TextView ss_textView;
    private ImageView ss_imageView;
    private ProgressBar ss_progress_bar;
    private Thread progressBarThread;
    private final int DURATION =3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //initializing the components by Id
        ss_imageView =  (ImageView) findViewById(R.id.ss_imageView);
        //ss_textView = (TextView) findViewById(R.id.ss_textView);
        ss_progress_bar = (ProgressBar) findViewById(R.id.ss_progress_bar);

        //setting the splash screen animation to the splash screen
        Animation splashScreenAnim = AnimationUtils.loadAnimation(this, R.anim.splash_transition);
        ss_imageView.startAnimation(splashScreenAnim);
        //ss_textView.startAnimation(splashScreenAnim);

        //creating an intent to communicate with the menu screen activity
        final Intent intent = new Intent(this, game_menu.class);

        //setting a timer to automatically continue to the main menu screen after splash screen
//        Thread timer = new Thread(){
//            public void run(){
//                try {
//                    sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                finally {
//                     startActivity(intent);
//                     finish();
//                }
//            }
//        };
//        timer.start();

        progressBarThread = new Thread() {

            @Override
            public void run() {
                synchronized (this) {
                    try {
                        wait(1000);
                        ss_progress_bar.setProgress(25);
                        wait(2000);
                        ss_progress_bar.setProgress(50);
                        wait(1500);
                        ss_progress_bar.setProgress(80);
                        wait(DURATION);
                    } catch (InterruptedException e) {
                    } finally {
                        ss_progress_bar.setProgress(100);
                        finish();
                        startActivity(intent);
                    }
                }
            }

        };
        progressBarThread.start();

    }
}
