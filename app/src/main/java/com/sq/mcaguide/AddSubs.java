package com.sq.mcaguide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class AddSubs extends AppCompatActivity {
    RecyclerView rview;
   SubAdapter adapter;
    String subjectList[]= {"Machine Learning","Core Java","Python","Artificial Intelligence","Advanced java","Computer Networks","Theory Of Computing"};
    String semList[]= {"5th Sem","3rd Sem","5th Sem","4th Sem","4th Sem","2nd Sem","3rd Sem"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subs);
        rview = findViewById(R.id.rview);
        rview.setHasFixedSize(true);
        rview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SubAdapter(AddSubs.this,subjectList,semList);
        rview.setAdapter(adapter);

        rview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),v.toString()+" Selected",Toast.LENGTH_LONG).show();
            }
        });
//        rview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(),"Added Subject : "+subjectList[position],Toast.LENGTH_LONG).show();
//            }
//        });
    }
}
