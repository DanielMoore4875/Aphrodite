package ca.kainotomia.it.aphrodite.ui.create_layout;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.kainotomia.it.aphrodite.R;

public class ChooseLayoutLocFragment extends Fragment {

    private ChooseLayoutLocViewModel mViewModel;

    public static ChooseLayoutLocFragment newInstance() {
        return new ChooseLayoutLocFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.choose_layout_loc_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ChooseLayoutLocViewModel.class);
        // TODO: Use the ViewModel
    }

}