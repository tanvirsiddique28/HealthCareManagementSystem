package com.example.hospitalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    EditText edUserName,edEmail,edPassword,edConfirmPassword;
    Button btn;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        edUserName = findViewById(R.id.editTextBMBFullName);
        edEmail = findViewById(R.id.editTextBMBAddress);
        edPassword = findViewById(R.id.editTextBMBPinCode);
        edConfirmPassword = findViewById(R.id.editTextLBMBContactNum);
        btn = findViewById(R.id.buttonBMBbook);
        tv = findViewById(R.id.textViewOldUser);

        //Database Object
        //within the parameter(context,database name,factory,version)
        Database db = new Database(getApplicationContext(),"healthcare",null,1);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUserName.getText().toString();
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String confirmPassword = edConfirmPassword.getText().toString();

                if(username.length()==0||email.length()==0||username.length()==0||confirmPassword.length()==0){
                    Toast.makeText(getApplicationContext(),"Please fill all details",Toast.LENGTH_SHORT).show();
                }else {
                    if(password.compareTo(confirmPassword)==0){
                        if(isValid(password)){
                            db.register(username,email,password);
                            Toast.makeText(getApplicationContext(),"Register Successfully!!",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
                        }else {
                            Toast.makeText(getApplicationContext(),"Password must contain 8 characters,having letter,digit and special symbol",Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(getApplicationContext(),"Password and confirm password didn't match",Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
            }
        });
    }

    public  static  boolean isValid(String passwordAdhere){
        int f1=0,f2=0,f3=0;
        if(passwordAdhere.length()<8){
            return false;
        }else {
            for(int p=0;p<passwordAdhere.length();p++){
                if(Character.isLetter(passwordAdhere.charAt(p))){
                    f1=1;
                }
            }
            for(int r=0;r<passwordAdhere.length();r++){
                if(Character.isDigit(passwordAdhere.charAt(r))){
                    f2=1;
                }
            }
            for(int s=0;s<passwordAdhere.length();s++){

                    char c = passwordAdhere.charAt(s);
                    if(c>=33&&c<=46||c==64){
                    f3=1;
                    }

            }

            if(f1==1 && f2==1 & f3==1)
                return true;
                return  false;
        }
    }
}