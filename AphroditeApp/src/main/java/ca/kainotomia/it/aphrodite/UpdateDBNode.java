package ca.kainotomia.it.aphrodite;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class UpdateDBNode {

    /*
        Principle: Don't Repeat Yourself
            This class is the interface between the app and the database. Each method corresponds to
            some functionality that exists in the app that needs to store or retrieve data from the
            database.
     */

    private final DatabaseReference databaseReference;
    private final FirebaseUser firebaseUser;

    public UpdateDBNode(String dbRef) {
        this.databaseReference = FirebaseDatabase.getInstance().getReference(dbRef);
        this.firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    public UpdateDBNode() {
        databaseReference = null;
        this.firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }

    public boolean addUser(String uid, String name, String email) {
        if (Objects.requireNonNull(getDatabaseReference().getKey()).equals("users")) {
            getDatabaseReference().child(uid).child("name").setValue(name);
            getDatabaseReference().child(uid).child("email").setValue(email);
            System.out.println("User added to DB");
            return true;
        } else {
            System.out.println("Node Incorrect: User not created");
            return false;
        }
    }

    public boolean deleteUser(String uid, String email) {
        if (Objects.requireNonNull(getDatabaseReference().getKey()).equals("users")) {
            // need to remove all things associated with the user
            getDatabaseReference().child(uid).removeValue();
            System.out.println("Removed User from database");


            return true;
        } else {
            System.out.println("Node Incorrect: User not removed");
            return false;
        }
    }

    public FirebaseUser getFirebaseUser() {
        return this.firebaseUser;
    }

    /*
        Will store the uid and its children will be the modules (key) with their locations (value)
        modules[][]:
                0           1
             0 modName1 location1
             1 modName2 location2
             2 ...      ...
         */
    public boolean addLayout(String layoutName, boolean[] modIsChecked, String[] moduleName, String[] moduleLoc) {
        if (Objects.requireNonNull(getDatabaseReference().getKey()).equals("layouts")) {
            for (int i = 0; i < 7; i++) {
                if (modIsChecked[i]) {
                    getDatabaseReference()
                            .child(getCurrentUid())
                            .child(layoutName)
                            .child(moduleName[i])
                            .setValue(moduleLoc[i]);
                }  //if module isn't selected, skip it

            }
            return true;
        } else {
            System.out.println("Node Incorrect: Layout not Added");
            return false;
        }
    }

    public boolean saveRating(String review, float star) {
        if (Objects.requireNonNull(getDatabaseReference().getKey()).equals("rating")) {
            getDatabaseReference()
                    .child(getCurrentUid())
                    .child("Review")
                    .setValue(review);

            getDatabaseReference()
                    .child(getCurrentUid())
                    .child("Star")
                    .setValue(star);
            return true;
        }
        return false;
    }

//    public boolean layoutNameExists(String layoutName) {
//        if (Objects.requireNonNull(getDatabaseReference().getKey()).equals("layouts")) {
//           if (layoutName)
//            return true;
//        } else {
//            System.out.println("Node Incorrect: nothing checked");
//            return false;
//        }
//    }

    //EDITING THE LAYOUT METHODS

    private boolean layoutHasModule(String layoutName, String modName) {
        if (Objects.requireNonNull(getDatabaseReference().getKey()).equals("layouts")) {
            DatabaseReference modRef = getDatabaseReference().child(getCurrentUid()).child(layoutName);
            boolean hasModule = false;
//            modRef.get


            return true;
        } else {
            return false;
        }
    }

    public boolean editLayout(String layName) {
        if (Objects.requireNonNull(getDatabaseReference().getKey()).equals("layouts")) {
//            String cal
//            getDatabaseReference().child(getCurrentUid()).child(layName).

            return true;
        } else {
            return false;
        }

    }

    //DONE EDITING LAYOUT METHODS

    public boolean editLayout(String uid, String layoutName, String[][] modules) {
        if (Objects.requireNonNull(getDatabaseReference().getKey()).equals("layouts")) {
            for (String[] module : modules) {
                getDatabaseReference()
                        .child(uid)
                        .child(layoutName)
                        .child(module[0])
                        .setValue(module[1]);
            }
            return true;
        } else {
            System.out.println("Node Incorrect: Layout not Added");
            return false;
        }
    }
//    ArrayList<String> userLayoutNamesIN = new ArrayList<>();
//    public FirebaseRecyclerAdapter getCurrentUserLayouts(FirebaseRecyclerAdapter adapter, RecyclerView recyclerView) {
//
//        if (Objects.requireNonNull(getDatabaseReference().getKey()).equals("layouts")) {
//
//            //Call interface
//            getLayoutData(new LayoutsCallback() {
//                @Override
//                public void onCallback(ArrayList<String> layoutNamesList) {
//                    userLayoutNamesIN = new ArrayList<>(layoutNamesList);
////                    Collections.copy(userLayoutNamesIN,layoutNamesList);
//                }
//            });
//
//            System.out.println("DB NODE: " + userLayoutNamesIN);
//
////            return userLayoutNamesIN;
//
//        } else {
//            System.out.println("Incorrect Data Node");
//            return null;
//
//        }
//        return null; // must return the adapter
//    }

//    //only called when layouts is the database reference
//    private void getLayoutData(LayoutsCallback myCallBack) {
//        getDatabaseReference().child(getCurrentUid()).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                ArrayList<String> userLayoutNames = new ArrayList<>();
//                for (DataSnapshot child : snapshot.getChildren()) {
//                    System.out.println("KEY: " + child.getKey());
//                    userLayoutNames.add(child.getKey());
//                    System.out.println("IN DATA UPDATE: " + userLayoutNames);
//                    myCallBack.onCallback(userLayoutNames);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }

//    //for getting data from on data change
//    private interface LayoutsCallback {
//        void onCallback(ArrayList<String> layoutNamesList);
//    }

    public String getCurrentUid() {
        return getFirebaseUser().getUid();
    }

    public String getCurrentUserName() {
        return getFirebaseUser().getDisplayName();
    }

    public boolean changeLEDColour(String colour) {
        if (Objects.requireNonNull(getDatabaseReference().getKey()).equals("led_colour")) {
            getDatabaseReference()
                    .child(getCurrentUid())
                    .setValue(colour);
            return true;
        } else {
            System.out.println("Node Incorrect: Colour not added");
            return false;
        }
    }

    public void setMicState(boolean b) {
        if (Objects.requireNonNull(getDatabaseReference().getKey()).equals("mic_state")) {
            getDatabaseReference()
                    .child(getCurrentUid())
                    .setValue(b);

        }
    }
}
