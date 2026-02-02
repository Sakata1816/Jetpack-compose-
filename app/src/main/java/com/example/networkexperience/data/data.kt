package com.example.networkexperience.data

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
                       val comments:List<Comment> = emptyList(),
                       val error:String?=null )

