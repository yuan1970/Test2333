package com.example.test233;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author anyu
 * 2021/10/27 9:51
 */
public class MyLiveData<T> extends LiveData<T> {

    private final HashMap<Integer, Boolean> observers = new HashMap<>();

    @Override
    public void setValue(T value) {
        if (value != null) {
            for (Map.Entry<Integer, Boolean> entry : observers.entrySet()) {
                entry.setValue(false);
            }
            super.setValue(value);
        }
    }

    public void observeInFragment(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
    /*fragment 传入getViewLifecycleOwner
     getViewLifeCycleOwner 是 2020 年新增的特性，
     主要是为了解决 getView() 的生命长度 比 fragment 短（仅存活于 onCreateView 之后和 onDestroyView 之前），
     导致某些时候 fragment 其他成员还活着，但 getView() 为 null 的 生命周期安全问题，
     也即，在 fragment 的场景下，请使用 getViewLifeCycleOwner 来作为 liveData 的观察者。
     Activity 则不用改变。*/
        //源码这里(下一行)是activity.getViewModelStore()，是为了保证同一个ViewModel环境下"唯一可信源"
        Integer storeId = System.identityHashCode(observer);
        observe(storeId, owner, observer);
    }

    private void observe(
            @NonNull Integer storeId,
            @NonNull LifecycleOwner owner,
            @NonNull Observer<? super T> observer
    ) {
        if (observers.get(storeId) == null) {
            observers.put(storeId, true);
        }

        super.observe(owner, t -> {
            if (Boolean.FALSE.equals(observers.get(storeId))) {
                observers.put(storeId, true);
                if (t != null) {
                    observer.onChanged(t);
                }
            }
        });
    }

}
