package com.exercise.allan.criminalintent.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.exercise.allan.criminalintent.R;
import com.exercise.allan.criminalintent.activities.CrimeActivity;
import com.exercise.allan.criminalintent.activities.CrimePagerActivity;
import com.exercise.allan.criminalintent.extras.Util;
import com.exercise.allan.criminalintent.models.Crime;
import com.exercise.allan.criminalintent.models.CrimeLab;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CrimeListFragment extends Fragment {

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mCrimeAdapter;
    private int mLastUpdatedPositionChanged;


    public CrimeListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list,container,false);
        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mCrimeRecyclerView.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);



        updateUI();


        return view;
    }


    /**
     * Setting up RecyclerView and Adapter for updating the UI.
     */
    private void updateUI(){
        CrimeLab crimeLab = CrimeLab.getInstance(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();


        if(mCrimeAdapter==null){
            mCrimeAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mCrimeAdapter);
        }else {
            //mCrimeAdapter.notifyDataSetChanged();
            mCrimeAdapter.notifyItemChanged(mLastUpdatedPositionChanged);
        }


    }



    /**
     * Adapter class to use by RecyclerView
     */
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{

        private List<Crime> mCrimes;

        /**
         *
         * @param crimes = list of crime to be passed into constructor
         *
         */
        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;

        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_crime,parent,false);
            return new CrimeHolder(view);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            holder.bindCrime(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }




    /**
     * ViewHolder class to use by the adapter
     */
    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;
        private Crime mCrime;


        /**
         *
         * @param crime
         * to be called in onBindViewHolder of the Adapter
         */
        public void bindCrime(Crime crime){
            mCrime = crime;
            mTitleTextView.setText(crime.getTitle());
            mDateTextView.setText(Util.formatDate(crime.getDate()));
            mSolvedCheckBox.setChecked(crime.isSolved());

        }

        public CrimeHolder(View itemView) {
            super(itemView);

            //dont forget to set the onclick here
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_crime_title_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_crime_date_text_view);
            mSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_crime_solved_check_box);
        }

        @Override
        public void onClick(View v) {

            /*Intent intent = new Intent(getActivity(),CrimeActivity.class);
            intent.putExtra("com.exercise.allan.criminalintent.activities.crime_id", mCrime.getId());*/

            mLastUpdatedPositionChanged = getAdapterPosition();
            //Intent intent = CrimeActivity.newIntent(getActivity(),mCrime.getId());
            Intent intent = CrimePagerActivity.newIntent(getActivity(),mCrime.getId());
            startActivity(intent);
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }
}
