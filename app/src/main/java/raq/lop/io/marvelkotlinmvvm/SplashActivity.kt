package raq.lop.io.marvelkotlinmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import raq.lop.io.marvelkotlinmvvm.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}