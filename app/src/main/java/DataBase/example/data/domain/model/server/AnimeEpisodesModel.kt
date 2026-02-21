package DataBase.example.data.domain.model.server

data class AnimeEpisodesModel(
    val episodes: List<EpisodeModel>,
    val pagination: PaginationModel
)

data class EpisodeModel(
    val id: Int,
    val title: String?,
    val titleJapanese: String?,
    val aired: String?,
    val score: Double?,
    val isFiller: Boolean,
    val isRecap: Boolean
)

data class PaginationModel(
    val lastVisiblePage: Int,
    val hasNextPage: Boolean
)


