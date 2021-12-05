package ca.kainotomia.it.aphrodite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;

import ca.kainotomia.it.aphrodite.ui.login.SignInFragment;
import ca.kainotomia.it.aphrodite.ui.login.SignUpFragment;

public class LoginActivity extends AppCompatActivity {

    SignInFragment signInFragment;
    SignUpFragment signUpFragment;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();
        // if user is logged in, skip this page
        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            this.finish();
        }
        signInFragment = new SignInFragment();
        signUpFragment = new SignUpFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.login_frag_host, signInFragment);
        transaction.add(R.id.login_frag_host, signUpFragment);
        transaction.addToBackStack("login");
        transaction.commit();
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
//

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }
//    Handler handler = new Handler();
//
//    //    private static int AUTH_REQUEST_CODE = 3222;
//    private FirebaseAuth AphFBAuth;
//    private FirebaseAuth.AuthStateListener authListen;
//    private List<AuthUI.IdpConfig> providers;
//
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        AphFBAuth.addAuthStateListener(authListen);
////        AphFBAuth.signIn
//    }
//
//    @Override
//    protected void onStop() {
//        if (authListen != null) {
//            AphFBAuth.removeAuthStateListener(authListen);
//        }
//        //THIS WILL SIGN THE USER OUT
////        FirebaseAuth.getInstance().signOut();
//        super.onStop();
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_login); //Firebase UI replaces this
//        initAuth();
//    }
//
//    private void initAuth() {
//
//        providers = Arrays.asList(
//                new AuthUI.IdpConfig.GoogleBuilder().build(),    // Google Sign In
//                new AuthUI.IdpConfig.EmailBuilder().setRequireName(true).build()     // Email Sign In
//        );
//
//        AphFBAuth = FirebaseAuth.getInstance(); // Starting state of firebase auth
//        authListen = firebaseAuth -> {
//            /*lamba replaces:
//                authListen = new FirebaseAuth.AuthStateListener() {
//                @Override
//                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//            */
//
//            // Get user
//            FirebaseUser user = firebaseAuth.getCurrentUser();
//            if (user != null) {
//                UpdateDBNode usersNode = new UpdateDBNode("users");
//
//
//                if (usersNode.addUser(user.getUid(), user.getDisplayName(), user.getEmail())) {
//                    Toast.makeText(LoginActivity.this, getString(R.string.Login_welcomeName_txt) + user.getDisplayName(), Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(LoginActivity.this, getString(R.string.Login_userError_txt), Toast.LENGTH_SHORT).show();
//                }
//
//                System.out.println("\nName: " + user.getDisplayName() +
//                        "\nUserID: " + user.getUid() +
//                        "\nEmail: " + user.getEmail() +
//                        "\nPhone Number: " + user.getPhoneNumber() +
//                        "\nProvider ID: " + user.getProviderId() +
//                        "\nTenant ID: " + user.getTenantId() +
//                        "\nPhoto URL: " + user.getPhotoUrl());
//                handler.postDelayed(() -> {
//                    //Finished login, go to MainActivity
//
//                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                    finish();
//                }, 500);
//            } else {
//                //New way of logging in without using startActivityForResult
//                Intent intent = AuthUI.getInstance()
//                        .createSignInIntentBuilder()
//                        .setAvailableProviders(providers)
//                        .setIsSmartLockEnabled(false)
//                        .setLogo(R.drawable.aphrodite_logo)
//                        .setTheme(R.style.LoginTheme)
//                        .build();
//                activityResultLauncher.launch(intent);
//            }
//        };
//    }
//
//    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
//        if (result.getResultCode() == Activity.RESULT_OK) {
//            // no request codes
//            Intent data = result.getData();
//        }
//    });
}