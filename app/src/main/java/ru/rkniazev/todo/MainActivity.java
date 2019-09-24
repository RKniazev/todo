package ru.rkniazev.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final List<Plan> plan = new ArrayList<>();
    private final RecyclerView.Adapter adapter = new ItemAdapter(this.plan);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recycler = findViewById(R.id.recycler);

        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
    }

    public void add(View view){
        EditText text = this.findViewById(R.id.inpText);
        if (!text.getText().toString().equals("")){
            this.plan.add(new Plan(text.getText().toString()));
            adapter.notifyItemInserted(this.plan.size()-1);
        }
        Toast.makeText(this,"added: " + text.getText().toString(),Toast.LENGTH_SHORT).show();
        text.setText("");
    }

    private static final class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private final List<Plan> items;

        public ItemAdapter(List<Plan> items) {
            this.items = items;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int index) {
            return new RecyclerView.ViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.item, parent, false)
            ) {};
        }

        @Override
        public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int index) {
            TextView name = holder.itemView.findViewById(R.id.name);
            name.setText(this.items.get(index).getName());
            Button btnDel = (Button) holder.itemView.findViewById(R.id.del);
            btnDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    items.remove(index);
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getItemCount() {
            return this.items.size();
        }
    }
}
