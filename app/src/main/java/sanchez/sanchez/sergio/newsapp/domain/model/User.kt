package sanchez.sanchez.sergio.newsapp.domain.model

import android.net.Uri

data class User (
    var displayName: String,
    var email: String,
    var photoUrl: Uri?
)