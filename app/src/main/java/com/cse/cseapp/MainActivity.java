package com.cse.cseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText edName;
    private EditText edMobileNo;
    private Button btnlogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edName=(EditText)findViewById(R.id.edName);
        edMobileNo=(EditText)findViewById(R.id.edPassword);
        btnlogin=(Button)findViewById(R.id.btnlogin);


    }
    private void validate(String userName,String userPassword){
        if ((userName=="Admin")||(userPassword=="1234")){
            Intent intent = new Intent(MainActivity.this,SecondActivity.class);

        }
    }

    com.google.firebase:firebase-database:16.0.5
    private void UserLogin(){

        String email = edName.getText().toString().trim();
        final String password = edMobileNo.getText().toString().trim();

        if (email.isEmpty()) {
            edName.setError("Email is required");
            edMobileNo.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edName.setError("Please enter a valid email");
            edName.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            edMobileNo.setError("Password is required");
            edMobileNo.requestFocus();
            return;
        }

        if (password.length() < 6) {
            edMobileNo.setError("Minimum length of password should be 6");
            edMobileNo.requestFocus();
            return;
        }




        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    finish();
                    Intent Intent = new Intent(LogIn.this,Profile.class);
                    Intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(Intent);

                }else {
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    edMobileNo.setText("");
                }
            }
        });

    }

}
