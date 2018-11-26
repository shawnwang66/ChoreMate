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
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import model.CompletedChore;
import model.CompletedChoreAdapter;

public class ViewCompletedFragment extends Fragment {

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    public ViewCompletedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.view_completed, container, false);
        RecyclerView mRecyclerView = rootView.findViewById(R.id.recycler_list);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        LayoutManagerType mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        mRecyclerView.setLayoutManager(mLayoutManager);

        List<CompletedChore> chores = new ArrayList<>();
        CompletedChore chore1 = new CompletedChore(R.drawable.floors,"Cleaning Floor","8:23am, Nov 18th");
        CompletedChore chore2 = new CompletedChore(R.drawable.dishes,"Cleaning Dishes","4:45pm, Nov 19th");
        CompletedChore chore3 = new CompletedChore(R.drawable.clean_dishes,"Cleaning Dishes","12:45pm, Nov 19th");

        chores.add(chore1);
        chores.add(chore2);
        chores.add(chore3);
        RecyclerView.Adapter mAdapter = new CompletedChoreAdapter(chores,getContext());
        mRecyclerView.setAdapter(mAdapter);
        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
        mRecyclerView.setLayoutAnimation(animation);
        return rootView;
    }
}
