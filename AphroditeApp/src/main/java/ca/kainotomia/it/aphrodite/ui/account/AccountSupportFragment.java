//Jose Antonio Castro Teodoro n01384776 Section B
//Daniel Moore n01354875 Section B
//Ryan Black n01305403 Section B
//Alyssa Gomez n01042777 Section B
package ca.kainotomia.it.aphrodite.ui.account;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import ca.kainotomia.it.aphrodite.R;

public class AccountSupportFragment extends Fragment implements View.OnClickListener{
    private AccountViewModel accountViewModel;
    private ViewGroup container;
    private LayoutInflater inflater;
    private TextView textView;
    private ImageView imageView;
    private Button button;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        this.container = container;
        this.inflater = inflater;

        View view = inflater.inflate(R.layout.fragment_account_support, container, false);

        Button AFS_Button_review = view.findViewById(R.id.AFS_Button_Review);

        AFS_Button_review.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.AFS_Button_Review:
                fragment = new AccountReviewFragment();
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
