package com.dreamsoftware.melodiqtv.ui.screens.app

import com.dreamsoftware.fudge.core.IFudgeTvScreenActionListener

interface IAppScreenActionListener: IFudgeTvScreenActionListener {
    fun onOpenSettingsPressed()
    fun onRestartAppPressed()
}