package com.example.splashscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends AppCompatActivity {
    EditText resertPass;
    Button myResert;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        resertPass = findViewById(R.id.emailId);
        myResert    = findViewById(R.id.resertId);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        myResert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setTitle("Resert pasert!!");
                progressDialog.setMessage("please wait....");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                final String emailResert = myResert.getText().toString().trim();
                if (emailResert.isEmpty()){
                    myResert .setError("Field can't empty !!");
                    myResert.requestFocus();

                }else{
                    firebaseAuth.sendPasswordResetEmail(emailResert).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                startActivity(new Intent(ForgetPassword.this, Login.class));
                                finish();

                            }else {
                               // String errorMessage = task.getException().getMessage();
                              //Toast.makeText(ForgetPassword.this,"error" + errorMessage,Toast.LENGTH_LONG).show();
                                startActivity(new Intent(ForgetPassword.this, Login.class));
                                finish();

                            }
                        }
                    });
                }
                progressDialog.dismiss();
            }
        });
    }
}