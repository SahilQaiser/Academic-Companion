package com.sq.mcaguide;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ViewPDF extends AppCompatActivity {

    PDFView pdfView;
    File localFile;
    String u = "https://firebasestorage.googleapis.com/v0/b/academiccompanion-ae3db.appspot.com/o/notes%2Fsub1_notes.pdf?alt=media&token=6120b2ac-9a9b-429a-923c-de82108ce253";
    URL url;
    public FirebaseStorage storage;
    public StorageReference storageRef;;
    String subID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdf);


        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                subID= null;
            } else {
                subID= extras.getString("SUBID");
            }
        } else {
            subID= (String) savedInstanceState.getSerializable("SUBID");
        }
        //Log.d("PDFActivity",subID);
        pdfView=findViewById(R.id.pdfView);
        //Storage references part
        try {
            localFile = File.createTempFile(subID, ".pdf");
            Log.d("PDFNameSUBID",subID);
            storageRef.child(subID+".pdf").getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    try {
                        displayPDF(localFile);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }

            });
        } catch (IOException e) {
            e.printStackTrace();
        }




        try {
            displayPDF(localFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void displayPDF(File file) throws FileNotFoundException {
        //pdfView.fromAsset(name).load();
        pdfView.fromFile(file).load();

    }
    private File downloadFile() throws IOException {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl("gs://academiccompanion-ae3db.appspot.com");
        StorageReference islandRef = storageRef.child("AI_4.pdf");

        File rootPath = new File(Environment.getExternalStorageDirectory(), "file_name");
        if(!rootPath.exists()) {
            rootPath.mkdirs();
        }

        final File localFile = new File(rootPath,"AI_4.pdf");

        islandRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                Log.e("firebase ",";local tem file created  created " +localFile.toString());
                //  updateDb(timestamp,localFile.toString(),position);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.e("firebase ",";local tem file not created  created " +exception.toString());
            }
        });
        return localFile;
    }
}
