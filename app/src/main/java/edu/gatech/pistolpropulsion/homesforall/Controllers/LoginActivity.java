package edu.gatech.pistolpropulsion.homesforall.Controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.gatech.pistolpropulsion.homesforall.R;

public class LoginActivity extends Activity {

    private EditText editUser;
    private EditText editPass;
    private TextView enter;
    private TextView cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

        enter = (TextView) findViewById(R.id.enter_textView);
        cancel = (TextView) findViewById(R.id.cancel_textView);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editUser = (EditText) findViewById(R.id.editUser);
                editPass = (EditText) findViewById(R.id.editPass);
                if ((editUser.getText().toString().equals("user"))&&(editPass.getText().toString().equals("pass"))) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Username or Password", Toast.LENGTH_SHORT).show();
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
