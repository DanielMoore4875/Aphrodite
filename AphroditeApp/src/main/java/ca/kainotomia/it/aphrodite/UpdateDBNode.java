package ca.kainotomia.it.aphrodite;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

    public FirebaseUser getFirebaseUser() {
        return this.firebaseUser;
    }

    public String getCurrentUid() {
        return getFirebaseUser().getUid();
    }

    public String getCurrentUserName() {
        return getFirebaseUser().getDisplayName();
    }

    /*
        Will store the uid and its children will be the modules (key) with their locations (value)
        modules[][]:
                0           1
             0 modName1 location1
             1 modName2 location2
             2 ...      ...
             This method is for editing and adding layouts
         */
    public boolean addLayout(String layoutName, boolean[] modIsChecked, String[] moduleName, String[] moduleLoc) {
        if (Objects.requireNonNull(getDatabaseReference().getKey()).equals("layouts")) {
            for (int i = 0; i < 8; i++) {
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
            return false;
        }
    }

    public boolean editLayout(String layoutName, boolean[] modIsChecked, String[] moduleName, String[] moduleLoc) {
        if (Objects.requireNonNull(getDatabaseReference().getKey()).equals("layouts")) {
            for (int i = 0; i < 8; i++) {
                if (modIsChecked[i]) {
                    getDatabaseReference()
                            .child(getCurrentUid())
                            .child(layoutName)
                            .child(moduleName[i])
                            .setValue(moduleLoc[i]);
                } else {
                    //if not checked remove the value
                    getDatabaseReference()
                            .child(getCurrentUid())
                            .child(layoutName)
                            .child(moduleName[i])
                            .removeValue();
                }
            }
            return true;
        } else {
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


    public boolean changeLEDColour(String colour) {
        if (Objects.requireNonNull(getDatabaseReference().getKey()).equals("led_colour")) {
            getDatabaseReference()
                    .child(getCurrentUid())
                    .setValue(colour);
            return true;
        } else {
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

    // Add a voice command title and description
    public void addVoiceCommand(String title, String desc) {
        if (Objects.requireNonNull(getDatabaseReference().getKey()).equals("user_voice_commands")) {
            // no description = delete command
            if (desc.equals("")) {
                desc = null;
            }
            String finalDesc = desc;
            getDatabaseReference().child(getCurrentUid()).get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    DataSnapshot data = task.getResult();
                    int numOfCommands;
                    numOfCommands = (int) Objects.requireNonNull(task.getResult()).getChildrenCount();
                    if (numOfCommands < 3) {
                        UpdateDBNode.this.getDatabaseReference()
                                .child(UpdateDBNode.this.getCurrentUid())
                                .child(title)
                                .setValue(finalDesc);
                    } else if (Objects.requireNonNull(data).hasChild(title)) {
                        //edit voice command that exists
                        UpdateDBNode.this.getDatabaseReference()
                                .child(UpdateDBNode.this.getCurrentUid())
                                .child(title)
                                .setValue(finalDesc);
                    }
                }
            });
        }
    }

    public boolean setCurrentLayout(String currentLayout) {
        if (Objects.requireNonNull(getDatabaseReference().getKey()).equals("user_curr_layout")) {
            getDatabaseReference().child(getCurrentUid()).setValue(currentLayout);
            return true;
        }
        return false;
    }
}
