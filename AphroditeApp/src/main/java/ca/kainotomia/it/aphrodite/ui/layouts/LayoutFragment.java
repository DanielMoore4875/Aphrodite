//Jose Antonio Castro Teodoro n01384776 Section B
//Daniel Moore n01354875 Section B
//Ryan Black n01305403 Section B
//Alyssa Gomez n01042777 Section B
package ca.kainotomia.it.aphrodite.ui.layouts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ca.kainotomia.it.aphrodite.MainActivity;
import ca.kainotomia.it.aphrodite.R;

public class LayoutFragment extends Fragment {

    private EditText editText, edt;
    private Button button;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter adapter;


        private void Load() {
            Query query = FirebaseDatabase.getInstance().getReference().child("posts");
            FirebaseRecyclerOptions<Model> options = new FirebaseRecyclerOptions.Builder<Model>()
                    .setQuery(query, new SnapshotParser<Model>() {
                        @NonNull
                        @Override
                        public Model parseSnapshot(@NonNull DataSnapshot snapshot) {
                            return new Model(snapshot.child("id").getValue().toString(),
                                    snapshot.child("title").getValue().toString(),
                                    snapshot.child("Description").getValue().toString());
                        }
                    })
                    .build();

            adapter = new FirebaseRecyclerAdapter<Model, ViewHolder>(options) {
                @Override
                public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.list_item, parent, false);

                    return new ViewHolder(view);
                }


                @Override
                protected void onBindViewHolder(ViewHolder holder, final int position, Model model) {
                    holder.setTxtTitle(model.getmTitle());
                    holder.setTxtDesc(model.getmDescription());

                    holder.root.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(getActivity(), "Opening layout...", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            };
            recyclerView.setAdapter(adapter);
            adapter.startListening();

        }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_layouts, container, false);

        editText = root.findViewById(R.id.et);
        edt = root.findViewById(R.id.etd);
        button = root.findViewById(R.id.btn);
        recyclerView = root.findViewById(R.id.list);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("posts").push();
                Map<String,Object> map = new HashMap<>();
                map.put("id",databaseReference.getKey());
                map.put("title",editText.getText().toString());
                map.put("Description",edt.getText().toString());

                databaseReference.setValue(map);
            }
        });

        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        Load(); //method containing  FirebaseRecyclerAdapter


        return root;
    }
}