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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import ca.kainotomia.it.aphrodite.LoginActivity;
import ca.kainotomia.it.aphrodite.R;
import ca.kainotomia.it.aphrodite.UpdateDBNode;


public class AccountFragment extends Fragment implements View.OnClickListener {


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button AF_Button_about = view.findViewById(R.id.AF_Button_about);
        Button AF_Button_support = view.findViewById(R.id.AF_Button_support);
        Button AF_Button_settings = view.findViewById(R.id.AF_Button_settings);
        Button AF_Button_changePass = view.findViewById(R.id.AF_Button_changePass);

        //if user not signed in send to sign in page
        if (FirebaseAuth.getInstance().getCurrentUser()== null) {
            startActivity(new Intent(getActivity(), LoginActivity.class));
            requireActivity().finish();
        }
        TextView usernameText = view.findViewById(R.id.AF_TextView_username);
        UpdateDBNode dbNode = new UpdateDBNode();
        final String nameDisplay = dbNode.getCurrentUserName() +  "\n" + dbNode.getFirebaseUser().getEmail();
        usernameText.setText(nameDisplay);

        AF_Button_about.setOnClickListener(this);
        AF_Button_settings.setOnClickListener(this);
        AF_Button_support.setOnClickListener(this);
        AF_Button_changePass.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onClick(View view) {
        Fragment fragment;
        int id = view.getId();
        if (id == R.id.AF_Button_about) {
            fragment = new AccountAboutFragment();
            replaceFragment(fragment);
        } else if (id == R.id.AF_Button_support) {
            fragment = new AccountReviewFragment();
            replaceFragment(fragment);
        } else if (id == R.id.AF_Button_settings) {
            fragment = new AccountSettingsFragment();
            replaceFragment(fragment);
        } else if (id == R.id.AF_Button_changePass) {
            fragment = new SettingsChangePassFragment();
            replaceFragment(fragment);
        }
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}