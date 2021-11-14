package ca.kainotomia.it.aphrodite.ui.account;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.snackbar.Snackbar;

import ca.kainotomia.it.aphrodite.R;
import ca.kainotomia.it.aphrodite.ui.home.HomeFragment;

public class AccountReviewFragment extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_account_review, container, false);
        Button AFR_Button_submit = (Button) view.findViewById(R.id.AFR_Button_submit);
        final RatingBar AFR_ratingBar_ratebar = (RatingBar) view.findViewById(R.id.AFR_RatingBar_ratebar);

        AFR_ratingBar_ratebar.setOnClickListener(this);
        AFR_Button_submit.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.AFR_Button_submit:
                Snackbar.make(view, (R.string.reviewsnack), Snackbar.LENGTH_LONG).show();
                fragment = new HomeFragment();
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
