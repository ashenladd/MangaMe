package com.example.mangame

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mangame.adapter.HomeAdapter
import com.example.mangame.adapter.MangaModel
import com.example.mangame.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val list = ArrayList<MangaModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupData()
        setupRecyclerView()
        setupToolbar()
    }

    private fun setupToolbar() {
        binding.apply {
            appbarHome.toolbarHome.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.about_page ->{
                        navigateToAboutActivity()
                        true
                    }

                    else -> false
                }
            }
        }
    }

    private fun navigateToAboutActivity() {
        val intent = Intent(this@HomeActivity, AboutActivity::class.java)
        startActivity(intent)
    }

    private fun setupRecyclerView() {
        val adapter = HomeAdapter(list)

        binding.apply {
            rvManga.setHasFixedSize(true)
            rvManga.layoutManager = LinearLayoutManager(this@HomeActivity)
            rvManga.adapter = adapter
        }

        adapter.setOnItemClickCallback(object : HomeAdapter.OnItemClickCallback {
            override fun onItemClicked(data: MangaModel) {
                navigateToDetailActivity(data)
            }
        })
    }

    private fun navigateToDetailActivity(data: MangaModel) {
        val intent = Intent(this@HomeActivity, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_MANGA, data)
        startActivity(intent)
    }

    private fun setupData() {
        val dataName = resources.getStringArray(R.array.manga_name)
        val dataDesc = resources.getStringArray(R.array.manga_sinopsis)
        val dataThumbnail = resources.getStringArray(R.array.manga_thumbnail)
        val dataViews = resources.getStringArray(R.array.manga_views)
        val dataComments = resources.getStringArray(R.array.manga_comments)
        val dataRating = resources.getStringArray(R.array.manga_rating)
        val dataAuthor = resources.getStringArray(R.array.manga_author)
        val dataGenre = resources.getStringArray(R.array.manga_genre)

        for (i in dataName.indices) {
            val manga = MangaModel(
                dataName[i],
                dataDesc[i],
                dataThumbnail[i],
                dataViews[i],
                dataComments[i],
                dataRating[i],
                dataGenre[i],
                dataAuthor[i]
            )
            list.add(manga)
        }
    }


}