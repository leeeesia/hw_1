package ru.netology.nmedia

import android.os.Bundle
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

        fun value(value: Int): String {
            if (value > 999999) {
                return value.div(1000000).toString() + "M"
            } else if (value > 999) {
                return if (value < 10000) {
                    String.format("%.f", value.toDouble().div(1000))
                } else {
                    value.div(1000).toString() + "K"
                }
            } else return value.toString()
        }

        with(binding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content

            likeValue?.text = value(post.likes)
            shareValue?.text = value(post.share)

            if (post.likedByMe) {
                likeIcon.setImageResource(R.drawable.ic_favorite_24)
            }

            likeIcon.setOnClickListener {
                post.likedByMe = !post.likedByMe
                if (post.likedByMe) post.likes++ else post.likes--


                likeIcon.setImageResource(
                    if (post.likedByMe) {
                        R.drawable.ic_favorite_24
                    } else {
                        R.drawable.ic_favorite_border_24
                    }
                )
                likeValue?.text = value(post.likes)
            }

            shareIcon.setOnClickListener {
                post.share++
                shareValue?.text = value(post.share)
            }

        }


    }
}