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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private EditText loginEmail;
    private EditText loginPassword;
    private Button  loginBouton;
    private Button  signUpBouton;
    private TextView forgotPass;

    private FirebaseAuth mAuth;

    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        loginEmail = (EditText) findViewById(R.id.email_Id_Login);
        loginPassword   = (EditText) findViewById(R.id.email_Id_Login);
        loginBouton     = (Button) findViewById(R.id.bouton_Id_Login);
        signUpBouton    = (Button) findViewById(R.id.bouton_Id_SignUp);
        forgotPass      = (TextView) findViewById(R.id.forgetId);
        progressDialog  = new ProgressDialog(Login.this);


            // bouton signUp

            signUpBouton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(Login.this, SignUp.class);
                    startActivity(intent);
                    finish();
                }
            });


            //bouton resert password
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Login.this, ForgetPassword.class);
                startActivity(intent);
                finish();
            }
        });

            // bouton login

        loginBouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setTitle("Login");
                progressDialog.setMessage("Please wait.......");
                progressDialog.setCanceledOnTouchOutside(false);


                String  email = loginEmail.getText().toString();
                String  pass    = loginPassword.getText().toString();

                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)){
                    progressDialog.show();

                    mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){

                                sendTomain();


                            }else {
                                String error = task.getException().getMessage();
                                Toast.makeText(Login.this, "Error" + error, Toast.LENGTH_LONG).show();




                            }

                            progressDialog.dismiss();
                        }
                    });

                }


            }
        });


    }



    // Method demarer

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();


        if (currentUser != null){

           sendTomain();

        }
    }

    private void sendTomain() {
        Intent intent = new Intent(Login.this, SignUp.class);
        startActivity(intent);
        finish();

    }
}
