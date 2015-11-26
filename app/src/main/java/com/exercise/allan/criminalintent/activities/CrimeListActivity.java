package com.exercise.allan.criminalintent.activities;

import android.support.v4.app.Fragment;

import com.exercise.allan.criminalintent.fragments.CrimeListFragment;

/**
 * Created by allan on 24/11/2015.
 */
public class CrimeListActivity extends SingleFragmentActivity {


    //overriden method from SingleFragmentActivity
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
