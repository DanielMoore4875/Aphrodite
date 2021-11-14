package ca.kainotomia.it.aphrodite.ui.create_layout;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import ca.kainotomia.it.aphrodite.R;
import ca.kainotomia.it.aphrodite.ui.account.AccountFragment;
import ca.kainotomia.it.aphrodite.ui.account.AccountSettingsFragment;
import ca.kainotomia.it.aphrodite.ui.account.AccountSupportFragment;
import ca.kainotomia.it.aphrodite.ui.home.HomeFragment;

public class ChooseLayoutLocFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.choose_layout_loc_fragment, container, false);

        Spinner time = rootView.findViewById(R.id.CLF_spinner_time);
        Button button = rootView.findViewById(R.id.CLF_Button_submit);

//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.Layout_Values, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //time.setAdapter(adapter);

       button.setOnClickListener((View.OnClickListener) this);
       //time.setOnItemClickListener((AdapterView.OnItemClickListener) this);
        return rootView;
    }

//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        String text = parent.getItemAtPosition(position).toString();
//        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT);
//    }

    public void onClick(View view) {
        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.CLF_Button_submit:
                Toast.makeText(getContext(), "Saved Page Layout", Toast.LENGTH_SHORT);
                fragment = new HomeFragment();
                replaceFragment(fragment);
                break;
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}