package com.sq.mcaguide;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddSubs extends AppCompatActivity {
    RecyclerView rview;
    Map<String, Object> subs;
    FirebaseFirestore db;
    private static final String TAG = "AddSubs";
   SubAdapter adapter;
    ArrayList<String> subjectList;
    String[] subList= {"Machine Learning","Core Java","Python","Artificial Intelligence","Advanced java","Computer Networks","Theory Of Computing"};
    ArrayList<String> semList;
    String[] sList = {"5th Sem","3rd Sem","5th Sem","4th Sem","4th Sem","2nd Sem","3rd Sem"};
    String[] uList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subs);
        rview = findViewById(R.id.rview);
        rview.setHasFixedSize(true);
        db = FirebaseFirestore.getInstance();
        Query mquery;
        subjectList=new ArrayList<>();
        semList=new ArrayList<>();
        subs=new HashMap<>();
        rview.setLayoutManager(new LinearLayoutManager(this));
        //FireStore Start
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // yourMethod();
            }
        }, 2000);
        subList=Dashboard.subList;
        sList=Dashboard.sList;
        uList=Dashboard.uList;

        Log.d(TAG," Working fine after DB Operations");

        adapter = new SubAdapter(AddSubs.this,subList,sList,uList);
        rview.setAdapter(adapter);



//        rview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(),"Added Subject : "+subjectList[position],Toast.LENGTH_LONG).show();
//            }
//        });
    }
}
