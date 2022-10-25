package com.example.test233;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import java.util.LinkedList;
import java.util.List;

/**
 * @author anyu
 * @date 2022/10/15-8:38
 * @desc
 */
public class FourthFragment extends Fragment {

    private TextView mTvDoctorDiagnosis,mTvInterventionPlan,mTvRecommendPrescription,mTvRecommendFood,mTvRecommendDrug,mTvRecommendOthers;
    private ViewPager2 viewPager2;
    private MyLiveData<TextView> currentTab=new MyLiveData<> ();

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate (R.layout.fragment_fourth,container,false);
    }


    @Override
    public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);
        mTvDoctorDiagnosis=view.findViewById (R.id.tv_doctor_diagnosis);
        mTvInterventionPlan=view.findViewById (R.id.tv_intervention_plan);
        mTvRecommendPrescription=view.findViewById (R.id.tv_recommend_prescription);
        mTvRecommendFood=view.findViewById (R.id.tv_recommend_food);
        mTvRecommendDrug=view.findViewById (R.id.tv_recommend_drug);
        mTvRecommendOthers=view.findViewById (R.id.tv_recommend_others);

        viewPager2=view.findViewById (R.id.vp);

        List<Fragment> fragmentList = new LinkedList<> ();
        fragmentList.add (DoctorDiagnosisFragment.newInstance ());
        fragmentList.add (InterventionPlanFragment.newInstance ());
        fragmentList.add (RecommendFragment.newInstance ());
        fragmentList.add (RecommendFragment.newInstance ());
        fragmentList.add (RecommendFragment.newInstance ());
        fragmentList.add (RecommendFragment.newInstance ());

        viewPager2.setUserInputEnabled(false);

        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getParentFragmentManager (),getLifecycle(),fragmentList);
        viewPager2.setAdapter(myFragmentPagerAdapter);

        viewPager2.setCurrentItem (0);
        currentTab.setValue (mTvDoctorDiagnosis);

        mTvDoctorDiagnosis.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                viewPager2.setCurrentItem (0);
                changeTabState (currentTab.getValue (),mTvDoctorDiagnosis);
            }
        });

        mTvInterventionPlan.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                viewPager2.setCurrentItem (1);
                changeTabState (currentTab.getValue (),mTvInterventionPlan);
            }
        });

        mTvRecommendPrescription.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                viewPager2.setCurrentItem (2);
                changeTabState (currentTab.getValue (),mTvRecommendPrescription);
            }
        });

        mTvRecommendFood.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                viewPager2.setCurrentItem (3);
                changeTabState (currentTab.getValue (),mTvRecommendFood);
            }
        });

        mTvRecommendDrug.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                viewPager2.setCurrentItem (4);
                changeTabState (currentTab.getValue (),mTvRecommendDrug);
            }
        });

        mTvRecommendOthers.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                viewPager2.setCurrentItem (5);
                changeTabState (currentTab.getValue (),mTvRecommendOthers);
            }
        });
    }

    private void changeTabState(TextView currentTab,TextView clickTab){
        if (clickTab.getId ()==clickTab.getId ()){
            return;
        }
        currentTab.setBackground (getContext ().getResources ().getDrawable (R.drawable.bg_white));
        currentTab.setTextColor (getContext ().getResources() .getColor (R.color.black));

        clickTab.setBackground (getContext ().getResources ().getDrawable (R.drawable.bg_blue));
        clickTab.setTextColor (getContext ().getResources() .getColor (R.color.white));
    }
}
