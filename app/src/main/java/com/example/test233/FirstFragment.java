package com.example.test233;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anyu
 * @date 2022/10/15-8:38
 * @desc
 */
public class FirstFragment extends Fragment {

    private ShowAdapter mAdapter;

    {
        mAdapter = new ShowAdapter ();
    }

    private RecyclerView recyclerView;

    private MyLiveData<Integer> mCurrentPosition = new MyLiveData<> ();

    private Handler mHandler = new Handler ();

    private Runnable bannerRunnable = new Runnable () {
        @Override
        public void run () {
            if (mCurrentPosition.getValue ()+1<mAdapter.getData ().size ()){
                recyclerView.scrollToPosition (mCurrentPosition.getValue () + 1);
                mCurrentPosition.setValue (mCurrentPosition.getValue ()+1);
            }
            mHandler.postDelayed (bannerRunnable, 3000);
        }
    };


    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate (R.layout.fragment_first, container, false);
    }


    @Override
    public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);
        mCurrentPosition.setValue (0);

        recyclerView = view.findViewById (R.id.rv);
        recyclerView.setLayoutManager (new LinearLayoutManager (getContext ()) {
            @Override
            public boolean canScrollVertically () {
                return false;
            }
        });

        recyclerView.setAdapter (mAdapter);

        List<ShowBean> data = new ArrayList<> ();

        ShowBean img = new ShowBean ();
        img.setType (0);
        img.setImg ("https://lf-cdn-tos.bytescm.com/obj/static/xitu_extension/static/github.46c47564.png");
        img.setText ("图文展示");
        data.add (img);

        ShowBean img2 = new ShowBean ();
        img.setType (0);
        img.setImg ("https://lf-cdn-tos.bytescm.com/obj/static/xitu_extension/static/github.46c47564.png");
        img.setText ("图文展示222222222222222222");
        data.add (img2);


        ShowBean img3 = new ShowBean ();
        img.setType (0);
        img.setImg ("https://lf-cdn-tos.bytescm.com/obj/static/xitu_extension/static/github.46c47564.png");
        img.setText ("图文展示333333333333333333333333333");
        data.add (img3);


        ShowBean text = new ShowBean ();
        img.setType (1);
        img.setText ("文字展示");
        data.add (text);

        mAdapter.setList (data);

        mHandler.removeCallbacksAndMessages(null);
        mHandler.postDelayed(bannerRunnable, 3000);

    }
}
