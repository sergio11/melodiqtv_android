package com.dreamsoftware.melodiqtv.ui.screens.songhub

import com.dreamsoftware.fudge.component.menu.FudgeTvFilterVO
import com.dreamsoftware.fudge.core.IFudgeTvScreenActionListener

interface SongHubScreenActionListener: IFudgeTvScreenActionListener {

    fun onFilterClicked()
    fun onSortedClicked()
    fun onSortCleared()
    fun onDismissSortSideMenu()
    fun onDismissFilterSideMenu()
    fun onFilterCleared()
    fun onDismissFieldFilterSideMenu()
    fun onFilterFieldSelected(filter: FudgeTvFilterVO)
    fun onSelectedSortedItem(currentIndex: Int)
    fun onSelectedFilterOption(currentIndex: Int)
    fun onChangeSelectedTab(index: Int)
    fun onChangeFocusTab(index: Int)
    fun onItemClicked(id: String)
}