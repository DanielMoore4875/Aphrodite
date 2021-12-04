package ca.kainotomia.it.aphrodite.ui.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import ca.kainotomia.it.aphrodite.R;


public class SignInFragment extends Fragment {

    private EditText emailET_L;
    private EditText passwordET_L;

    private Button registerET_L;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sign_in, container, false);

        emailET_L = root.findViewById(R.id.FSI_emailEditTXT);
        passwordET_L = root.findViewById(R.id.FSI_passEditTXT);

        registerET_L = root.findViewById(R.id.FSI_signin);

        return root;
    }
}