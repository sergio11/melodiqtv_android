package sanchez.sanchez.sergio.newsapp.domain.model

data class AuthCredentials(
     var idToken: String,
     var accessToken: String? = null
)