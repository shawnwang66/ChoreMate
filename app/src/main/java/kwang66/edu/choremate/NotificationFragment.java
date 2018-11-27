package kwang66.edu.choremate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import java.util.ArrayList;
import java.util.List;

import model.CompletedChore;
import model.CompletedChoreAdapter;
import model.Notification;
import model.NotificationAdapter;

public class NotificationFragment extends Fragment {


    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    public NotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.notification, container, false);
        RecyclerView mRecyclerView = rootView.findViewById(R.id.notification_list);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        NotificationFragment.LayoutManagerType mCurrentLayoutManagerType = NotificationFragment.LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        mRecyclerView.setLayoutManager(mLayoutManager);

        List<Notification> notifications = new ArrayList<>();
        Notification Note1 = new Notification(R.drawable.john,"John","12:30pm, Nov 27th","I have finished a chore(clean dishes),check it out!","view");
        Notification Note2 = new Notification(R.drawable.dez,"Dez","3:24pm, Nov 27th","I have created a chore(Buy Coke) and assigned it to you.","detail");
        Notification Note3 = new Notification(R.drawable.matt,"Matt", "11:14am, Nov 24th","I have created a chore(clean dishes) and assigned to John.",null);

        notifications.add(Note1);
        notifications.add(Note2);
        notifications.add(Note3);

        RecyclerView.Adapter mAdapter = new NotificationAdapter(notifications,getContext());
        mRecyclerView.setAdapter(mAdapter);
        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
        mRecyclerView.setLayoutAnimation(animation);
        return rootView;

    }
}
