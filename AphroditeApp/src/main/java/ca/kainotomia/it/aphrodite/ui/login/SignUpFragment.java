package ca.kainotomia.it.aphrodite.ui.login;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.Objects;

import ca.kainotomia.it.aphrodite.R;


public class SignUpFragment extends Fragment {

    private EditText nameET_R;
    private EditText emailET_R;
    private EditText passwordET_R;

    private EditText confirmPassET_R;

    private Button registerBtn_R;

    private Button goToLoginBtn;

    private ProgressBar signUpProgress;

    private FirebaseAuth firebaseAuthSignUp;

    ConnectivityManager connectivityManager;
    NetworkInfo networkInfo;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sign_up, container, false);

        connectivityManager = (ConnectivityManager) requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();

        signUpProgress = root.findViewById(R.id.FSU_progressBar);

        nameET_R = root.findViewById(R.id.FSU_nameEditTXT);
        emailET_R = root.findViewById(R.id.FSU_emailEditTXT);
        passwordET_R = root.findViewById(R.id.FSU_passEditTXT);

        confirmPassET_R = root.findViewById(R.id.FSU_confrimPassEditTXT);

        registerBtn_R = root.findViewById(R.id.FSU_signup);
        registerBtn_R.setOnClickListener(v -> {
            if ((networkInfo == null || !networkInfo.isConnected())) {
                Toast.makeText(getActivity(), getString(R.string.voice_noConnection), Toast.LENGTH_LONG).show();
            } else {
                checkUserExists();
            }
        });

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
        if (!nameET_R.getText().toString().isEmpty() && !emailET_R.getText().toString().isEmpty()
                && !passwordET_R.getText().toString().isEmpty() && !confirmPassET_R.getText().toString().isEmpty()) {

            if (passwordET_R.getText().toString().matches(confirmPassET_R.getText().toString())) {
                //8 chars, one lower, one upper, one digit, one special char (not '+' or '~' )
                if (passwordET_R.getText().toString().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {


                    UserProfileChangeRequest addUserName = new UserProfileChangeRequest.Builder()
                            .setDisplayName(nameET_R.getText().toString())
                            .build();


                    signUpProgress.setVisibility(View.VISIBLE);
                    registerBtn_R.setEnabled(false);
                    goToLoginBtn.setEnabled(false);
                    firebaseAuthSignUp.createUserWithEmailAndPassword(emailET_R.getText().toString(),
                            passwordET_R.getText().toString()).addOnSuccessListener(authResult -> {
                        setPBInvisibleAndEnableButtons();
                        emailET_R.setText("");
                        nameET_R.setText("");
                        passwordET_R.setText("");

                        Objects.requireNonNull(firebaseAuthSignUp.getCurrentUser()).updateProfile(addUserName)
                                .addOnFailureListener(e -> Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show());

                        if (firebaseAuthSignUp.getCurrentUser() != null) {
                            firebaseAuthSignUp.signOut();
                            Toast.makeText(getActivity(), getString(R.string.Login_userCreated_txt), Toast.LENGTH_SHORT).show();
                            SignInFragment signInFragment = new SignInFragment();
                            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                            transaction.replace(R.id.login_frag_host, signInFragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                        }
                    }).addOnFailureListener(e -> {
                        setPBInvisibleAndEnableButtons();
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
                } else {
                    setPBInvisibleAndEnableButtons();
                    Toast.makeText(getActivity(), "Pass must be:\n - 8 chars\nAt least one\n - digit\n - lowercase letter\n - uppercase letter", Toast.LENGTH_LONG).show();
                }
            } else {
                setPBInvisibleAndEnableButtons();
                Toast.makeText(getActivity(), getString(R.string.FSU_matchPasswordsError), Toast.LENGTH_SHORT).show();
            }
        } else {
            setPBInvisibleAndEnableButtons();
            Toast.makeText(getActivity(), getString(R.string.FSU_emptyFieldsError), Toast.LENGTH_SHORT).show();
        }
    }

    public void setPBInvisibleAndEnableButtons() {
        signUpProgress.setVisibility(View.INVISIBLE);
        registerBtn_R.setEnabled(true);
        goToLoginBtn.setEnabled(true);
    }

    private void checkUserExists() {
        if (firebaseAuthSignUp != null && !emailET_R.getText().toString().isEmpty() && !passwordET_R.getText().toString().isEmpty()) {
            if (isValidEmail(emailET_R.getText())) {
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
                                setPBInvisibleAndEnableButtons();
                            }

                        })
                        .addOnFailureListener(e -> {
                            setPBInvisibleAndEnableButtons();
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            } else {
                Toast.makeText(getActivity(), getString(R.string.FSU_SI_email_incorrectERROR), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getActivity(), getString(R.string.FSU_emptyFieldsError), Toast.LENGTH_SHORT).show();
        }
    }

    //regex valid email
    public static boolean isValidEmail(CharSequence email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
}