package ru.netology.nmedia.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post
import kotlin.math.log

class PostRepositoryInMemory : PostRepository {
    private var nextId = 0L
    private var posts = listOf(
        Post(
            id = ++nextId,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likes = 159,
            likedByMe = false,
            videoLink = "https://www.youtube.com/watch?v=WhWc3b3KhnY"
        ), Post(
            id = ++nextId,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это Третий пост! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likes = 1399,
            likedByMe = false,
            videoLink = "https://www.youtube.com/watch?v=WhWc3b3KhnY"
        ), Post(
            id = ++nextId,
            author = "Университет интернет-профессий будущего",
            content = "Привет, это мы! Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likes = 15999,
            likedByMe = false
        ), Post(
            id = ++nextId,
            author = "Нетология.",
            content = "Привет, это Первый пост! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likes = 152529,
            likedByMe = false,
            videoLink = "https://www.youtube.com/watch?v=WhWc3b3KhnY"
        )


    ).reversed()

    private val data = MutableLiveData(posts)

    override fun getData(): LiveData<List<Post>> = data

    override fun likeById(id: Long) {
        posts = posts.map { post ->
            if (post.id == id) {
                post.copy(
                    likedByMe = !post.likedByMe,
                    likes = if (!post.likedByMe) post.likes + 1 else post.likes - 1
                )
            } else {
                post
            }
        }
        data.value = posts
    }

    override fun shareById(id: Long) {
        posts = posts.map { post ->
            if (post.id == id) {
                post.copy(
                    share = post.share + 1
                )
            } else {
                post
            }
        }
        data.value = posts
    }

    override fun removeById(id: Long) {
        posts = posts.filter {
            it.id != id
        }
        data.value = posts
    }

    override fun save(post: Post) {
        if (post.id == 0L) {
            posts = listOf(
                post.copy(
                    id = ++nextId,
                    published = "Now",
                    author = "Author"
                )
            ) + posts
            data.value = posts
            return
        }
        posts = posts.map {
            if (post.id != it.id) it else it.copy(content = post.content)
        }
        data.value = posts
    }



}