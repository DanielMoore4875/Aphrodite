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

public class AccountSettingsFragment extends Fragment{

    private AccountViewModel accountViewModel;
    private ViewGroup container;
    private LayoutInflater inflater;
    private Button button;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_account_settings, container, false);

        Button AFSS_Button_terminate = (Button) rootView.findViewById(R.id.AFSS_Button_terminate);

        //AFSS_Button_terminate.setOnClickListener(this);

        this.container = container;
        this.inflater = inflater;

        return rootView;
    }


//    public void onClick(View view) {
//        Fragment fragment = null;
//        switch (view.getId()) {
//            case R.id.AFSS_Button_terminate:
//                fragment = new HomeFragment();
//                replaceFragment(fragment);
//                break;
//
//        }
//
//        public void replaceFragment(Fragment someFragment){
//            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
//            transaction.replace(R.id.nav_host_fragment, someFragment);
//            transaction.addToBackStack(null);
//            transaction.commit();
//        }
//    }
}
