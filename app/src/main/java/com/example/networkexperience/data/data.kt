package com.example.networkexperience.data

import okhttp3.Response

//создаются классы для данных которыебудут парсить json(получать или отправлять данные)
data class Post(val userId:Int?=null,
                val id:Int?=null,
                val title:String?=null,
                val body:String?=null)

data class Comment(val postId:Int?,
                   val id:Int?,
                   val name:String?,
                   val email:String?,
                   val body:String?)

data class UserUIState(val isLoading:Boolean=false,
                       val posts:List<Post> = emptyList(),
                       val post: Post? = null,
                       val comments:List<Comment> = emptyList(),
                       val error:String?=null )

