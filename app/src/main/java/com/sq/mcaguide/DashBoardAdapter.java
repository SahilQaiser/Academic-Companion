package com.sq.mcaguide;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class DashBoardAdapter extends RecyclerView.Adapter<DashBoardAdapter.ViewHolder>
{
    private ArrayList<CardItem> mArrayList;
    private Context context;


    public  static  class ViewHolder extends RecyclerView.ViewHolder
    {
        // items from a cardView, yanai ki cardview kai sarai items, jin ko humaai runtime pai chage karna hai
        public TextView subName;
        public TextView semName;
        public CardView cardViewItem;

        public ViewHolder( View v)
        {
            super(v);
            //pass the values to the below objects in onBindViewHolder
            // but first we have to inflate the card_item layout file in onCreateViewHolders
            subName=v.findViewById(R.id.cardView_subject);
            semName=v.findViewById(R.id.cardview_sem);
            cardViewItem=v.findViewById(R.id.cardView);// for handling click event
        }
    }

    public DashBoardAdapter(ArrayList<CardItem> mArrayList,Context context)
    {
        this.mArrayList = mArrayList;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i)
    {
        //inflate the card_item layout file
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_cardview,parent,false);
        ViewHolder evh=new ViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        final CardItem currentItem=mArrayList.get(position);

        //setting values to the childrens of the cardview
        holder.subName.setText(currentItem.getSubjectName());
        holder.semName.setText(currentItem.getSemName());


        holder.cardViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"You selected "+currentItem.getSubjectName()+"\n"+currentItem.getSemName(),Toast.LENGTH_LONG).show();
            }
        });
        holder.cardViewItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context,"Wont Delete it Yet ;)",Toast.LENGTH_LONG).show();
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

}
