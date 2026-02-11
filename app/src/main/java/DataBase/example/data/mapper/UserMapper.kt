package DataBase.example.data.mapper

import DataBase.example.data.local.domainModel.User
import DataBase.example.data.local.entity.UserEntity

fun UserEntity.toUser(): User {
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
