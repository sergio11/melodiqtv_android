package com.dreamsoftware.melodiqtv.ui.screens.subscription

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.melodiqtv.R
import com.dreamsoftware.melodiqtv.ui.screens.subscription.components.SubscriptionHeadline
import com.dreamsoftware.melodiqtv.ui.screens.subscription.components.SubscriptionOptions
import com.dreamsoftware.fudge.component.FudgeTvButton
import com.dreamsoftware.fudge.component.FudgeTvButtonStyleTypeEnum
import com.dreamsoftware.fudge.component.FudgeTvButtonTypeEnum
import com.dreamsoftware.fudge.component.FudgeTvDialog
import com.dreamsoftware.fudge.component.FudgeTvScreenContent

@Composable
fun SubscriptionScreenContent(
    uiState: SubscriptionUiState,
    actionListener: ISubscriptionScreenActionListener
) {
    with(uiState) {
        FudgeTvScreenContent(onErrorAccepted = actionListener::onErrorMessageCleared) {
            FudgeTvDialog(
                isVisible = showSubscriptionAddedDialog,
                mainLogoRes = R.drawable.main_logo,
                titleRes = R.string.add_subscription_completed_dialog_title,
                descriptionRes = R.string.add_subscription_completed_dialog_description,
                onAcceptClicked = actionListener::onCompleted
            )
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .semantics { contentDescription = "Subscription Screen" },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Image(
                    modifier = Modifier
                        .size(width = 324.dp, height = 452.dp)
                        .border(
                            border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .clip(RoundedCornerShape(16.dp)),
                    painter = painterResource(id = R.drawable.signup_background),
                    contentDescription = stringResource(id = R.string.image_cannot_be_loaded),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(28.dp))
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(32.dp),
                ) {
                    SubscriptionHeadline()
                    SubscriptionOptions(
                        subscriptionOptions = subscriptionList,
                        selectedSubscription = selectedSubscription,
                        updateSelectedSubscriptionOption = actionListener::onSubscriptionOptionUpdated
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        FudgeTvButton(
                            type = FudgeTvButtonTypeEnum.LARGE,
                            style = FudgeTvButtonStyleTypeEnum.NORMAL,
                            textRes = R.string.subscribe_now,
                            onClick = actionListener::onSubscribe
                        )
                        if(hasActiveSubscription) {
                            FudgeTvButton(
                                type = FudgeTvButtonTypeEnum.LARGE,
                                style = FudgeTvButtonStyleTypeEnum.INVERSE,
                                textRes = R.string.restore_purchases,
                                onClick = actionListener::onRestorePurchases
                            )
                        } else {
                            FudgeTvButton(
                                type = FudgeTvButtonTypeEnum.LARGE,
                                style = FudgeTvButtonStyleTypeEnum.INVERSE,
                                textRes = R.string.subscription_not_interested_button_text,
                                onClick = actionListener::onNotInterested
                            )
                        }
                    }
                }
            }
        }
    }
}