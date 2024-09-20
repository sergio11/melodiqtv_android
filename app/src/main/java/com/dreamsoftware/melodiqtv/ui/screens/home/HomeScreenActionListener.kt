package com.dreamsoftware.melodiqtv.ui.screens.home

import com.dreamsoftware.fudge.core.IFudgeTvScreenActionListener
import com.dreamsoftware.melodiqtv.domain.model.SongBO

interface HomeScreenActionListener: IFudgeTvScreenActionListener {

    fun onOpenSongDetail(song: SongBO)
    fun onCategorySelected(categoryId: String)
}