package kuba.kern.passholder.Navi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import kuba.kern.passholder.Authorization.MainActivity;
import kuba.kern.passholder.R;

public class NaviMain extends AppCompatActivity {

 //   FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

  //  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.buttonAddNew);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_navi_main);
        setContentView(R.layout.activity_navi_main);
//        if (user == null) {
//           // Intent intent = new Intent(this, MainActivity.class);
//            //   startActivity(intent);
//        }
//        else{
//            setContentView(R.layout.activity_navi_main);
//        }
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(NaviMain.this, NewRecord.class));
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_top_logout:
                Toast.makeText(this,"Wylogowano.",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_top_profile:
                Toast.makeText(this,"Do profilu.",Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch(item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_save:
                            selectedFragment = new SafeFragment();
                            break;
                        case R.id.nav_folder:
                            selectedFragment = new FolderFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };

    public void recordAdd(View view) {

        Intent intent = new Intent(this, NewRecord.class);
        startActivity(intent);
    }


}