//Jose Antonio Castro Teodoro n01384776 Section B
//Daniel Moore n01354875 Section B
//Ryan Black n01305403 Section B
//Alyssa Gomez n01042777 Section B
package ca.kainotomia.it.aphrodite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BlankFragment extends android.app.Fragment {

public BlankFragment() {
        // Required empty public constructor
        }

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
        }
}
