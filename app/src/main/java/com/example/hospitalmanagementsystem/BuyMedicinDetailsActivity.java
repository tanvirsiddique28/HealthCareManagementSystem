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

public class BuyMedicinDetailsActivity extends AppCompatActivity {

    TextView tvPackageName,tvTotalCost;
    EditText edDetails;
    Button btnAddCart,btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicin_details);

        tvPackageName = findViewById(R.id.textViewHealthTitle);
        tvTotalCost = findViewById(R.id.textViewCartBuyMedTotalCost);
        edDetails = findViewById(R.id.editTextBuyMedMultiline);
        edDetails.setKeyListener(null);
        btnAddCart = findViewById(R.id.buttonHealthArticleBack);
        btnBack = findViewById(R.id.buttonCartBuyMedCheckout);


        Intent intent = getIntent();
        tvPackageName.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        tvTotalCost.setText("Total Cost: "+intent.getStringExtra("text3")+"/");

        btnAddCart.setOnClickListener(new View.OnClickListener() {
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
                    db.addCart(username,product,price,"medicine");//insert
                    Toast.makeText(getApplicationContext(),"Record Inserted to Cart",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuyMedicinDetailsActivity.this,BuyMedicineActivity.class));
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicinDetailsActivity.this,BuyMedicineActivity.class));
            }
        });

    }
}