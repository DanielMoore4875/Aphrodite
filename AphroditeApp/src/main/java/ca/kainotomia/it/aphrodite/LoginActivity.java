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
                .setPositiveButton(R.string.MA_AlertDialogExitApp_yes, (dialog, which) -> finish())
                .setNegativeButton(R.string.MA_AlertDialogExitApp_no, (dialog, which) -> dialog.dismiss())
                .create();
    }


    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

}