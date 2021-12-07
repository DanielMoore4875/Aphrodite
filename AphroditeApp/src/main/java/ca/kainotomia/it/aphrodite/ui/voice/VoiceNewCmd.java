package ca.kainotomia.it.aphrodite.ui.voice;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

import ca.kainotomia.it.aphrodite.R;
import ca.kainotomia.it.aphrodite.UpdateDBNode;
import ca.kainotomia.it.aphrodite.ui.home.HomeFragment;
import ca.kainotomia.it.aphrodite.ui.layouts.LayoutFragment;

public class VoiceNewCmd extends Fragment {

    private EditText title;
    private EditText desc;
    private Button submit;
    public Button cancel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title = view.findViewById(R.id.voice_newCmdTitle);
        desc = view.findViewById(R.id.voice_newCmdDesc);
        submit = view.findViewById(R.id.voice_newCmdBtn);
        cancel = view.findViewById(R.id.voice_newCmdBtn_cancel);

        cancel.setOnClickListener(v -> {
            Fragment newCmD = new VoiceFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.voice_nestedFragment, newCmD);
            transaction.addToBackStack(null);
            transaction.commit();
        });

        submit.setOnClickListener(v -> {
            String titleTxt = title.getText().toString();
            String descTxt = desc.getText().toString();
            UpdateDBNode dbNode = new UpdateDBNode("user_voice_commands");
            if (!titleTxt.equals("") && !descTxt.equals("")) {
                dbNode.addVoiceCommand(titleTxt, descTxt);
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.remove(VoiceNewCmd.this).commit();
                //remove the keyboard if it is out
                InputMethodManager inputMethodManager = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(requireView().getWindowToken(), 0);
            } else {
                Toast.makeText(getActivity(), "Cannot be empty", Toast.LENGTH_SHORT).show();
            }

            System.out.println("HERE");
        });

    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.voice_new_cmd, container, false);

    }
}
