package DataBase.example.data.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
 data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int=0,
    var name: String?=null,
    var email: String?=null
){
     constructor(name: String, email: String):this(0,name,email)

 }
