//Jose Antonio Castro Teodoro n01384776 Section B
//Daniel Moore n01354875 Section B
//Ryan Black n01305403 Section B
//Alyssa Gomez n01042777 Section B
package ca.kainotomia.it.aphrodite;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int RC_SIGN_IN = 9001;

    private FirebaseAuth AphroditeFBAuth;

    private GoogleSignInClient AphroditeGSIC;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Configure Google Sign In
        GoogleSignInOptions gSIO = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.LA_webClientID_txt))
                .requestEmail()
                .build();

        AphroditeGSIC = GoogleSignIn.getClient(this, gSIO);

        // Initialize Firebase Auth
        AphroditeFBAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if a user is signed in (non-null) and update UI
        FirebaseUser currentUser = AphroditeFBAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result from Intent GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In Success, Authenticate with Firebase
                GoogleSignInAccount userAccount = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + userAccount.getId());

            } catch (ApiException e) {
                // Google Sign In failed, update UI
                Log.w(TAG, "Google Sign In Failed", e);
            }
        }
    }

    private void firebaseAuthWithGoogle(String userID) {
        AuthCredential creds = GoogleAuthProvider.getCredential(userID, null);
        AphroditeFBAuth.signInWithCredential(creds)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, user's signed in info can be used in app
                            Log.d(TAG, "signInWithCreds:success");
                            FirebaseUser user = AphroditeFBAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // Sign in failed, display message to user
                            Log.w(TAG, "signInWithCreds:fail", task.getException());
                            updateUI(null);
                        }
                    }
                });
    }

    private void signIn() {
        Intent signInIntent = AphroditeGSIC.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();
    }

    private void updateUI(FirebaseUser currentUser) {
        // REMOVE LOGIN SCREEN IF USER IS ALREADY LOGGED IN
        // STORE LOGGED IN STATE AND SHARE WITH OTHER ACTIVITIES
    }


    //    SignInButton signInButton;
//
//    private GoogleApiClient googleApiClient;
//    TextView textView;
//    private static final int RC_SIGN_IN = 1;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        // Configure Google Sign In
//        GoogleSignInOptions gSI = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken("862254589566-0cvfjpl3d7l16qh1v7rohm87t6cpg6ae.apps.googleusercontent.com")
//                .requestEmail()
//                .build();


//        GoogleSignInOptions gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//        googleApiClient=new GoogleApiClient.Builder(this)
//                .enableAutoManage(this,this)
//                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
//                .build();
//
//        // Set the dimensions of the sign-in button.
//        SignInButton signInButton = findViewById(R.id.sign_in_button);
//        signInButton.setOnClickListener(v -> {
//            Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
//            startActivityForResult(intent,RC_SIGN_IN);
//            finish();
//        });
//        signInButton.setSize(SignInButton.SIZE_STANDARD);
}

//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//
//    }
