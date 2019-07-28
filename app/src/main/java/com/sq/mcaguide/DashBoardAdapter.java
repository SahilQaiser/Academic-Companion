package com.sq.mcaguide;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class DashBoardAdapter extends RecyclerView.Adapter<DashBoardAdapter.ViewHolder>  {
    private ArrayList<CardItem> mArrayList;
    private Context context;
    private boolean delete;
    View view;
    int pos;


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
        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_cardview,parent,false);
        ViewHolder evh=new ViewHolder(view);
        return evh;
    }


    public void showPopup(View v, final Context c, final String subID) {
        PopupMenu popup = new PopupMenu(c, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.sub_click_menu, popup.getMenu());
        popup.show();

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                int id=item.getItemId();

                switch(id)
                {
                    case R.id.menu_notes:
                        Intent n = new Intent(context,ViewPDF.class);
                        n.putExtra("SUBID",subID);
                        (c).startActivity(n);
                        //Toast.makeText(c, "NOtes popup "+subID, Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.menu_syllabus:
                        Toast.makeText(c, "Syllabus popup", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_papers:
                        Toast.makeText(c, "Papers popup", Toast.LENGTH_SHORT).show();
                        break;
                }

                return false;
            }
        });
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position)
    {
        final CardItem currentItem=mArrayList.get(position);

        //setting values to the childrens of the cardview
        final String subject=currentItem.getSubjectName();
        String semester=currentItem.getSemName();
        final String url=currentItem.getUrl();
        final String id=subject+"_"+semester;
        holder.subName.setText(subject);
        holder.semName.setText(semester);


        holder.cardViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"You selected "+currentItem.getSubjectName()+"\n"+currentItem.getSemName(),Toast.LENGTH_LONG).show();
                //Custom menu
                //View v=views.get(position);
                showPopup(v,context,subject);

            }
        });

        holder.cardViewItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view)
            {
                showDialog(context,position);
                return true;
            }
        });

    }

    private boolean showDialog(final Context context, int position)
    {
        pos=position;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Delete");
        alertDialog.setMessage("Are you sure to delete the subject?");
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Log.d("position", ""+pos);
                try {
                    mArrayList.remove(pos);
                }catch (IndexOutOfBoundsException e)
                {
                    pos=mArrayList.size()-1;
                    mArrayList.remove(pos);
                }
                Log.d("size_", ""+mArrayList.size());
                notifyItemRemoved(pos);
                saveData(mArrayList);
                Toast.makeText(context,"Subject Deleted",Toast.LENGTH_LONG).show();
            }
        })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog dialog = alertDialog.create();
        dialog.show();
        return delete;
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    private void saveData(ArrayList<CardItem> list) {
        SharedPreferences sharedPreferences = MainActivity.context.getSharedPreferences("Subject", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString("subject_list", json);
        editor.apply();
    }

}
