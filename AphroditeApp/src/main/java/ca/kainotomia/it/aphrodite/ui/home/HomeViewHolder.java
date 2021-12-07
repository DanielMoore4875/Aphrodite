package ca.kainotomia.it.aphrodite.ui.home;

import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ca.kainotomia.it.aphrodite.R;

public class HomeViewHolder  extends RecyclerView.ViewHolder {

    //Refactored and removed old code
    private final Button btn;

    public HomeViewHolder(@NonNull View itemView) {
        super(itemView);
        this.btn = itemView.findViewById(R.id.FHRVI_btn);
    }
    public Button getBtn() {
        return this.btn;
    }

}
