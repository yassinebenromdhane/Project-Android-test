package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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

public class RegisterActivity extends AppCompatActivity {
    EditText email , password ;
    Button register;
    String emailPatern ="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog pd;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView btn=findViewById(R.id.Login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });

        email = findViewById(R.id.nameArt);
        password = findViewById(R.id.prix);
        register = findViewById(R.id.add);
        pd = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                perForAuth();

            }


            private  void perForAuth () {
                String validEmail = email.getText().toString();
                String validPassword = password.getText().toString();
                if (!validEmail.matches(emailPatern)){
                    email.setError("invald email");
                }
                else if (validPassword.isEmpty() || validPassword.length()<6) {
                    password.setError("invalid password");
                }

                else{
                    pd.setMessage("please wait until compleating registration");
                    pd.setTitle("registration");
                    pd.setCanceledOnTouchOutside(false);
                    pd.show();
                    mAuth.createUserWithEmailAndPassword(validEmail,validPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                pd.dismiss();
                                sendUser();
                                Toast.makeText(RegisterActivity.this , "Registration Successful" , Toast.LENGTH_LONG);
                            }
                            else{
                                pd.dismiss();
                                Toast.makeText(RegisterActivity.this , ""+task.getException() , Toast.LENGTH_LONG);
                            }
                        }
                    });
                }
            }
        });
    }
    private void sendUser() {
        Intent i = new Intent(RegisterActivity.this , MainActivity.class);
        i.setFlags(i.FLAG_ACTIVITY_CLEAR_TASK|i.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);

    }
}