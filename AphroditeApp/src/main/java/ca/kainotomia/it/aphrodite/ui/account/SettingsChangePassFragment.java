//Jose Antonio Castro Teodoro n01384776 Section B
//Daniel Moore n01354875 Section B
//Ryan Black n01305403 Section B
//Alyssa Gomez n01042777 Section B
package ca.kainotomia.it.aphrodite.ui.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ca.kainotomia.it.aphrodite.LoginActivity;
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


        savePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentUser = FirebaseAuth.getInstance().getCurrentUser();
                final String oldPassword = oldPass.getText().toString();
                final String newPassword = newPass.getText().toString();
                final String confirmPassword = confirmPass.getText().toString();
                if (currentUser != null) {
                    // if there is no text in any of the fields, tell user to enter text
                    if (oldPassword.matches("") || newPassword.matches("") || confirmPassword.matches("")) {
                        Toast.makeText(getActivity(), getString(R.string.field_cannot_be_empty_txt), Toast.LENGTH_SHORT).show();
                    } else {
                        updatePass(currentUser, oldPassword, newPassword);
                    }
//                    if (newPass.isEm && !confirmPass.toString().equals("")) {
//                        updatePass(currentUser, oldPassword, newPassword);
//                    } else {
//                        Toast.makeText(getActivity(), getString(R.string.no_user_txxt), Toast.LENGTH_SHORT).show();
//                    }


                } else {
                    System.out.println("NO USER SIGNED IN");
                }
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

    public void updatePass(FirebaseUser user, String passOLD, String passNEW) {
        final String email = user.getEmail();
        AuthCredential authCredential = null;
        if (email != null) {
            authCredential = EmailAuthProvider.getCredential(email, passOLD);
            user.reauthenticate(authCredential).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        user.updatePassword(passNEW).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getActivity(), getString(R.string.password_updated_txt), Toast.LENGTH_SHORT).show();
                                    //SIGN THE USER OUT
                                    FirebaseAuth.getInstance().signOut();
                                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                                    startActivity(intent);
                                    getActivity().finish();
                                } else {
                                    Toast.makeText(getActivity(), getString(R.string.password_error_txt), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(getActivity(), getString(R.string.password_error_txt), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            System.out.println("USER IS NULL");
        }

    }
}