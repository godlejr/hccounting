package hcc.accouting.haccounting.ui.main.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainTabAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragments;
    private FragmentManager fragmentManager;
    private Context context;
    public MainTabAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
        this.fragmentManager = fragmentManager;
        this.fragments = new ArrayList<>();
        this.context = context;

    }

    public void addFragment(Fragment fragment) {
        fragments.add(fragment);
    }

    public void removeFragment(Fragment fragment) {
        fragments.remove(fragment);
    }

    public void removeFragment(int position) {
        fragments.remove(position);
    }

    public void setFragmentAtThePosition(int position, Fragment fragment) {
        fragments.set(position, fragment);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = fragments.get(position);
        Bundle bundle = new Bundle(1);
        //bundle.putSerializable("user", (Serializable) user);

        if (fragment.getArguments() == null) {
            fragment.setArguments(bundle);
        }

        return fragment;
    }


    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public int getItemPosition(Object object) {
        Fragment fragment = (Fragment) object;
        int position = fragments.indexOf(fragment);
        if (position >= 0) {
            return position;
        } else {
            return POSITION_NONE;
        }
    }
}
