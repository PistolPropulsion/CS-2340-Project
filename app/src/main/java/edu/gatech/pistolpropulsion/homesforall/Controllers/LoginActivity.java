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
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;
import java.util.Map;

import edu.gatech.pistolpropulsion.homesforall.Models.User;
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
        ImageView cancel = findViewById(R.id.imageView_login_back_arrow);
        TextView register = findViewById(R.id.textView_welcome_registerButton);
        TextView resetPass = findViewById(R.id.textView_login_resetPassButton);


        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase mRef = FirebaseDatabase.getInstance();
        final User[] user1 = new User[1];

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editUser = findViewById(R.id.editText_login_email);
                editPass = findViewById(R.id.editText_login_password);

                DatabaseReference usersReference = mRef.getReference().child("users").child("standardUsers");
                usersReference.orderByChild("email").equalTo(editUser.getText().toString()).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Map<String, Object> userUpdates = new HashMap<>();
                        user1[0] = dataSnapshot.getValue(User.class);

                        if ((editUser.getText() == null) || (editUser.getText().length() <= 0)
                                || (editPass.getText() == null) || (editPass.getText().length() <= 0)) {
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }
                        mAuth.signInWithEmailAndPassword(editUser.getText().
                                toString(), editPass.getText().toString())
                                .addOnCompleteListener(LoginActivity.this,
                                        new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (user1[0] != null && user1[0].getAttempts() <= 0) {
                                                    Log.d(TAG, "signInWithEmail:locked out");
                                                    Toast.makeText(getApplicationContext(),
                                                            "Account locked. Please contact Admin", Toast.LENGTH_SHORT).show();
                                                }else if (task.isSuccessful()) {
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
                                                    user1[0].failedAttempt();
                                                    System.out.println(user1[0].getEmail() + " " + user1[0].getAttempts());
                                                    userUpdates.put(dataSnapshot.getKey(), user1[0]);
                                                    usersReference.updateChildren(userUpdates);
                                                }
                                            }
                                        });
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {}
                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                });

                DatabaseReference usersReference1 = mRef.getReference().child("users").child("administrators");
                usersReference1.orderByChild("email").equalTo(editUser.getText().toString()).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Map<String, Object> userUpdates = new HashMap<>();
                        user1[0] = dataSnapshot.getValue(User.class);

                        if ((editUser.getText() == null) || (editUser.getText().length() <= 0)
                                || (editPass.getText() == null) || (editPass.getText().length() <= 0)) {
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }
                        mAuth.signInWithEmailAndPassword(editUser.getText().
                                toString(), editPass.getText().toString())
                                .addOnCompleteListener(LoginActivity.this,
                                        new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (user1[0] != null && user1[0].getAttempts() <= 0) {
                                                    Log.d(TAG, "signInWithEmail:locked out");
                                                    Toast.makeText(getApplicationContext(),
                                                            "Account locked. Please contact Admin", Toast.LENGTH_SHORT).show();
                                                }else if (task.isSuccessful()) {
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
                                                    user1[0].failedAttempt();
                                                    System.out.println(user1[0].getEmail() + " " + user1[0].getAttempts());
                                                    userUpdates.put(dataSnapshot.getKey(), user1[0]);
                                                    usersReference1.updateChildren(userUpdates);
                                                }
                                            }
                                        });
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {}
                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
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

        resetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editUser = findViewById(R.id.editText_login_email);
                FirebaseAuth.getInstance().sendPasswordResetEmail(editUser.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "Email sent.");
                                    Toast.makeText(getApplicationContext(),
                                            "Reset password email sent.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(),
                                            "Please enter a valid email.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }


}
