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

public class ShowSavedLayoutFragment extends Fragment {

    private ShowSavedLayoutViewModel mViewModel;

    public static ShowSavedLayoutFragment newInstance() {
        return new ShowSavedLayoutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.show_saved_layout_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ShowSavedLayoutViewModel.class);
        // TODO: Use the ViewModel
    }

}