package com.dreamsoftware.melodiqtv.ui.screens.trainingdetail

import com.dreamsoftware.fudge.core.IFudgeTvScreenActionListener

interface TrainingDetailScreenActionListener: IFudgeTvScreenActionListener {

    fun onTrainingProgramStarted()
    fun onTrainingProgramMoreInfoRequested()
    fun onTrainingFavoriteClicked()
}