package ru.rkniazev.todo;

import androidx.fragment.app.Fragment;

public class AddActivity extends BaseFragment {
    @Override
    public Fragment loadFragment() {
        int index = getIntent().getIntExtra("index", -1);
        return AddPalnFragment.instanceOf(index);
    }
}
