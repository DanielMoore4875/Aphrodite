//Jose Antonio Castro Teodoro n01384776 Section B
//Daniel Moore n01354875 Section B
//Ryan Black n01305403 Section B
//Alyssa Gomez n01042777 Section B

package ca.kainotomia.it.aphrodite;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

import ca.kainotomia.it.aphrodite.ui.home.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";



    @Override
    protected void onStop() {
        super.onStop();
        // IF THIS IS USED, user will be signed out when screen rotates
        // DO NOT USE
        // FirebaseAuth.getInstance().signOut();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Custom Action Bar
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);

        //for setting orientation
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // menu should be considered as top level destinations.
        // Passing each menu ID as a set of Ids because each
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_layout, R.id.navigation_account, R.id.navigation_voice)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        AlertDialog areYouSureExit = AskExit(); // Ask about exiting app
        areYouSureExit.show();
    }

    /*
    Pattern: Builder
        Using the Alert Dialog Builder to for the back button press
     */

    private AlertDialog AskExit() {
        return new AlertDialog.Builder(this)
                .setTitle(R.string.MA_AlertDialogExitApp_title)
                .setIcon(R.drawable.cancel_button)
                .setMessage(R.string.MA_AlertDialogExitApp_msg)
                .setPositiveButton(R.string.MA_AlertDialogExitApp_yes, (dialog, which) -> {
//                    FirebaseAuth.getInstance().signOut();
                    finish();

                })
                .setNegativeButton(R.string.MA_AlertDialogExitApp_no, (dialog, which) -> dialog.dismiss())
                .create();
    }

    public void handleButton(View view) {
        super.onBackPressed();
    }
}