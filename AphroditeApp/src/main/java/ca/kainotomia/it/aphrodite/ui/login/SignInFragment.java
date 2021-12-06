package ca.kainotomia.it.aphrodite.ui.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.Objects;

import ca.kainotomia.it.aphrodite.MainActivity;
import ca.kainotomia.it.aphrodite.R;


public class SignInFragment extends Fragment {

    private EditText emailET_L;
    private EditText passwordET_L;

    private Button loginBtn_L;

    private ProgressBar signInProgress;

    private FirebaseAuth firebaseAuthSignIn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sign_in, container, false);

        signInProgress = root.findViewById(R.id.FSI_progressBar);
        emailET_L = root.findViewById(R.id.FSI_emailEditTXT);
        passwordET_L = root.findViewById(R.id.FSI_passEditTXT);

        loginBtn_L = root.findViewById(R.id.FSI_signin);

        loginBtn_L.setOnClickListener(v -> signInUser());

        firebaseAuthSignIn = FirebaseAuth.getInstance();
        return root;
    }

    private void signInUser() {
        if (!emailET_L.getText().toString().isEmpty() && !passwordET_L.getText().toString().isEmpty()) {

            if (firebaseAuthSignIn != null) {
                signInProgress.setVisibility(View.VISIBLE);
                loginBtn_L.setEnabled(false);

                firebaseAuthSignIn.signInWithEmailAndPassword(emailET_L.getText().toString(),
                        passwordET_L.getText().toString())
                        .addOnSuccessListener(authResult -> {
                            startActivity(new Intent(requireActivity().getApplicationContext(), MainActivity.class));
                            signInProgress.setVisibility(View.INVISIBLE);
                            loginBtn_L.setEnabled(true);

                            System.out.println("\nName: " + Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getDisplayName() +
                                    "\nUserID: " + FirebaseAuth.getInstance().getCurrentUser().getUid() +
                                    "\nEmail: " + FirebaseAuth.getInstance().getCurrentUser().getEmail() +
                                    "\nPhone Number: " + FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber() +
                                    "\nProvider ID: " + FirebaseAuth.getInstance().getCurrentUser().getProviderId() +
                                    "\nTenant ID: " + FirebaseAuth.getInstance().getCurrentUser().getTenantId() +
                                    "\nPhoto URL: " + FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl());
                            requireActivity().finish();
                        })
                        .addOnFailureListener(e -> {
                            signInProgress.setVisibility(View.INVISIBLE);

                            loginBtn_L.setEnabled(true);
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();

                        });
            }
        } else {
            Toast.makeText(getActivity(), getString(R.string.FSI_fieldEmptyError), Toast.LENGTH_SHORT).show();
        }
    }
}