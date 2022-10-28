package com.example.quotescelebrities.presentation.View


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.quotes.data.local.entities.QuoteEntity
import com.example.quotes.databinding.ActivityQuoteAddBinding
import com.example.quotes.presentation.View_Model.QuoteAddViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint

class QuoteAddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuoteAddBinding
    private lateinit var mUserViewModel: QuoteAddViewModel


    override fun onCreate(savedInstanceState: Bundle?) {

        mUserViewModel = ViewModelProvider(this).get(QuoteAddViewModel::class.java)

        super.onCreate(savedInstanceState)
        binding = ActivityQuoteAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // setContentView(R.layout.activity_add_quote)
        
        binding.buttonAddQuote.setOnClickListener {
            insertDataToDatabase()
        }
    }

    private fun insertDataToDatabase() {
        val addAuthor = binding.edtAddAuthor.text.toString()
        val addQuote = binding.edtAddQuote.text.toString()

        if (inputCheck(addAuthor, addQuote)){

            //Create Quote object
            val quote = QuoteEntity(0, addAuthor, addQuote)

            //Add to database
            mUserViewModel.addQuote(quote)
            Toast.makeText(this, "Cita agregada Correctamente", Toast.LENGTH_SHORT).show()
            binding.edtAddQuote.text.clear()
            binding.edtAddAuthor.text.clear()

        }else{
            Toast.makeText(this, "No se ah podido crear la cita", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(addAuthor: String, addQuote: String): Boolean{
        return !(TextUtils.isEmpty(addAuthor) && TextUtils.isEmpty(addQuote))
    }
}