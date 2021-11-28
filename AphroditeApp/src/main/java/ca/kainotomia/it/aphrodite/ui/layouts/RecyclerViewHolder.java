package ca.kainotomia.it.aphrodite.ui.layouts;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ca.kainotomia.it.aphrodite.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    private TextView view;
    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView.findViewById(R.id.randomText);
    }

    public TextView getView(){
        return view;
    }
}
