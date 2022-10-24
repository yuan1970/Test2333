package com.example.test233

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * @author anyu
 * @date 2022/10/15-15:17
 * @desc
 */
class DoctorDiagnosisAdapter : BaseMultiItemQuickAdapter<DoctorDiagnosisBean, BaseViewHolder>() {

    init {
        addItemType(DoctorDiagnosisMultipleEntity.DIAGNOSIS, R.layout.diagnosis)
        addItemType(DoctorDiagnosisMultipleEntity.IMG, R.layout.img_2)
        addItemType(DoctorDiagnosisMultipleEntity.PERFORMANCE, R.layout.performance)
    }

    override fun convert(holder: BaseViewHolder, item: DoctorDiagnosisBean) {
        when (holder.itemViewType) {
            DoctorDiagnosisMultipleEntity.DIAGNOSIS -> {


            }
            DoctorDiagnosisMultipleEntity.IMG->{

            }
            DoctorDiagnosisMultipleEntity.PERFORMANCE->{

            }
        }
    }
}