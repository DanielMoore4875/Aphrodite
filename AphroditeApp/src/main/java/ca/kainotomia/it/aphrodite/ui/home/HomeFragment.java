//Jose Antonio Castro Teodoro n01384776 Section B
//Daniel Moore n01354875 Section B
//Ryan Black n01305403 Section B
//Alyssa Gomez n01042777 Section B
package ca.kainotomia.it.aphrodite.ui.home;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.Query;

import java.util.ArrayList;

import ca.kainotomia.it.aphrodite.R;
import ca.kainotomia.it.aphrodite.UpdateDBNode;
import ca.kainotomia.it.aphrodite.ui.account.AccountSettingsFragment;
import ca.kainotomia.it.aphrodite.ui.create_layout.CreateLayoutFragment;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerViewHome;
    private FirebaseRecyclerAdapter<HomeModel, HomeViewHolder> firebaseRecyclerAdapterHome;
    ExtendedFloatingActionButton addLayoutFAB;

    private ProgressBar homePB;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        homePB = root.findViewById(R.id.Home_progressBar);



        recyclerViewHome = root.findViewById(R.id.Home_recyclerView);
        recyclerViewHome.setHasFixedSize(false);
        recyclerViewHome.setMotionEventSplittingEnabled(false);
        recyclerViewHome.setLayoutManager(new GridLayoutManager(root.getContext(), 2));
        addLayoutFAB = root.findViewById(R.id.home_fab);

        addLayoutFAB.setOnClickListener(v -> {
            CreateLayoutFragment createLayoutFragment = new CreateLayoutFragment();
            replaceFragment(createLayoutFragment);
        });

        getLayouts();


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firebaseRecyclerAdapterHome.startListening();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        firebaseRecyclerAdapterHome.stopListening();
    }

    private void getLayouts() {
        UpdateDBNode dbNode = new UpdateDBNode("layouts");
        Query query = dbNode.getDatabaseReference().child(dbNode.getCurrentUid());

        FirebaseRecyclerOptions<HomeModel> options = new FirebaseRecyclerOptions.Builder<HomeModel>()
                .setQuery(query, snapshot -> new HomeModel(snapshot.getKey())).build();
        firebaseRecyclerAdapterHome = new FirebaseRecyclerAdapter<HomeModel, HomeViewHolder>(options) {

            @Override
            public int getItemViewType(int position) {

                return R.layout.fragment_home_recyclerview_item;
            }

            @NonNull
            @Override
            public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
                homePB.setVisibility(View.VISIBLE);
                return new HomeViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull HomeViewHolder holder, int position, @NonNull HomeModel model) {
                holder.getBtn().setText(model.getTitle());

                holder.getBtn().setOnClickListener(v -> {
                    UpdateDBNode updateDBNode = new UpdateDBNode("user_curr_layout");
                    boolean currLayoutUpdated = updateDBNode.setCurrentLayout(holder.getBtn().getText().toString());
                    if (currLayoutUpdated) {
                        Toast.makeText(getActivity(), holder.getBtn().getText().toString(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), getString(R.string.HF_updateCurrentLayoutError), Toast.LENGTH_SHORT).show();
                    }
                });

                homePB.setVisibility(View.INVISIBLE);
            }


        };

        recyclerViewHome.setAdapter(firebaseRecyclerAdapterHome);

    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}

