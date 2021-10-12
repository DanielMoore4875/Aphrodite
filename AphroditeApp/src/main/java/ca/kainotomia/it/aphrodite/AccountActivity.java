//Jose Antonio Castro Teodoro n01384776 Section B
//Daniel Moore n01354875 Section B
//Ryan Black n01305403 Section B
//Alyssa Gomez n01042777 Section B
package ca.kainotomia.it.aphrodite;

import android.app.FragmentManager;
import android.os.Bundle;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import ca.kainotomia.it.aphrodite.ui.account.AccountFragment;

public class AccountActivity extends AppCompatActivity {

    private FrameLayout frameLayout;

    @Override
    public void onBackPressed() {

        if (frameLayout != null) {
            frameLayout.removeAllViews();
        }

        FragmentManager fragmentManager = getFragmentManager();

        if (fragmentManager.getBackStackEntryCount() > 1 ) {
            fragmentManager.popBackStack();
            return;
        }

        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_account);

        frameLayout = findViewById(R.id.AF_FrameLayout_orientation);

        // Load the fragment into the layout handle.
        getFragmentManager().beginTransaction()
                .replace(R.id.AF_FrameLayout_orientation, new AccountFragment())
                .commit();
    }
}
