package com.dreamsoftware.melodiqtv.data.remote.exception

open class RemoteDataSourceException(message: String? = null, cause: Throwable? = null): Exception(message, cause)

// Auth Data Source
class AuthRemoteException(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class SignInRemoteException(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class SignUpRemoteException(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)

// Categories
class FetchCategoriesRemoteException(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class FetchCategoryByIdRemoteException(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)

// Artists
class FetchArtistsRemoteException(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class FetchArtistByIdRemoteException(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)

// Subscriptions
class FetchSubscriptionsRemoteException(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)

// User Subscriptions
class FetchUserSubscriptionRemoteException(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class AddUserSubscriptionRemoteException(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class RemoveUserSubscriptionRemoteException(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class VerifyHasActiveSubscriptionRemoteException(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)

// Songs
class FetchSongsRemoteException(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class FetchSongByIdRemoteException(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class FetchSongByCategoryRemoteException(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class FetchFeaturedSongsRemoteException(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class FetchRecommendedSongsRemoteException(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)

// Profiles
class FetchProfilesRemoteException(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class UpdateProfileRemoteException(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class DeleteProfileRemoteException(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class CreateProfileRemoteException(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class VerifyProfileRemoteException(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class FetchProfileByIdRemoteException(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)

// Users
class CreateUserDetailRemoteException(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class UpdateUserDetailRemoteException(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class FetchUserDetailRemoteException(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class UpdateProfilesCountRemoteException(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)

// Favorites
class AddToFavoritesRemoteException(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class RemoveFromFavoritesRemoteException(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class RemoveAllFavoritesRemoteException(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class GetFavoritesByUserRemoteException(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)
class HasSongInFavoritesRemoteException(message: String? = null, cause: Throwable? = null): RemoteDataSourceException(message, cause)