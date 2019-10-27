package ru.rkniazev.todo;

import androidx.fragment.app.Fragment;

public class ItemFragmentActivity extends BaseFragment {
    @Override
    public Fragment loadFragment() {
        int index = getIntent().getIntExtra("index", -1);
        return ItemFragment.instanceOf(index);
    }
}