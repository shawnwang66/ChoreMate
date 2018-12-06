package kwang66.edu.choremate;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Button;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Time;
import java.util.Date;

import model.Chore;
import model.ChoreManager;
import model.Notification;
import model.NotificationManager;
import model.UserManager;

public class CreateChoreFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    public CreateChoreFragment() {
        // Required empty public constructor
    }

    private DatePickerDialog.OnDateSetListener dateSetListener;
    private TimePickerDialog.OnTimeSetListener timeSetListener;

    private EditText nameSetter;
    private RadioGroup difficultySetter;
    private EditText dateSetter;
    private EditText timeSetter;
    private Spinner assigner;
    private Button createButton;

    private String choreName;
    private int choreDifficulty;
    private String dateText;
    private String timeText;
    private String assigneeText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View choreView = inflater.inflate(R.layout.create_chore, container, false);

        // Naming chore
        nameSetter = choreView.findViewById(R.id.name);

        // Difficulty selection
        difficultySetter = choreView.findViewById(R.id.radio);
        difficultySetter.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                choreDifficulty = group.indexOfChild(group.findViewById(checkedId)) + 1;
            }
        });

        // Date selection
        dateSetter = choreView.findViewById(R.id.date);
        dateSetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(v);
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month += 1;
                dateText = month + "/" + dayOfMonth + "/" + year;
                dateSetter.setText(dateText);
            }
        };

        // Time selection
        timeSetter = choreView.findViewById(R.id.time);
        timeSetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime(v);
            }
        });

        timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Time time = new Time(hourOfDay, minute, 0);
                Format formatter = new SimpleDateFormat("hh:mm a");
                timeText = formatter.format(time);
                timeSetter.setText((CharSequence) timeText);
            }
        };

        // Assignee selection
        ArrayAdapter<CharSequence> assignAdapter = ArrayAdapter.createFromResource(
                this.getActivity(),
                R.array.names,
                android.R.layout.simple_spinner_item);
        assignAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        assigner = choreView.findViewById(R.id.assign);
        assigner.setAdapter(assignAdapter);
        assigner.setOnItemSelectedListener(this);

        // Creating chore
        createButton = choreView.findViewById(R.id.create);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createChore(v);
            }
        });

        return choreView;
    }

    private void setDate(View view) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog picker = new DatePickerDialog(
                this.getActivity(),
                R.style.DatePickerTheme,
                dateSetListener,
                year, month, day);

        picker.show();
    }

    private void setTime(View view) {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);

        TimePickerDialog picker = new TimePickerDialog(
                this.getActivity(),
                R.style.TimePickerTheme,
                timeSetListener,
                hour, minute, false);

        picker.show();
    }

    private void createChore(View view) {
        choreName = nameSetter.getText().toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(
                this.getActivity(),
                R.style.AlertDialogTheme);

        builder.setTitle(choreName);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.setMessage(Html.fromHtml(summaryBuilder(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            builder.setMessage(Html.fromHtml(summaryBuilder()));
        }
        builder.setCancelable(true);

        builder.setPositiveButton("Assign Chore!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // create chore object and add to chore list
                Chore createdChore = new Chore(
                        choreName, dateText, timeText,
                        UserManager.getInstance().getUser(assigneeText), choreDifficulty);
                if (createdChore.getAssignee().getName() == UserManager.getInstance().users.get(0).getName()) {
                    // add a new chore for yourself
                    ChoreManager.getInstance().chores.add(createdChore);

                } else {
                    // add a chore for other people
                    ChoreManager.getInstance().groupChore.add(createdChore);

                }
                String createdDate = new SimpleDateFormat("HH:mm MM/dd").format(new Date());
                Notification Note1 = new Notification(R.drawable.john, "John",
                        createdDate,
                        "John has created a chore (" + createdChore.getChoreName() +
                                ") and assigned it to " + createdChore.getAssignee().getName() +
                                ".", null);
                NotificationManager.getInstance().notifications.add(0, Note1);
                // go to calendar view
                if (createdChore.getAssignee().getName() == UserManager.getInstance().users.get(0).getName()) {
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.main_frame, new CalendarViewFragment());
                    ft.commit();
                } else {
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.main_frame, new GroupCalendarViewFragment());
                    ft.commit();
                }
            }
        });

        builder.setNegativeButton("Edit Chore", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog choreSummary = builder.create();
        choreSummary.show();
    }

    private String summaryBuilder() {
        String smallBreak = "<br>";

        String review = "Please review the chore's settings before assigning it:<br><br>";
        String assignee = "<b><i>Assigned To: </i></b>" + assigneeText + "<br>";
        String deadline = "<b><i>Due By: </i></b>" + dateText + " at " + timeText + "<br>";
        String difficulty = null;
        String cost = null;
        switch (choreDifficulty) {
            case 1:
                difficulty = "<b><i>Chore Difficulty: </i></b>" + "Very easy<br>";
                cost = "<b><i>Chore Cost: </i></b>" + "$1<br>";
                break;
            case 2:
                difficulty = "<b><i>Chore Difficulty: </i></b>" + "Easy<br>";
                cost = "<b><i>Chore Cost: </i></b>" + "$2<br>";
                break;
            case 3:
                difficulty = "<b><i>Chore Difficulty: </i></b>" + "Medium<br>";
                cost = "<b><i>Chore Cost: </i></b>" + "$3<br>";
                break;
            case 4:
                difficulty = "<b><i>Chore Difficulty: </i></b>" + "Hard<br>";
                cost = "<b><i>Chore Cost: </i></b>" + "$4<br>";
                break;
            case 5:
                difficulty = "<b><i>Chore Difficulty: </i></b>" + "Very hard<br>";
                cost = "<b><i>Chore Cost: </i></b>" + "$5<br>";
                break;
        }

        return review + assignee + smallBreak + deadline + smallBreak + difficulty + smallBreak + cost;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.assign:
                assigneeText = assigner.getSelectedItem().toString();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        assigneeText = "Select a Member";
    }
}
