package com.example.hospitalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String [][] doctor_details1 = {
            {"Doctor Name: Abrar Rahman","Hospital Address : Dhanmondi","Exp : 15yrs","Mobile No:01921032389","600"},
            {"Doctor Name: Polash Hasan","Hospital Address : Dhanmondi","Exp : 8yrs","Mobile No:01821039876","600"},
            {"Doctor Name: Zahid Islam","Hospital Address : Mirpur","Exp : 5yrs","Mobile No:019111032333","600"},
            {"Doctor Name: Mufti Rahman","Hospital Address : Mohammadpur","Exp : 12yrs","Mobile No:01721032377","600"},
            {"Doctor Name: Sohag Ahmed","Hospital Address : Mirpur","Exp : 18yrs","Mobile No:01621032389","600"},
    };

    private String [][] doctor_details2 = {
            {"Doctor Name: Fahim Ahmed","Hospital Address : Mirpur","Exp : 17yrs","Mobile No:01921032311","700"},
            {"Doctor Name: Nayeem Ahmed","Hospital Address : Gulshan","Exp : 20yrs","Mobile No:01721039876","1600"},
            {"Doctor Name: Ali Nur","Hospital Address : Banani","Exp : 9yrs","Mobile No:019111032333","500"},
            {"Doctor Name: Rubel Ahmed","Hospital Address : Dhanmondi","Exp : 11yrs","Mobile No:01921032377","2600"},
            {"Doctor Name: Siyam Islam","Hospital Address : Banani","Exp : 18yrs","Mobile No:01821032389","900"},
    };

    private String [][] doctor_details3 = {
            {"Doctor Name: Fayaz Islam","Hospital Address : Banani","Exp : 15yrs","Mobile No:01921032311","1000"},
            {"Doctor Name: Anisur Rahman","Hospital Address : Gulistan","Exp : 8yrs","Mobile No:01821039878","600"},
            {"Doctor Name: Najmul Hasan","Hospital Address : Keraniganj","Exp : 5yrs","Mobile No:019111032399","800"},
            {"Doctor Name: Jewel Rana","Hospital Address : Badda","Exp : 12yrs","Mobile No:01721032377","1600"},
            {"Doctor Name: Arfan Habib","Hospital Address : Dhanmondi","Exp : 18yrs","Mobile No:0162103222","1500"},
    };

    private String [][] doctor_details4 = {
            {"Doctor Name: Ashikuzzaman Ashik","Hospital Address : Dhanmondi","Exp : 15yrs","Mobile No:01921032480","700"},
            {"Doctor Name: Mizanur Rahman","Hospital Address :  Keraniganj","Exp : 8yrs","Mobile No:01821039345","800"},
            {"Doctor Name: Zahid Emon","Hospital Address : Gulshan","Exp : 5yrs","Mobile No:019111032789","900"},
            {"Doctor Name: Abdun Nahid","Hospital Address : Dhanmondi","Exp : 12yrs","Mobile No:01721032777","200"},
            {"Doctor Name: Rakib Rahul","Hospital Address : Banani","Exp : 18yrs","Mobile No:01621032821","300"},
    };

    private String [][] doctor_details5 = {
            {"Doctor Name: Shahdat Hossain","Hospital Address : Mohammadpur","Exp : 15yrs","Mobile No:01921032786","1100"},
            {"Doctor Name: Masud Rana","Hospital Address : Badda","Exp : 8yrs","Mobile No:01821039344","1000"},
            {"Doctor Name: Minul Islam","Hospital Address :  Banani","Exp : 5yrs","Mobile No:017111032333","700"},
            {"Doctor Name: Abdus Salam","Hospital Address : Dhanmondi","Exp : 12yrs","Mobile No:01821032377","800"},
            {"Doctor Name: Shayan Ahmed","Hospital Address : Gulshan","Exp : 18yrs","Mobile No:01721032389","900"},
    };
    TextView tv;
    Button btn;
    ListView listView;
    private String [][] doctor_details = {};
    ArrayList list;
    SimpleAdapter simpleAdapter;
    HashMap<String,String> item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.textViewHealthTitle);
        btn = findViewById(R.id.buttonHealthArticleBack);
        listView = findViewById(R.id.listViewHealthList);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Family Physician")==0){
            doctor_details = doctor_details1;
        }else if(title.compareTo("Dietician")==0){
            doctor_details = doctor_details2;
        }else if(title.compareTo("Dentist")==0){
            doctor_details = doctor_details3;
        }else if(title.compareTo("Sergeon")==0){
            doctor_details = doctor_details4;
        }else {
            doctor_details = doctor_details5;
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class));
            }
        });
        // insert data to listview start
        list = new ArrayList();
        for(int i=0;i<doctor_details.length;i++){
        item = new HashMap<String,String>();
        item.put("line1",doctor_details[i][0]);
        item.put("line2",doctor_details[i][1]);
        item.put("line3",doctor_details[i][2]);
        item.put("line4",doctor_details[i][3]);
        item.put("line5","Cons Fees:"+doctor_details[i][4]+"/");
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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
                intent.putExtra("text1",title);
                intent.putExtra("text2",doctor_details[i][0]);
                intent.putExtra("text3",doctor_details[i][1]);
                intent.putExtra("text4",doctor_details[i][3]);
                intent.putExtra("text5",doctor_details[i][4]);
                startActivity(intent);
            }
        });
    }
}