package kwang66.edu.choremate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class CompleteChoreFragment extends Fragment {

    public CompleteChoreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.complete_chore, container, false);
        Button button = view.findViewById(R.id.send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft  = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_frame, new CalendarViewFragment());
                ft.commit();
            }
        });

        return view;
    }
}
