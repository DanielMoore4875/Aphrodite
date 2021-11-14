package ca.kainotomia.it.aphrodite;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class UpdateDBNode {

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
    public boolean addLayout(String uid, String layoutName, String[][] modules) {
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
}
