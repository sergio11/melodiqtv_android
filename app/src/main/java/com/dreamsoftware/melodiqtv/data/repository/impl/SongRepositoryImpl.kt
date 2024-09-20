package com.dreamsoftware.melodiqtv.data.repository.impl

import com.dreamsoftware.melodiqtv.data.remote.datasource.IArtistsRemoteDataSource
import com.dreamsoftware.melodiqtv.data.remote.datasource.IFavoritesRemoteDataSource
import com.dreamsoftware.melodiqtv.data.remote.dto.request.AddFavoriteSongDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.request.SongFilterDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.response.ArtistDTO
import com.dreamsoftware.melodiqtv.data.remote.dto.response.SongDTO
import com.dreamsoftware.melodiqtv.data.remote.exception.AddToFavoritesRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.GetFavoritesByUserRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.HasTrainingInFavoritesRemoteException
import com.dreamsoftware.melodiqtv.data.remote.exception.RemoteDataSourceException
import com.dreamsoftware.melodiqtv.data.remote.exception.RemoveFromFavoritesRemoteException
import com.dreamsoftware.melodiqtv.data.repository.impl.core.SupportRepositoryImpl
import com.dreamsoftware.melodiqtv.domain.exception.AddFavoriteSongException
import com.dreamsoftware.melodiqtv.domain.exception.FetchFavoritesSongsByUserException
import com.dreamsoftware.melodiqtv.domain.exception.FetchFeaturedSongsException
import com.dreamsoftware.melodiqtv.domain.exception.FetchSongByCategoryException
import com.dreamsoftware.melodiqtv.domain.exception.FetchSongByIdException
import com.dreamsoftware.melodiqtv.domain.exception.FetchSongsException
import com.dreamsoftware.melodiqtv.domain.exception.FetchSongsRecommendedException
import com.dreamsoftware.melodiqtv.domain.exception.RemoveFavoriteSongException
import com.dreamsoftware.melodiqtv.domain.exception.VerifyFavoriteSongException
import com.dreamsoftware.melodiqtv.domain.model.AddFavoriteSongBO
import com.dreamsoftware.melodiqtv.domain.model.SongBO
import com.dreamsoftware.melodiqtv.domain.model.SongFilterDataBO
import com.dreamsoftware.melodiqtv.domain.model.TrainingTypeEnum
import com.dreamsoftware.melodiqtv.domain.repository.ISongRepository
import com.dreamsoftware.melodiqtv.utils.IOneSideMapper
import com.dreamsoftware.melodiqtv.utils.enumNameOfOrDefault
import com.dreamsoftware.melodiqtv.utils.executeAsync
import com.dreamsoftware.melodiqtv.utils.parallelMap
import kotlinx.coroutines.CoroutineDispatcher

