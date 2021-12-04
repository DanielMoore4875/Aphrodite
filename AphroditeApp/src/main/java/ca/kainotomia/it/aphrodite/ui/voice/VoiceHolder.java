package ca.kainotomia.it.aphrodite.ui.voice;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ca.kainotomia.it.aphrodite.R;

public class VoiceHolder extends RecyclerView.ViewHolder {
    private final TextView title;
    private final TextView desc;

    public VoiceHolder(@NonNull View itemView) {
        super(itemView);
        this.title = itemView.findViewById(R.id.VC_cmdTitle);
        this.desc = itemView.findViewById(R.id.VC_cmdDesc);
    }

    public TextView getTitle() {
        return this.title;
    }

    public TextView getDesc() {
        return this.desc;
    }

}
