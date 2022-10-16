package com.example.test233;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import org.greenrobot.eventbus.EventBus;

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

    private ExpandableListView expandableListView;

    private MyLiveData<Integer> mCurrentPosition = new MyLiveData<> ();

    private Handler mHandler = new Handler ();

    private Runnable bannerRunnable = new Runnable () {
        @Override
        public void run () {
            if (mCurrentPosition.getValue ()+1<mAdapter.getData ().size ()){

                recyclerView.scrollToPosition (mCurrentPosition.getValue () + 1);
                mCurrentPosition.setValue (mCurrentPosition.getValue ()+1);
            }else {
                ShufflingStatesEvent event=new ShufflingStatesEvent (Key.FROM_FIRST_FRAGMENT);
                event.setState (true);
                EventBus.getDefault ().post (event);
            }
            mHandler.postDelayed (bannerRunnable, 1000);
        }
    };


    private String[] groups={"好友","同学","同事"};
    private String[][] childs={{"Tom","Jerry","Jeck"},{"XY","WX","YH"},{}};


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
        expandableListView=view.findViewById (R.id.elv);
        MyExpandableAdapter adapter = new MyExpandableAdapter (getContext (), groups, childs);
        expandableListView.setAdapter(adapter);

        for(int i = 0; i < adapter.getGroupCount(); i++){
            expandableListView.expandGroup(i);
        }

        expandableListView.setOnGroupClickListener (new ExpandableListView.OnGroupClickListener () {
            @Override
            public boolean onGroupClick (ExpandableListView parent, View v, int groupPosition, long id) {
                Log.e ("TAG", "onItemClick: ------------------------>>>>>"+groupPosition+"------"+groups[groupPosition] );
                if (childs[groupPosition]==null||childs[groupPosition].length==0){
                    adapter.setPosition (groupPosition,-1);
                }else {
                    adapter.setPosition (-1,-1);
                }
                return false;
            }
        });
        expandableListView.setOnChildClickListener (new ExpandableListView.OnChildClickListener () {
            @Override
            public boolean onChildClick (ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Log.e ("TAG", "onItemClick: ------------------------>>>>>"+childPosition+"------"+childs[groupPosition][childPosition] );
                adapter.setPosition (groupPosition,childPosition);
                return true;
            }
        });

        mCurrentPosition.observeInFragment (this, new Observer<Integer> () {
            @Override
            public void onChanged (Integer integer) {
                if (!expandableListView.isGroupExpanded (integer)){
                    expandableListView.expandGroup (integer);
                }
            }
        });

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
        img2.setType (0);
        img2.setImg ("https://lf-cdn-tos.bytescm.com/obj/static/xitu_extension/static/github.46c47564.png");
        img2.setText ("图文展示222222222222222222");
        data.add (img2);


        ShowBean img3 = new ShowBean ();
        img3.setType (0);
        img3.setImg ("https://lf-cdn-tos.bytescm.com/obj/static/xitu_extension/static/github.46c47564.png");
        img3.setText ("图文展示333333333333333333333333333");
        data.add (img3);


        ShowBean text = new ShowBean ();
        text.setType (1);
        text.setText ("文字展示");
        data.add (text);

        mAdapter.setList (data);

//        mHandler.removeCallbacksAndMessages(null);
//        mHandler.postDelayed(bannerRunnable, 1000);

    }
}
