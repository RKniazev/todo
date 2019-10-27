package ru.rkniazev.todo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ItemFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.acticity_item, container, false);
        Store store = Store.getStore();
        int index = getArguments().getInt("index");
        Plan plan = store.get(index);
        TextView title = view.findViewById(R.id.itemActivityTitle);
        title.setText(plan.getName());
        TextView disc = view.findViewById(R.id.itemActivityDisc);
        disc.setText(plan.getDiscription());
        TextView created = view.findViewById(R.id.itemActivityCreated);
        created.setText(plan.getCreatedToString());
        TextView finish = view.findViewById(R.id.itemActivityFinish);
        if (plan.isDone()) {
            finish.setText(plan.getFinishToString());
        }else {
            finish.setText("");
        }

        ImageButton btn = view.findViewById(R.id.itemActivityBtnEdit);

        btn.setOnClickListener(
                v -> {
                    Intent intent = new Intent(this.getActivity(), AddActivity.class);
                    intent.putExtra("index",index);
                    startActivity(intent);
                }
        );
        return view;
    }

    public static ItemFragment instanceOf(int index){
        Bundle args = new Bundle();
        args.putInt("index", index);
        ItemFragment frg = new ItemFragment();
        frg.setArguments(args);
        return frg;
    }
}
