package com.sq.mcaguide;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Dashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    int time=0;
    public static SharedPreferences sharedPreferences;
    private static final String TAG="Dashboard";
    FirebaseFirestore db;
    ArrayList<String> subjectList;
    ArrayList<String> semList;
    ArrayList<String> urlList;
    Intent i;
    public static String[] sList = {"5th Sem","3rd Sem","5th Sem","4th Sem","4th Sem","2nd Sem","3rd Sem"};
    public static String[] subList= {"Machine Learning","Core Java","Python","Artificial Intelligence","Advanced java","Computer Networks","Theory Of Computing"};
    public static String[] uList={"NA","NA","NA","NA","NA","NA","NA"};
    Map<String, Object> subs;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context=Dashboard.this;
        FloatingActionButton fab = findViewById(R.id.fabAdd);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(Dashboard.this,AddSubs.class);
                Handler handler = new Handler();
                if(time==0)
                {
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            time++;
                            startActivity(i);
                        }
                    }, 1000);
                } else
                    startActivity(i);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        //FireCloud
        db = FirebaseFirestore.getInstance();
        subjectList=new ArrayList<>();
        semList=new ArrayList<>();
        urlList=new ArrayList<>();
        subs=new HashMap<>();
        String result = readFromFireStore();
        Log.d(TAG,result);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //set data for dashboard
        populateDashBoard();

    }

    public  void populateDashBoard()
    {
        ArrayList<CardItem> cardItems=new ArrayList<>();
        sharedPreferences= MainActivity.context.getSharedPreferences("Subjects", Context.MODE_PRIVATE);
        String allSubjects=sharedPreferences.getString("subject_list","empty_list");
        Log.d("all_subjects", allSubjects);
        String [] sub_id=allSubjects.split(",");
//        cardItems.add(new CardItem("AI","4th"));
//        cardItems.add(new CardItem("ML","5th"));
//        cardItems.add(new CardItem("DL","6th"));
        for (String separateIdSub: sub_id)
        {
            String []subAndId=separateIdSub.split("_");
            String subject=subAndId[0];
            String semester=subAndId[1];
            cardItems.add(new CardItem(subject,semester));
        }

        mRecyclerView=findViewById(R.id.dashboardRV);
        mRecyclerView.setHasFixedSize(true);
        mAdapter=new DashBoardAdapter(cardItems,this);
        mLayoutManager=new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }

    private String readFromFireStore() {


        db.collection("subjects")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int i;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d(TAG, document.getId() + " => " + document.getData());
                                subs = document.getData();
                                i=0;
                                for(Map.Entry sub:subs.entrySet())
                                {
                                    if(i==0)
                                    {
                                        subjectList.add(sub.getValue().toString());
                                        i++;
                                    }
                                    else if(i==1)
                                    {
                                        semList.add(sub.getValue().toString());
                                        i++;
                                    }
                                    else if(i==2)
                                    {
                                        urlList.add(sub.getValue().toString());
                                        i++;
                                    }
                                    Log.d(TAG," Working fine inside loop");
                                }
                                Log.d(TAG," Working fine outside loop");
                            }
                            Log.d(TAG," Working fine, ArrayList to Array Done");
                            subList = subjectList.toArray(new String[subjectList.size()]);
                            sList = semList.toArray(new String[semList.size()]);
                            uList = urlList.toArray(new String[urlList.size()]);

                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
        return "SUCCESS";
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_notes) {

        } else if (id == R.id.nav_papers) {

        } else if (id == R.id.nav_downloads) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
