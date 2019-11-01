/*
package com.example.flowerpotapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private MainActivity parent;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            //name = itemView.findViewById(R.id.device_name);
        }
    }

    public MyAdapter(Context context){
        this.context = context;
        this.parent = (MainActivity) context;
    }

    @NonNull
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType){
        Log.d("posts_tag", "create");
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.device_layout, viewGroup, false);
        return new MyViewHolder(view);
    }

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolderTemp, final int position){
        Log.d("posts_tag", "bind");
        final Flowerpot flowerpot = parent.database.getFlowerpot(position);
        final MyViewHolder viewHolder = (MyViewHolder) viewHolderTemp;
        viewHolder.name.setText(flowerpot.name);
    }

    public int getItemCount(){
        return parent.database.getItemCount();
    }
}

 */
