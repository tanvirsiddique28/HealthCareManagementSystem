package com.example.hospitalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyMedicineActivity extends AppCompatActivity {
    private String[][] packages =
            {
                    {"Uprise D3 1000 IU Capsule","","","","50"},
                    {"HealVit Chromium Picolinate 200mcg Capsule","","","","305"},
                    {"Viatmin B Complete Capsules","","","","448"},
                    {"Inlife Vitamin E Wheat Germ Oil Capsule","","","","539"},
                    {"Dolo 650 Tablet","","","","30"},
                    {"Crocin 650 Advance Tablet","","","","50"},
                    {"Strepsils Medicated Lozenges for Sore Throat","","","","40"},
                    {"Tata Lmg Calcium + Vitamin D3","","","","30"},
                    {"Feronia -XT Tablet","","","","130"}
            };

    private String[] packages_details =

                    {
                            "This capsule helps maintain healthy vitamin D levels in the body\n"+
                                    "It improves calcium and phosphorus absorption in the body\n"+
                                    "It supports bone, muscle and nerve health",

                            "is an essential trace mineral that plays an important role\n"+
                                    "in helping insulin regulate blood glucose.",

                            "used to treat or prevent vitamin deficiency due to poor diet\n"+
                                    ", certain illnesses, alcoholism, or during pregnancy",

                            "It promotes health as well as skin benefit\n"+
                                    "It helps reduce skin blemish and pigmentation\n"+
                                    "It act as safeguard the skin from the harsh UVA and UVB sun rays\n"+
                                    "It helps to maintain a healthy body with overall wellness",

                            "It is used to treat headaches, migraine, nerve pain, toothache,\n"+"sore throat, period (menstrual) pains,\n"+
                                    "arthritis, muscle aches, and the common cold.",

                            "provide temporary, effective relief from tension headache pain,\n"+
                                    "migraine pain, toothache, cold and flu symptoms,\n"+
                                    "muscle aches, arthritis and osteoarthritis.",

                            "It provide fast-acting relief which"+
                                    "helps prevent sore throat pain from getting worse.",

                            "It is a dietary supplement formulated with calcium\n"+
                                    "citrate, vitamin D3, vitamin K2, alfalfa, magnesium\n"+
                                    "and zinc that promote bone and joint health.",

                            "Iron helps the body move oxygen throughout the\n"+
                                    "body and maintain red blood cells, which helps people\n"+
                                    "feel more energised and prevents anaemia from occurring.",



            };
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter simpleAdapter;
    ListView listView;
    Button btnBack,btnGoTOCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

        listView = findViewById(R.id.listViewHealthList);
        btnGoTOCart = findViewById(R.id.buttonHealthArticleBack);
        btnBack = findViewById(R.id.buttonCartBuyMedCheckout);

        // insert data to listview start
        list = new ArrayList();
        for(int i=0;i<packages.length;i++){
            item = new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total Cost:"+packages[i][4]+"/");
            list.add(item);
        }
        // list(from) to (multi_lines)
        simpleAdapter = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
        );
        listView.setAdapter(simpleAdapter);
        // insert data to listview end

        //get details of list item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(BuyMedicineActivity.this,BuyMedicinDetailsActivity.class);
                intent.putExtra("text1",packages[i][0]);
                intent.putExtra("text2",packages_details[i]);
                intent.putExtra("text3",packages[i][4]);
                startActivity(intent);
            }
        });

        btnGoTOCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this, CartMedicineActivity.class));
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this,HomeActivity.class));
            }
        });
    }
}