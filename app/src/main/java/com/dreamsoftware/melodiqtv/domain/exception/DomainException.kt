package com.dreamsoftware.melodiqtv.domain.exception

open class DomainRepositoryException(message: String? = null, cause: Throwable? = null): Exception(message, cause)
class RepositoryOperationException(message: String? = null, cause: Throwable? = null) : DomainRepositoryException(message, cause)

class InvalidDataException(errors: Map<String, String>, message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)

// Songs
class FetchSongsException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class FetchSongByIdException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class FetchSongByCategoryException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class FetchSongsRecommendedException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class FetchFeaturedSongsException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class AddFavoriteSongException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class FetchFavoritesSongsByUserException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class RemoveFavoriteSongException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class VerifyFavoriteSongException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)

// Subscriptions
class FetchSubscriptionsException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class FetchUserSubscriptionException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class VerifyHasActiveSubscriptionException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class AddUserSubscriptionException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class RemoveUserSubscriptionException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)

// Categories
class FetchCategoriesException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class FetchCategoryByIdException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)

// Artists
class FetchArtistsException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class FetchArtistByIdException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)

//Profiles
class FetchProfilesByUserException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class UpdateProfileException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class DeleteProfileException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class CreateProfileException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class VerifyPinException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class SelectProfileException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class GetProfileByIdException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class GetProfileSelectedException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class UserProfilesLimitReachedException(val maxProfilesLimit: Int, message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
// Users
class SignInException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class SignUpException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class SignOffException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class VerifySessionException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class GetUserDetailException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class GetUserAuthenticatedUidException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class UpdateUserDetailException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class GetUserPreferencesException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class SaveUserPreferencesException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)