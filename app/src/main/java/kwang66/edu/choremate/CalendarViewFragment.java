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
import model.CalendarViewAdapter;

public class CalendarViewFragment extends Fragment {

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    public CalendarViewFragment() {
        // Required empty public constructor
    }

    ImageView checkbox1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        final View view =  inflater.inflate(R.layout.calendar_view, container, false);
//        checkbox1 = (ImageView) view.findViewById(R.id.imageButton1);
//        checkbox1.setClickable(true);
//        checkbox1.bringToFront();
//        checkbox1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//          }
//        });
//
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.replace(R.id.main_frame, new CompleteChoreFragment());
//        ft.commit();
//
//        return view;
        View rootView = inflater.inflate(R.layout.view_completed, container, false);
        RecyclerView mRecyclerView = rootView.findViewById(R.id.recycler_list);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        CalendarViewFragment.LayoutManagerType mCurrentLayoutManagerType = CalendarViewFragment.LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        mRecyclerView.setLayoutManager(mLayoutManager);

        List<Chore> chores = new ArrayList<>();
        Chore chore1 = new Chore("Wash Dishes","Today, 7:00PM");
        Chore chore2 = new Chore("Throw Away Trash","Today, 10:00PM");
        Chore chore3 = new Chore("Vacuum Living Room","Thursday, 10:00PM");
        Chore chore4 = new Chore("Buy Coca-Cola","Thursday, 7:00PM");

        chores.add(chore1);
        chores.add(chore2);
        chores.add(chore3);
        chores.add(chore4);


        RecyclerView.Adapter mAdapter = new CalendarViewAdapter(chores,getContext());
        mRecyclerView.setAdapter(mAdapter);
        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
        mRecyclerView.setLayoutAnimation(animation);
        return rootView;
    }

}
