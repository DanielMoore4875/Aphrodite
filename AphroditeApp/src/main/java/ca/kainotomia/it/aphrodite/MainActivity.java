//Jose Antonio Castro Teodoro n01384776 Section B
//Daniel Moore n01354875 Section B
//Ryan Black n01305403 Section B
//Alyssa Gomez n01042777 Section B

package ca.kainotomia.it.aphrodite;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private boolean loggedIn = false;
//    private Bundle loggedInData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent login = new Intent(this, LoginActivity.class);
            startActivity(login);
            loggedIn = true;
//        loggedInData = savedInstanceState;
//        loggedIn = savedInstanceState.getBoolean("loggedIn", false); // user hasn't logged in yet


        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_layout, R.id.navigation_account, R.id.navigation_voice)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle activityData) {
        super.onSaveInstanceState(activityData);
//        activityData.putBoolean("loggedIn", loggedIn); // store whether the user is already logged in

    }

//    @Override
//    protected void onUserLeaveHint() {
////        super.onUserLeaveHint();
//        loggedIn = false; // user pressed home
//    }
//
//    @Override
//    protected void onPostResume() {
//        super.onPostResume();
//        if (!loggedIn) {
//            // Log in activity
//            Intent login = new Intent(this, LoginActivity.class);
//            startActivity(login);
//        }
//    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        AlertDialog areYouSureExit = AskExit(); // Ask about exiting app
        areYouSureExit.show();
    }

    private AlertDialog AskExit() {
        return new AlertDialog.Builder(this)
                .setTitle(R.string.MA_AlertDialogExitApp_title)
                .setIcon(R.drawable.cancel_button)
                .setMessage(R.string.MA_AlertDialogExitApp_msg)
                .setPositiveButton(R.string.MA_AlertDialogExitApp_yes, (dialog, which) -> finish())
                .setNegativeButton(R.string.MA_AlertDialogExitApp_no, (dialog, which) -> dialog.dismiss())
                .create();
    }
}