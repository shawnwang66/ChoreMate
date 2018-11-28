package model;

import android.content.Context;
import android.graphics.Color;
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
        private final TextView completedText;
        private final ImageView completedSign;
        private final ImageView checkbox;
        private final ImageView clockIcon;
        private final View lineBreak;

        //get each view from layout
        public MyViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.choreName);
            completedText = v.findViewById(R.id.completedText);
            dueDate=v.findViewById(R.id.dueDate);
            checkbox = v.findViewById(R.id.checkbox);
            completedSign = v.findViewById(R.id.completedSign);
            lineBreak = v.findViewById(R.id.dividerLine1);
            clockIcon = v.findViewById(R.id.clockImg1);

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
        if(position < chores.size()) {
            holder.name.setText(chores.get(position).getChoreName());
            holder.dueDate.setText(chores.get(position).getChoreDueDate() + "   " + chores.get(position).getChoreDueTime());
            holder.checkbox.setClickable(true);
            holder.checkbox.bringToFront();

            holder.checkbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String createdDate = new SimpleDateFormat("HH:mm MM/dd").format(new Date());
                    CompletedManager completedManager = CompletedManager.getInstance();
                    CompletedChore completedChore = new CompletedChore(null,chores.get(position).getChoreName(),createdDate,chores.get(position).getAssignee().getName(),0,0,false);
                    completedManager.chores.add(0,completedChore);
                    Notification Note1 = new Notification(R.drawable.john, "John",
                            createdDate,
                            "I have finished a chore(" + ChoreManager.getInstance().chores.get(position).getChoreName() +
                                    "), check it out!", "view");
                    NotificationManager.getInstance().notifications.add(0, Note1);
                    ChoreManager.getInstance().chores.remove(position);

                    MainActivity activity = (MainActivity) mContext;
                    FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.main_frame, new CompleteChoreFragment());
                    ft.commit();
                }
            });
            holder.completedSign.setVisibility(View.INVISIBLE);
            holder.completedText.setVisibility(View.INVISIBLE);
            holder.checkbox.setVisibility(View.VISIBLE);
            holder.name.setVisibility(View.VISIBLE);
            holder.dueDate.setVisibility(View.VISIBLE);
            holder.clockIcon.setVisibility(View.VISIBLE);
            holder.lineBreak.setVisibility(View.VISIBLE);


            Date currentDate = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("hh:mm aa MM/dd/yyyy");
            String dateString = ChoreManager.getInstance().chores.get(position).getChoreDueTime() + " "+
                    ChoreManager.getInstance().chores.get(position).getChoreDueDate();


            try {
                Date dueDate = formatter.parse(dateString);
                if(dueDate.compareTo(currentDate) < 0) {
                    holder.dueDate.setTextColor(Color.RED);
                    holder.clockIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.time_red));
                } else {
                    holder.dueDate.setTextColor(holder.completedText.getTextColors());
                    holder.clockIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.calendar_clock));

                }

            } catch (Exception e) {

            }


        } else {
            holder.completedSign.setVisibility(View.VISIBLE);
            holder.completedText.setVisibility(View.VISIBLE);
            holder.checkbox.setVisibility(View.INVISIBLE);
            holder.name.setVisibility(View.INVISIBLE);
            holder.dueDate.setVisibility(View.INVISIBLE);
            holder.clockIcon.setVisibility(View.INVISIBLE);
            holder.lineBreak.setVisibility(View.INVISIBLE);
            if(chores.size() == 0) {
                holder.completedText.setText("You have no upcoming chores");
            } else {
                holder.completedText.setText("That's all your chores this week");

            }

        }

    }

    @Override
    public int getItemCount() {
        return chores.size() + 1;
    }
}
