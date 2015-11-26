package com.exercise.allan.criminalintent.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.exercise.allan.criminalintent.R;
import com.exercise.allan.criminalintent.fragments.CrimeFragment;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {

    private static final String EXTRA_CRIME_ID  = "com.exercise.allan.criminalintent.activities.crime_id";


    /**
     *
     * @param packageContext
     * @param crimeId
     * @return an Intent
     *
     * to be called in CrimeListFragment
     */
    public static Intent newIntent(Context packageContext, UUID crimeId){
        Intent intent = new Intent(packageContext,CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }


    //overriden method from SingleFragmentActivity
    @Override
    protected Fragment createFragment() {
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);
    }
}
