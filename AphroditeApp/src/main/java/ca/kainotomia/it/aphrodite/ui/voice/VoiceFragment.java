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
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.internal.zzx;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

import ca.kainotomia.it.aphrodite.LoginActivity;
import ca.kainotomia.it.aphrodite.MainActivity;
import ca.kainotomia.it.aphrodite.R;
import ca.kainotomia.it.aphrodite.ui.account.AccountFragment;

public class VoiceFragment extends Fragment {

    TextView n1, n2, n3, n4, n5, n6, n7;
    TextView d1, d2, d3, d4, d5, d6, d7;

    private VoiceViewModel voiceViewModel;
    ToggleButton muteMic;

    // Write a message to the database
    FirebaseDatabase database;
    DatabaseReference myRef;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        voiceViewModel =
                new ViewModelProvider(this).get(VoiceViewModel.class);

        return inflater.inflate(R.layout.voice_fragment, container, false);
        // View root = inflater.inflate(R.layout.voice_fragment, container, false);
        //        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        muteMic = view.findViewById(R.id.VC_muteMic_btnID);
        muteMic.setOnClickListener(v -> {
           if (muteMic.isChecked()) {
               Toast.makeText(getActivity(), "Mic is muted", Toast.LENGTH_SHORT).show();
           } else {
               Toast.makeText(getActivity(), "Mic is unmuted", Toast.LENGTH_SHORT).show();
           }
        });

        n1 = view.findViewById(R.id.VC_cmdName1_txt);
        n2 = view.findViewById(R.id.VC_cmdName2_txt);
        n3 = view.findViewById(R.id.VC_cmdName3_txt);
        n4 = view.findViewById(R.id.VC_cmdName4_txt);
        n5 = view.findViewById(R.id.VC_cmdName5_txt);
        n6 = view.findViewById(R.id.VC_cmdName6_txt);
        n7 = view.findViewById(R.id.VC_cmdName7_txt);
        n1.setText(R.string.VC_name_cmd1_txt);
        n2.setText(R.string.VC_name_cmd2_txt);
        n3.setText(R.string.VC_name_cmd3_txt);
        n4.setText(R.string.VC_name_cmd4_txt);
        n5.setText(R.string.VC_name_cmd5_txt);
        n6.setText(R.string.VC_name_cmd6_txt);
        n7.setText(R.string.VC_name_cmd7_txt);

        d1 = view.findViewById(R.id.VC_cmdDesc1_txt);
        d2 = view.findViewById(R.id.VC_cmdDesc2_txt);
        d3 = view.findViewById(R.id.VC_cmdDesc3_txt);
        d4 = view.findViewById(R.id.VC_cmdDesc4_txt);
        d5 = view.findViewById(R.id.VC_cmdDesc5_txt);
        d6 = view.findViewById(R.id.VC_cmdDesc6_txt);
        d7 = view.findViewById(R.id.VC_cmdDesc7_txt);
        d1.setText(R.string.VC_desc_cmd1_txt);
        d2.setText(R.string.VC_desc_cmd2_txt);
        d3.setText(R.string.VC_desc_cmd3_txt);
        d4.setText(R.string.VC_desc_cmd4_txt);
        d5.setText(R.string.VC_desc_cmd5_txt);
        d6.setText(R.string.VC_desc_cmd6_txt);
        d7.setText(R.string.VC_desc_cmd7_txt);


    }

}