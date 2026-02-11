package DataBase.example.data.mapper

import DataBase.example.data.domain.domainModel.User
import DataBase.example.data.data.local.entity.UserEntity

fun UserEntity.toDomain(): User {
    return User(
        id = id,
        name = name,
        email = email
    )
}

fun User.toEntity(): UserEntity {
    return UserEntity(
        id = id,
        name = name,
        email = email
    )
}
