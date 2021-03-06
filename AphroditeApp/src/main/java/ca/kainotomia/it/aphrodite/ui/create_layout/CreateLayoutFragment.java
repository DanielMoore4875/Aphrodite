package ca.kainotomia.it.aphrodite.ui.create_layout;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import java.util.ArrayList;

import ca.kainotomia.it.aphrodite.R;
import ca.kainotomia.it.aphrodite.UpdateDBNode;

public class CreateLayoutFragment extends Fragment {
    SwitchCompat timeSw;
    SwitchCompat dateSw;
    SwitchCompat calendarSw;
    SwitchCompat weatherSw;
    SwitchCompat temp_humSw;
    SwitchCompat stocksSw;
    SwitchCompat notifSw;

    Bundle layoutNameBun;

    Spinner timeSp;
    Spinner dateSp;
    Spinner calendarSp;
    Spinner weatherSp;
    Spinner temp_humSp;
    Spinner stocksSp;
    Spinner notifSp;

    Button saveLayout;
    EditText layoutNameEditText;

    String[] modNamesFromLeft;
    String[] modNamesFromLeftEdit;
    String[] masterModules;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.create_layout_page_fragment, container, false);

        // master array of the names of the modules
        masterModules = new String[]{getString(R.string.MASTER_Mod0), getString(R.string.MASTER_Mod1),
                getString(R.string.MASTER_Mod2), getString(R.string.MASTER_Mod3),
                getString(R.string.MASTER_Mod4), getString(R.string.MASTER_Mod5),
                getString(R.string.MASTER_Mod6)};
        modNamesFromLeft = new String[]{getString(R.string.MASTER_Mod0), getString(R.string.MASTER_Mod1),
                getString(R.string.MASTER_Mod2), getString(R.string.MASTER_Mod3),
                getString(R.string.MASTER_Mod4), getString(R.string.MASTER_Mod5),
                getString(R.string.MASTER_Mod6)};
        modNamesFromLeftEdit = new String[]{getString(R.string.MASTER_Mod0), getString(R.string.MASTER_Mod1),
                getString(R.string.MASTER_Mod2), getString(R.string.MASTER_Mod3),
                getString(R.string.MASTER_Mod4), getString(R.string.MASTER_Mod5),
                getString(R.string.MASTER_Mod6)};
        layoutNameEditText = root.findViewById(R.id.CLP_LayoutName_User_Input_PT);

        // If this fragment is called from the LayoutFragment page, this Bundle will contain the name of
        // the layout that needs to be edited. Use this name to the get its data from Firebase

        timeSw = root.findViewById(R.id.CLP_Feature_Time);
//        modNamesFromLeftEdit[0] = timeSw.getText().toString();
        dateSw = root.findViewById(R.id.CLP_Feature_Date);
//        modNamesFromLeftEdit[1] = dateSw.getText().toString();
        calendarSw = root.findViewById(R.id.CLP_Feature_Calendar);
//        modNamesFromLeftEdit[2] = calendarSw.getText().toString();
        weatherSw = root.findViewById(R.id.CLP_Feature_Weather);
//        modNamesFromLeftEdit[3] = weatherSw.getText().toString();
        temp_humSw = root.findViewById(R.id.CLP_Feature_RoomT_H);
//        modNamesFromLeftEdit[4] = temp_humSw.getText().toString();
        stocksSw = root.findViewById(R.id.CLP_Feature_Stocks);
//        modNamesFromLeftEdit[5] = stocksSw.getText().toString();
        notifSw = root.findViewById(R.id.CLP_Feature_SocialMediaNot);
