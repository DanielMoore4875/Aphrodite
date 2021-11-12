package ca.kainotomia.it.aphrodite.ui.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import ca.kainotomia.it.aphrodite.R;

public class AccountProfileFragment {

    private ViewGroup container;
    private LayoutInflater inflater;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        this.container = container;
        this.inflater = inflater;
        View view = inflater.inflate(R.layout.fragment_account_profile, container, false);
        return view;
    }
}
