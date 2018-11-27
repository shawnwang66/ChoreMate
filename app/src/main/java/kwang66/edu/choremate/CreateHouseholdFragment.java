package kwang66.edu.choremate;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class CreateHouseholdFragment extends Fragment {

    public ImageView checkbox1;
    public ImageView checkbox2;
    public ImageView checkbox3;
    public Button createHouse;
    public TextView member;
    public ImageView addBut;
    public EditText addMember;
    public boolean isChecked = false;

    public CreateHouseholdFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.create_household, container, false);



        addBut = (ImageView) view.findViewById(R.id.addButton);
        addBut.setClickable(true);
        addBut.bringToFront();
        addBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* String content = ((EditText)v.findViewById(R.id.addMember)).getText().toString();
                (TextView)v.findViewById(R.id.textView4).setText(content); */
                //v.findViewById(R.id.textView4).setVisibility(View.VISIBLE);
                member = (TextView) view.findViewById(R.id.textView4);
                member.setVisibility(View.VISIBLE);

                addMember = (EditText) view.findViewById(R.id.addMember);
                addMember.setVisibility(View.INVISIBLE);

            }
        });


            checkbox1 = (ImageView) view.findViewById(R.id.check1);
            checkbox1.setClickable(true);
            checkbox1.bringToFront();
            checkbox1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkbox1.setBackgroundResource(R.drawable.checkclicked);
                    checkbox2.setBackgroundResource(R.drawable.check);
                    checkbox3.setBackgroundResource(R.drawable.check);
                }
            });

            checkbox2 = (ImageView) view.findViewById(R.id.check2);
            checkbox2.setClickable(true);
            checkbox2.bringToFront();
            checkbox2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkbox2.setBackgroundResource(R.drawable.checkclicked);
                    checkbox1.setBackgroundResource(R.drawable.check);
                    checkbox3.setBackgroundResource(R.drawable.check);
                }
            });


            checkbox3 = (ImageView) view.findViewById(R.id.check3);
            checkbox3.setClickable(true);
            checkbox3.bringToFront();
            checkbox3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkbox3.setBackgroundResource(R.drawable.checkclicked);
                    checkbox1.setBackgroundResource(R.drawable.check);
                    checkbox2.setBackgroundResource(R.drawable.check);
                }
            });

        createHouse = (Button) view.findViewById(R.id.createHousehold);
        createHouse.setClickable(true);
        createHouse.bringToFront();
        createHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.main_frame, new CalendarViewFragment());
                ft.commit();
            }
        });
        return view;
    }
}
