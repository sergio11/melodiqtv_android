package com.dreamsoftware.melodiqtv.ui.screens.moreoptions

import com.dreamsoftware.fudge.core.IFudgeTvScreenActionListener

interface MoreOptionsScreenActionListener: IFudgeTvScreenActionListener {

    fun onBackPressed()
    fun onTrainingProgramOpened()
    fun onFavouriteClicked()
    fun onOpenInstructorDetail()
    fun onPlayTrainingSong()
}