package ca.kainotomia.it.aphrodite;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    Handler handler = new Handler();

    //    private static int AUTH_REQUEST_CODE = 3222;
    private FirebaseAuth AphFBAuth;
    private FirebaseAuth.AuthStateListener authListen;
    private List<AuthUI.IdpConfig> providers;
    private DatabaseReference usersTree;


    @Override
    protected void onStart() {
        super.onStart();
        AphFBAuth.addAuthStateListener(authListen);
    }

    @Override
    protected void onStop() {
        if (authListen != null) {
            AphFBAuth.removeAuthStateListener(authListen);
        }
        //THIS WILL SIGN THE USER OUT
//        FirebaseAuth.getInstance().signOut();
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance(); // get the database
        usersTree = firebaseDatabase.getReference("users"); // the users node of the database
//        setContentView(R.layout.activity_login); //Firebase UI replaces this
        initAuth();
    }

    private void initAuth() {
        providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build(),    // Google Sign In
                new AuthUI.IdpConfig.EmailBuilder().build()     // Email Sign In
        );

        AphFBAuth = FirebaseAuth.getInstance(); // Starting state of firebase auth
        authListen = firebaseAuth -> {
            /*lamba replaces:
                authListen = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            */

            // Get user
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                /*
                How it should look in database
                users
                  |- Uid(user uid)
                        |- email: ...
                        |- name: ...
                 */
                usersTree.child(user.getUid()).child("name").setValue(user.getDisplayName());
                usersTree.child(user.getUid()).child("email").setValue(user.getEmail());

                System.out.println("\nName: " + user.getDisplayName() +
                        "\nUserID: " + user.getUid() +
                        "\nEmail: " + user.getEmail() +
                        "\nPhone Number: " + user.getPhoneNumber() +
                        "\nProvider ID: " + user.getProviderId() +
                        "\nTenant ID: " + user.getTenantId() +
                        "\nPhoto URL: " + user.getPhotoUrl());
                Toast.makeText(LoginActivity.this, "Welcome " + user.getDisplayName() + "!", Toast.LENGTH_SHORT).show();
                handler.postDelayed(() -> {
                    //Finished login, go to MainActivity
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }, 500);
            } else {
                //New way of logging in without using startActivityForResult
                Intent intent = AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setIsSmartLockEnabled(false)
                        .setLogo(R.drawable.aphrodite_logo)
                        .setTheme(R.style.LoginTheme)
                        .build();
                activityResultLauncher.launch(intent);
            }
        };
    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK) {
            // no request codes
            Intent data = result.getData();
        }
        System.out.println("HELLO THIS WAS RUN");
    });
}