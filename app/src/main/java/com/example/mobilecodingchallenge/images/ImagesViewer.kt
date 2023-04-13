package com.example.mobilecodingchallenge.images

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilecodingchallenge.R
import com.example.mobilecodingchallenge.databinding.ActivityImagesBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class ImagesViewer : AppCompatActivity() {

    private lateinit var imageList: RecyclerView
    private lateinit var adapter: ImageAdapter
    private lateinit var images: List<Image>

    private lateinit var binding: ActivityImagesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityImagesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.apply {
            setBackgroundDrawable(ColorDrawable(Color.BLACK))
        }

        imageList = findViewById(R.id.image_list)

        val layoutManager = LinearLayoutManager(this)
        imageList.layoutManager = layoutManager

        // Fetch images from Unsplash API
        runBlocking {
            val client = UnsplashClient(ACCESS_KEY)
            val response = withContext(Dispatchers.IO) {
                client.getPhotos()
            }
            if (response.isSuccessful) {
                images = response.body()!!.map { photo ->
                    Image(photo.id, photo.title, Urls(photo.urls.regular))
                }
            } else {
                Toast.makeText(this@ImagesViewer, "Failed to fetch images", Toast.LENGTH_SHORT).show()
                images = emptyList()
            }

            adapter = ImageAdapter(images) { image ->
                // Handle click on image
                Toast.makeText(this@ImagesViewer, "Clicked on ${image.id}", Toast.LENGTH_SHORT).show()
            }
            imageList.adapter = adapter
        }
    }

    companion object {
        private const val ACCESS_KEY = "qlC1DTeQK0dPLngdRwzvzcFaHORH1rbfyFAlI5AfNyg"
    }
}
