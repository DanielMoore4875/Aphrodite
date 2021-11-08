package ca.kainotomia.it.aphrodite.ui.create_layout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.kainotomia.it.aphrodite.R;

public class CreateLayoutFragment extends Fragment {

    private CreateLayoutViewModel mViewModel;

    public static CreateLayoutFragment newInstance() {
        return new CreateLayoutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.create_layout_page_fragment, container, false);


        return root;
    }


}