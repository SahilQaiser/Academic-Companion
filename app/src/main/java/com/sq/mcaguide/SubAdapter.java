package com.sq.mcaguide;

import android.content.Context;
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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SubAdapter extends RecyclerView.Adapter<SubAdapter.ViewHolder> {

    private StorageReference mStorageRef;
    private Context mContext;
    private String[] mList;
    private String[] mSem;
    private String[] mUrl;
    private static final String TAG = "SubAdapter";
    String url;
    public SubAdapter()
    {

    }
    public SubAdapter(Context context, String[] list, String[] sem, String[] uList)
    {
        this.mList=list;
        this.mSem=sem;
        this.mUrl=uList;
        this.mContext=context;
        mStorageRef = FirebaseStorage.getInstance().getReference();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.subject_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String subject = mList[i];
        String sem = mSem[i]+" Semester";
        url = mUrl[i];
        viewHolder.textView.setText(subject);
        viewHolder.textView2.setText(sem);
        viewHolder.btn_dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("URL",url);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView,textView2;
        public Button btn_dl;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView =  itemView.findViewById(R.id.tvSub);
            textView2 = itemView.findViewById(R.id.tvSem);
            btn_dl = itemView.findViewById(R.id.btnDL);
        }
    }
}
