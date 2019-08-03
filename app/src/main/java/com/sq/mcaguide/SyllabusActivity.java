package com.sq.mcaguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class SyllabusActivity extends AppCompatActivity
{
    ArrayList<String> semesterList=new ArrayList<>();
    ArrayList<String> subjectList=new ArrayList<>();
    ArrayAdapter<String> arrayAdapterForSemester;
    ArrayAdapter<String> arrayAdapterForSubjects;
    HashMap<String,ArrayList<String>> semSubjects=new HashMap<>();
    ArrayList<String> semester1Subjects=new ArrayList<>();
    ArrayList<String> semester2Subjects=new ArrayList<>();
    ArrayList<String> semester3Subjects=new ArrayList<>();
    ArrayList<String> semester4Subjects=new ArrayList<>();
    ArrayList<String> semester5Subjects=new ArrayList<>();
    ArrayList<String> semester6Subjects=new ArrayList<>();
    Intent i;
    String subID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);

        final Spinner semSpinner=findViewById(R.id.spinner1);
        final Spinner subjectSpinner=findViewById(R.id.spinner2);
        final WebView webView = (WebView)findViewById(R.id.webView);

        WebSettings webSetting = webView.getSettings();
        webSetting.setBuiltInZoomControls(true);
        webSetting.setDisplayZoomControls(false);
        webSetting.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
// webView.loadUrl("file:///101.html");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSaveFormData(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setWebViewClient(new WebViewClient());

        //subjects, load subjects name  intially

        //sem1 subjects
        semester1Subjects.add("101");
        semester1Subjects.add("102");
        semester1Subjects.add("103");
        semester1Subjects.add("104");
        semester1Subjects.add("105");
        semester1Subjects.add("106");
        semester1Subjects.add("107");


        //sem2 subjects
        semester2Subjects.add("201");
        semester2Subjects.add("202");
        semester2Subjects.add("203");
        semester2Subjects.add("204");
        semester2Subjects.add("205");
        semester2Subjects.add("206");
        semester2Subjects.add("207");



        //sem3 subjects
        semester3Subjects.add("301");
        semester3Subjects.add("302");
        semester3Subjects.add("303");
        semester3Subjects.add("304");
        semester3Subjects.add("305");
        semester3Subjects.add("306");
        semester3Subjects.add("307");
        //sem4 subjects
        semester4Subjects.add("401");
        semester4Subjects.add("402");
        semester4Subjects.add("403");
        semester4Subjects.add("404");
        semester4Subjects.add("405");
        semester4Subjects.add("406");
        semester4Subjects.add("407");

        //sem5 subjects
        semester5Subjects.add("501");
        semester5Subjects.add("502");
        semester5Subjects.add("503");
        semester5Subjects.add("504");
        semester5Subjects.add("505");
        semester5Subjects.add("506");
        semester5Subjects.add("507");

        //sem6 subjects
        semester6Subjects.add("601");
        semester6Subjects.add("602");
        semester6Subjects.add("603");
        semester6Subjects.add("604");



        semSubjects.put("Semester 1",semester1Subjects);
        semSubjects.put("Semester 2",semester2Subjects);
        semSubjects.put("Semester 3",semester3Subjects);
        semSubjects.put("Semester 4",semester4Subjects);
        semSubjects.put("Semester 5",semester5Subjects);
        semSubjects.put("Semester 6",semester6Subjects);

        // semester
        semesterList.add("Semester 1");
        semesterList.add("Semester 2");
        semesterList.add("Semester 3");
        semesterList.add("Semester 4");
        semesterList.add("Semester 5");
        semesterList.add("Semester 6");


        arrayAdapterForSemester=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,semesterList);
        arrayAdapterForSemester.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semSpinner.setAdapter(arrayAdapterForSemester);
        semSpinner.setSelection(0); // set selected item semester 1
        semSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                Log.d("MSG", "onItemSelected: ");
                String selected= (String)semSpinner.getSelectedItem();
                Log.d("SPINNER", selected);
                Toast.makeText(getApplicationContext(), "You selected : \n"+selected, Toast.LENGTH_LONG).show();

                //set subjects based on Semester
                arrayAdapterForSubjects=new ArrayAdapter<>(SyllabusActivity.this,android.R.layout.simple_spinner_item,semSubjects.get(selected));
                arrayAdapterForSubjects.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                subjectSpinner.setAdapter(arrayAdapterForSubjects);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        subjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                Log.d("MSG", "onItemSelected: ");
                String subjectSelected= (String)subjectSpinner.getSelectedItem();
                Log.d("SPINNER", subjectSelected);
                Toast.makeText(getApplicationContext(), "Subject Selected : \n"+subjectSelected, Toast.LENGTH_LONG).show();
                subjectSelected=subjectSelected+".html";
                webView.loadUrl("file:///android_asset/"+subjectSelected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }
    private class WebViewClient extends android.webkit.WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            return super.shouldOverrideUrlLoading(view, url);
        }
    }
}
