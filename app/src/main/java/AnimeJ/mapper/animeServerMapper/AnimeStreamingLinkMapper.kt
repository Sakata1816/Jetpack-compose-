package AnimeJ.mapper.animeServerMapper

import AnimeJ.data.remote.jikan.DTO.StreamingLinkDto
import AnimeJ.data.remote.jikan.DTO.StreamingResponse
import AnimeJ.domain.model.server.StreamingLinkModel
import AnimeJ.domain.model.server.StreamingResponseModel

fun StreamingLinkDto.toModel(): StreamingLinkModel{
    return StreamingLinkModel(
        name=name,
        url=url
    )
}

fun StreamingResponse.toModel(): StreamingResponseModel{
    return StreamingResponseModel(
        data=data.map { it.toModel() }
    )

}