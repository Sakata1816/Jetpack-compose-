package data.data.server.DTO

data class StreamingResponse(
    val data: List<StreamingLinkDto>
)

data class StreamingLinkDto(
    val name: String?,
    val url: String?
)

