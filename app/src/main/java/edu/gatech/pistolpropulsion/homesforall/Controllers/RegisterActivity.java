package edu.gatech.pistolpropulsion.homesforall.Controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.*;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import edu.gatech.pistolpropulsion.homesforall.R;

import static android.content.ContentValues.TAG;

public class RegisterActivity extends Activity {

    private EditText editUser;
    private EditText editPass;
    private TextView enter;
    private TextView cancel;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

        enter = (TextView) findViewById(R.id.enter_textView);
        cancel = (TextView) findViewById(R.id.cancel_textView);
        mAuth = FirebaseAuth.getInstance();

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editUser = (EditText) findViewById(R.id.editUser);
                editPass = (EditText) findViewById(R.id.editPass);
                mAuth.createUserWithEmailAndPassword(editUser.getText().toString(), editPass.getText().toString())
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(getApplicationContext(), "You can now login with this user.",
                                            Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(getApplicationContext(), "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }
}
