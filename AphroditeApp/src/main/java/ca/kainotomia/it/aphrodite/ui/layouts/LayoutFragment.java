//Jose Antonio Castro Teodoro n01384776 Section B
//Daniel Moore n01354875 Section B
//Ryan Black n01305403 Section B
//Alyssa Gomez n01042777 Section B
package ca.kainotomia.it.aphrodite.ui.layouts;

import android.app.DownloadManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

import ca.kainotomia.it.aphrodite.R;
import ca.kainotomia.it.aphrodite.UpdateDBNode;

public class LayoutFragment extends Fragment {

    private RecyclerView recyclerView;
    private FirebaseRecyclerAdapter firebaseRecyclerAdapter;
    private ArrayList<String> layoutNames;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_layouts, container, false);

//        layoutNames = new ArrayList<String>();


        //Get the layout names that the user has created from Firebase
//        UpdateDBNode db = new UpdateDBNode("layouts");
        System.out.println("LAYOUT FRAG: " + layoutNames);


        //Create the recyclerview
        recyclerView = root.findViewById(R.id.FL_recyclerView);
        recyclerView.setHasFixedSize(false); //TODO CHANGE LATER
        recyclerView.setLayoutManager(new GridLayoutManager(root.getContext(), 2));
        Drawable img = ResourcesCompat.getDrawable(root.getContext().getResources(), R.drawable.sun_button_oak, null); //TODO

        fetch();

//        recyclerView.setAdapter(db.getCurrentUserLayouts(firebaseRecyclerAdapter, recyclerView));

//        recyclerView.setAdapter(new UserLayoutsAdapter(layoutNames,img, layoutNames.size())); //TODO


        return root;
    }

    private void fetch() {
        UpdateDBNode db = new UpdateDBNode("layouts");
        Query query = db.getDatabaseReference().child(db.getCurrentUid());

        FirebaseRecyclerOptions<Model> options = new FirebaseRecyclerOptions.Builder<Model>().setQuery(query, snapshot -> new Model(snapshot.getKey())).build();
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Model, ViewHolder>(options) {
            @Override
            public int getItemViewType(int position) {
//                return super.getItemViewType(position);
                return R.layout.fragment_layouts_recyclerview_item; //TODO maybe change viewtpe in oncreateviewhodler
            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
                return new ViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Model model) {
                holder.getBtn().setText(model.getTitle());

                holder.getBtn().setOnClickListener(v -> {
                    Toast.makeText(getActivity(),"HELLOOOO", Toast.LENGTH_SHORT).show();
                    //TODO Save position and title of button to refence it again when the create/edit layouts page is created
                });
            }

            //TODO If user is disconencted rom netowrkm, show notificaiotn that list will not be populated correctly
            //TODO also store the current layout offline somehow


        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firebaseRecyclerAdapter.startListening();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        firebaseRecyclerAdapter.stopListening();
    }
}