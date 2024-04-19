package com.example.myapplication1

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(private val context: Context):ViewModel() {

    private var quoteList:Array<Quote> = emptyArray()
    private var index:Int=0

    init {
        quoteList=loadQuotesFromAssets()
    }

    private fun loadQuotesFromAssets(): Array<Quote> {
        val inputStream=context.assets.open("quotes.json")
        val size=inputStream.available()
        val buffer=ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json=String(buffer, Charsets.UTF_8)
        val gson=Gson()
        return gson.fromJson(json, Array<Quote>::class.java)
    }

    fun getQuote():Quote{
        return quoteList[index]
    }
    fun getNextQuote():Quote{
        return quoteList[++index % quoteList.size]
    }
    fun getPrevQuote():Quote{
        return quoteList[(--index + quoteList.size) % quoteList.size]
    }
}