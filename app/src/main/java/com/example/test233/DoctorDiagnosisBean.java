package com.example.test233;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @author anyu
 * @date 2022/10/15-14:41
 * @desc
 */
public class DoctorDiagnosisBean implements MultiItemEntity {


    private Integer type=0;


    public Integer getType () {
        return type;
    }

    public void setType (Integer type) {
        this.type = type;
    }


    @Override
    public int getItemType () {
        return type;
    }
}
