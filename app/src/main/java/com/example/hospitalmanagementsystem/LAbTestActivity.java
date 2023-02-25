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

public class LAbTestActivity extends AppCompatActivity {
    private String [][] packages=
            {
                    {"package 1: Full Body Checkup","","","","999"},
                    {"package 2: Blood Glucose Fasting","","","","299"},
                    {"package 3: COVID-19 Antibody - igG","","","","899"},
                    {"package 4: Thyrod Check","","","","499"},
                    {"package 5: Imunity Check","","","","699"}

            };
    private String [] package_details =
            {
                    "Blood Glucose Fasting\n"+
                            "Complete Hemogram\n"+
                            "HbA1c\n"+
                            "Iron Studies\n"+
                            "LDH Lactate Deydrogenase, Serum\n"+
                            "Lipid Profile\n"+
                            "Liver Function Test\n"+
                            "Blood Glucose Fasting",
                    "COVID-19 Antibody - igG",
                    "Thyrod Profile Total(T3,T4 & TSH Ultra sensitive)",
                    "Complete Hemogram\n"+
                            "CRP(C Reactive Protein)Quantiative,Serum\n"+
                            "Iron Studies\n"+
                            "Kidney Function Test",
                            "Vitamin D Total-25 Hydroxy\n"+
                            "Liver Function Test\n"+
                            "Lipid Profile"
            };
    Button btnGoToCart,btnBack;
    ListView listView;
    ArrayList list;
    SimpleAdapter simpleAdapter;
    HashMap<String,String> item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);

        btnGoToCart = findViewById(R.id.buttonHealthArticleBack);
        btnBack = findViewById(R.id.buttonCartBuyMedCheckout);
        listView = findViewById(R.id.listViewHealthList);


        // insert data to listview start
        list = new ArrayList();
        for(int i=0;i<packages.length;i++){
            item = new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total Costs:"+packages[i][4]+"/");
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
                Intent intent = new Intent(LAbTestActivity.this,LabTestDetailActivity.class);
                intent.putExtra("text1",packages[i][0]);
                intent.putExtra("text2",package_details[i]);
                intent.putExtra("text3",packages[i][4]);
                startActivity(intent);
            }
        });

        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LAbTestActivity.this,CartLabActivity.class));
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LAbTestActivity.this,HomeActivity.class));

            }
        });
    }
}