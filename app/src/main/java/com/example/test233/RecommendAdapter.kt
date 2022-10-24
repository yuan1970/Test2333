package com.example.test233

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * @author anyu
 * @date 2022/10/24-16:03
 * @desc
 */
class RecommendAdapter : BaseMultiItemQuickAdapter<RecommendBean, BaseViewHolder>() {

    init {
        addItemType(RecommendMultipleEntity.NORMAL, R.layout.normal)
        addItemType(RecommendMultipleEntity.IMG, R.layout.img_3)
        addItemType(RecommendMultipleEntity.OTHER, R.layout.other)
    }

    override fun convert(holder: BaseViewHolder, item: RecommendBean) {
        when (holder.itemViewType) {
            RecommendMultipleEntity.NORMAL -> {


            }
            RecommendMultipleEntity.IMG->{

            }
            RecommendMultipleEntity.OTHER->{

            }
        }
    }
}