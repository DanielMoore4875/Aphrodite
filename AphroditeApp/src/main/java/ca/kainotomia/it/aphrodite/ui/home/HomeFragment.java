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

        //HOME Page
        //This is for the "add" button, to bring user to the Create Layout Page
        goToCreateLayout_H = root.findViewById(R.id.H_AddButton_button);
        goToCreateLayout_H.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //CreateLayoutFragment is the name of the fragment class you are trying to go to
               Fragment fragment = new CreateLayoutFragment();
               //this is for declaring the transaction
               FragmentTransaction ft = getParentFragmentManager().beginTransaction();
               //nav_host_fragment is the id of the fragment in MainActivity
               ft.replace(R.id.nav_host_fragment, fragment);
               //this is for the back button but we have other functionality for that
               //so it is just null
               ft.addToBackStack(null);
               //this commits the changes you've made to the fragment and updates the fragment
               ft.commit();
           }
       });

        return root;
    }



}

