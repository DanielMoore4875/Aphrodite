package ca.kainotomia.it.aphrodite.ui.voice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private Button submit;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title = view.findViewById(R.id.voice_newCmdTitle);
        desc = view.findViewById(R.id.voice_newCmdDesc);
        submit = view.findViewById(R.id.voice_newCmdBtn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleTxt = title.getText().toString();
                String descTxt = desc.getText().toString();
                UpdateDBNode dbNode = new UpdateDBNode("user_voice_commands");
                dbNode.addVoiceCommand(titleTxt,descTxt);

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.voice_new_cmd, container, false);
        return root;
    }
}
