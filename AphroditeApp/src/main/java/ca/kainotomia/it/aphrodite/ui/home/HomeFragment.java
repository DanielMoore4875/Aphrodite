//Jose Antonio Castro Teodoro n01384776 Section B
//Daniel Moore n01354875 Section B
//Ryan Black n01305403 Section B
//Alyssa Gomez n01042777 Section B
package ca.kainotomia.it.aphrodite.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;

import ca.kainotomia.it.aphrodite.R;
import ca.kainotomia.it.aphrodite.ui.account.AccountSettingsFragment;
import ca.kainotomia.it.aphrodite.ui.create_layout.CreateLayoutFragment;

public class HomeFragment extends Fragment implements View.OnClickListener{

    Button morning;
    Button stocks;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        Button goToCreateLayout_H = (Button) root.findViewById(R.id.H_AddButton_button);

        morning = root.findViewById(R.id.H_MorningButton_button);
        stocks = root.findViewById(R.id.H_StockButton_button);

        goToCreateLayout_H.setOnClickListener(this);

        morning.setOnClickListener(v -> {
            Toast.makeText(getActivity(), getString(R.string.Home_def_morn), Toast.LENGTH_SHORT).show();
        });

        stocks.setOnClickListener(v -> {
            Toast.makeText(getActivity(), getString(R.string.Home_def_stocks), Toast.LENGTH_SHORT).show();
        });


        return root;
    }

    public void onClick(View view) {
        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.H_AddButton_button:
                fragment = new CreateLayoutFragment();
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

