package com.example.hospitalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderDetailsActivity extends AppCompatActivity {

    ListView listView;
    TextView textView;
    private String [][] order_details = {};
    ArrayList list;
    SimpleAdapter simpleAdapter;
    HashMap<String,String> item;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        listView = findViewById(R.id.listViewHealthList);
        btnBack = findViewById(R.id.buttonHealthArticleBack);
        textView = findViewById(R.id.textViewHealthTitle);

        //fetch order data to list start
        Database db = new Database(getApplicationContext(),"healthcare",null,1);
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefers", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();
        ArrayList dbData = db.getOrderData(username);

        order_details = new String[dbData.size()][];
        for(int i=0;i<order_details.length;i++){
            order_details[i] = new String[5];

            String arrData = dbData.get(i).toString();
            String [] strData = arrData.split(java.util.regex.Pattern.quote("$"));
            order_details[i][0] = strData[0];
            order_details[i][1] = strData[1];

            if(strData[7].compareTo("medicine")==0){
                order_details[i][3] = "Del:"+strData[4];
            }else{
                order_details[i][3] = "Del:"+strData[4]+" "+strData[5];
            }
            order_details[i][2] = "Tk"+strData[6];
            order_details[i][4] =  strData[7];
        }
        // insert data to listview start
        list = new ArrayList();
        for(int i=0;i<order_details.length;i++){
            item = new HashMap<String,String>();
            item.put("line1",order_details[i][0]);
            item.put("line2",order_details[i][1]);
            item.put("line3",order_details[i][2]);
            item.put("line4",order_details[i][3]);
            item.put("line5",order_details[i][4]);
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

        //fetch order data to list end

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderDetailsActivity.this,HomeActivity.class));
            }
        });
    }
}