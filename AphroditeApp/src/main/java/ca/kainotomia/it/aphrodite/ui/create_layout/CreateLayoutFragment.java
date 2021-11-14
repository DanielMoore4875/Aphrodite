package ca.kainotomia.it.aphrodite.ui.create_layout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Arrays;

import ca.kainotomia.it.aphrodite.R;

public class CreateLayoutFragment extends Fragment implements View.OnClickListener {
    SwitchCompat timeSw;
    SwitchCompat dateSw;
    SwitchCompat calendarSw;
    SwitchCompat weatherSw;
    SwitchCompat temp_humSw;
    SwitchCompat stocksSw;
    SwitchCompat youtubeSw;
    SwitchCompat notifSw;

    Spinner timeSp;
    Spinner dateSp;
    Spinner calendarSp;
    Spinner weatherSp;
    Spinner temp_humSp;
    Spinner stocksSp;
    Spinner youtubeSp;
    Spinner notifSp;

    Button saveLayout;

    String layoutName;
    EditText layoutNameEditText;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.create_layout_page_fragment, container, false);

        layoutNameEditText = root.findViewById(R.id.CLP_LayoutName_User_Input_PT);
        layoutName = layoutNameEditText.getText().toString();

        timeSw = root.findViewById(R.id.CLP_Feature_Time);
        dateSw = root.findViewById(R.id.CLP_Feature_Date);
        calendarSw = root.findViewById(R.id.CLP_Feature_Calendar);
        weatherSw = root.findViewById(R.id.CLP_Feature_Weather);
        temp_humSw = root.findViewById(R.id.CLP_Feature_RoomT_H);
        stocksSw = root.findViewById(R.id.CLP_Feature_Stocks);
        youtubeSw = root.findViewById(R.id.CLP_Feature_Youtube);
        notifSw = root.findViewById(R.id.CLP_Feature_SocialMediaNot);

        timeSw.setChecked(false);
        dateSw.setChecked(false);
        calendarSw.setChecked(false);
        weatherSw.setChecked(false);
        temp_humSw.setChecked(false);
        stocksSw.setChecked(false);
        youtubeSw.setChecked(false);
        notifSw.setChecked(false);

        timeSw.setOnClickListener(v -> handleSwitch(0, timeSw));
//        dateSw.setOnClickListener(v -> handleSwitch(1));
//        calendarSw.setOnClickListener(v -> handleSwitch(2));
//        weatherSw.setOnClickListener(v -> handleSwitch(3));
//        temp_humSw.setOnClickListener(v -> handleSwitch(4));
//        stocksSw.setOnClickListener(v -> handleSwitch(5));
//        youtubeSw.setOnClickListener(v -> handleSwitch(6));
//        notifSw.setOnClickListener(v -> handleSwitch(7));


        timeSp = root.findViewById(R.id.CLP_spinner_time);
        dateSp = root.findViewById(R.id.CLP_spinner_date);
        calendarSp = root.findViewById(R.id.CLP_spinner_calendar);
        weatherSp = root.findViewById(R.id.CLP_spinner_weather);
        temp_humSp = root.findViewById(R.id.CLP_spinner_temp_hum);
        stocksSp = root.findViewById(R.id.CLP_spinner_stocks);
        youtubeSp = root.findViewById(R.id.CLP_spinner_youtube);
        notifSp = root.findViewById(R.id.CLP_spinner_notif);

        timeSp.setEnabled(false);
        dateSp.setEnabled(false);
        calendarSp.setEnabled(false);
        weatherSp.setEnabled(false);
        temp_humSp.setEnabled(false);
        stocksSp.setEnabled(false);
        youtubeSp.setEnabled(false);
        notifSp.setEnabled(false);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.Layout_Values, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        timeSp.setAdapter(adapter);
        dateSp.setAdapter(adapter);
        calendarSp.setAdapter(adapter);
        weatherSp.setAdapter(adapter);
        temp_humSp.setAdapter(adapter);
        stocksSp.setAdapter(adapter);
        youtubeSp.setAdapter(adapter);
        notifSp.setAdapter(adapter);

        saveLayout = root.findViewById(R.id.CLP_SaveButton);
        saveLayout.setOnClickListener(this);

        return root;
    }

    private void handleSwitch(int modNum, SwitchCompat v) {
        switch (modNum) {
            case 0:
                //time
                timeSp.setEnabled(v.isEnabled());
                break;
            case 1:
                //date
                break;
            case 2:
                //calendar
                break;
            case 3:
                //weather
                break;
            case 4:
                //temp hum
                break;
            case 5:
                //stocks
                break;
            case 6:
                //youtube
                break;
            case 7:
                //notifs
                break;
        }
    }

    public void onClick(View view) {
        ChooseLayoutLocFragment frag_CLL = new ChooseLayoutLocFragment();
        if (view.getId() == R.id.CLP_SaveButton) {
            boolean[] modulesIsChecked = {
                    timeSw.isChecked(),
                    dateSw.isChecked(),
                    calendarSw.isChecked(),
                    weatherSw.isChecked(),
                    temp_humSw.isChecked(),
                    stocksSw.isChecked(),
                    youtubeSw.isChecked(),
                    notifSw.isChecked()
            };
            System.out.println(Arrays.toString(modulesIsChecked));
            // get all toggle values
//            Bundle moduleInfo = new Bundle();
//            moduleInfo.putBooleanArray("moduleCheckState", modulesIsChecked);
//            getParentFragmentManager().setFragmentResult("moduleBoolArray", moduleInfo);
//            frag_CLL.setArguments(moduleInfo);
//            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
//            transaction.replace(R.id.nav_host_fragment, frag_CLL);
////            transaction.addToBackStack(null);
//            transaction.commit();
        }
    }


}