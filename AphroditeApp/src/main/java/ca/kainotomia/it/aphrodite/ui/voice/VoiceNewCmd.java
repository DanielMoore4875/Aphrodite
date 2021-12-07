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

import ca.kainotomia.it.aphrodite.R;
import ca.kainotomia.it.aphrodite.UpdateDBNode;

public class VoiceNewCmd extends Fragment {

    private EditText title;
    private EditText desc;
    public Button cancel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title = view.findViewById(R.id.voice_newCmdTitle);
        desc = view.findViewById(R.id.voice_newCmdDesc);
        Button submit = view.findViewById(R.id.voice_newCmdBtn);
        cancel = view.findViewById(R.id.voice_newCmdBtn_cancel);

        cancel.setOnClickListener(v -> {
            title.setText("");
            desc.setText("");
            requireActivity().getSupportFragmentManager().setFragmentResult("cancel_pressed", new Bundle());
            clearKeyboard();
        });

        submit.setOnClickListener(v -> {
            String titleTxt = title.getText().toString();
            String descTxt = desc.getText().toString();
            UpdateDBNode dbNode = new UpdateDBNode("user_voice_commands");
            // if description is null the command will be removed
            if (!titleTxt.equals("")) {
                dbNode.addVoiceCommand(titleTxt, descTxt);
                requireActivity().getSupportFragmentManager().setFragmentResult("submit_pressed", new Bundle());
                clearKeyboard();
                title.setText("");
                desc.setText("");
            } else {
                Toast.makeText(getActivity(), getString(R.string.field_cannot_be_empty_txt), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.voice_new_cmd, container, false);

    }

    private void clearKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(requireView().getWindowToken(), 0);
    }
}
