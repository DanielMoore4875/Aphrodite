//Jose Antonio Castro Teodoro n01384776 Section B
//Daniel Moore n01354875 Section B
//Ryan Black n01305403 Section B
//Alyssa Gomez n01042777 Section B
package ca.kainotomia.it.aphrodite.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import ca.kainotomia.it.aphrodite.R;
import ca.kainotomia.it.aphrodite.UpdateDBNode;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[][] layoutsTest = {
                {"weather", "top_left"},
                {"mod2", "bottom_right"},
                {"speed", "middle_center"}
        };
        Button addPlus = view.findViewById(R.id.H_AddButton_button);
        addPlus.setOnClickListener(v -> {
//            System.out.println("HEY THERE");
//            UpdateDBNode layouts = new UpdateDBNode("layouts");
//            layouts.addLayout(layouts.getCurrentUid(),"Test Name",layoutsTest);
            UpdateDBNode modules = new UpdateDBNode("modules");
            modules.addModule(modules.getCurrentUid(),"TESTING AGAIN", "ABC");
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.H_HomeTitle_title);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });


        return root;
    }
}