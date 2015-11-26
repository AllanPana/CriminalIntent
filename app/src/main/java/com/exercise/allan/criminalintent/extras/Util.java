package com.exercise.allan.criminalintent.extras;



import android.text.format.DateFormat;

import java.util.Date;

/**
 * Created by allan on 25/11/2015.
 */
public class Util {



    //return a String formatted date = Monday, Jan 1, 2001
    public static String formatDate(Date date){
        String dateStr = (String) DateFormat.format("EEEE, MMM dd, yyyy", date);
        return dateStr;
    }
}
