package com.dreamsoftware.melodiqtv.ui.screens.moreoptions

import com.dreamsoftware.fudge.core.IFudgeTvScreenActionListener

interface MoreOptionsScreenActionListener: IFudgeTvScreenActionListener {

    fun onBackPressed()
    fun onPlaySongVideoClip()
    fun onFavouriteClicked()
    fun onOpenArtistDetail()
    fun onPlaySong()
    fun onOpenSongLyricsDetail()
}