package ca.kainotomia.it.aphrodite.ui.layouts;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ca.kainotomia.it.aphrodite.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    public LinearLayout root;
    public TextView txtTitle;
    public TextView txtDesc;


    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        root = itemView.findViewById(R.id.root);
        txtTitle = itemView.findViewById(R.id.list_title);
        txtDesc = itemView.findViewById(R.id.list_desc);

    }

    public void setTxtTitle(String string) {
        txtTitle.setText(string);
    }


    public void setTxtDesc(String string) {
        txtDesc.setText(string);
    }
}
