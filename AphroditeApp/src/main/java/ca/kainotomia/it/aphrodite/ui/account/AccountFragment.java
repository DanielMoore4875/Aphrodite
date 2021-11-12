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

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import ca.kainotomia.it.aphrodite.R;


public class AccountFragment extends Fragment implements View.OnClickListener {

    public AccountFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_account, container, false);

        Button AF_Button_about = (Button) rootView.findViewById(R.id.AF_Button_about);
        Button AF_Button_support = (Button) rootView.findViewById(R.id.AF_Button_support);
        Button AF_Button_settings = (Button) rootView.findViewById(R.id.AFSS_Button_add);

        AF_Button_about.setOnClickListener(this);
        AF_Button_settings.setOnClickListener(this);
        AF_Button_support.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View view) {
        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.AF_Button_about:
                fragment = new AccountFragment();
                replaceFragment(fragment);
                break;

            case R.id.AF_Button_support:
                fragment = new AccountSupportFragment();
                replaceFragment(fragment);
                break;
            case R.id.AFSS_Button_add:
                fragment = new Fragment();
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