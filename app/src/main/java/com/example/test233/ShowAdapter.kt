package com.example.test233

import android.util.Log
import android.widget.TextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import kotlin.math.log

/**
 * @author anyu
 * @date 2022/10/15-15:17
 * @desc
 */
class ShowAdapter : BaseMultiItemQuickAdapter<ShowBean, BaseViewHolder>() {

    init {
        addItemType(ShowMultipleEntity.IMG, R.layout.img);
        addItemType(ShowMultipleEntity.TEXT, R.layout.text);
        addItemType(ShowMultipleEntity.OTHERS, R.layout.others);
    }

    override fun convert(holder: BaseViewHolder, item: ShowBean) {
        when (holder.itemViewType) {
            ShowMultipleEntity.IMG -> {
                Glide.with(context).load(item.img).centerCrop().into(holder.getView(R.id.img))
                holder.getView<TextView>(R.id.text).text = item.text

            }
            ShowMultipleEntity.TEXT->{
                holder.getView<TextView>(R.id.text).text = item.text
            }
            ShowMultipleEntity.OTHERS->{

            }
        }
    }
}