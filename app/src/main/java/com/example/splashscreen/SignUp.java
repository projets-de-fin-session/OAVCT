package com.example.splashscreen;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SignUp extends AppCompatActivity {
   private EditText signupEmail;
   private EditText signupPasswor;
   private EditText signupPassConfirm;
   private Button   signUpButon;
   private Button   loginBouton;
   private ProgressDialog progressDialog;

   private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        mAuth = FirebaseAuth.getInstance();

        signupEmail = (EditText) findViewById(R.id.email_Id_SignUP);
        signupPasswor = (EditText) findViewById(R.id.password_Id_SignUp);
        signupPassConfirm = (EditText) findViewById(R.id.password_Id_SignUp);
        signUpButon =   (Button) findViewById(R.id.bouton_Id_SignUp);
        loginBouton =   (Button) findViewById(R.id.bouton_Id_Login);
        progressDialog  = new ProgressDialog(SignUp.this);

        loginBouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
                finish();

            }
        });

        signUpButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = signupEmail.getText().toString();
                String pass = signupPasswor.getText().toString();
                String  passConf    = signupPassConfirm.getText().toString();


                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(passConf)){

                    if (pass.equals(passConf)){
                            progressDialog.setTitle("SignUp");
                            progressDialog.setMessage("Please wait.......");
                            progressDialog.setCanceledOnTouchOutside(false);
                            progressDialog.show();

                        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()){

                                    sendTomain();

                                }else {
                                    String errorMassage = task.getException().getMessage();
                                    Toast.makeText(SignUp.this, "error" + errorMassage, Toast.LENGTH_LONG).show();

                                }
                                progressDialog.dismiss();

                            }
                        });

                    }else {

                        Toast.makeText(SignUp.this, "confirm password and password doesn't match", Toast.LENGTH_LONG).show();
                    }


                }else {

                    // ajouter...............
                }
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null){
            sendTomain();


        }
    }

    private void sendTomain() {
        Intent intent = new Intent(SignUp.this, Login.class);
        startActivity(intent);
        finish();
    }
}