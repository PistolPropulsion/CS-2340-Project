package edu.gatech.pistolpropulsion.homesforall.Controllers;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import edu.gatech.pistolpropulsion.homesforall.R;

public class Welcome extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);

        TextView loginView = (TextView) findViewById(R.id.login_textView);

        loginView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Welcome.this, LoginActivity.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
        //do nothing
    }

}
