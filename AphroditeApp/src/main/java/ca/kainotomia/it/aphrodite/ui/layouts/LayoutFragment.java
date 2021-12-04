//Jose Antonio Castro Teodoro n01384776 Section B
//Daniel Moore n01354875 Section B
//Ryan Black n01305403 Section B
//Alyssa Gomez n01042777 Section B
package ca.kainotomia.it.aphrodite.ui.layouts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ca.kainotomia.it.aphrodite.R;
import ca.kainotomia.it.aphrodite.UpdateDBNode;
import ca.kainotomia.it.aphrodite.ui.create_layout.CreateLayoutFragment;

public class LayoutFragment extends Fragment {

    private RecyclerView recyclerView;
    private FirebaseRecyclerAdapter<Model, ViewHolder> firebaseRecyclerAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_layouts, container, false);


        //Create the recyclerview
        recyclerView = root.findViewById(R.id.FL_recyclerView);
        recyclerView.setHasFixedSize(false); //TODO CHANGE LATER
        recyclerView.setMotionEventSplittingEnabled(false);
        recyclerView.setLayoutManager(new GridLayoutManager(root.getContext(), 2));

        // Get firebase layout data
        fetch();
        return root;
    }

    private void fetch() {
        UpdateDBNode db = new UpdateDBNode("layouts");
        Query query = db.getDatabaseReference().child(db.getCurrentUid());

        FirebaseRecyclerOptions<Model> options = new FirebaseRecyclerOptions.Builder<Model>().setQuery(query, snapshot -> new Model(snapshot.getKey())).build();
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Model, ViewHolder>(options) {
            @Override
            public int getItemViewType(int position) {
                return R.layout.fragment_layouts_recyclerview_item;
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

                UpdateDBNode db = new UpdateDBNode("layouts");
                ArrayList<String> moduleNameData = new ArrayList<>();
                ArrayList<String> moduleLocData = new ArrayList<>();

                holder.getBtn().setOnClickListener(v -> {

//                    db.getDatabaseReference().child(db.getCurrentUid()).child(model.getTitle()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//                        @Override
//                        public void onComplete(@NonNull Task<DataSnapshot> task) {
//                            if (task.isSuccessful()) {
//                                DataSnapshot snapshot = task.getResult();
//                                System.out.println(snapshot.getChildrenCount());
////                                snapshot.getChildrenCount();
//                            }
//                        }
//                    });

                    db.getDatabaseReference().child(db.getCurrentUid()).child(model.getTitle()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String btnName = holder.getBtn().getText().toString();
                            System.out.println(snapshot.getChildrenCount());
                            for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                                String modVal = (String) dataSnapshot.getValue(true);
                                moduleNameData.add(dataSnapshot.getKey());
                                moduleLocData.add(modVal);
                            }
                            System.out.println(moduleNameData);
                            System.out.println(moduleLocData);
                            CreateLayoutFragment createLayoutFragment = new CreateLayoutFragment();
                            Bundle createLayoutBundle = new Bundle();
                            createLayoutBundle.putString("layoutName", model.getTitle());
                            createLayoutBundle.putStringArrayList("layoutNameData", moduleNameData);
                            createLayoutBundle.putStringArrayList("layoutLocData", moduleLocData);
                            createLayoutFragment.setArguments(createLayoutBundle);

                            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                            transaction.replace(R.id.nav_host_fragment, createLayoutFragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                            Toast.makeText(getActivity(), btnName, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });



//
//                    db.getDatabaseReference().child(db.getCurrentUid()).child(model.getTitle()).addChildEventListener(new ChildEventListener() {
//                        @Override
//                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                            int count = (int) snapshot.getChildrenCount();
//                            System.out.println(count + " CHILDREN");
//                            String btnName = holder.getBtn().getText().toString();
//                            if (model.getTitle().equals(btnName)) {
//                                String modVal = (String) snapshot.getValue(true);
//                                moduleNameData.add(snapshot.getKey());
//                                moduleLocData.add(modVal);
//                                count--;
//                                System.out.println(count);
//
//                            }
//                            System.out.println(count);
////                            CreateLayoutFragment createLayoutFragment = new CreateLayoutFragment();
////                            Bundle createLayoutBundle = new Bundle();
////                            createLayoutBundle.putString("layoutName", model.getTitle());
////                            createLayoutBundle.putStringArrayList("layoutNameData", moduleNameData);
////                            createLayoutBundle.putStringArrayList("layoutLocData", moduleLocData);
////                            createLayoutFragment.setArguments(createLayoutBundle);
////
////                            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
////                            transaction.replace(R.id.nav_host_fragment, createLayoutFragment);
////                            transaction.addToBackStack(null);
////                            transaction.commit();
////                            Toast.makeText(getActivity(), btnName, Toast.LENGTH_SHORT).show();
//                        }
//
//                        @Override
//                        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                            System.out.println("CHANGED");
//                        }
//
//                        @Override
//                        public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//                        }
//
//                        @Override
//                        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                            System.out.println("MOVED");
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//                        }
//                    });
                });
            }
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