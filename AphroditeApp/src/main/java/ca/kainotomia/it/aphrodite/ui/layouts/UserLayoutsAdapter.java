package ca.kainotomia.it.aphrodite.ui.layouts;

import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;

import java.util.ArrayList;

import ca.kainotomia.it.aphrodite.R;

public class UserLayoutsAdapter extends FirebaseListAdapter<String> {
    public UserLayoutsAdapter(@NonNull FirebaseListOptions<String> options) {
        super(options);
    }

    @Override
    protected void populateView(@NonNull View v, @NonNull String model, int position) {

    }
//
//    private ArrayList<String> layoutName;
//    private Drawable img;
//    private int size;
//
//    @Override
//    public int getItemViewType(final int position) {
////        return super.getItemViewType(position);
//        return R.layout.fragment_layouts_recyclerview_item;
//    }
//
//    public UserLayoutsAdapter(ArrayList<String> layoutName, Drawable img, int size) {
//        this.layoutName = layoutName;
//        this.img = img;
//        this.size = size;
//        System.out.println("USER LAYOUTS: " + layoutName);
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(viewType,parent,false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
////        holder.getTitle().setText(this.layoutName);
////        holder.getImg().setImageResource(R.drawable.sun_button_oak);
//        System.out.println("POS" + position);
//        holder.getBtn().setText(getLayoutName(position));
////        Drawable des = get
//        holder.getBtn().setCompoundDrawablesWithIntrinsicBounds(null,getImg(),null,null);
//        holder.getBtn().setPadding(100,100,100,100);
////        holder.getBtn().
////        holder.getBtn().get
//
//    }
//
//    public int getSize() {
//        return this.size;
//    }
//
//    @Override
//    public int getItemCount() {
//        return getSize();
//    }
//
//    public String getLayoutName(int position) {
//        return this.layoutName.get(position);
//    }
//
//    public Drawable getImg() {
//        return this.img;
//    }
}
