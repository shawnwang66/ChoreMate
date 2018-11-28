package kwang66.edu.choremate;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import model.Chore;
import model.GroupViewAdapter;
import model.ChoreManager;

public class GroupCalendarViewFragment extends Fragment{

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    public GroupCalendarViewFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.view_completed, container, false);
        RecyclerView mRecyclerView = rootView.findViewById(R.id.recycler_list);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        GroupCalendarViewFragment.LayoutManagerType mCurrentLayoutManagerType = GroupCalendarViewFragment.LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        mRecyclerView.setLayoutManager(mLayoutManager);

        List<Chore> chores = ChoreManager.getInstance().groupChore;
        System.out.println(chores.size() + "UUU");


        RecyclerView.Adapter mAdapter = new GroupViewAdapter(chores,getContext());
        mRecyclerView.setAdapter(mAdapter);
        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
        mRecyclerView.setLayoutAnimation(animation);
        return rootView;
    }

}
