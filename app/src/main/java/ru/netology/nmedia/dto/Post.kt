package ru.netology.nmedia.dto

data class Post (
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val views: Int = 1500,
    val likes: Int = 129,
    val likedByMe: Boolean = false,
    val share: Int  = 0
)