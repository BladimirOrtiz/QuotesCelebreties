package com.example.quotes.data

import androidx.lifecycle.LiveData
import com.example.quotes.data.local.daos.QuoteDao
import com.example.quotes.data.local.entities.QuoteEntity

class QuoteRepositoryAdd (private val quoteDao: QuoteDao) {

    val readAllDaata: LiveData<List<QuoteEntity>> = quoteDao.readAllData()

    suspend fun addQuote(quote: QuoteEntity){
        quoteDao.insert(quote)
    }

}