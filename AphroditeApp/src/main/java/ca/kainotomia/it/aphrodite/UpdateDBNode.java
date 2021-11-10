package ca.kainotomia.it.aphrodite;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.Objects;

public class UpdateDBNode {

    private final DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;

    public UpdateDBNode(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }

    public boolean addUser(String uid, String name, String email) {
        if (Objects.requireNonNull(getDatabaseReference().getKey()).equals("users")) {
            getDatabaseReference().child(uid).child("name").setValue(name);
            getDatabaseReference().child(uid).child("email").setValue(email);
            return true;
        } else {
            System.out.println("Node Incorrect: User not created");
            return false;
        }
    }

    public boolean addLayout(String uid, String layoutName, String[] modules, String[] locations) {
        if (Objects.requireNonNull(getDatabaseReference().getKey()).equals("layouts")) {

//            getDatabaseReference().child(uid).child("name").setValue(name);
//            getDatabaseReference().child(uid).child("email").setValue(email);
            return true;
        } else {
            System.out.println("Node Incorrect: Layout not Added");
            return false;
        }
    }
}
