package data.mapper.animeServerMapper

import data.data.server.jikan.DTO.StreamingLinkDto
import data.data.server.jikan.DTO.StreamingResponse
import data.domain.model.server.StreamingLinkModel
import data.domain.model.server.StreamingResponseModel

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