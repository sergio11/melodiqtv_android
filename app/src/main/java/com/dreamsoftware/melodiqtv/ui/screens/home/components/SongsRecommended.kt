package com.dreamsoftware.melodiqtv.ui.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.melodiqtv.R
import com.dreamsoftware.fudge.component.FudgeTvCard
import com.dreamsoftware.fudge.component.FudgeTvText
import com.dreamsoftware.fudge.component.FudgeTvTextTypeEnum
import com.dreamsoftware.melodiqtv.domain.model.SongBO

@Composable
internal fun SongsRecommended(
    modifier: Modifier = Modifier,
    state: List<SongBO>,
    onClick: (SongBO) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
        FudgeTvText(
            modifier = Modifier.padding(start = 32.dp),
            type = FudgeTvTextTypeEnum.HEADLINE_MEDIUM,
            titleRes = R.string.home_recommended_for_you_row_title,
            textColor = MaterialTheme.colorScheme.onSurface,
            textBold = true
        )
        LazyHorizontalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .height(370.dp),
            rows = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(horizontal = 32.dp)
        ) {
            items(state) { song ->
                FudgeTvCard(
                    modifier = modifier.width(196.dp),
                    imageUrl = song.imageUrl,
                    title = song.title,
                    subtitle = "${song.duration} - ${song.artistName}",
                    onClick = { onClick(song) }
                )
            }
        }
    }
}