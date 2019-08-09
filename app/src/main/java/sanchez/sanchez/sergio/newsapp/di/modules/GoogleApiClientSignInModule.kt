package sanchez.sanchez.sergio.healthycitizen.di.modules

import android.app.Activity
import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.Scope
import com.google.android.gms.fitness.FitnessOptions
import dagger.Module
import dagger.Provides
import sanchez.sanchez.sergio.brownie.di.scopes.PerFragment
import sanchez.sanchez.sergio.healthycitizen.R

@Module
class GoogleApiClientSignInModule{

    @PerFragment
    @Provides
    fun provideGoogleSignInOptions(appContext: Context, fitnessOptions: FitnessOptions): GoogleSignInOptions =
        GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(appContext.getString(R.string.default_web_client_id))
            .requestEmail()
            .requestId()
            .addExtension(fitnessOptions)
            .requestScopes(Scope(Scopes.FITNESS_ACTIVITY_READ_WRITE))
            .requestScopes(Scope(Scopes.FITNESS_LOCATION_READ_WRITE))
            .requestScopes(Scope(Scopes.FITNESS_BODY_READ_WRITE))
            .requestScopes(Scope(Scopes.FITNESS_NUTRITION_READ_WRITE))
            .requestScopes(Scope(Scopes.FITNESS_BLOOD_GLUCOSE_READ_WRITE))
            .requestScopes(Scope(Scopes.FITNESS_BODY_TEMPERATURE_READ_WRITE))
            .requestScopes(Scope(Scopes.FITNESS_OXYGEN_SATURATION_READ_WRITE))
            .requestProfile()
            .build()

    @PerFragment
    @Provides
    fun providesGoogleApiClientSignIn(
        activity: Activity,
        googleSignInOptions: GoogleSignInOptions): GoogleSignInClient =
        GoogleSignIn.getClient(activity, googleSignInOptions)

}