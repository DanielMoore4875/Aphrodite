package ca.kainotomia.it.aphrodite.ui.voice;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.slider.Slider;

import ca.kainotomia.it.aphrodite.R;
import ca.kainotomia.it.aphrodite.UpdateDBNode;

public class LEDColourFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        UpdateDBNode dbNode = new UpdateDBNode("led_colour");

        Button changeColour = view.findViewById(R.id.LED_colour_btn);

        TextView redTxt = view.findViewById(R.id.LED_Red_Txt);
        TextView greenTxt = view.findViewById(R.id.LED_Green_Txt);
        TextView blueTxt = view.findViewById(R.id.LED_Blue_Txt);
        redTxt.setText(R.string.LED_r_txt);
        greenTxt.setText(R.string.LED_g_txt);
        blueTxt.setText(R.string.LED_b_txt);

        Slider red = view.findViewById(R.id.LED_Red_Slider);
        Slider green = view.findViewById(R.id.LED_Green_Slider);
        Slider blue = view.findViewById(R.id.LED_Blue_Slider);

        /*
            Principle: Keep It Simple and Stupid
                Each slider updates the colour value in real time and has a separate listener to
                clearly see where the data is being saved. The button then calls a method that
                handles the saving of the data in the database.
         */

        red.addOnChangeListener(((slider, value, fromUser) -> {
            int redI = (int) value;
            redTxt.setText(getString(R.string.LED_R) + redI);
        }));

        green.addOnChangeListener((slider, value, fromUser) -> {
            int greenI = (int) value;
            greenTxt.setText(getString(R.string.LED_G) + greenI);
        });

        blue.addOnChangeListener((slider, value, fromUser) -> {
            int blueI = (int) value;
            blueTxt.setText(getString(R.string.LED_B) + blueI);
        });

        changeColour.setOnClickListener(v -> {
            // cannot be put in strings.xml
            String colour = (int) red.getValue() + "," + (int) green.getValue() + "," + (int) blue.getValue();
            boolean added = dbNode.changeLEDColour(colour);
            if (added) {
                Toast.makeText(getActivity(), "Colour Added", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Colour Couldn't Be Added", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_led_colour, container, false);
    }
}