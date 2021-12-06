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
        //Button AF_Button_changePass = view.findViewById(R.id.AF_Button_changePass);

        //get current username and display it in the username textview
        TextView usernameText = view.findViewById(R.id.AF_TextView_username);
        UpdateDBNode dbNode = new UpdateDBNode();
        usernameText.setText(dbNode.getCurrentUserName());

        AF_Button_about.setOnClickListener(this);
        AF_Button_settings.setOnClickListener(this);
        AF_Button_support.setOnClickListener(this);
        //AF_Button_changePass.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);
        Button logoutAccount = rootView.findViewById(R.id.AFSS_Button_logout);

        logoutAccount.setOnClickListener(v -> {
            System.out.println("LOGOUT");
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        });

        return rootView;
    }

    @Override
    public void onClick(View view) {
        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.AF_Button_about:
                fragment = new AccountAboutFragment();
                replaceFragment(fragment);
                break;

            case R.id.AF_Button_support:
                fragment = new AccountReviewFragment();
                replaceFragment(fragment);
                break;
            case R.id.AF_Button_settings:
                fragment = new AccountSettingsFragment();
                replaceFragment(fragment);
                break;
        }
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}