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

    public void addModule(String uid, String modName, String value) {
        if (Objects.requireNonNull(getDatabaseReference().getKey()).equals("modules")) {
            getDatabaseReference()
                    .child(uid)
                    .child(modName)
                    .setValue(value);
            System.out.println("YESSSSS");
        } else {
            System.out.println(" NO");
        }
    }

    public String getCurrentUid() {
        return getFirebaseUser().getUid();
    }
}
