package sanchez.sanchez.sergio.newsapp.di.modules

import android.app.Activity
import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.Module
import dagger.Provides
import sanchez.sanchez.sergio.brownie.di.scopes.PerFragment
import sanchez.sanchez.sergio.healthycitizen.R

@Module
class GoogleApiClientSignInModule{

    @PerFragment
    @Provides
    fun provideGoogleSignInOptions(appContext: Context): GoogleSignInOptions =
        GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(appContext.getString(R.string.default_web_client_id))
            .requestEmail()
            .requestId()
            .requestProfile()
            .build()

    @PerFragment
    @Provides
    fun providesGoogleApiClientSignIn(
        activity: Activity,
        googleSignInOptions: GoogleSignInOptions): GoogleSignInClient =
        GoogleSignIn.getClient(activity, googleSignInOptions)

}