package ru.rkniazev.todo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
    }

    public void addPlan(View view){
        EditText title = this.findViewById(R.id.title);
        EditText disc = this.findViewById(R.id.disc);
        if (!title.getText().toString().equals("")) {
            Store.getStore().add(new Plan(title.getText().toString(),disc.getText().toString()));
            Intent intent = new Intent(this.getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "write text for your plan", Toast.LENGTH_SHORT).show();
        }
    }
}
