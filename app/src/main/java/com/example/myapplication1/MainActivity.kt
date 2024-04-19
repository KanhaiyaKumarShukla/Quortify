package com.example.myapplication1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private val fb:FloatingActionButton
        get() = findViewById(R.id.floatingActionButton)
    private val prevBtn:TextView
        get() = findViewById(R.id.previous_btn)
    private val nextBtn:TextView
        get() = findViewById(R.id.next_btn)
    private val quoteTv:TextView
        get() = findViewById(R.id.quoteText)
    private val authorTv:TextView
        get() = findViewById(R.id.quoteAuthor)
    private lateinit var mainViewModel:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel= ViewModelProvider(this, viewModelFactory(application))[MainViewModel::class.java]
        setQuote(mainViewModel.getQuote())

        prevBtn.setOnClickListener {
            setQuote(mainViewModel.getPrevQuote())
        }
        nextBtn.setOnClickListener {
            setQuote(mainViewModel.getNextQuote())
        }
        fb.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, mainViewModel.getQuote().text)
            startActivity(intent)
        }
    }

    private fun setQuote(quote: Quote) {
        quoteTv.text=quote.text
        authorTv.text=quote.author
    }
}