package com.example.test233;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    private MyExpandableAdapter expandAdapter;

    private RecyclerView recyclerView;

    private ExpandableListView expandableListView;

    private MyLiveData<Integer> mCurrentPosition = new MyLiveData<> ();

    private Handler mHandler = new Handler ();

    private Runnable bannerRunnable = new Runnable () {
        @Override
        public void run () {
            if (mCurrentPosition.getValue () + 1 < mAdapter.getData ().size ()) {

                mCurrentPosition.setValue (mCurrentPosition.getValue () + 1);
            } else {
                ShufflingStatesEvent event = new ShufflingStatesEvent (Key.FROM_FIRST_FRAGMENT);
                event.setState (true);
                EventBus.getDefault ().post (event);
            }
            mHandler.postDelayed (bannerRunnable, 10000);
        }
    };


    private String[] groups = {"好友", "同学", "同事"};
    private String[][] childs = {{"Tom", "Jerry", "Jeck"}, {"XY", "WX", "YH"}, {}};


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
        expandableListView = view.findViewById (R.id.elv);

        initExpandableList ();

        mCurrentPosition.observeInFragment (this, new Observer<Integer> () {
            @Override
            public void onChanged (Integer integer) {
                int[] data = getGroupAdChildNum (integer);
                if (!expandableListView.isGroupExpanded (data[0])) {
                    expandableListView.expandGroup (data[0]);
                }
                expandAdapter.setPosition (data[0], data[1]);
                recyclerView.scrollToPosition (integer);
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

        ShowBean img4 = new ShowBean ();
        img4.setType (0);
        img4.setImg ("https://lf-cdn-tos.bytescm.com/obj/static/xitu_extension/static/github.46c47564.png");
        img4.setText ("图文展示44444444444444444444444444444");
        data.add (img4);


        ShowBean img5 = new ShowBean ();
        img5.setType (0);
        img5.setImg ("https://lf-cdn-tos.bytescm.com/obj/static/xitu_extension/static/github.46c47564.png");
        img5.setText ("图文展示5555555555555555555555555555555555555");
        data.add (img5);

        ShowBean text2 = new ShowBean ();
        text2.setType (1);
        text2.setText ("文字展示7777777777777777777777777");
        data.add (text2);

        mAdapter.setList (data);

        mHandler.removeCallbacksAndMessages (null);
        mHandler.postDelayed (bannerRunnable, 10000);

    }

    private void initExpandableList () {

        expandAdapter = new MyExpandableAdapter (getContext (), groups, childs);
        expandableListView.setAdapter (expandAdapter);
        if (childs[0].length != 0) {
            expandAdapter.setPosition (0, 0);
        } else {
            expandAdapter.setPosition (0, -1);
        }

        for (int i = 0; i < expandAdapter.getGroupCount (); i++) {
            expandableListView.expandGroup (i);
        }

        expandableListView.setOnGroupClickListener (new ExpandableListView.OnGroupClickListener () {
            @Override
            public boolean onGroupClick (ExpandableListView parent, View v, int groupPosition, long id) {
                Log.e ("TAG", "onItemClick: ------------------------>>>>>" + groupPosition + "------" + groups[groupPosition]);
                if (childs[groupPosition] == null || childs[groupPosition].length == 0) {
                    expandAdapter.setPosition (groupPosition, -1);
                    int choosePosition = 0;
                    for (int i = 0; i < groupPosition; i++) {
                        if (childs[i].length == 0) {
                            choosePosition++;
                        } else {
                            choosePosition = choosePosition + childs[i].length;
                        }
                    }
                    mCurrentPosition.setValue (choosePosition);
                    mHandler.removeCallbacksAndMessages (null);
                    mHandler.postDelayed (bannerRunnable, 10000);
                } else {
                    int integer = mCurrentPosition.getValue ();
                    int[] data = getGroupAdChildNum (integer);
                    expandAdapter.setPosition (data[0], data[1]);
                }
                return false;
            }
        });
        expandableListView.setOnChildClickListener (new ExpandableListView.OnChildClickListener () {
            @Override
            public boolean onChildClick (ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Log.e ("TAG", "onItemClick: ------------------------>>>>>" + childPosition + "------" + childs[groupPosition][childPosition]);
                expandAdapter.setPosition (groupPosition, childPosition);
                int choosePosition = 0;
                for (int i = 0; i < groupPosition; i++) {
                    if (childs[i].length == 0) {
                        choosePosition++;
                    } else {
                        choosePosition = choosePosition + childs[i].length;
                    }
                }

                if (childs[groupPosition].length == 0) {
                    choosePosition++;
                } else {
                    choosePosition = choosePosition + childPosition + 1;
                }

                mCurrentPosition.setValue (choosePosition - 1);
                mHandler.removeCallbacksAndMessages (null);
                mHandler.postDelayed (bannerRunnable, 10000);
                return true;
            }
        });
    }

    private int[] getGroupAdChildNum (int integer) {
        int choosePosition = -1;
        int recordGroupNum = 0;
        int recordChildNum = 0;
        for (int i = 0; i < groups.length; i++) {
            if (childs[i].length == 0) {
                choosePosition++;
                if (choosePosition == integer) {
                    recordGroupNum = i;
                    recordChildNum = -1;
                    break;
                }
            } else {
                for (int j = 0; j < childs[i].length; j++) {
                    choosePosition++;
                    if (choosePosition == integer) {
                        recordGroupNum = i;
                        recordChildNum = j;
                        break;
                    }
                }
            }
        }

        return new int[]{recordGroupNum, recordChildNum};
    }
}
