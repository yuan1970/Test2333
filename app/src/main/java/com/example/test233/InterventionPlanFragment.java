package com.example.test233;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @author anyu
 * @date 2022/10/24-15:47
 * @desc
 */
public class InterventionPlanFragment extends Fragment {


    public static InterventionPlanFragment newInstance() {
        InterventionPlanFragment fragment = new InterventionPlanFragment();
        Bundle args = new Bundle();

        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate (R.layout.fragment_intervention_plan,container,false);
    }


    @Override
    public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);
    }
}
