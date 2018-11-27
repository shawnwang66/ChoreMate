package model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

import kwang66.edu.choremate.MainActivity;
import kwang66.edu.choremate.CompleteChoreFragment;

import kwang66.edu.choremate.R;

public class CalendarViewAdapter extends RecyclerView.Adapter<CalendarViewAdapter.MyViewHolder> {

    private List<Chore> chores;
    Context mContext;


    public CalendarViewAdapter(List<Chore> chores, Context mContext) {
        this.chores = chores;
        this.mContext = mContext;
    }

    public static class MyViewHolder extends ViewHolder {
        private final TextView name;
        private final TextView dueDate;
        private final ImageView checkbox;
        //get each view from layout
        public MyViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.choreName);
            dueDate=v.findViewById(R.id.dueDate);
            checkbox = v.findViewById(R.id.checkbox);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.calendar_view_list_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,final int position) {
        //TODO
        holder.name.setText(chores.get(position).getChoreaName());
        holder.dueDate.setText(chores.get(position).getChoreDueDate());
        holder.checkbox.setClickable(true);
        holder.checkbox.bringToFront();

        holder.checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity activity = (MainActivity)mContext;
                FragmentTransaction ft  = activity.getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.main_frame, new CompleteChoreFragment());
                ft.commit();

            }
        });

    }

    @Override
    public int getItemCount() {
        return chores.size();
    }
}
