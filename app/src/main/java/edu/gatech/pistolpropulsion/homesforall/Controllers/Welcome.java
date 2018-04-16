package edu.gatech.pistolpropulsion.homesforall.Controllers;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import edu.gatech.pistolpropulsion.homesforall.R;

import static android.content.ContentValues.TAG;

/**
 * welcome activity - it's the first screen upon opening the app
 */
public class Welcome extends Activity {

    TextView loginView;
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        final Animation animBlink = AnimationUtils.loadAnimation(this,
                R.anim.blink);

        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);

        loginView = findViewById(R.id.button_welcome_login);
        register = findViewById(R.id.button_welcome_register);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
            Intent i = new Intent(Welcome.this, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        } else {
            // User is signed out
            Log.d(TAG, "onAuthStateChanged:signed_out");
        }

        loginView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animBlink);
                startActivity(new Intent(Welcome.this, LoginActivity.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animBlink);
                startActivity(new Intent(Welcome.this, RegisterActivity.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
        //do nothing
    }

}
