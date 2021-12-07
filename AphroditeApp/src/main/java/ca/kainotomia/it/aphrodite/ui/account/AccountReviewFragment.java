package ca.kainotomia.it.aphrodite.ui.account;

import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;

import ca.kainotomia.it.aphrodite.R;
import ca.kainotomia.it.aphrodite.UpdateDBNode;

public class AccountReviewFragment extends Fragment implements View.OnClickListener {

    EditText AFR_EditText_input;
    Button AFR_Button_submit;
    RatingBar AFR_ratingBar_ratebar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_account_review, container, false);
        AFR_EditText_input = view.findViewById(R.id.AFR_EditText_input);
        AFR_Button_submit = view.findViewById(R.id.AFR_Button_submit);
        AFR_ratingBar_ratebar = view.findViewById(R.id.AFR_RatingBar_ratebar);

        //sets widgets to OnClickListener
        AFR_ratingBar_ratebar.setOnClickListener(this);
        AFR_Button_submit.setOnClickListener(this);
        AFR_EditText_input.setOnClickListener(this);
        AFR_EditText_input.setInputType(InputType.TYPE_CLASS_TEXT);

        return view;
    }

    @Override
    public void onClick(View view) {
        UpdateDBNode dbNode = new UpdateDBNode("rating");
        if (view.getId() == R.id.AFR_Button_submit) {
            Snackbar.make(view, (R.string.reviewsnack), Snackbar.LENGTH_LONG).show();
            String review = AFR_EditText_input.getText().toString();
            float rating = AFR_ratingBar_ratebar.getRating();
            if (!dbNode.saveRating(review, rating)) {
                Toast.makeText(getActivity(), getString(R.string.word_error_saving_review), Toast.LENGTH_SHORT).show();
            }
            Navigation.findNavController(view).navigate(R.id.navigation_home);
        }
    }

}
