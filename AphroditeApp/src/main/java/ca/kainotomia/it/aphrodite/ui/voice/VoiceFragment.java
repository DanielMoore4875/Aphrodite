//Jose Antonio Castro Teodoro n01384776 Section B
//Daniel Moore n01354875 Section B
//Ryan Black n01305403 Section B
//Alyssa Gomez n01042777 Section B
package ca.kainotomia.it.aphrodite.ui.voice;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

import ca.kainotomia.it.aphrodite.R;

public class VoiceFragment extends Fragment {

    Spinner spinner;
    String spinItem;

    EditText cmdName;
    Button recordCmd;

    Snackbar showInfo;

    ArrayList<String> cmdArr;

    private VoiceViewModel voiceViewModel;

    ArrayAdapter<String> cmdAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        voiceViewModel =
                new ViewModelProvider(this).get(VoiceViewModel.class);
        View root = inflater.inflate(R.layout.voice_fragment, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spinner = view.findViewById(R.id.VF_voice_spinner);

        cmdName = view.findViewById(R.id.VF_commandName_txt);
        recordCmd = view.findViewById(R.id.VF_record_btn);
        recordCmd.setEnabled(false);

        //LATER WILL BE GETTING DATA FROM DATABASE
        cmdArr = new ArrayList<>();
        cmdArr.add("empty cmd");
        cmdArr.add("empty cmd");
        cmdArr.add("empty cmd");
        cmdArr.add("empty cmd");
        System.out.println(cmdArr.toString());

        String[] hello = cmdArr.toArray(new String[cmdArr.size()]);

        cmdAdapter = new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,hello);
        cmdAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(cmdAdapter);

        recordCmd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               cmdArr.add(cmdName.getText().toString());
                String[] hello = cmdArr.toArray(new String[cmdArr.size()]);

                cmdAdapter = new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,hello);
                cmdAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                spinner.setAdapter(cmdAdapter); //UPDATES DROP DOWN SPINNER
                System.out.println(cmdArr.toString());
            }
        });

        //Disable record button if command name is empty
        cmdName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.toString().trim().length() == 0) {
                    recordCmd.setEnabled(false);
                } else {
                    recordCmd.setEnabled(true);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() == 0) {
                    recordCmd.setEnabled(false);
                } else {
                    recordCmd.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });






    }

}