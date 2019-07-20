package com.sq.mcaguide;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DashAdapter extends RecyclerView.Adapter<DashAdapter.ViewHolder> {
    private Context mContext;
    private String[] mList,sList;
    public DashAdapter()
    {

    }
    public DashAdapter(Context context, String[] mList,String[] sList)
    {
        this.mContext=context;
        this.mList=mList;
        this.sList=sList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.subject_item,viewGroup,false);
        return new DashAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final String subject = "AI";//mList[i];
        String sem = "3rd Semester";//mList[i]+" Semester";

        viewHolder.textView.setText(subject);
        viewHolder.textView2.setText(sem);
    }


    @Override
    public int getItemCount() {
        return 0;
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
