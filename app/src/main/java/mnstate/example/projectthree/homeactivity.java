package mnstate.example.projectthree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class homeactivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeactivity);

        Bundle bundle=getIntent().getExtras();
        String arrived=bundle.getString("usernamego");
        TextView btext=findViewById(R.id.btnrec);
        btext.setText("Welcome ! "+arrived);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.drawer_nav);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_camera:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame_container, new Welcome(), Welcome.FRAGMENT_TAG)
                                .commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_gallery:
                        Intent i = new Intent(homeactivity.this, working.class);
                        startActivity(i);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_upload:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame_container, new About(), About.FRAGMENT_TAG)
                                .commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_covid:
                       //working
                        Intent iintent = new Intent(homeactivity.this, covidhome.class);
                        startActivity(iintent);

                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new Welcome(), Welcome.FRAGMENT_TAG).commit();
    }
}