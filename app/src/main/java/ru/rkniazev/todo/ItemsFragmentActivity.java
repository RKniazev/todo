package ru.rkniazev.todo;

import androidx.fragment.app.Fragment;

public class ItemsFragmentActivity extends BaseFragment{
    @Override
    public Fragment loadFragment() {
        return new ItemsFragment();
    }
}
