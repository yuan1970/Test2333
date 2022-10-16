package com.example.test233;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @author anyu
 * @date 2022/10/15-14:41
 * @desc
 */
public class ShowBean implements MultiItemEntity {

    private String text;
    private String img;
    private Integer type=0;

    public String getText () {
        return text;
    }

    public void setText (String text) {
        this.text = text;
    }

    public String getImg () {
        return img;
    }

    public void setImg (String img) {
        this.img = img;
    }

    public Integer getType () {
        return type;
    }

    public void setType (Integer type) {
        this.type = type;
    }


    @Override
    public String toString () {
        return "ShowBean{" +
                "text='" + text + '\'' +
                ", img='" + img + '\'' +
                ", type=" + type +
                '}';
    }

    @Override
    public int getItemType () {
        return type;
    }
}
