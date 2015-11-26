package com.exercise.allan.criminalintent.models;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by allan on 24/11/2015.
 *
 * A singleton class to hold the list of crimes
 */
public class CrimeLab {

    private static CrimeLab sCrimeLab;
    private List<Crime> mCrimes;


    /**
     *
     * @param context
     */
    private CrimeLab(Context context){
        mCrimes = new ArrayList<>();
        for(int i=0;i<50;i++){
            Crime crime = new Crime();
            crime.setTitle("Crime # " + (i+1));
            crime.setSolved(i%2==0);
            mCrimes.add(crime);
        }
    }

    /**
     *
     * @return list of crime
     */
    public List<Crime> getCrimes() {
        return mCrimes;
    }


    /**
     *
     * @param uuid
     * @return crime by id
     */
    public Crime getCrime(UUID uuid){
        for(Crime crime : mCrimes){
            if(crime.getId().equals(uuid)){
                return crime;
            }
        }
        return null;
    }


    /**
     *
     * @param context
     * @return single instance of CrimeLab
     */
    public static CrimeLab getInstance(Context context){
        if(sCrimeLab == null){
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }
}
