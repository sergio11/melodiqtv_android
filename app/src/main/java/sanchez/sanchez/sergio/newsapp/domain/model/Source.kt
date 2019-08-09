package sanchez.sanchez.sergio.newsapp.domain.model

/**
 * A single news source
 */
data class Source(
    val id: String,
    val name: String,
    val description: String,
    val url: String,
    val category: CategoryEnum,
    val language: String,
    val country: String
)