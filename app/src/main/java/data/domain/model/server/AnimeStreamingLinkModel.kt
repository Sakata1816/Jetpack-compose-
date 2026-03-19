package data.domain.model.server

data class StreamingResponseModel(
    val data: List<StreamingLinkModel>
)

data class StreamingLinkModel(
    val name: String?,
    val url: String?
)

