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

import ca.kainotomia.it.aphrodite.R;
import ca.kainotomia.it.aphrodite.ui.account.AccountFragment;

public class VoiceFragment extends Fragment {

    TextView n1, n2, n3, n4, n5, n6, n7;
    TextView d1, d2, d3, d4, d5, d6, d7;

    private VoiceViewModel voiceViewModel;
    Button muteMic;

    // Write a message to the database
    FirebaseDatabase database;
    DatabaseReference myRef;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        voiceViewModel =
                new ViewModelProvider(this).get(VoiceViewModel.class);

        return inflater.inflate(R.layout.voice_fragment, container, false);
        // View root = inflater.inflate(R.layout.voice_fragment, container, false);
        //
        //        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        muteMic = view.findViewById(R.id.VC_muteMic_btnID);
        muteMic.setOnClickListener(v -> {
            // do stuff on muteMic Click

        });

        FirebaseUser auth = FirebaseAuth.getInstance().getCurrentUser();
        System.out.println("USER: " + auth);

        database = FirebaseDatabase.getInstance();
       myRef = database.getReference("message");

        myRef.setValue("Hello, World!");

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


    }

}