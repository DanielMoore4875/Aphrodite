package ca.kainotomia.it.aphrodite;

import android.app.FragmentManager;
import android.os.Bundle;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import ca.kainotomia.it.aphrodite.ui.account.AccountFragment;

public class AccountActivity extends AppCompatActivity {

    private FrameLayout frameLayout;


    public void loadFragment(Fragment fragment) {
        // Clear any existing layout.
        frameLayout.removeAllViews();

        // Load the new fragment to the layout.
        getFragmentManager().beginTransaction()
                .addToBackStack(null) // Go to the previous fragment when clicking the back button.
                .replace(R.id.AF_FrameLayout_orientation, fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        // Clear any existing layouts before popping the stack.
        if (frameLayout != null) {
            frameLayout.removeAllViews();
        }

        // Create a fragment manager to load the previous fragment.
        FragmentManager fragmentManager = getFragmentManager();

        // If there are fragments left in the stack, load the previous fragment.
        // This may be needed when calling addToBackStack(null) to load fragments.
        if (fragmentManager.getBackStackEntryCount() > 1 ) {
            fragmentManager.popBackStack();
            return;
        }

        // Exit the app if there are no more fragments.
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Attach the frame layout to a handle.
        frameLayout = (FrameLayout) findViewById(R.id.AF_FrameLayout_orientation);

        // Load the fragment into the layout handle.
        getFragmentManager().beginTransaction()
                .replace(R.id.AF_FrameLayout_orientation, new AccountFragment())
                .commit();
    }
}
