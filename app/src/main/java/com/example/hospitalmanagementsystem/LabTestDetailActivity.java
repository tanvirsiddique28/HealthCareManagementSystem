package com.example.hospitalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LabTestDetailActivity extends AppCompatActivity {
TextView tvPackageName,tvTotalCost;
EditText edPackageDetails;
Button btnAddToCart,btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_detail);

        tvPackageName = findViewById(R.id.textViewHealthTitle);
        tvTotalCost = findViewById(R.id.textViewCartBuyMedTotalCost);
        edPackageDetails = findViewById(R.id.editTextBuyMedMultiline);
        btnAddToCart = findViewById(R.id.buttonHealthArticleBack);
        btnBack = findViewById(R.id.buttonCartBuyMedCheckout);


        edPackageDetails.setKeyListener(null);
        Intent intent = getIntent();
        tvPackageName.setText(intent.getStringExtra("text1"));
        edPackageDetails.setText(intent.getStringExtra("text2"));
        tvTotalCost.setText("Total Cost: "+intent.getStringExtra("text3")+"/");

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefers", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                String product = tvPackageName.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db = new Database(getApplicationContext(),"healthcare",null,1);

                //check whether the product is already added or not
                if(db.checkCart(username,product)==1){//checking product is exixt or not
                    Toast.makeText(getApplicationContext(),"Product is already added",Toast.LENGTH_SHORT).show();
                }else{
                    db.addCart(username,product,price,"lab");//insert
                    Toast.makeText(getApplicationContext(),"Record Inserted to Cart",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestDetailActivity.this,LAbTestActivity.class));
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestDetailActivity.this,LAbTestActivity.class));
            }
        });


    }
}