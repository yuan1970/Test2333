package com.example.test233;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author anyu
 * @date 2022/10/24-14:37
 * @desc
 */
public class DoctorDiagnosisFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate (R.layout.fragment_doctor_diagnosis,container,false);
    }


    @Override
    public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);
        RecyclerView recyclerView=view.findViewById (R.id.rv);
        recyclerView.setLayoutManager (new LinearLayoutManager (getContext ()){
            @Override
            public boolean canScrollVertically () {
                return false;
            }
        });
    }
}
