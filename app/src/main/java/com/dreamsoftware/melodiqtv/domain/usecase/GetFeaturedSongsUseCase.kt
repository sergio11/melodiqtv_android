package com.dreamsoftware.melodiqtv.domain.usecase

import com.dreamsoftware.melodiqtv.domain.repository.ISubscriptionsRepository
import com.dreamsoftware.melodiqtv.domain.repository.ISongRepository
import com.dreamsoftware.melodiqtv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCase
import com.dreamsoftware.melodiqtv.domain.model.SongBO

class GetFeaturedSongsUseCase(
    private val userRepository: IUserRepository,
    private val subscriptionsRepository: ISubscriptionsRepository,
    private val songRepository: ISongRepository
) : FudgeTvUseCase<List<SongBO>>() {
    override suspend fun onExecuted(): List<SongBO> {
        val userUid = userRepository.getAuthenticatedUid()
        val hasActiveSubscription = subscriptionsRepository.hasActiveSubscription(userUid)
        return songRepository.getFeaturedSongs(includePremium = hasActiveSubscription).toList()
    }
}