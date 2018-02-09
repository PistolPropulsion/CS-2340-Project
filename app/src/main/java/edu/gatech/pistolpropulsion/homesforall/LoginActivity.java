package edu.gatech.pistolpropulsion.homesforall;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView enter = (TextView) findViewById(R.id.enter_textView);
        TextView cancel = (TextView) findViewById(R.id.cancel_textView);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editUser = (EditText) findViewById(R.id.editUser);
                EditText editPass = (EditText) findViewById(R.id.editPass);
                if ((editUser.getText().toString().equals("user"))&&(editPass.getText().toString().equals("pass"))) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, Welcome.class));
            }
        });



    }
}
