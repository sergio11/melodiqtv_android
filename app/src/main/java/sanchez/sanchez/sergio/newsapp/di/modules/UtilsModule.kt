package sanchez.sanchez.sergio.healthycitizen.di.modules

import android.content.Context
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import sanchez.sanchez.sergio.brownie.di.scopes.PerApplication
import timber.log.Timber

@Module
class UtilsModule {

    @Provides
    @PerApplication
    fun providePicasso(context: Context): Picasso =
        Picasso.Builder(context).apply {
            listener { picasso, uri, exception ->
                Timber.d(exception)
            }
        }.build()

}