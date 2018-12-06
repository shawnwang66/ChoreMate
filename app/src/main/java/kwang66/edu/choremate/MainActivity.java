package kwang66.edu.choremate;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import model.CompletedChore;
import model.CompletedManager;
import model.UserManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        CompletedManager completedManager = CompletedManager.getInstance();
        List<CompletedChore> chores = new ArrayList<>();
        CompletedChore chore1 = new CompletedChore(getResources().getDrawable(R.drawable.floors),"Cleaning Floor","8:23am, Nov 18th","Dez",2,0,true);
        CompletedChore chore2 = new CompletedChore(getResources().getDrawable(R.drawable.dishes),"Cleaning Dishes","4:45pm, Nov 19th","Gunther",0,3,true);
        CompletedChore chore3 = new CompletedChore(getResources().getDrawable(R.drawable.clean_dishes),"Cleaning Dishes","12:45pm, Nov 19th","Dez",3,0,true);

        chores.add(chore1);
        chores.add(chore2);
        chores.add(chore3);

        completedManager.chores=chores;

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        TextView headerName = (TextView) header.findViewById(R.id.userName);
        ImageView headerAvatar = (ImageView) header.findViewById(R.id.imageView);
        headerAvatar.setImageResource(UserManager.getInstance().users.get(0).getAvatar());
        headerName.setText(UserManager.getInstance().users.get(0).getName());

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_frame, new CalendarViewFragment());
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.nav_create_household) {
            fragment = new CreateHouseholdFragment();
        } else if (id == R.id.nav_create_chore) {
            fragment = new CreateChoreFragment();
        } else if (id == R.id.nav_calendar) {
            fragment=new CalendarViewFragment();
        }  else if (id == R.id.nav_group_calendar) {
            fragment = new GroupCalendarViewFragment();
        } else if (id == R.id.nav_completed) {
            fragment = new ViewCompletedFragment();
        } else if (id == R.id.nav_notification){
            fragment = new NotificationFragment();
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_frame, fragment);
            ft.commit();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
