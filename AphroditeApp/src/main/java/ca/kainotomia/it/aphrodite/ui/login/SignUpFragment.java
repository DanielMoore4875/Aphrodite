package ca.kainotomia.it.aphrodite.ui.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.Objects;

import ca.kainotomia.it.aphrodite.R;


public class SignUpFragment extends Fragment {

    private EditText nameET_R;
    private EditText emailET_R;
    private EditText passwordET_R;

    private Button registerBtn_R;

    private Button goToLoginBtn;

    private ProgressBar signUpProgress;

    private FirebaseAuth firebaseAuthSignUp;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sign_up, container, false);

        signUpProgress = root.findViewById(R.id.FSU_progressBar);

        nameET_R = root.findViewById(R.id.FSU_nameEditTXT);
        emailET_R = root.findViewById(R.id.FSU_emailEditTXT);
        passwordET_R = root.findViewById(R.id.FSU_passEditTXT);

        registerBtn_R = root.findViewById(R.id.FSU_signup);
        registerBtn_R.setOnClickListener(v -> checkUserExists());

        goToLoginBtn = root.findViewById(R.id.FSU_goToLoginBtn);
        goToLoginBtn.setOnClickListener(v -> {
            SignInFragment signInFragment = new SignInFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.login_frag_host, signInFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });
        firebaseAuthSignUp = FirebaseAuth.getInstance();

        return root;
    }


    public void createUser() {
        if (!nameET_R.getText().toString().isEmpty() && !emailET_R.getText().toString().isEmpty() && !passwordET_R.getText().toString().isEmpty()) {

            UserProfileChangeRequest addUserName = new UserProfileChangeRequest.Builder()
                    .setDisplayName(nameET_R.getText().toString())
                    .build();


            signUpProgress.setVisibility(View.VISIBLE);
            registerBtn_R.setEnabled(false);
            goToLoginBtn.setEnabled(false);
            firebaseAuthSignUp.createUserWithEmailAndPassword(emailET_R.getText().toString(),
                    passwordET_R.getText().toString()).addOnSuccessListener(authResult -> {
                        signUpProgress.setVisibility(View.INVISIBLE);
                        registerBtn_R.setEnabled(true);
                        goToLoginBtn.setEnabled(true);
                        emailET_R.setText("");
                        nameET_R.setText("");
                        passwordET_R.setText("");

                        Objects.requireNonNull(firebaseAuthSignUp.getCurrentUser()).updateProfile(addUserName)
                                .addOnFailureListener(e -> Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show());

                        if (firebaseAuthSignUp.getCurrentUser() != null) {
                            firebaseAuthSignUp.signOut();
                            SignInFragment signInFragment = new SignInFragment();
                            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                            transaction.replace(R.id.login_frag_host, signInFragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                        }
                    }).addOnFailureListener(e -> {
                        signUpProgress.setVisibility(View.INVISIBLE);
                        registerBtn_R.setEnabled(true);
                        goToLoginBtn.setEnabled(true);
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        } else {
            Toast.makeText(getActivity(), getString(R.string.FSU_emptyFieldsError), Toast.LENGTH_SHORT).show();
        }
    }

    private void checkUserExists() {
        if (firebaseAuthSignUp != null && !emailET_R.getText().toString().isEmpty() && !passwordET_R.getText().toString().isEmpty()) {
            signUpProgress.setVisibility(View.VISIBLE);
            registerBtn_R.setEnabled(false);
            goToLoginBtn.setEnabled(false);

            firebaseAuthSignUp.fetchSignInMethodsForEmail(emailET_R.getText().toString())
                    .addOnCompleteListener(task -> {
                        boolean checkResult = !Objects.requireNonNull(Objects.requireNonNull(task.getResult()).getSignInMethods()).isEmpty();

                        if (!checkResult) {
                            createUser();
                        } else {
                            Toast.makeText(getActivity(), getString(R.string.FSU_userExistsError), Toast.LENGTH_LONG).show();
                            signUpProgress.setVisibility(View.INVISIBLE);
                            registerBtn_R.setEnabled(true);
                            goToLoginBtn.setEnabled(true);
                        }

                    })
                    .addOnFailureListener(e -> {
                        signUpProgress.setVisibility(View.INVISIBLE);
                        registerBtn_R.setEnabled(true);
                        goToLoginBtn.setEnabled(true);
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        }
    }
}