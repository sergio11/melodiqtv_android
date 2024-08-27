package com.dreamsoftware.melodiqtv.domain.usecase

import com.dreamsoftware.melodiqtv.domain.exception.InvalidDataException
import com.dreamsoftware.melodiqtv.domain.model.AvatarTypeEnum
import com.dreamsoftware.melodiqtv.domain.model.CreateProfileRequestBO
import com.dreamsoftware.melodiqtv.domain.model.SignUpBO
import com.dreamsoftware.melodiqtv.domain.model.UserDetailBO
import com.dreamsoftware.melodiqtv.domain.repository.IProfilesRepository
import com.dreamsoftware.melodiqtv.domain.repository.IUserRepository
import com.dreamsoftware.melodiqtv.domain.validation.IBusinessEntityValidator
import com.dreamsoftware.fudge.core.FudgeTvUseCaseWithParams
import java.util.UUID

class SignUpUseCase(
    private val userRepository: IUserRepository,
    private val profilesRepository: IProfilesRepository,
    private val validator: IBusinessEntityValidator<SignUpBO>
) : FudgeTvUseCaseWithParams<SignUpUseCase.Params, UserDetailBO>() {

    private companion object {
        const val DEFAULT_PIN = 123456
    }

    override suspend fun onExecuted(params: Params): UserDetailBO = with(params) {
        params.toSignUpBO().let { signUpBO ->
            validator.validate(signUpBO).takeIf { it.isNotEmpty() }?.let { errors ->
                throw InvalidDataException(errors, "Invalid data provided")
            } ?: run {
                userRepository.signUp(signUpBO).also {
                    profilesRepository.createProfile(toCreateProfileBO(it.uuid))
                }
            }
        }
    }

    private fun Params.toSignUpBO() = SignUpBO(
        username = username,
        password = password,
        confirmPassword = repeatPassword,
        email = email,
        firstName = firstName,
        lastName = lastName
    )


    private fun Params.toCreateProfileBO(userId: String) = CreateProfileRequestBO(
        uid = UUID.randomUUID().toString(),
        alias = username,
        pin = DEFAULT_PIN,
        avatarType = AvatarTypeEnum.BOY,
        userId = userId
    )

    data class Params(
        val username: String,
        val repeatPassword: String,
        val password: String,
        val email: String,
        val firstName: String,
        val lastName: String
    )
}