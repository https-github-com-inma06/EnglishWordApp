package com.uhavecodingproblem.wordsrpg.util

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager

/**
 * wordsrpg
 * Class: LinearLayoutWapper
 * Created by pyg10.
 * Created On 2021-03-27.
 * Description:
 */

// 간혈적으로 아래의 에러가 발생
// java.lang.IndexOutOfBoundsException: Inconsistency detected. Invalid view holder adapter positionItemViewHolder{970784e position=4 id=-1, oldPos=0, pLpos:0 scrap [attachedScrap] tmpDetached no parent} androidx.recyclerview.widget.RecyclerView{d03a7db VFED..... ......ID 24,395-948,1364 #7f0901c7 app:id/recyclerview_stage}, adapter:com.uhavecodingproblem.wordsrpg.component.library.recyclerviewadapter.StageDialogRecyclerViewAdapter@6156278, layout:androidx.recyclerview.widget.LinearLayoutManager@bebad51, context:android.view.ContextThemeWrapper@74235ae


class LinearLayoutWrapper : LinearLayoutManager {

    constructor(context: Context) : super(context)

    constructor(context: Context, orientation: Int, reverseLayout: Boolean) : super(context, orientation, reverseLayout)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    )

    override fun supportsPredictiveItemAnimations(): Boolean = false
}