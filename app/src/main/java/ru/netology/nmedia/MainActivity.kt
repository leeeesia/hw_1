package ru.netology.nmedia

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { post ->
            with(binding) {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                viewValue.text = Format.value(post.views)
                likeValue.text = Format.value(post.likes)
                shareValue.text = Format.value(post.share)

                if (post.likedByMe) {
                    likeIcon.setImageResource(R.drawable.ic_favorite_24)
                } else {
                    likeIcon.setImageResource(R.drawable.ic_favorite_border_24)
                }
            }
        }
        binding.likeIcon.setOnClickListener {
            viewModel.like()
        }

        binding.shareIcon.setOnClickListener {
            viewModel.share()
        }

    }
}