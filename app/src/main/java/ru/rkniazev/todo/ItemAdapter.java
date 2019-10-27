package ru.rkniazev.todo;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public final class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int index) {
        return new RecyclerView.ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.items, parent, false)
        ) {};
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int index) {
        TextView title = holder.itemView.findViewById(R.id.title);
        holder.itemView.setOnClickListener(
                view -> {
                    Intent intent = new Intent(title.getContext(), ItemFragmentActivity.class);
                    intent.putExtra("index", index);
                    title.getContext().startActivity(intent);
                }
        );
        TextView disc = holder.itemView.findViewById(R.id.disc);
        TextView created = holder.itemView.findViewById(R.id.created);
        final TextView finish = holder.itemView.findViewById(R.id.finish);
        final CheckBox done = holder.itemView.findViewById(R.id.done);
        final Plan plan = Store.getStore().get(index);

        title.setText(plan.getName());
        if (plan.getDiscription().getBytes().length < 50){
            disc.setText(plan.getDiscription());
        }else {
            disc.setText(plan.getDiscription().substring(0,47) + " ...");
        }

        created.setText(plan.getCreatedToString());

        done.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton view, boolean checked) {
                plan.setDone(checked);
                if (checked == true) {
                    finish.setText(plan.getFinishToString());
                } else {
                    finish.setText("");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return Store.getStore().getSize();
    }

}
