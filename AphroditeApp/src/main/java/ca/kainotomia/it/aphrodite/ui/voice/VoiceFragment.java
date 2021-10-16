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
import android.widget.TextView;

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

    TextView n1, n2, n3, n4, n5, n6, n7;
    TextView d1, d2, d3, d4, d5, d6, d7;

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

        String names = "placeholder command name";
        String desc = "placeholder command desc";

        n1 = view.findViewById(R.id.VC_cmdName1_txt);
        n2 = view.findViewById(R.id.VC_cmdName2_txt);
        n3 = view.findViewById(R.id.VC_cmdName3_txt);
        n4 = view.findViewById(R.id.VC_cmdName4_txt);
        n5 = view.findViewById(R.id.VC_cmdName5_txt);
        n6 = view.findViewById(R.id.VC_cmdName6_txt);
        n7 = view.findViewById(R.id.VC_cmdName7_txt);
        n1.setText(names);
        n2.setText(names);
        n3.setText(names);
        n4.setText(names);
        n5.setText(names);
        n6.setText(names);
        n7.setText(names);

        d1 = view.findViewById(R.id.VC_cmdDesc1_txt);
        d2 = view.findViewById(R.id.VC_cmdDesc2_txt);
        d3 = view.findViewById(R.id.VC_cmdDesc3_txt);
        d4 = view.findViewById(R.id.VC_cmdDesc4_txt);
        d5 = view.findViewById(R.id.VC_cmdDesc5_txt);
        d6 = view.findViewById(R.id.VC_cmdDesc6_txt);
        d7 = view.findViewById(R.id.VC_cmdDesc7_txt);
        d1.setText(desc);
        d2.setText(desc);
        d3.setText(desc);
        d4.setText(desc);
        d5.setText(desc);
        d6.setText(desc);
        d7.setText(desc);



//        spinner = view.findViewById(R.id.VF_voice_spinner);
//
//        cmdName = view.findViewById(R.id.VF_commandName_txt);
//        recordCmd = view.findViewById(R.id.VF_record_btn);
//        recordCmd.setEnabled(false);
//
//        //LATER WILL BE GETTING DATA FROM DATABASE
//        cmdArr = new ArrayList<>();
//        cmdArr.add("empty cmd");
//        cmdArr.add("empty cmd");
//        cmdArr.add("empty cmd");
//        cmdArr.add("empty cmd");
//        System.out.println(cmdArr.toString());
//
//        String[] hello = cmdArr.toArray(new String[cmdArr.size()]);
//
//        cmdAdapter = new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,hello);
//        cmdAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
//        spinner.setAdapter(cmdAdapter);
//
//        recordCmd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               cmdArr.add(cmdName.getText().toString());
//                String[] hello = cmdArr.toArray(new String[cmdArr.size()]);
//
//                cmdAdapter = new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,hello);
//                cmdAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
//                spinner.setAdapter(cmdAdapter); //UPDATES DROP DOWN SPINNER
//                System.out.println(cmdArr.toString());
//            }
//        });
//
//        //Disable record button if command name is empty
//        cmdName.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                if (s.toString().trim().length() == 0) {
//                    recordCmd.setEnabled(false);
//                } else {
//                    recordCmd.setEnabled(true);
//                }
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.toString().trim().length() == 0) {
//                    recordCmd.setEnabled(false);
//                } else {
//                    recordCmd.setEnabled(true);
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });


    }

}