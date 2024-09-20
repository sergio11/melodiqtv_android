package com.dreamsoftware.melodiqtv.ui.screens.favorites

import com.dreamsoftware.fudge.core.IFudgeTvScreenActionListener
import com.dreamsoftware.melodiqtv.domain.model.SongBO

interface FavoritesScreenActionListener: IFudgeTvScreenActionListener {

    fun onSongSelected(song: SongBO)
    fun onOpenSongDetail(id: String)
    fun onSongRemovedFromFavorites(id: String)
    fun onDismissRequest()
}