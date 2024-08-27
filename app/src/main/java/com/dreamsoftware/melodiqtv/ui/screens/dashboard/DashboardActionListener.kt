package com.dreamsoftware.melodiqtv.ui.screens.dashboard

import com.dreamsoftware.fudge.component.FudgeTvNavigationDrawerItemModel
import com.dreamsoftware.fudge.core.IFudgeTvScreenActionListener

interface DashboardActionListener: IFudgeTvScreenActionListener {
    fun onMenuItemSelected(menuItem: FudgeTvNavigationDrawerItemModel)
}