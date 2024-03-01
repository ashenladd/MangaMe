package com.example.mangame

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.mangame.adapter.MangaModel
import com.example.mangame.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MANGA = "extra_manga"
    }

    private lateinit var binding: ActivityDetailBinding
    private var data: MangaModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        data = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(
                EXTRA_MANGA,
                MangaModel::class.java
            )
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_MANGA)
        }

        setupData()
        setupToolbar()
    }

    private fun setupToolbar() {
        binding.appbarDetail.apply {
            toolbar.title = getString(
                R.string.format_detail,
                data?.title.orEmpty()
            )
            toolbar.inflateMenu(R.menu.detail_menu)

            toolbar.setNavigationOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
            toolbar.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.action_share -> {
                        val shareText =
                            data?.title.orEmpty() + "\n\n" +
                                    data?.desc.orEmpty()

                        val sendIntent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, shareText)
                            type = "text/plain"
                        }

                        val shareIntent = Intent.createChooser(sendIntent, null)
                        startActivity(shareIntent)
                        true
                    }

                    else -> false
                }
            }
        }
    }

    private fun setupData() {
        binding.apply {
            tvMangaName.text = data?.title.orEmpty()
            tvMangaSynopsis.text = data?.desc.orEmpty()
            tvMangaViews.text = data?.views.orEmpty()
            tvMangaRating.text = data?.rating.orEmpty()
            tvMangaAuthor.text = getString(
                R.string.format_author,
                data?.author.orEmpty()
            )
            tvMangaGenre.text = data?.genre.orEmpty()
            Glide.with(this@DetailActivity)
                .load(data?.thumbnail)
                .into(ivManga)
        }
    }
}