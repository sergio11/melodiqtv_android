package com.dreamsoftware.melodiqtv.ui.screens.favorites

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.tv.material3.MaterialTheme
import coil.compose.AsyncImage
import com.dreamsoftware.fudge.component.FudgeTvButton
import com.dreamsoftware.fudge.component.FudgeTvButtonStyleTypeEnum
import com.dreamsoftware.fudge.component.FudgeTvButtonTypeEnum
import com.dreamsoftware.fudge.component.FudgeTvCard
import com.dreamsoftware.fudge.component.FudgeTvFocusRequester
import com.dreamsoftware.fudge.component.FudgeTvLoadingState
import com.dreamsoftware.fudge.component.FudgeTvNoContentState
import com.dreamsoftware.fudge.component.FudgeTvScreenContent
import com.dreamsoftware.fudge.component.FudgeTvText
import com.dreamsoftware.fudge.component.FudgeTvTextTypeEnum
import com.dreamsoftware.fudge.utils.conditional
import com.dreamsoftware.fudge.utils.shadowBox
import com.dreamsoftware.melodiqtv.R
import com.dreamsoftware.melodiqtv.domain.model.SongBO
import com.dreamsoftware.melodiqtv.ui.theme.popupShadow
import com.dreamsoftware.melodiqtv.ui.theme.surfaceContainerHigh
import com.dreamsoftware.melodiqtv.ui.utils.formatSubtitle

@Composable
internal fun FavoritesScreenContent(
    uiState: FavoritesUiState,
    actionListener: FavoritesScreenActionListener
) {
    with(uiState) {
        FudgeTvScreenContent(onErrorAccepted = actionListener::onErrorMessageCleared) {
            if (isLoading) {
                FudgeTvLoadingState(modifier = Modifier.fillMaxSize())
            } else if(favoriteSongs.isEmpty()) {
                FudgeTvNoContentState(
                    modifier = Modifier.fillMaxSize(),
                    messageRes = R.string.favorites_not_workout_available
                )
            }else {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    FudgeTvText(
                        modifier = Modifier.padding(bottom = 8.dp, top = 56.dp, start = 32.dp, end = 32.dp),
                        type = FudgeTvTextTypeEnum.HEADLINE_MEDIUM,
                        titleRes = R.string.favorites_screen_title,
                        textBold = true
                    )
                    FudgeTvFocusRequester { focusRequester ->
                        LazyHorizontalGrid(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 24.dp),
                            contentPadding = PaddingValues(horizontal = 46.dp),
                            rows = GridCells.Fixed(2),
                            horizontalArrangement = Arrangement.spacedBy(24.dp),
                            verticalArrangement = Arrangement.spacedBy(24.dp)
                        ) {
                            itemsIndexed(items = favoriteSongs, key = { _, item -> item.id }) { idx, item ->
                                FudgeTvCard(modifier = Modifier
                                    .conditional(condition = idx == 0, ifTrue = {
                                        focusRequester(focusRequester)
                                    }),
                                    imageUrl = item.imageUrl,
                                    title = item.title,
                                    subtitle = item.formatSubtitle(),
                                    onClick = {
                                        actionListener.onSongSelected(item)
                                    })
                            }
                        }
                    }
                    AnimatedVisibility(
                        visible = songSelected != null,
                        enter = fadeIn(
                            animationSpec = tween(300)
                        ),
                        exit = fadeOut(
                            animationSpec = tween(300)
                        ),
                    ) {
                        songSelected?.let {
                            SongDetailsPopup(
                                song = it,
                                onOpenSongDetail = actionListener::onOpenSongDetail,
                                onRemoveFromFavorites = actionListener::onSongRemovedFromFavorites,
                                onBackPressed = actionListener::onDismissRequest
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SongDetailsPopup(
    song: SongBO,
    onOpenSongDetail: (id: String) -> Unit,
    onRemoveFromFavorites: (id: String) -> Unit,
    onBackPressed: () -> Unit
) {
    with(MaterialTheme.colorScheme) {
        with(song) {
            Dialog(onDismissRequest = onBackPressed) {
                FudgeTvFocusRequester { focusRequester ->
                    Box(
                        modifier = Modifier
                            .background(surfaceContainerHigh, RoundedCornerShape(16.dp))
                            .fillMaxWidth(0.75f)
                            .fillMaxHeight(0.8f)
                            .shadowBox(
                                color = popupShadow,
                                blurRadius = 40.dp,
                                offset = DpOffset(0.dp, 8.dp)
                            )
                            .clip(RoundedCornerShape(16.dp))
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.5f)
                                .alpha(0.88f),
                            model = imageUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(surfaceVariant.copy(alpha = 0.6f))
                                .padding(horizontal = 24.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Spacer(modifier = Modifier.fillMaxHeight(0.42f))
                            FudgeTvText(
                                modifier = Modifier.padding(bottom = 8.dp),
                                type = FudgeTvTextTypeEnum.HEADLINE_SMALL,
                                textColor = onSurface,
                                titleText = title,
                                singleLine = true,
                                overflow = TextOverflow.Ellipsis,
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                FudgeTvText(
                                    modifier = Modifier,
                                    type = FudgeTvTextTypeEnum.LABEL_MEDIUM,
                                    titleText = formatSubtitle(),
                                    textColor = onSurface,
                                    overflow = TextOverflow.Ellipsis,
                                    softWrap = true,
                                    maxLines = 4
                                )
                            }
                            FudgeTvText(
                                titleText = description,
                                modifier = Modifier.padding(bottom = 28.dp),
                                type = FudgeTvTextTypeEnum.BODY_SMALL,
                                textColor = onSurface,
                                overflow = TextOverflow.Ellipsis,
                                softWrap = true,
                                maxLines = 3
                            )
                            FudgeTvButton(
                                modifier = Modifier
                                    .focusRequester(focusRequester)
                                    .fillMaxWidth()
                                    .padding(bottom = 12.dp),
                                type = FudgeTvButtonTypeEnum.MEDIUM,
                                style = FudgeTvButtonStyleTypeEnum.NORMAL,
                                textRes = R.string.favorites_open_song_detail
                            ) {
                                onOpenSongDetail(id)
                            }
                            FudgeTvButton(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 12.dp),
                                type = FudgeTvButtonTypeEnum.MEDIUM,
                                style = FudgeTvButtonStyleTypeEnum.TRANSPARENT,
                                textRes = R.string.more_options_remove_from_favorites_button_text
                            ) {
                                onRemoveFromFavorites(id)
                            }
                        }
                    }
                }
            }
        }
    }
}