package com.sq.mcaguide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SubAdapter extends RecyclerView.Adapter<SubAdapter.ViewHolder> {

    private StorageReference mStorageRef;
    private Context mContext;
    Intent addSub;
    private String[] mList;
    private String[] mSem;
    private String[] mID;
    private static final String TAG = "SubAdapter";
    String sid;
    SharedPreferences sharedPreferences;
    ArrayList<CardItem> subjectList;
    boolean isDuplicate=false;

    public SubAdapter()
    {

    }
    public SubAdapter(Context context, String[] list, String[] sem, String[] uid)
    {
        this.mList=list;
        this.mSem=sem;
        this.mID=uid;
        this.mContext=context;
        //mStorageRef = FirebaseStorage.getInstance().getReference();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.subject_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final String subject = mList[i];

        final String sem = mSem[i];
        final String id=subject+"_"+sem;
        sid = mID[i];
        Log.d("ID_of_subject "+i,sid);
        viewHolder.textView.setText(subject);
        viewHolder.textView2.setText(sem);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                loadData(); // means subjectList now contains the stored list
                Log.d("ID_of_subject_onCLick "+i,subject+"-------"+sem+"----"+sid);
                Log.d("ID_of_subject_onCLick "+i,Globals.getSubjectID(subject,sem));
                isDuplicate= validation(new CardItem(subject,sem,sid)); //check for duplication
                Log.d("dup_", ""+isDuplicate);
                if(isDuplicate)
                {   // duplicate, show toast msg and don't add it
                    Toast.makeText(v.getContext(),subject+"\n"+sem+"\n"+sid+"already added",Toast.LENGTH_LONG).show();
                }
                else
                {   // not duplicate, so add it
                    subjectList.add(new CardItem(subject,sem,sid));
                    Toast.makeText(v.getContext(),"Added  "+subject+sid,Toast.LENGTH_LONG).show();
                }
                saveData();

                ((Activity)mContext).finish();
            }
        });
    }

    private boolean validation(CardItem cardItem)
    {
        boolean isFound = false;
        for(CardItem item:subjectList)
        {
            Log.d("Subject_id", ""+item.getSid());
            if(item.getSubjectName().equals(cardItem.getSubjectName()) && item.getSemName().equals(cardItem.getSemName()))
            {
                isFound=true;
                break;
            }
            else
            {
                isFound=false;
            }
        }
        return isFound;
    }

    @Override
    public int getItemCount() {
        return mList.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView,textView2;
        //public Button btn_dl;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView =  itemView.findViewById(R.id.tvSub);
            textView2 = itemView.findViewById(R.id.tvSem);
            //btn_dl = itemView.findViewById(R.id.btnDL);
        }
    }

    private void saveData() {
        SharedPreferences sharedPreferences = MainActivity.context.getSharedPreferences("Subject", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(subjectList);
        editor.putString("subject_list", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences =  MainActivity.context.getSharedPreferences("Subject", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("subject_list", null);
        Type type = new TypeToken<ArrayList<CardItem>>() {}.getType();
        subjectList = gson.fromJson(json, type);

        if (subjectList == null) {
            subjectList = new ArrayList<>();
        }
    }

}
