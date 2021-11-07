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

import ca.kainotomia.it.aphrodite.R;

public class AccountSupportFragment extends android.app.Fragment {
    private AccountViewModel accountViewModel;
    private ViewGroup container;
    private LayoutInflater inflater;
    private TextView textView;
    private ImageView imageView;
    private Button button;

    public AccountSupportFragment() {
    }

    public View initializeUserInterface() {
        View view;

        // If there is already a layout inflated, remove it.
        if (container != null) {
            container.removeAllViewsInLayout();
        }

        // Get the screen orientation.
        int orientation = getActivity().getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            view = inflater.inflate(R.layout.fragment_account_support, container, false);
        }
        else { // orientation == Configuration.ORIENTATION_LANDSCAPE
            view = inflater.inflate(R.layout.fragment_account_support_horizontal, container, false);
        }

        // Instantiate our widgets from the layout.
        textView = view.findViewById(R.id.AFS_TextView_title);
        textView = view.findViewById(R.id.AFS_TextView_Email);
        textView = view.findViewById(R.id.AFS_TextView_Number);
        imageView = view.findViewById(R.id.AFS_ImageView_logo);

        // Display the orientation in the text view.
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            textView.setText("Portrait");
        }
        else {
            textView.setText("Landscape");

            // Get the width of the screen.
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            int width = displayMetrics.widthPixels;

            // If we have a small screen, adjust text size.
            if (width < 793) {
                textView.setTextSize(12);
            }
        }

        return view;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        this.container = container;
        this.inflater = inflater;
        return initializeUserInterface();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        View view = initializeUserInterface();
        container.addView(view);
        super.onConfigurationChanged(newConfig);
    }
}
