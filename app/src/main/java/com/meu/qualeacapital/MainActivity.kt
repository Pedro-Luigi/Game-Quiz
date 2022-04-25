package com.meu.qualeacapital

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.meu.qualeacapital.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    companion object{
        var mixPosition = Constants.searchQuestion()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(QuestionActivity.sum > mixPosition.size){
            binding.imageView.setColorFilter(Color.parseColor("#F1BE06"))
            binding.tvCongratulation.visibility = View.VISIBLE
        }

        binding.btnPlay.setOnClickListener {
            val intent = Intent(this, QuestionActivity::class.java)
            startActivity(intent)
            mixPosition = Constants.searchQuestion()
        }
    }
}