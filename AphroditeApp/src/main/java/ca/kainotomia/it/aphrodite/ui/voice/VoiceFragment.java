//Jose Antonio Castro Teodoro n01384776 Section B
//Daniel Moore n01354875 Section B
//Ryan Black n01305403 Section B
//Alyssa Gomez n01042777 Section B
package ca.kainotomia.it.aphrodite.ui.voice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;

import ca.kainotomia.it.aphrodite.R;

public class VoiceFragment extends Fragment {

    Spinner spinner;
    String spinItem;

    Snackbar showInfo;

    Button remove_btn;
    Button add_btn;

    private VoiceViewModel voiceViewModel;


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

        remove_btn = view.findViewById(R.id.VF_remove_command_btn);
        add_btn = view.findViewById(R.id.VF_add_command_btn);

        spinner = view.findViewById(R.id.VF_voice_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.voicecommands, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinItem = parent.getItemAtPosition(position).toString();
                System.out.println(spinItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfo = Snackbar.make(view, R.string.VF_snackbar_add_txt,Snackbar.LENGTH_LONG);
                showInfo.show();
            }
        });

        remove_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfo = Snackbar.make(view, R.string.VF_snackbar_remove_txt,Snackbar.LENGTH_LONG);
                showInfo.show();
            }
        });


    }

}