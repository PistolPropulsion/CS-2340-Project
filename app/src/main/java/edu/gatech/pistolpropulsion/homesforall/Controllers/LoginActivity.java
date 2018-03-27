package edu.gatech.pistolpropulsion.homesforall.Controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import edu.gatech.pistolpropulsion.homesforall.R;

public class LoginActivity extends Activity {

    private EditText editUser;
    private EditText editPass;
    private TextView enter;
    private TextView cancel;
    private TextView register;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

        enter = (TextView) findViewById(R.id.textView_login_enterButton);
        cancel = (TextView) findViewById(R.id.textView_login_cancelButton);
        register = (TextView) findViewById(R.id.textView_welcome_registerButton);
        mAuth = FirebaseAuth.getInstance();



        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editUser = (EditText) findViewById(R.id.editText_login_username);
                editPass = (EditText) findViewById(R.id.editText_login_password);
                //just for testing lol
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                mAuth.signInWithEmailAndPassword(editUser.getText().toString(), editPass.getText().toString())
//                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if (task.isSuccessful()) {
//                                    // Sign in success, update UI with the signed-in user's information
//                                    Log.d(TAG, "signInWithEmail:success");
//                                    FirebaseUser user = mAuth.getCurrentUser();
//                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                                } else {
//                                    // If sign in fails, display a message to the user.
//                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
//                                    Toast.makeText(getApplicationContext(), "Authentication failed.",
//                                            Toast.LENGTH_SHORT).show();
//                                }
//
//                                // ...
//                            }
//                        });
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, Welcome.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

    }


}
