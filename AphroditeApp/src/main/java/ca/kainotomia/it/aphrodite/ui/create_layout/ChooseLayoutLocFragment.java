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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.Arrays;

import ca.kainotomia.it.aphrodite.R;
import ca.kainotomia.it.aphrodite.UpdateDBNode;
import ca.kainotomia.it.aphrodite.ui.account.AccountAboutFragment;
import ca.kainotomia.it.aphrodite.ui.account.AccountSettingsFragment;
import ca.kainotomia.it.aphrodite.ui.account.AccountSupportFragment;
import ca.kainotomia.it.aphrodite.ui.home.HomeFragment;

public class ChooseLayoutLocFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    Spinner time;
    Spinner date;
    Spinner calender;
    Spinner weather;
    Spinner temphum;
    Spinner stocks;
    Spinner youtube;
    Spinner notifications;
    Button saveButton;

    String[] locations;

    EditText CLP_layoutName;
    TextView CLL_layoutName;

    Button saveModules;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        UpdateDBNode dbNode = new UpdateDBNode("layouts");
        locations = new String[8];

        CLP_layoutName = view.findViewById(R.id.CL_LayoutName_User_Input_PT);
        CLL_layoutName = view.findViewById(R.id.ML_LayoutName);

        saveModules = view.findViewById(R.id.CL_SaveButton);
        saveModules.setOnClickListener(this);


        time = view.findViewById(R.id.CLF_spinner_time);
        date = view.findViewById(R.id.CLF_spinner_date);
        calender = view.findViewById(R.id.CLF_spinner_calender);
        weather = view.findViewById(R.id.CLF_spinner_weather);
        temphum = view.findViewById(R.id.CLF_spinner_temp_hum);
        stocks = view.findViewById(R.id.CLF_spinner_stocks);
        youtube = view.findViewById(R.id.CLF_spinner_youtube);
        notifications = view.findViewById(R.id.CLF_spinner_notif);
        saveButton = (Button) view.findViewById(R.id.CLF_Button_submit);

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

        saveButton.setOnClickListener(this);
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.choose_layout_loc_fragment, container, false);


        return rootView;
    }

    private void handleSpinnerItem(int pos) {
        //pos from 0-7 (8)
    }

    public void onClick(View view) {
        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.CLF_Button_submit:
                Snackbar.make(view, (R.string.savedmodulesnack), Snackbar.LENGTH_LONG).show();
                System.out.println(Arrays.toString(locations));

//                fragment = new HomeFragment();
//                replaceFragment(fragment);
                break;
            case R.id.CL_SaveButton:
                CLL_layoutName.setText(CLP_layoutName.getText().toString());
                if (CLP_layoutName == null) {
                    Snackbar.make(view, (R.string.CLL_snackbar_error_txt), Snackbar.LENGTH_SHORT).show();
                }
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
        locations[0] = time.getSelectedItem().toString();
        locations[1] = date.getSelectedItem().toString();
        locations[2] = calender.getSelectedItem().toString();
        locations[3] = weather.getSelectedItem().toString();
        locations[4] = temphum.getSelectedItem().toString();
        locations[5] = stocks.getSelectedItem().toString();
        locations[6] = youtube.getSelectedItem().toString();
        locations[7] = notifications.getSelectedItem().toString();
        System.out.println("IN HERE");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}