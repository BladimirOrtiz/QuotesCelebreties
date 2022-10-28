package com.example.quotes.presentation.View_Model


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.quotes.data.QuoteRepositoryAdd
import com.example.quotes.data.local.QuoteDB
import com.example.quotes.data.local.entities.QuoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class QuoteAddViewModel (application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<QuoteEntity>>
    private val repository: QuoteRepositoryAdd

    init {
        val quoteDao = QuoteDB.getDatabase(application,viewModelScope).quoteDao()
        repository = QuoteRepositoryAdd(quoteDao)
        readAllData = repository.readAllDaata

    }

    fun addQuote(quote:QuoteEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addQuote(quote)
        }

    }

}
    }

}