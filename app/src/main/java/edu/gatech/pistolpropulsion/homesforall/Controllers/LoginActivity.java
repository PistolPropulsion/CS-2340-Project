package edu.gatech.pistolpropulsion.homesforall.Controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import edu.gatech.pistolpropulsion.homesforall.R;

import static android.content.ContentValues.TAG;

/**
 * login activity, controls the login screen
 */
public class LoginActivity extends Activity {

    private EditText editUser;
    private EditText editPass;

    @RequiresApi(api = Build.VERSION_CODES.ECLAIR)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

        TextView  enter = findViewById(R.id.textView_login_enterButton);
        TextView cancel = findViewById(R.id.textView_login_cancelButton);
        TextView register = findViewById(R.id.textView_welcome_registerButton);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();



        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editUser = findViewById(R.id.editText_login_username);
                editPass = findViewById(R.id.editText_login_password);
                if ((editUser.getText() == null) || (editUser.getText().length() <= 0)
                        || (editPass.getText() == null) || (editPass.getText().length() <= 0)) {
                    Toast.makeText(getApplicationContext(), "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                //just for testing lol
                //startActivity(new Intent(LoginActivity.this, MainActivity.class));
                mAuth.signInWithEmailAndPassword(editUser.getText().
                        toString(), editPass.getText().toString())
//                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                            @Override
//                            public void onSuccess(AuthResult authResult) {
//                                Log.d(TAG, "signInWithEmail:success");
//                                FirebaseUser user = mAuth.getCurrentUser();
//                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Log.w(TAG, "signInWithEmail:failure", e);
//                                Toast.makeText(getApplicationContext(), "Authentication failed.",
//                                        Toast.LENGTH_SHORT).show();
//                            }
//                        })
                        .addOnCompleteListener(LoginActivity.this,
                                new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success
                                    // update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    startActivity(new Intent(LoginActivity.this,
                                            MainActivity.class));
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG,
                                            "signInWithEmail:failure", task.getException());
                                    Toast.makeText(getApplicationContext(),
                                          "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });
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
