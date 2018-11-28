package model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import kwang66.edu.choremate.MainActivity;
import kwang66.edu.choremate.CompleteChoreFragment;

import kwang66.edu.choremate.R;
public class GroupViewAdapter extends RecyclerView.Adapter<GroupViewAdapter.MyViewHolder> {


    private List<Chore> chores;
    Context mContext;


    public GroupViewAdapter(List<Chore> chores, Context mContext) {
        this.chores = chores;
        this.mContext = mContext;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView dueDate;
        private final TextView completedText;
        private final ImageView completedSign;
        private final ImageView clockIcon;
        private final ImageView avatarIcon;
        private final View lineBreak;

        //get each view from layout
        public MyViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.choreName);
            completedText = v.findViewById(R.id.completedText);
            dueDate = v.findViewById(R.id.dueDate);
            completedSign = v.findViewById(R.id.completedSign);
            lineBreak = v.findViewById(R.id.dividerLine1);
            clockIcon = v.findViewById(R.id.clockImg1);
            avatarIcon = v.findViewById(R.id.avatarImage);

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.group_view_list_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        //TODO
        if (position < chores.size()) {
            holder.name.setText(chores.get(position).getChoreName());
            holder.avatarIcon.setImageResource(chores.get(position).getAssignee().getAvatar());
            holder.dueDate.setText(chores.get(position).getChoreDueDate() + "   " + chores.get(position).getChoreDueTime());

            holder.completedSign.setVisibility(View.INVISIBLE);
            holder.completedText.setVisibility(View.INVISIBLE);
            holder.name.setVisibility(View.VISIBLE);
            holder.dueDate.setVisibility(View.VISIBLE);
            holder.clockIcon.setVisibility(View.VISIBLE);
            holder.avatarIcon.setVisibility(View.VISIBLE);
            holder.lineBreak.setVisibility(View.VISIBLE);


        } else {
            holder.completedSign.setVisibility(View.VISIBLE);
            holder.completedText.setVisibility(View.VISIBLE);
            holder.name.setVisibility(View.INVISIBLE);
            holder.dueDate.setVisibility(View.INVISIBLE);
            holder.clockIcon.setVisibility(View.INVISIBLE);
            holder.lineBreak.setVisibility(View.INVISIBLE);
            holder.avatarIcon.setVisibility(View.INVISIBLE);

            if (chores.size() == 0) {
                holder.completedText.setText("No upcoming chores");
            } else {
                holder.completedText.setText("That's all for your household");

            }

        }

    }

    @Override
    public int getItemCount() {
        return chores.size() + 1;
    }

}
