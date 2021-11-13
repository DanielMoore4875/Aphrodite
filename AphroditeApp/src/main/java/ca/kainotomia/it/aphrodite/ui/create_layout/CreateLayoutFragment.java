package ca.kainotomia.it.aphrodite.ui.create_layout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import ca.kainotomia.it.aphrodite.R;
import ca.kainotomia.it.aphrodite.ui.home.HomeFragment;

public class CreateLayoutFragment extends Fragment {

    private CreateLayoutViewModel mViewModel;

    public static CreateLayoutFragment newInstance() {
        return new CreateLayoutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.create_layout_page_fragment, container, false);

        Switch time = root.findViewById(R.id.CL_Feature_Time);
        Switch date = root.findViewById(R.id.CL_Feature_Date);
        Switch calender = root.findViewById(R.id.CL_Feature_Calendar);
        Switch weather = root.findViewById(R.id.CL_Feature_Weather);
        Switch temp_hum = root.findViewById(R.id.CL_Feature_RoomT_H);
        Switch stocks = root.findViewById(R.id.CL_Feature_Stocks);
        Switch youtube = root.findViewById(R.id.CL_Feature_Youtube);
        Switch notif = root.findViewById(R.id.CL_Feature_SocialMediaNot);

        Button button = root.findViewById(R.id.CL_SaveButton);

        time.setChecked(false);
        date.setChecked(false);
        calender.setChecked(false);
        weather.setChecked(false);
        temp_hum.setChecked(false);
        stocks.setChecked(false);
        youtube.setChecked(false);
        notif.setChecked(false);

        button.setOnClickListener((View.OnClickListener) this);

        return root;
    }

    public void onClick(View view) {
        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.CL_SaveButton:
                fragment = new ChooseLayoutLocFragment();
                replaceFragment(fragment);
                break;
        }
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}