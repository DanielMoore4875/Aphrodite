package ca.kainotomia.it.aphrodite.ui.create_layout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.snackbar.Snackbar;

import ca.kainotomia.it.aphrodite.R;
import ca.kainotomia.it.aphrodite.ui.account.AccountAboutFragment;
import ca.kainotomia.it.aphrodite.ui.account.AccountSettingsFragment;
import ca.kainotomia.it.aphrodite.ui.account.AccountSupportFragment;
import ca.kainotomia.it.aphrodite.ui.home.HomeFragment;

public class ChooseLayoutLocFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.choose_layout_loc_fragment, container, false);

        Spinner time = rootView.findViewById(R.id.CLF_spinner_time);
        Spinner date = rootView.findViewById(R.id.CLF_spinner_date);
        Spinner calender = rootView.findViewById(R.id.CLF_spinner_calender);
        Spinner weather = rootView.findViewById(R.id.CLF_spinner_weather);
        Spinner temphum = rootView.findViewById(R.id.CLF_spinner_temp_hum);
        Spinner stocks = rootView.findViewById(R.id.CLF_spinner_stocks);
        Spinner youtube = rootView.findViewById(R.id.CLF_spinner_youtube);
        Spinner notifications = rootView.findViewById(R.id.CLF_spinner_notif);
        Button button = (Button) rootView.findViewById(R.id.CLF_Button_submit);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.Layout_Values, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        time.setAdapter(adapter);
        date.setAdapter(adapter);
        calender.setAdapter(adapter);
        weather.setAdapter(adapter);
        temphum.setAdapter(adapter);
        stocks.setAdapter(adapter);
        youtube.setAdapter(adapter);
        notifications.setAdapter(adapter);

        button.setOnClickListener(this);
        time.setOnItemSelectedListener(this);
        date.setOnItemSelectedListener(this);
        calender.setOnItemSelectedListener(this);
        weather.setOnItemSelectedListener(this);
        temphum.setOnItemSelectedListener(this);
        stocks.setOnItemSelectedListener(this);
        youtube.setOnItemSelectedListener(this);
        notifications.setOnItemSelectedListener(this);

        time.setPrompt(getString(R.string.CLF_time));
        date.setPrompt("");
        calender.setPrompt("");
        weather.setPrompt("");
        temphum.setPrompt("");
        stocks.setPrompt("");
        youtube.setPrompt("");
        notifications.setPrompt("");

        return rootView;
    }

    public void onClick(View view) {
        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.CLF_Button_submit:
                Snackbar.make(view, (R.string.savedmodulesnack), Snackbar.LENGTH_LONG).show();
                fragment = new HomeFragment();
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


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}