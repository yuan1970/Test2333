package com.example.test233;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Locale;

/**
 * @author anyu
 * @date 2022/10/15-8:38
 * @desc
 */
public class SecondFragment extends Fragment implements TextToSpeech.OnInitListener{

    private TextToSpeech ttobj;

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate (R.layout.fragment_second,container,false);
    }


    @Override
    public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);

         ttobj = new TextToSpeech (getActivity (),this);

        TextView textView=view.findViewById (R.id.tv);
        textView.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                ttobj.speak("星期三星期三的星期三星期三星期三的星期三星期三星期三的星期三星期三星期三的星期三", TextToSpeech.QUEUE_FLUSH, null);
            }
        });

    }

    @Override
    public void onDestroyView () {
        super.onDestroyView ();
        if (ttobj!=null){
            ttobj.stop ();
            ttobj.shutdown ();
        }
    }

    @Override
    public void onInit (int status) {
        if (status == TextToSpeech.SUCCESS) {
            //设置首选语言为中文,注意，语言可能是不可用的，结果将指示此
            int result = ttobj.setLanguage(Locale.CHINA);
            if (result == TextToSpeech.LANG_MISSING_DATA ||
                    result == TextToSpeech.LANG_NOT_SUPPORTED) {
                //语言数据丢失或不支持该语言。
                Log.e("TAG", "语言数据丢失或不支持该语言");
            } else {
                //检查文档中其他可能的结果代码。
                // 例如，语言可能对区域设置可用，但对指定的国家和变体不可用
                // TTS引擎已成功初始化。
                // 允许用户按下按钮让应用程序再次发言。
            }
        } else {
            // 初始化失败
            Log.e("TAG", "初始化失败");
        }
    }
}
