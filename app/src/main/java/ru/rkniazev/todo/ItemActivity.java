package ru.rkniazev.todo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ItemActivity extends AppCompatActivity {
 @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_item);
        Plan plan = Store.getStore().get(getIntent().getIntExtra("index",0));

        TextView title = this.findViewById(R.id.itemActivityTitle);
        title.setText(plan.getName());
        TextView disc = this.findViewById(R.id.itemActivityDisc);
        disc.setText(plan.getDiscription());
        TextView created = this.findViewById(R.id.itemActivityCreated);
        created.setText(plan.getCreatedToString());
        TextView finish = this.findViewById(R.id.itemActivityFinish);
        if (plan.isDone()) {
            finish.setText(plan.getFinishToString());
        }else {
            finish.setText("");
        }

        this.findViewById(R.id.itemActivityBtnEdit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit(getIntent().getIntExtra("index",0));
            }
        });
    }

    public void edit(int index){
        Intent edit = new Intent(this.getApplicationContext(), AddActivity.class);
        edit.putExtra("editIndex", index);
        startActivity(edit);
    }
}