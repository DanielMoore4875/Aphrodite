package ca.kainotomia.it.aphrodite.ui.create_layout;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;

import ca.kainotomia.it.aphrodite.R;
import ca.kainotomia.it.aphrodite.UpdateDBNode;
import ca.kainotomia.it.aphrodite.ui.home.HomeFragment;

public class CreateLayoutFragment extends Fragment {
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
    EditText layoutNameEditText;

    String[] modNamesFromLeft;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.create_layout_page_fragment, container, false);
        modNamesFromLeft = new String[8];
        layoutNameEditText = root.findViewById(R.id.CLP_LayoutName_User_Input_PT);

        // If this fragment is called from the LayoutFragment page, this Bundle will contain the name of
        // the layout that needs to be edited. Use this name to the get its data from Firebase

        timeSw = root.findViewById(R.id.CLP_Feature_Time);
        dateSw = root.findViewById(R.id.CLP_Feature_Date);
        calendarSw = root.findViewById(R.id.CLP_Feature_Calendar);
        weatherSw = root.findViewById(R.id.CLP_Feature_Weather);
        temp_humSw = root.findViewById(R.id.CLP_Feature_RoomT_H);
        stocksSw = root.findViewById(R.id.CLP_Feature_Stocks);
        youtubeSw = root.findViewById(R.id.CLP_Feature_Youtube);
        notifSw = root.findViewById(R.id.CLP_Feature_SocialMediaNot);

        timeSp = root.findViewById(R.id.CLP_spinner_time);
        dateSp = root.findViewById(R.id.CLP_spinner_date);
        calendarSp = root.findViewById(R.id.CLP_spinner_calendar);
        weatherSp = root.findViewById(R.id.CLP_spinner_weather);
        temp_humSp = root.findViewById(R.id.CLP_spinner_temp_hum);
        stocksSp = root.findViewById(R.id.CLP_spinner_stocks);
        youtubeSp = root.findViewById(R.id.CLP_spinner_youtube);
        notifSp = root.findViewById(R.id.CLP_spinner_notif);

        //Pattern: Adapter. Using this array adapter to store strings to be used in the dropdown without changing the code
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

        timeSp.setEnabled(false);
        dateSp.setEnabled(false);
        calendarSp.setEnabled(false);
        weatherSp.setEnabled(false);
        temp_humSp.setEnabled(false);
        stocksSp.setEnabled(false);
        youtubeSp.setEnabled(false);
        notifSp.setEnabled(false);

        // TODO
        Bundle layoutNameBun = getArguments();
        if (layoutNameBun != null) {
            String layoutName = layoutNameBun.getString("layoutName");
            ArrayList<String> layoutNameData = layoutNameBun.getStringArrayList("layoutNameData");
            ArrayList<String> layoutLocData = layoutNameBun.getStringArrayList("layoutLocData");
            layoutNameEditText.setText(layoutName); // set layout name from previous screen


            for (int i = 0; i < layoutNameData.size(); i++) {
                switch (layoutNameData.get(i)) {
                    case "Time":
                        timeSw.setChecked(true);
                        for (int j = 0; j < adapter.getCount(); j++) {
                            if (timeSp.getItemAtPosition(j).equals(layoutLocData.get(i))) {
                                timeSp.setSelection(j);
                                break;
                            }
                        }
                        timeSp.setEnabled(true);
                        handleSwitch(timeSw, timeSp, 0);
                        break;
                    case "Date":
                        dateSw.setChecked(true);
                        for (int j = 0; j < adapter.getCount(); j++) {
                            if (dateSp.getItemAtPosition(j).equals(layoutLocData.get(i))) {
                                dateSp.setSelection(j);
                                break;
                            }
                        }
                        dateSp.setEnabled(true);
                        handleSwitch(dateSw, dateSp, 1);
                        break;
                    case "Calendar":
                        calendarSw.setChecked(true);
                        for (int j = 0; j < adapter.getCount(); j++) {
                            if (calendarSp.getItemAtPosition(j).equals(layoutLocData.get(i))) {
                                calendarSp.setSelection(j);
                                break;
                            }
                        }
                        calendarSp.setEnabled(true);
                        handleSwitch(calendarSw, calendarSp, 2);
                        break;
                    case "Weather":
                        weatherSw.setChecked(true);
                        for (int j = 0; j < adapter.getCount(); j++) {
                            if (weatherSp.getItemAtPosition(j).equals(layoutLocData.get(i))) {
                                weatherSp.setSelection(j);
                                break;
                            }
                        }
                        weatherSp.setEnabled(true);
                        handleSwitch(weatherSw, weatherSp, 3);
                        break;
                    case "Room_Temp_Hum":
                        temp_humSw.setChecked(true);
                        for (int j = 0; j < adapter.getCount(); j++) {
                            if (temp_humSp.getItemAtPosition(j).equals(layoutLocData.get(i))) {
                                temp_humSp.setSelection(j);
                                break;
                            }
                        }
                        temp_humSp.setEnabled(true);
                        handleSwitch(temp_humSw, temp_humSp, 4);
                        break;
                    case "Stocks":
                        stocksSw.setChecked(true);
                        for (int j = 0; j < adapter.getCount(); j++) {
                            if (stocksSp.getItemAtPosition(j).equals(layoutLocData.get(i))) {
                                stocksSp.setSelection(j);
                                break;
                            }
                        }
                        stocksSp.setEnabled(true);
                        handleSwitch(stocksSw, stocksSp, 5);
                        break;
                    case "Youtube":
                        youtubeSw.setChecked(true);
                        for (int j = 0; j < adapter.getCount(); j++) {
                            if (youtubeSp.getItemAtPosition(j).equals(layoutLocData.get(i))) {
                                youtubeSp.setSelection(j);
                                break;
                            }
                        }
                        youtubeSp.setEnabled(true);
                        handleSwitch(youtubeSw, youtubeSp, 6);
                        break;
                    case "Notifications":
                        notifSw.setChecked(true);
                        for (int j = 0; j < adapter.getCount(); j++) {
                            if (notifSp.getItemAtPosition(j).equals(layoutLocData.get(i))) {
                                notifSp.setSelection(j);
                                break;
                            }
                        }
                        notifSp.setEnabled(true);
                        handleSwitch(notifSw, notifSp, 7);
                        break;
                }
            }

        }

        timeSw.setOnClickListener(v -> handleSwitch(timeSw, timeSp, 0));
        dateSw.setOnClickListener(v -> handleSwitch(dateSw, dateSp, 1));
        calendarSw.setOnClickListener(v -> handleSwitch(calendarSw, calendarSp, 2));
        weatherSw.setOnClickListener(v -> handleSwitch(weatherSw, weatherSp, 3));
        temp_humSw.setOnClickListener(v -> handleSwitch(temp_humSw, temp_humSp, 4));
        stocksSw.setOnClickListener(v -> handleSwitch(stocksSw, stocksSp, 5));
        youtubeSw.setOnClickListener(v -> handleSwitch(youtubeSw, youtubeSp, 6));
        notifSw.setOnClickListener(v -> handleSwitch(notifSw, notifSp, 7));

        if (layoutNameBun == null) {
            timeSp.setEnabled(false);
            dateSp.setEnabled(false);
            calendarSp.setEnabled(false);
            weatherSp.setEnabled(false);
            temp_humSp.setEnabled(false);
            stocksSp.setEnabled(false);
            youtubeSp.setEnabled(false);
            notifSp.setEnabled(false);
        }

        saveLayout = root.findViewById(R.id.CLP_SaveButton);
        saveLayout.setOnClickListener(v -> handleSaveBtn());

        return root;
    }


    private void handleSaveBtn() {
        UpdateDBNode dbNode = new UpdateDBNode("layouts");
        String[] moduleNames = getResources().getStringArray(R.array.Layout_Values);
        String layoutName = layoutNameEditText.getText().toString();


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

        String[] modulesLocation = {
                timeSp.getSelectedItem().toString(),
                dateSp.getSelectedItem().toString(),
                calendarSp.getSelectedItem().toString(),
                weatherSp.getSelectedItem().toString(),
                temp_humSp.getSelectedItem().toString(),
                stocksSp.getSelectedItem().toString(),
                youtubeSp.getSelectedItem().toString(),
                notifSp.getSelectedItem().toString()
        };

        if (!checkAllChosen(modulesIsChecked, modulesLocation, moduleNames)) {
            //need to choose a location
            //show alert dialog
            Toast.makeText(getActivity(), getString(R.string.CL_noChosenLoc_error_txt), Toast.LENGTH_SHORT).show();
        } else {
            if (!layoutName.isEmpty()) {
                System.out.println(Arrays.toString(modulesIsChecked));
                System.out.println(Arrays.toString(modulesLocation));
                for (int i = 0; i < modulesLocation.length; i++) {
                    if (modulesLocation[i].equals(moduleNames[0])) {
                        modulesLocation[i] = "null";
                    }
                }
//                System.out.println("NAME: " + layoutName);
//                System.out.println("MOD NAMES:  " + Arrays.toString(modNamesFromLeft));
//                System.out.println("MOD LOC: " + Arrays.toString(modulesLocation));
                dbNode.addLayout(layoutName, modulesIsChecked, modNamesFromLeft, modulesLocation);
                //cant be put in strings.xml
                Toast.makeText(getActivity(), "Layout created: " + layoutName + "\nFor: " + dbNode.getCurrentUserName(), Toast.LENGTH_SHORT).show();
                Fragment home = new HomeFragment();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, home);
                transaction.addToBackStack(null);
                transaction.commit();
            } else {
                Toast.makeText(getActivity(), getString(R.string.CL_enterName_txt), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean checkAllChosen(boolean[] checkedMods, String[] modsLoc, String[] modNames) {
        for (int i = 0; i < 8; i++) {
            if (checkedMods[i] && modsLoc[i].equals(modNames[0])) {
                //havent chosen location for this module
                return false;
            }

        }
        return true;
    }

    private void handleSwitch(SwitchCompat v, Spinner sp, int position) {
        //time
        sp.setEnabled(v.isChecked());
        if (!v.isChecked()) {
            //reset spinner
            sp.setSelection(0);
        } else {
            modNamesFromLeft[position] = v.getText().toString();
        }
    }
}