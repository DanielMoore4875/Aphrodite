package ca.kainotomia.it.aphrodite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    Handler handler = new Handler();

    private static int AUTH_REQUEST_CODE = 3222;
    private FirebaseAuth AphFBAuth;
    private FirebaseAuth.AuthStateListener authListen;
    private List<AuthUI.IdpConfig> providers;


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
        FirebaseAuth.getInstance().signOut();
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);

        initAuth();
    }

    private void initAuth() {
        providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build()    // Google Sign In
        );

        AphFBAuth = FirebaseAuth.getInstance(); // Starting state of firebase auth
        authListen = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                // Get user
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    System.out.println("\nName: " + user.getDisplayName() +
                            "\nUserID: " + user.getUid() +
                            "\nEmail: " + user.getEmail() +
                            "\nPhone Number: " + user.getPhoneNumber() +
                            "\nProvider ID: " + user.getProviderId() +
                            "\nTenant ID: " + user.getTenantId() +
                            "\nPhoto URL: " + user.getPhotoUrl());
                    Toast.makeText(LoginActivity.this, "Logged In: " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
                    handler.postDelayed(() -> {
                        //LOGIN ACTIVITY HERE
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }, 500);
                } else {
                    // Login
                    startActivityForResult(AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .setIsSmartLockEnabled(false)
                            .setLogo(R.drawable.aphrodite_logo)
                            .setTheme(R.style.LoginTheme)
                            .build(), AUTH_REQUEST_CODE);
                }
            }
        };
    }
}