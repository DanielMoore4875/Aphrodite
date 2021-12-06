//Jose Antonio Castro Teodoro n01384776 Section B
//Daniel Moore n01354875 Section B
//Ryan Black n01305403 Section B
//Alyssa Gomez n01042777 Section B
package ca.kainotomia.it.aphrodite.ui.account;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

import ca.kainotomia.it.aphrodite.LoginActivity;
import ca.kainotomia.it.aphrodite.MainActivity;
import ca.kainotomia.it.aphrodite.R;
import ca.kainotomia.it.aphrodite.UpdateDBNode;

public class AccountSettingsFragment extends Fragment {

    EditText confirmPass;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_account_settings, container, false);

        Button terminateAccount = view.findViewById(R.id.AFSS_Button_terminate);
        //Button logoutAccount = view.findViewById(R.id.AFSS_Button_logout);

        confirmPass = view.findViewById(R.id.AFSS_confirmPassET);

        terminateAccount.setOnClickListener(v -> {
            AlertDialog areYouSureTerminate = AskTerminate(); // Ask about exiting app
            areYouSureTerminate.show();
            System.out.println("TERMINATE");
        });

        /*
        logoutAccount.setOnClickListener(v -> {
            System.out.println("LOGOUT");
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        });
        */

        return view;
    }

    private AlertDialog AskTerminate() {
        return new AlertDialog.Builder(requireActivity())
                .setTitle(getString(R.string.ASF_AD_title))
                .setIcon(R.drawable.terminate)
                .setMessage(getString(R.string.ASF_AD_msg))
                .setPositiveButton(getString(R.string.ASF_AD_yes), (dialog, which) -> {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null && !confirmPass.getText().toString().isEmpty()) {
                        AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), confirmPass.getText().toString());
                        user.reauthenticate(credential)
                                .addOnSuccessListener(unused -> user.delete()
                                        .addOnCompleteListener(task -> {
                                            Toast.makeText(getActivity(), getString(R.string.user_deleted_txt), Toast.LENGTH_SHORT).show();
                                            FirebaseAuth.getInstance().signOut();
                                            startActivity(new Intent(getActivity(), LoginActivity.class));
                                            requireActivity().finish();
                                        })
                                        .addOnFailureListener(e -> Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show()))
                                .addOnFailureListener(e -> Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show());

                    } else {
                        Toast.makeText(getActivity(), getString(R.string.ACF_user_signin_error), Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(getString(R.string.ASF_AD_no), (dialog, which) -> dialog.dismiss())
                .create();
    }

}
