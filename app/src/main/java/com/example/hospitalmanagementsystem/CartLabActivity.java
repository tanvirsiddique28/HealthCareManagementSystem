package com.example.hospitalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.HashMap;

public class CartLabActivity extends AppCompatActivity {

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter simpleAdapter;
    TextView tvTotal;
    ListView listView;
    Button btnDate,btnTime,btnBack,btnCheckout;

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private String [][] packages = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_lab);

        listView = findViewById(R.id.listViewHealthList);
        btnDate = findViewById(R.id.buttonCartBuyMedDate);
        btnTime = findViewById(R.id.buttonCartMedTime);
        btnBack = findViewById(R.id.buttonHealthArticleBack);
        btnCheckout = findViewById(R.id.buttonCartBuyMedCheckout);
        tvTotal = findViewById(R.id.textViewCartBuyMedTotalCost);

        //fetch the user name
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefers", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();

        //database object
        Database db = new Database(getApplicationContext(),"healthcare",null,1);

        //fetch cart data
        float totalAmount = 0;
        ArrayList dbData =db.getCartData(username,"lab");
        //Toast.makeText(getApplicationContext(),""+dbData,Toast.LENGTH_SHORT).show();

        packages = new String[dbData.size()][];
        for (int i=0;i<packages.length;i++){
                packages[i] = new String[5];
        }

        for(int i=0;i<dbData.size();i++){
                String arrData = dbData.get(i).toString();
                String [] strData = arrData.split(java.util.regex.Pattern.quote("$"));

                packages[i][0] = strData[0];
                packages[i][4] = "Cost: "+strData[1]+"/";
                totalAmount = totalAmount+Float.parseFloat(strData[1]);
        }
        tvTotal.setText("Total Cost:"+totalAmount);

        // insert data to listview start
        list = new ArrayList();
        for(int i=0;i<packages.length;i++){
            item = new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Cons Fees:"+packages[i][4]+"/");
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


        //DatePicker
        initDatePicker();
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        //TimePicker
        initTimePicker();
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CartLabActivity.this,LabTestBookActivity.class);
                intent.putExtra("price",tvTotal.getText());
                intent.putExtra("date",btnDate.getText());
                intent.putExtra("time",btnTime.getText());
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartLabActivity.this,LAbTestActivity.class));
            }
        });
    }
    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1+1;

                btnDate.setText(i2+"/"+i1+"/"+i);
            }
        };
        Calendar calendar = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            int style = AlertDialog.THEME_HOLO_DARK;
            datePickerDialog = new DatePickerDialog(this,style,dateSetListener,year,month,day);
            datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis()+86400000);
        }

    }
    private void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                btnTime.setText(i+":"+i1);

            }
        };
        Calendar calendar = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            calendar = Calendar.getInstance();
            int hrs = calendar.get(Calendar.HOUR);
            int mins = calendar.get(Calendar.MINUTE);


            int style = AlertDialog.THEME_HOLO_DARK;
            timePickerDialog = new TimePickerDialog(this,style,timeSetListener,hrs,mins,true);

        }

    }
}