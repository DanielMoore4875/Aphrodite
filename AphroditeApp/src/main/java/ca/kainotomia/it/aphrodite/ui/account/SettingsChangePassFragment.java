//Jose Antonio Castro Teodoro n01384776 Section B
//Daniel Moore n01354875 Section B
//Ryan Black n01305403 Section B
//Alyssa Gomez n01042777 Section B
package ca.kainotomia.it.aphrodite.ui.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ca.kainotomia.it.aphrodite.R;

public class SettingsChangePassFragment extends Fragment {

    private EditText oldPass;
    private EditText newPass;
    private EditText confirmPass;

    Button savePass;

    private FirebaseUser currentUser;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_settings_change_pass, container, false);

        oldPass = root.findViewById(R.id.CP_PlainText_title_PasswordInput);
        newPass = root.findViewById(R.id.CP_PlainText_title_NewPasswordInput);
        confirmPass = root.findViewById(R.id.CP_PlainText_title_ConfirmPasswordInput);

        savePass = root.findViewById(R.id.CP_Button_Save);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        savePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!newPass.toString().equals("") && !confirmPass.toString().equals("")) {
//                    AuthCredential credential = EmailAuthProvider.getCredential(Objects.requireNonNull(currentUser.getEmail()), oldPass.toString());
//                    currentUser.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            currentUser.updatePassword(newPass.toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if (task.isSuccessful()) {
//                                        Toast.makeText(getActivity(), getString(R.string.password_updated_txt), Toast.LENGTH_SHORT).show();
//                                    }
//
//                                }
//                            });
//                        }
//                    });
//                }
                //not working properly

            }
        });


        // Inflate the layout for this fragment
        return root;
    }
}