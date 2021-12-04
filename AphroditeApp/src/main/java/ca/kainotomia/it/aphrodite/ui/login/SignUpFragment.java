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
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import ca.kainotomia.it.aphrodite.R;
import ca.kainotomia.it.aphrodite.ui.voice.LEDColourFragment;


public class SignUpFragment extends Fragment {

    private EditText nameET_R;
    private EditText emailET_R;
    private EditText passwordET_R;

    private Button registerBtn_R;

    private Button goToLoginBtn;

    private FirebaseAuth firebaseAuth;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sign_up, container, false);

        nameET_R = root.findViewById(R.id.FSU_nameEditTXT);
        emailET_R = root.findViewById(R.id.FSU_emailEditTXT);
        passwordET_R = root.findViewById(R.id.FSU_passEditTXT);

        registerBtn_R = root.findViewById(R.id.FSU_signup);
        registerBtn_R.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });

        goToLoginBtn = root.findViewById(R.id.FSU_goToLoginBtn);
        goToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignInFragment signInFragment = new SignInFragment();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.login_frag_host, signInFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        firebaseAuth = FirebaseAuth.getInstance();

        return root;
    }

    public void createUser() {
        if (!nameET_R.getText().toString().isEmpty() && !emailET_R.getText().toString().isEmpty() && passwordET_R.getText().toString().isEmpty()) {
            firebaseAuth.createUserWithEmailAndPassword(emailET_R.getText().toString(),
                    passwordET_R.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Toast.makeText(getActivity(), "USER CREATED", Toast.LENGTH_SHORT).show();
                    if (firebaseAuth.getCurrentUser() != null) {
                        firebaseAuth.signOut();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(), "USER NOT CREATED", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getActivity(), "NOT EMPTY", Toast.LENGTH_SHORT).show();
        }
    }
}