package model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import kwang66.edu.choremate.R;


public class CompletedChoreAdapter extends RecyclerView.Adapter<CompletedChoreAdapter.MyViewHolder> {

    private List<CompletedChore> chores;
    Context mContext;


    public CompletedChoreAdapter(List<CompletedChore> chores, Context mContext) {
        this.chores = chores;
        this.mContext = mContext;
    }

    public static class MyViewHolder extends ViewHolder {
        private final ImageView imageView;
        private final TextView name;
        private final Button pass;
        private final Button notpass;
        //get each view from layout
        public MyViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.name_time);
            imageView=v.findViewById(R.id.pic);
            pass = v.findViewById(R.id.pass);
            notpass = v.findViewById(R.id.notpass);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,final int position) {
        //TODO
        String subText = chores.get(position).getName()+": "+chores.get(position).getTime();
        holder.name.setText(subText);
        holder.imageView.setImageResource(chores.get(position).getImage());
        holder.pass.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                chores.get(position).setUpvote(chores.get(position).getUpvote()+1);
                NotificationManager notificationManager = NotificationManager.getInstance();
                String createdDate = new SimpleDateFormat("HH:mm MM/dd").format(new Date());
                Notification note = new Notification(R.drawable.john,"John",createdDate,"I have marked a chore("+chores.get(position).getName()+") as passed. " +
                        ". Status: "+chores.get(position).getUpvote()+" up votes, "+chores.get(position).getDownvote()+" down votes.",null);
                notificationManager.notifications.add(0,note);

                if (chores.get(position).getUpvote()==4){
                    User completer = UserManager.getInstance().getUser(chores.get(position).getCompleter());
                    Notification approval = new Notification(completer.getAvatar(),completer.getName(),createdDate,"I am approved to get my money back for completing "+chores.get(position).getName()+
                            "!",null);
                    notificationManager.notifications.add(0,approval);
                }
                chores.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, getItemCount());
                CharSequence text = "Chore marked as passed!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(mContext, text, duration);
                toast.show();
            }
        });
        holder.notpass.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                chores.get(position).setDownvote(chores.get(position).getDownvote()+1);
                NotificationManager notificationManager = NotificationManager.getInstance();
                String createdDate = new SimpleDateFormat("HH:mm MM/dd").format(new Date());
                Notification note = new Notification(R.drawable.john,"John",createdDate,"I have marked a chore("+chores.get(position).getName()+") as not passed" +
                        ". Status: "+chores.get(position).getUpvote()+" up votes, "+chores.get(position).getDownvote()+" down votes.",null);
                notificationManager.notifications.add(0,note);
                if (chores.get(position).getDownvote()==4){
                    User completer = UserManager.getInstance().getUser(chores.get(position).getCompleter());
                    Notification approval = new Notification(completer.getAvatar(),completer.getName(),createdDate,"I lose money for not completing "+chores.get(position).getName()+
                            ". Redo it to claim the ransom!",null);
                    notificationManager.notifications.add(0,approval);
                }
                chores.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, getItemCount());
                CharSequence text = "Chore marked as not-passed!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(mContext, text, duration);
                toast.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return chores.size();
    }
}
