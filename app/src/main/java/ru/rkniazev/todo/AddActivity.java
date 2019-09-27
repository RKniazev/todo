package ru.rkniazev.todo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    private boolean created;
    private int updIndex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

        int index = getIntent().getIntExtra("editIndex",-1);
        this.updIndex = index;
        if (index != -1){
            this.created = false;
            Plan plan = Store.getStore().get(index);
            EditText title = this.findViewById(R.id.title);
            title.setText(plan.getName());
            EditText disc = this.findViewById(R.id.disc);
            disc.setText(plan.getDiscription());
            Button btn = this.findViewById(R.id.add);
            btn.setText("edit");
        }
        else {
            this.created = true;
        }
    }

    public void addPlan(View view){
        EditText title = this.findViewById(R.id.title);
        EditText disc = this.findViewById(R.id.disc);
        if (!title.getText().toString().equals("")) {
            if (created == true) {
                Store.getStore().add(new Plan(title.getText().toString(), disc.getText().toString()));
                Intent intent = new Intent(this.getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }else {
                Store.getStore().update(this.updIndex,title.getText().toString(),disc.getText().toString());
                Intent intent = new Intent(this.getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }

        }
        else {
            Toast.makeText(this, "write text for your plan", Toast.LENGTH_SHORT).show();
        }
    }
}
