package com.example.android.helloandroid2;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends FragmentActivity implements Animation.AnimationListener {

    Animation anim1, anim2, anim3, anim4,anim5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animationTask();
    }

    public void webIntent(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.udacity.com"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void phoneIntent(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("tel:650-555-5555"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void googleMapsIntent(View view) {
        Uri gmmIntentUri = Uri.parse("google.navigation:q=an+2465 Latham St+Mountain View");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            Toast.makeText(this, "Sorry Something wrong has happened check if your device has google maps instaled", Toast.LENGTH_LONG).show();
        }

    }

    public void facebookIntent(View view) {
        String uri = getFacebookPageURL(this);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(uri));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void twitterIntent(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://twitter.com/udacity?lang=ar"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void linkedInIntent(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("linkedin://group/[164070]"));
        PackageManager packageManager = getApplicationContext().getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (list.isEmpty()) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/edu/udacity-164070"));
        }
        startActivity(intent);
    }

    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=https://www.facebook.com/Udacity";
            } else { //older versions of fb app
                return "fb://page/Udacity";
            }
        } catch (PackageManager.NameNotFoundException e) {
            return "https://www.facebook.com/Udacity"; //normal web url
        }
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
    void animationTask(){
        anim1 = AnimationUtils.loadAnimation(this, R.anim.animation1);
        anim2 = AnimationUtils.loadAnimation(this, R.anim.move);
        anim2.setDuration(2000);
        anim3 = AnimationUtils.loadAnimation(this, R.anim.move);
        anim3.setDuration(2200);
        anim4 = AnimationUtils.loadAnimation(this, R.anim.move);
        anim4.setDuration(2400);
        anim5=AnimationUtils.loadAnimation(this,R.anim.move2);
        ImageView linkedin = findViewById(R.id.linkedin);
        ImageView twitter = findViewById(R.id.twitter);
        ImageView facebook = findViewById(R.id.facebook);
        TextView text = findViewById(R.id.address);
        TextView text2 = findViewById(R.id.phone);
        TextView text3 = findViewById(R.id.web);
        linkedin.startAnimation(anim5);
        facebook.startAnimation(anim5);
        twitter.startAnimation(anim5);
        linkedin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.startAnimation(anim1);
                    return true;
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    linkedInIntent(v);
                    return true;
                }
                return false;
            }
        });
        facebook.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.startAnimation(anim1);
                    return true;
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    facebookIntent(v);
                    return true;
                }
                return false;
            }

        });
        twitter.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.startAnimation(anim1);
                    return true;
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    twitterIntent(v);
                    return true;
                }
                return false;
            }
        });
        text.startAnimation(anim2);
        text2.startAnimation(anim3);
        text3.startAnimation(anim4);
    }
}