internal class SongRepositoryImpl(
    private val favoritesRemoteDataSource: IFavoritesRemoteDataSource,
    private val instructorRemoteDataSource: IArtistsRemoteDataSource,
    private val songsMapper: IOneSideMapper<Pair<SongDTO, ArtistDTO>, SongBO>,
    private val addFavoriteMapper: IOneSideMapper<AddFavoriteSongBO, AddFavoriteSongDTO>,
    private val trainingFilterDataMapper: IOneSideMapper<SongFilterDataBO, SongFilterDTO>,
    private val dispatcher: CoroutineDispatcher
) : SupportRepositoryImpl(dispatcher), ISongRepository {

    @Throws(FetchSongsException::class)
    override suspend fun getSongs(data: SongFilterDataBO, includePremium: Boolean): Iterable<ITrainingProgramBO> = with(data) {
        safeExecute {
            val filterDTO = trainingFilterDataMapper.mapInToOut(data)
            try {
                when (type) {
                    TrainingTypeEnum.WORK_OUT -> workoutRemoteDataSource.getTrainings(filterDTO, includePremium).workoutsToTrainingPrograms()
                    TrainingTypeEnum.SERIES -> seriesRemoteDataSource.getTrainings(filterDTO, includePremium).seriesToTrainingPrograms()
                    TrainingTypeEnum.CHALLENGES -> challengeRemoteDataSource.getTrainings(filterDTO, includePremium).challengesToTrainingPrograms()
                    TrainingTypeEnum.ROUTINE -> routineRemoteDataSource.getTrainings(filterDTO, includePremium).routinesToTrainingPrograms()
                }
            } catch (ex: RemoteDataSourceException) {
                throw FetchSongsException("An error occurred when fetching trainings", ex)
            }
        }
    }

    @Throws(FetchSongByIdException::class)
    override suspend fun getTrainingById(id: String, type: TrainingTypeEnum): ITrainingProgramBO =
        safeExecute {
            try {
                when (type) {
                    TrainingTypeEnum.WORK_OUT -> workoutRemoteDataSource.getTrainingById(id).toTrainingProgram()
                    TrainingTypeEnum.SERIES -> seriesRemoteDataSource.getTrainingById(id).toTrainingProgram()
                    TrainingTypeEnum.CHALLENGES -> challengeRemoteDataSource.getTrainingById(id).toTrainingProgram()
                    TrainingTypeEnum.ROUTINE -> routineRemoteDataSource.getTrainingById(id).toTrainingProgram()
                }
            } catch (ex: RemoteDataSourceException) {
                throw FetchSongsException("An error occurred when fetching the training", ex)
            }
        }

    @Throws(FetchSongsRecommendedException::class)
    override suspend fun getSongsRecommended(includePremium: Boolean): Iterable<ITrainingProgramBO> = safeExecute {
        try {
            val workoutDeferred = executeAsync(dispatcher) { workoutRemoteDataSource.getRecommendedTrainings(includePremium).workoutsToTrainingPrograms() }
            val seriesDeferred = executeAsync(dispatcher) { seriesRemoteDataSource.getRecommendedTrainings(includePremium).seriesToTrainingPrograms() }
            val challengesDeferred = executeAsync(dispatcher) { challengeRemoteDataSource.getRecommendedTrainings(includePremium).challengesToTrainingPrograms() }
            val routinesDeferred = executeAsync(dispatcher) { routineRemoteDataSource.getRecommendedTrainings(includePremium).routinesToTrainingPrograms() }
            workoutDeferred.await() + seriesDeferred.await() + challengesDeferred.await() + routinesDeferred.await()
        } catch (ex: RemoteDataSourceException) {
            throw FetchSongsRecommendedException(
                "An error occurred when fetching the recommended trainings",
                ex
            )
        }
    }

    @Throws(FetchFeaturedSongsException::class)
    override suspend fun getFeaturedSongs(includePremium: Boolean): Iterable<ITrainingProgramBO> = safeExecute {
        try {
            val workoutDeferred = executeAsync(dispatcher) { workoutRemoteDataSource.getFeaturedTrainings(includePremium).workoutsToTrainingPrograms() }
            val seriesDeferred = executeAsync(dispatcher) { seriesRemoteDataSource.getFeaturedTrainings(includePremium).seriesToTrainingPrograms() }
            val routinesDeferred = executeAsync(dispatcher) { routineRemoteDataSource.getFeaturedTrainings(includePremium).routinesToTrainingPrograms() }
            workoutDeferred.await() + seriesDeferred.await() + routinesDeferred.await()
        } catch (ex: RemoteDataSourceException) {
            throw FetchFeaturedSongsException(
                "An error occurred when fetching the featured training",
                ex
            )
        }
    }

    @Throws(FetchSongByCategoryException::class)
    override suspend fun getSongsByCategory(id: String, includePremium: Boolean): Iterable<ITrainingProgramBO> =
        safeExecute {
            try {
                val workoutDeferred = executeAsync(dispatcher) { workoutRemoteDataSource.getTrainingByCategory(id, includePremium).workoutsToTrainingPrograms() }
                val seriesDeferred = executeAsync(dispatcher) { seriesRemoteDataSource.getTrainingByCategory(id, includePremium).seriesToTrainingPrograms() }
                val challengesDeferred = executeAsync(dispatcher) { challengeRemoteDataSource.getTrainingByCategory(id, includePremium).challengesToTrainingPrograms() }
                val routinesDeferred = executeAsync(dispatcher) { routineRemoteDataSource.getTrainingByCategory(id, includePremium).routinesToTrainingPrograms() }
                workoutDeferred.await() + seriesDeferred.await() + routinesDeferred.await() + challengesDeferred.await()
            } catch (ex: RemoteDataSourceException) {
                throw FetchSongByCategoryException(
                    "An error occurred when fetching the training",
                    ex
                )
            }
        }

    @Throws(AddFavoriteSongException::class)
    override suspend fun addFavoriteSong(data: AddFavoriteSongBO): Boolean =
        safeExecute {
            try {
                favoritesRemoteDataSource.addFavorite(addFavoriteMapper.mapInToOut(data))
            } catch (ex: AddToFavoritesRemoteException) {
                throw AddFavoriteSongException(
                    "An error occurred when adding training to favorites",
                    ex
                )
            }
        }

    @Throws(FetchFavoritesSongsByUserException::class)
    override suspend fun getFavoritesSongsByProfile(profileId: String): List<ITrainingProgramBO> = safeExecute {
        try {
            favoritesRemoteDataSource.getFavoritesByUser(profileId).parallelMap {
                getSongById(
                    id = it.songId,
                    type = enumNameOfOrDefault(it.trainingType, TrainingTypeEnum.WORK_OUT)
                )
            }
        } catch (ex: GetFavoritesByUserRemoteException) {
            throw FetchFavoritesSongsByUserException(
                "An error occurred when fetching favorites",
                ex
            )
        }
    }

    @Throws(VerifyFavoriteSongException::class)
    override suspend fun hasSongInFavorites(profileId: String, songId: String): Boolean = safeExecute {
        try {
            favoritesRemoteDataSource.hasTrainingInFavorites(
                profileId = profileId,
                trainingId = songId
            )
        } catch (ex: HasTrainingInFavoritesRemoteException) {
            throw VerifyFavoriteSongException(
                "An error occurred when checking favorites",
                ex
            )
        }
    }

    @Throws(RemoveFavoriteSongException::class)
    override suspend fun removeFavoriteSong(profileId: String, songId: String): Boolean = safeExecute {
        try {
            favoritesRemoteDataSource.removeFavorite(
                profileId = profileId,
                trainingId = songId
            )
        } catch (ex: RemoveFromFavoritesRemoteException) {
            throw RemoveFavoriteSongException(
                "An error occurred when removing training from favorites",
                ex
            )
        }
    }

    private suspend fun WorkoutDTO.toTrainingProgram(): ITrainingProgramBO =
        let { workout -> workout to instructorRemoteDataSource.getArtistById(workout.instructor) }
            .let(workoutMapper::mapInToOut)

    private suspend fun SeriesDTO.toTrainingProgram(): ITrainingProgramBO =
        let { series -> series to instructorRemoteDataSource.getArtistById(series.instructor) }
            .let(seriesMapper::mapInToOut)

    private suspend fun ChallengeDTO.toTrainingProgram(): ITrainingProgramBO =
        let { challenge ->
            val instructor = instructorRemoteDataSource.getArtistById(challenge.instructor)
            val weaklyPlans = challenge.weaklyPlans.parallelMap { weaklyPlan ->
                workoutRemoteDataSource.getTrainingByIdList(weaklyPlan.workouts)
            }.flatten()
            challengesMapper.mapInToOut(Triple(challenge, weaklyPlans, instructor))
        }

    private suspend fun RoutineDTO.toTrainingProgram(): ITrainingProgramBO =
        let { routine -> routine to instructorRemoteDataSource.getArtistById(routine.instructor) }
            .let(routineMapper::mapInToOut)

    private suspend fun List<WorkoutDTO>.workoutsToTrainingPrograms(): Iterable<ITrainingProgramBO> =
        parallelMap { workout -> workout to instructorRemoteDataSource.getArtistById(workout.instructor) }
            .let(workoutMapper::mapInListToOutList)

    private suspend fun List<SeriesDTO>.seriesToTrainingPrograms(): Iterable<ITrainingProgramBO> =
        parallelMap { series -> series to instructorRemoteDataSource.getArtistById(series.instructor) }
            .let(seriesMapper::mapInListToOutList)

    private suspend fun List<ChallengeDTO>.challengesToTrainingPrograms(): Iterable<ITrainingProgramBO> =
        parallelMap { challenge ->
            val instructor = instructorRemoteDataSource.getArtistById(challenge.instructor)
            val weaklyPlans = challenge.weaklyPlans.parallelMap { weaklyPlan ->
                workoutRemoteDataSource.getTrainingByIdList(weaklyPlan.workouts)
            }.flatten()
            challengesMapper.mapInToOut(Triple(challenge, weaklyPlans, instructor))
        }

    private suspend fun List<RoutineDTO>.routinesToTrainingPrograms(): Iterable<ITrainingProgramBO> =
        parallelMap { series -> series to instructorRemoteDataSource.getArtistById(series.instructor) }
            .let(routineMapper::mapInListToOutList)
}