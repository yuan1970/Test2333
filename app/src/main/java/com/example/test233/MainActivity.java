package com.example.test233;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;



/**
 * @author anyu
 * @date 2022/10/15-8:22
 * @desc
 */
public class MainActivity extends AppCompatActivity {

    private NavController controller;
    private TextView firstTab,secondTab,thirdTab,fourthTab;

    private MyLiveData<TextView> currentTab=new MyLiveData<> ();

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        EventBus.getDefault ().register (this);
        firstTab=findViewById (R.id.tv_first_fragment_tab);
        secondTab=findViewById (R.id.tv_second_fragment_tab);
        thirdTab=findViewById (R.id.tv_third_fragment_tab);
        fourthTab=findViewById (R.id.tv_fourth_fragment_tab);
        initListener();
        currentTab.setValue (firstTab);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initListener () {
        firstTab.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                controller.navigate (R.id.firstFragment);
                if (currentTab.getValue ().getId ()!=firstTab.getId ()){
                    changeTabStates (currentTab.getValue (),firstTab);
                    currentTab.setValue (firstTab);
                }
            }
        });

        secondTab.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                controller.navigate (R.id.secondFragment);
                if (currentTab.getValue ().getId ()!=secondTab.getId ()){
                    changeTabStates (currentTab.getValue (),secondTab);
                    currentTab.setValue (secondTab);
                }
            }
        });

        thirdTab.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                controller.navigate (R.id.thirdFragment);
                if (currentTab.getValue ().getId ()!=thirdTab.getId ()){
                    changeTabStates (currentTab.getValue (),thirdTab);
                    currentTab.setValue (thirdTab);
                }
            }
        });

        fourthTab.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                controller.navigate (R.id.fourthFragment);
                if (currentTab.getValue ().getId ()!=fourthTab.getId ()){
                    changeTabStates (currentTab.getValue (),fourthTab);
                    currentTab.setValue (fourthTab);
                }
            }
        });
    }

    @Override
    protected void onStart () {
        super.onStart ();
        controller = Navigation.findNavController (this, R.id.nav_host_fragment);
    }

    @Override
    protected void onDestroy () {
        super.onDestroy ();
        EventBus.getDefault ().unregister (this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void shufflingPages (ShufflingStatesEvent event) {
        switch (event.getFrom ()) {
            case Key.FROM_FIRST_FRAGMENT:
                if (event.isState ()) {
                    controller.navigate (R.id.secondFragment);
                    changeTabStates (firstTab,secondTab);
                }
                break;
            case Key.FROM_SECOND_FRAGMENT:
                if (event.isState ()) {
                    controller.navigate (R.id.thirdFragment);
                    changeTabStates (secondTab,thirdTab);
                }
                break;
            case Key.FROM_THIRD_FRAGMENT:
                break;
            case Key.FROM_FOURTH_FRAGMENT:
                break;
            default:break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void changeTabStates(TextView firstTab, TextView secondTab){
        firstTab.setTypeface (Typeface.DEFAULT);
        secondTab.setTypeface (null,Typeface.BOLD);
        firstTab.setTextSize (28);
        secondTab.setTextSize (30);
        firstTab.setTextColor (getColor (R.color.greyText));
        secondTab.setTextColor (getColor (R.color.black));
    }
}
