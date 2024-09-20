package com.dreamsoftware.melodiqtv.ui.screens.songdetail

import com.dreamsoftware.fudge.core.IFudgeTvScreenActionListener

interface SongDetailScreenActionListener: IFudgeTvScreenActionListener {

    fun onPlaySongVideoClip()
    fun onOpenMoreInfo()
    fun onSongFavoriteClicked()
}