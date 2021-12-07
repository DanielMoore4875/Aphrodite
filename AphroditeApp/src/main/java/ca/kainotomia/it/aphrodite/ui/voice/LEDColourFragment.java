package ca.kainotomia.it.aphrodite.ui.voice;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.slider.Slider;
import com.google.firebase.auth.TwitterAuthCredential;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Query;

import org.w3c.dom.Text;

import ca.kainotomia.it.aphrodite.R;
import ca.kainotomia.it.aphrodite.UpdateDBNode;

public class LEDColourFragment extends Fragment {

    private String rgbColour;

    private TextView redTxt;
    private TextView greenTxt;
    private TextView blueTxt;

    private Slider red;
    private Slider green;
    private Slider blue;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView r_txt = view.findViewById(R.id.LED_Red_LetterR);
        TextView g_txt = view.findViewById(R.id.LED_Green_LetterG);
        TextView b_txt = view.findViewById(R.id.LED_Blue_LetterB);
        r_txt.setText(R.string.LED_r_txt);
        g_txt.setText(R.string.LED_g_txt);
        b_txt.setText(R.string.LED_b_txt);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         /*
            Principle: Keep It Simple and Stupid
                Each slider updates the colour value in real time and has a separate listener to
                clearly see where the data is being saved. The button then calls a method that
                handles the saving of the data in the database.
         */
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_led_colour, container, false);
        redTxt = root.findViewById(R.id.LED_Red_Txt);
        greenTxt = root.findViewById(R.id.LED_Green_Txt);
        blueTxt = root.findViewById(R.id.LED_Blue_Txt);

        red = root.findViewById(R.id.LED_Red_Slider);
        green = root.findViewById(R.id.LED_Green_Slider);
        blue = root.findViewById(R.id.LED_Blue_Slider);

        redTxt.setText(getString(R.string.zero_txt));       // R
        greenTxt.setText(getString(R.string.zero_txt));     // G
        blueTxt.setText(getString(R.string.zero_txt));      // B

        Button changeColour = root.findViewById(R.id.LED_colour_btn);

        UpdateDBNode dbNode = new UpdateDBNode("led_colour");
        dbNode.getDatabaseReference().child(dbNode.getCurrentUid()).get()
                .addOnSuccessListener(dataSnapshot -> {
                    if (dataSnapshot.getValue() != null) {
                        rgbColour = dataSnapshot.getValue().toString();
                    }
                    if (rgbColour != null) {
                        // DB format: "R,G,B"
                        String[] split = rgbColour.split(",");
                        redTxt.setText(split[0]);       // R
                        red.setValue(Float.parseFloat(split[0]));
                        greenTxt.setText(split[1]);     // G
                        green.setValue(Float.parseFloat(split[1]));
                        blueTxt.setText(split[2]);      // B
                        blue.setValue(Float.parseFloat(split[2]));
                    }
                })
                .addOnFailureListener(e -> Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show());

        red.addOnChangeListener(((slider, value, fromUser) -> {
            String redI = Integer.toString((int) value);
            redTxt.setText(redI);
        }));

        green.addOnChangeListener((slider, value, fromUser) -> {
            String greenI = Integer.toString((int) value);
            greenTxt.setText(greenI);
        });

        blue.addOnChangeListener((slider, value, fromUser) -> {
            String blueI = Integer.toString((int) value);
            blueTxt.setText(blueI);
        });

        changeColour.setOnClickListener(v -> {
            // cannot be put in strings.xml
            String colour = (int) red.getValue() + "," + (int) green.getValue() + "," + (int) blue.getValue();
            boolean added = dbNode.changeLEDColour(colour);
            if (added) {
                Toast.makeText(getActivity(), getString(R.string.voice_colourAdded), Toast.LENGTH_SHORT).show();
                Fragment voice = new VoiceFragment();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, voice);
                transaction.addToBackStack(null);
                transaction.commit();
            } else {
                Toast.makeText(getActivity(), getString(R.string.voice_colourAddedError), Toast.LENGTH_SHORT).show();
            }
        });


        return root;
    }
}