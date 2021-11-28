package ca.kainotomia.it.aphrodite.ui.layouts;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import ca.kainotomia.it.aphrodite.R;


public class LayoutFragment extends Fragment {


    private RecyclerView recyclerView;

    public LayoutFragment() {

    }


    public static LayoutFragment newInstance(String param1, String param2) {
        LayoutFragment fragment = new LayoutFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_layouts, container, false);
        recyclerView = v.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        recyclerView.setAdapter(new RandomNumListAdapter(1234));
        return v;
    }
}