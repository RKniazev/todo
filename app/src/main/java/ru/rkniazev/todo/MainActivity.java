package ru.rkniazev.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

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

        Intent intent = new Intent(this.getApplicationContext(), AddActivity.class);
        startActivity(intent);
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
                            .inflate(R.layout.items, parent, false)
            ) {};
        }

        @Override
        public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int index) {
            TextView title = holder.itemView.findViewById(R.id.title);
            TextView disc = holder.itemView.findViewById(R.id.disc);
            TextView created = holder.itemView.findViewById(R.id.created);
            final TextView finish = holder.itemView.findViewById(R.id.finish);
            final CheckBox done = holder.itemView.findViewById(R.id.done);
            final Plan plan = Store.getStore().get(index);

            title.setText(plan.getName());
            if (plan.getDiscription().getBytes().length < 50){
                disc.setText(plan.getDiscription());
            }else {
                disc.setText(plan.getDiscription().substring(0,50) + " ...");
            }

            created.setText(formatDateToString(plan.getCreated()));
            finish.setText("");

            done.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton view, boolean checked) {
                    plan.setDone(checked);
                    if (checked == true) {
                        finish.setText(formatDateToString(plan.getFinish()));
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

        private String formatDateToString(Calendar calendar){
            return String.format(
                    Locale.getDefault(),"%02d.%02d.%d %02d:%02d",
                    calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR), calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE)
            );

        }
    }
}
