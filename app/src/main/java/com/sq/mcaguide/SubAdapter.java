package com.sq.mcaguide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.storage.StorageReference;

public class SubAdapter extends RecyclerView.Adapter<SubAdapter.ViewHolder> {

    SharedPreferences sharedPreferences;
    private StorageReference mStorageRef;
    private Context mContext;
    Intent addSub;
    private String[] mList;
    SharedPreferences.Editor editor;
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
        //mStorageRef = FirebaseStorage.getInstance().getReference();
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.subject_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final String subject = mList[i];

        String sem = mSem[i];
        url = mUrl[i];
        final String id=subject+"_"+sem;
        viewHolder.textView.setText(subject);
        viewHolder.textView2.setText(sem);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Toast.makeText(v.getContext(),"Added "+subject,Toast.LENGTH_LONG).show();


                Toast.makeText(v.getContext(),"Added "+subject,Toast.LENGTH_LONG).show();
                sharedPreferences=MainActivity.context.getSharedPreferences("Subjects",Context.MODE_PRIVATE);
                if(!sharedPreferences.contains("subject_list"))
                {
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("subject_list",id);
                    editor.commit();
                    Log.d("subjects_", subject);
                }
                else
                {
                    String preSubjects= sharedPreferences.getString("subject_list",null);
                    preSubjects=preSubjects+","+id;
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("subject_list",preSubjects);
                    editor.commit();
                    Log.d("subjects_", preSubjects);

                }
                ((Activity)mContext).finish();

            }
        });
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
}