//        modNamesFromLeftEdit[6] = notifSw.getText().toString();

        timeSp = root.findViewById(R.id.CLP_spinner_time);
        dateSp = root.findViewById(R.id.CLP_spinner_date);
        calendarSp = root.findViewById(R.id.CLP_spinner_calendar);
        weatherSp = root.findViewById(R.id.CLP_spinner_weather);
        temp_humSp = root.findViewById(R.id.CLP_spinner_temp_hum);
        stocksSp = root.findViewById(R.id.CLP_spinner_stocks);
        notifSp = root.findViewById(R.id.CLP_spinner_notif);

        //Pattern: Adapter. Using this array adapter to store strings to be used in the dropdown without changing the code
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireActivity().getBaseContext(), R.array.Layout_Values, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        timeSp.setAdapter(adapter);
        dateSp.setAdapter(adapter);
        calendarSp.setAdapter(adapter);
        weatherSp.setAdapter(adapter);
        temp_humSp.setAdapter(adapter);
        stocksSp.setAdapter(adapter);
        notifSp.setAdapter(adapter);

        timeSp.setEnabled(false);
        dateSp.setEnabled(false);
        calendarSp.setEnabled(false);
        weatherSp.setEnabled(false);
        temp_humSp.setEnabled(false);
        stocksSp.setEnabled(false);
        notifSp.setEnabled(false);


        layoutNameBun = getArguments();
        if (layoutNameBun != null) {
            String layoutName = layoutNameBun.getString("layoutName");
            ArrayList<String> layoutNameData = layoutNameBun.getStringArrayList("layoutNameData");
            ArrayList<String> layoutLocData = layoutNameBun.getStringArrayList("layoutLocData");
            layoutNameEditText.setText(layoutName); // set layout name from previous screen


            for (int i = 0; i < layoutNameData.size(); i++) {
                switch (layoutNameData.get(i)) {
                    case "time":
                    case "Time":
                        timeSw.setChecked(true);
                        for (int j = 0; j < adapter.getCount(); j++) {
                            if (timeSp.getItemAtPosition(j).equals(layoutLocData.get(i))) {
                                timeSp.setSelection(j);
                                break;
                            }
                        }
                        timeSp.setEnabled(true);
                        handleSwitch(timeSw, timeSp);
                        break;
                    case "date":
                    case "Date":
                        dateSw.setChecked(true);
                        for (int j = 0; j < adapter.getCount(); j++) {
                            if (dateSp.getItemAtPosition(j).equals(layoutLocData.get(i))) {
                                dateSp.setSelection(j);
                                break;
                            }
                        }
                        dateSp.setEnabled(true);
                        handleSwitch(dateSw, dateSp);
                        break;
                    case "calendar":
                    case "Calendar":
                        calendarSw.setChecked(true);
                        for (int j = 0; j < adapter.getCount(); j++) {
                            if (calendarSp.getItemAtPosition(j).equals(layoutLocData.get(i))) {
                                calendarSp.setSelection(j);
                                break;
                            }
                        }
                        calendarSp.setEnabled(true);
                        handleSwitch(calendarSw, calendarSp);
                        break;
                    case "weather":
                    case "Weather":
                        weatherSw.setChecked(true);
                        for (int j = 0; j < adapter.getCount(); j++) {
                            if (weatherSp.getItemAtPosition(j).equals(layoutLocData.get(i))) {
                                weatherSp.setSelection(j);
                                break;
                            }
                        }
                        weatherSp.setEnabled(true);
                        handleSwitch(weatherSw, weatherSp);
                        break;
                    case "temp-humid":
                    case "Room_Temp_Hum":
                        temp_humSw.setChecked(true);
                        for (int j = 0; j < adapter.getCount(); j++) {
                            if (temp_humSp.getItemAtPosition(j).equals(layoutLocData.get(i))) {
                                temp_humSp.setSelection(j);
                                break;
                            }
                        }
                        temp_humSp.setEnabled(true);
                        handleSwitch(temp_humSw, temp_humSp);
                        break;
                    case "stocks":
                    case "Stocks":
                        stocksSw.setChecked(true);
                        for (int j = 0; j < adapter.getCount(); j++) {
                            if (stocksSp.getItemAtPosition(j).equals(layoutLocData.get(i))) {
                                stocksSp.setSelection(j);
                                break;
                            }
                        }
                        stocksSp.setEnabled(true);
                        handleSwitch(stocksSw, stocksSp);
                        break;
                    case "notifications":
                    case "Notifications":
                        notifSw.setChecked(true);
                        for (int j = 0; j < adapter.getCount(); j++) {
                            if (notifSp.getItemAtPosition(j).equals(layoutLocData.get(i))) {
                                notifSp.setSelection(j);
                                break;
                            }
                        }
                        notifSp.setEnabled(true);
                        handleSwitch(notifSw, notifSp);
                        break;
                }
            }

        }

        timeSw.setOnClickListener(v -> handleSwitch(timeSw, timeSp));
        dateSw.setOnClickListener(v -> handleSwitch(dateSw, dateSp));
        calendarSw.setOnClickListener(v -> handleSwitch(calendarSw, calendarSp));
        weatherSw.setOnClickListener(v -> handleSwitch(weatherSw, weatherSp));
        temp_humSw.setOnClickListener(v -> handleSwitch(temp_humSw, temp_humSp));
        stocksSw.setOnClickListener(v -> handleSwitch(stocksSw, stocksSp));
        notifSw.setOnClickListener(v -> handleSwitch(notifSw, notifSp));

        if (layoutNameBun == null) {
            timeSp.setEnabled(false);
            dateSp.setEnabled(false);
            calendarSp.setEnabled(false);
            weatherSp.setEnabled(false);
            temp_humSp.setEnabled(false);
            stocksSp.setEnabled(false);
            notifSp.setEnabled(false);
        }

        saveLayout = root.findViewById(R.id.CLP_SaveButton);
        saveLayout.setOnClickListener(v -> handleSaveBtn(root));

        return root;
    }


    private void handleSaveBtn(View view) {
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
                notifSw.isChecked()
        };

        String[] modulesLocation = {
                timeSp.getSelectedItem().toString(),
                dateSp.getSelectedItem().toString(),
                calendarSp.getSelectedItem().toString(),
                weatherSp.getSelectedItem().toString(),
                temp_humSp.getSelectedItem().toString(),
                stocksSp.getSelectedItem().toString(),
                notifSp.getSelectedItem().toString()
        };

        if (!checkAllChosen(modulesIsChecked, modulesLocation, moduleNames)) {
            //need to choose a location
            //show alert dialog
            Toast.makeText(getActivity(), getString(R.string.CL_noChosenLoc_error_txt), Toast.LENGTH_SHORT).show();
        } else {
            if (!layoutName.isEmpty()) {
                for (int i = 0; i < modulesLocation.length; i++) {
                    if (modulesLocation[i].equals(moduleNames[0])) {
                        modulesLocation[i] = "null";
                    }
                }
                if (layoutNameBun == null) {
                    if (dbNode.addLayout(layoutName, modulesIsChecked, modNamesFromLeft, modulesLocation)) {
                        Toast.makeText(getActivity(), layoutName + " " + getString(R.string.word_created), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), layoutName + " " + getString(R.string.word_error_creating), Toast.LENGTH_SHORT).show();
                    }
                    // Uses the bottom navigation instead of fragment transaction to move places
                    Navigation.findNavController(view).navigate(R.id.navigation_home);
                } else {
                    if (dbNode.editLayout(layoutName, modulesIsChecked, modNamesFromLeftEdit, modulesLocation)) {
                        Toast.makeText(getActivity(), layoutName + " " + getString(R.string.word_edited), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), layoutName + " " + getString(R.string.word_error_editing), Toast.LENGTH_SHORT).show();
                    }
                    // Uses the bottom navigation instead of fragment transaction to move places
                    Navigation.findNavController(view).navigate(R.id.navigation_layout);
                }
            } else {
                Toast.makeText(getActivity(), getString(R.string.CL_enterName_txt), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    // check if all chosen modules have a location selected
    private boolean checkAllChosen(boolean[] checkedMods, String[] modsLoc, String[] modNames) {
        for (int i = 0; i < 7; i++) {
            if (checkedMods[i] && modsLoc[i].equals(modNames[0])) {
                return false;
            }
        }
        return true;
    }

    private void handleSwitch(SwitchCompat v, Spinner sp) {
        //time
        sp.setEnabled(v.isChecked());
        if (!v.isChecked()) {
            //reset spinner
            sp.setSelection(0);
        }
    }
}