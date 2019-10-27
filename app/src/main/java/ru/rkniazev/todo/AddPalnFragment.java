package ru.rkniazev.todo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddPalnFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add, container, false);
        checkEdit(view);
        return view;
    }

    public static AddPalnFragment instanceOf(int index){
        Bundle args = new Bundle();
        args.putInt("index", index);
        AddPalnFragment frg = new AddPalnFragment();
        frg.setArguments(args);
        return frg;
    }

    public void checkEdit(View view) {
        int planIndex = getArguments().getInt("index");
        if (planIndex != -1){
            EditText title = view.findViewById(R.id.title);
            EditText disc = view.findViewById(R.id.disc);
            Plan plan = Store.getStore().get(planIndex);
            title.setText(plan.getName());
            disc.setText(plan.getDiscription());
            Button added = view.findViewById(R.id.add);
            added.setText("edit");
            added.setOnClickListener(btn -> editPlan(view));

        }
        else {
            Button added = view.findViewById(R.id.add);
            added.setOnClickListener(btn -> addPlan(view));
        }
    }

    public void addPlan(View view) {
        EditText title = view.findViewById(R.id.title);
        EditText disc = view.findViewById(R.id.disc);
        Plan plan = new Plan(title.getText().toString(), disc.getText().toString());
        Store.getStore().add(plan);
        Intent intent = new Intent(getContext(), ItemsFragmentActivity.class);
        startActivity(intent);
    }

    public void editPlan(View view) {
        EditText title = view.findViewById(R.id.title);
        EditText disc = view.findViewById(R.id.disc);
        Store.getStore().update(getArguments().getInt("index"),title.getText().toString(), disc.getText().toString());
        Intent intent = new Intent(getContext(), ItemsFragmentActivity.class);
        startActivity(intent);
    }


}