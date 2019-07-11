package com.sq.mcaguide;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SubAdapter extends RecyclerView.Adapter<SubAdapter.ViewHolder> {

    private Context mContext;
    private String[] mList;
    private String[] mSem;

    public SubAdapter()
    {

    }
    public SubAdapter(Context context, String[] list, String[] sem)
    {
        this.mList=list;
        this.mSem=sem;
        this.mContext=context;
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
        String sem = mSem[i];
        viewHolder.textView.setText(subject);
        viewHolder.textView2.setText(sem);
    }

    @Override
    public int getItemCount() {
        return mList.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView,textView2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView =  itemView.findViewById(R.id.tvSub);
            textView2 = itemView.findViewById(R.id.tvSem);
        }
    }
}
