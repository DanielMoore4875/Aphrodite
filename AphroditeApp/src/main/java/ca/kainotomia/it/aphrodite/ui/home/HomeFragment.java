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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import ca.kainotomia.it.aphrodite.R;
import ca.kainotomia.it.aphrodite.ui.create_layout.CreateLayoutFragment;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    Button goToCreateLayout_H;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

       goToCreateLayout_H = root.findViewById(R.id.H_AddButton_button);

       goToCreateLayout_H.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Fragment fragment = new CreateLayoutFragment();
               FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
               FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
               fragmentTransaction.replace(R.id.container, CreateLayoutFragment.class, null);
               fragmentTransaction.addToBackStack(null);
               fragmentTransaction.commit();
           }
       });

        return root;
    }



}

