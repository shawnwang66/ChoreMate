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

import org.w3c.dom.Text;

import java.util.List;

import kwang66.edu.choremate.R;


public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {

    private List<Notification> notifications;
    Context mContext;


    public NotificationAdapter(List<Notification> notifications, Context mContext) {
        this.notifications = notifications;
        this.mContext = mContext;
    }

    public static class MyViewHolder extends ViewHolder {
        private final ImageView avatar;
        private final TextView name;
        private final TextView time;
        private final TextView msg;
        private final TextView action;
        //get each view from layout
        public MyViewHolder(View v) {
            super(v);
            avatar = v.findViewById(R.id.notification_avatar);
            name = v.findViewById(R.id.notification_name);
            time = v.findViewById(R.id.notification_time);
            msg = v.findViewById(R.id.notification_msg);
            action = v.findViewById(R.id.notification_action);
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
        holder.avatar.setImageResource();

        String subText = notifications.get(position).getName()+": "+notifications.get(position).getTime();
        holder.name.setText(subText);
        holder.imageView.setImageResource(chores.get(position).getImage());
        holder.pass.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
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
        return notifications.size();
    }
}
