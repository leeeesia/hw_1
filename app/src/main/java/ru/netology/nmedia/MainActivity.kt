package ru.netology.nmedia

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likedByMe = false
        )


        with(binding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            viewValue.text = Format.value(post.views)

            likeValue?.text = Format.value(post.likes)
            shareValue?.text = Format.value(post.share)

            if (post.likedByMe) {
                likeIcon.setImageResource(R.drawable.ic_favorite_24)
            }

            likeIcon.setOnClickListener {
                post.likedByMe = !post.likedByMe
                if (post.likedByMe) post.likes++ else post.likes--
                Log.d("stuff", "like")

                likeIcon.setImageResource(
                    if (post.likedByMe) {
                        R.drawable.ic_favorite_24
                    } else {
                        R.drawable.ic_favorite_border_24
                    }
                )
                likeValue?.text = Format.value(post.likes)
            }

            shareIcon.setOnClickListener {
                post.share++
                shareValue?.text = Format.value(post.share)
            }
            root.setOnClickListener {
                Log.d("stuff", "stuff")
            }

            avatar.setOnClickListener {
                Log.d("stuff", "avatar")
            }
        }


    }
}