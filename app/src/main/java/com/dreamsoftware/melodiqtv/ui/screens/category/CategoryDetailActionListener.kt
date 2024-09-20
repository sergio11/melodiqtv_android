package com.dreamsoftware.melodiqtv.ui.screens.category

import com.dreamsoftware.fudge.core.IFudgeTvScreenActionListener
import com.dreamsoftware.melodiqtv.domain.model.SongBO

interface CategoryDetailActionListener: IFudgeTvScreenActionListener {

    fun onSongOpened(song: SongBO)
}