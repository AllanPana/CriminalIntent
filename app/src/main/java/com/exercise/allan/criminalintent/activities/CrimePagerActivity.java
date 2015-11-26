package com.exercise.allan.criminalintent.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.exercise.allan.criminalintent.R;
import com.exercise.allan.criminalintent.fragments.CrimeFragment;
import com.exercise.allan.criminalintent.models.Crime;
import com.exercise.allan.criminalintent.models.CrimeLab;

import java.util.List;
import java.util.UUID;

/**
 * Created by allan on 25/11/2015.
 */
public class CrimePagerActivity extends FragmentActivity {

    private static final String EXTRA_CRIME_ID = "com.exercise.allan.criminalintent.activities.crime_id";
    private ViewPager mViewPager;
    private List<Crime> mCrimes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_crime_view_pager);
        mCrimes = CrimeLab.getInstance(this).getCrimes();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {

            @Override
            public Fragment getItem(int position) {

                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });


       setViewPagerPosition(crimeId);
    }



    //to
    public static Intent newIntent(Context con, UUID crimeId) {
        Intent intent = new Intent(con, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID,crimeId);
        return intent;
    }



    //set the viewpager when click specific item on the crimnListFragment
    private void setViewPagerPosition(UUID crimeId){
        if(crimeId!=null){
            for(int i=0; i<mCrimes.size();i++){
                if(mCrimes.get(i).getId().equals(crimeId)){
                    mViewPager.setCurrentItem(i);

                }
            }
        }

    }
}
