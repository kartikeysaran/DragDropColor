package kartikey.saran.myapplication.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import kartikey.saran.myapplication.Application;
import kartikey.saran.myapplication.Model.Obj;
import kartikey.saran.myapplication.R;

public class SelectPartAdapter extends RecyclerView.Adapter<SelectPartAdapter.ViewHolder> {

    ArrayList<Obj> objects;
    Context context;

    public SelectPartAdapter(ArrayList<Obj> objects, Context context) {
        this.context = context;
        this.objects = objects;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_select_parts, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SelectPartAdapter.ViewHolder holder, int position) {
        final Obj myListData = objects.get(position);
        holder.textView.setText(myListData.getTitle());
        holder.imageView.setImageResource(myListData.getImage());
        holder.checkBox.setChecked(false);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Application.objs.add(myListData);
                } else {
                    Application.objs.remove(myListData);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;
        CheckBox checkBox;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            checkBox = itemView.findViewById(R.id.checkbox);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
