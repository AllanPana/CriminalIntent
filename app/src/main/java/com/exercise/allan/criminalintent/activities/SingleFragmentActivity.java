package com.exercise.allan.criminalintent.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.exercise.allan.criminalintent.R;

/**
 * Created by allan on 24/11/2015.
 */
public abstract class SingleFragmentActivity extends FragmentActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);


        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if(fragment==null){
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container,fragment)
                    .commit();
        }
    }


    //abstract method for creating fragment to be implemented by child Activity
    protected abstract Fragment createFragment();


}
