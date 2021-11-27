package ca.kainotomia.it.aphrodite.ui.layouts;

import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import ca.kainotomia.it.aphrodite.R;

public class ViewHolder extends RecyclerView.ViewHolder {

//    private TextView title;
//    private ImageView img;
    private Button btn;
private GridLayout test;

//    public LinearLayout root;
//    public TextView txtTitle;
//    public TextView txtDesc;


    public ViewHolder(@NonNull View itemView) {
        super(itemView);
//        this.title = itemView.findViewById(R.id.FLRVI_textView);
//        this.img = itemView.findViewById(R.id.FLRVI_imageView);
        this.btn = itemView.findViewById(R.id.FLRVI_btn);
        this.test = itemView.findViewById(R.id.temp_GRIDLAY);

//        root = itemView.findViewById(R.id.root);
//        txtTitle = itemView.findViewById(R.id.list_title);
//        txtDesc = itemView.findViewById(R.id.list_desc);

    }

//    public TextView getTitle() {
//        return this.title;
//    }
//
//    public ImageView getImg() {
//        return this.img;
//    }


    public GridLayout getTest() {
        return test;
    }

    public Button getBtn() {
        return this.btn;
    }

//    public void setTxtTitle(String string) {
//        txtTitle.setText(string);
//    }
//
//
//    public void setTxtDesc(String string) {
//        txtDesc.setText(string);
//    }
}
