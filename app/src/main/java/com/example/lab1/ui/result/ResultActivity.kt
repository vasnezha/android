package com.example.lab1.ui.result

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.lab1.LabApplication
import com.example.lab1.R
import com.example.lab1.ui.adapters.ResultsAdapter
import com.example.lab1.util.ListContainer
import com.example.lab1.viewmodels.ResultViewModel

class ResultActivity : AppCompatActivity() {

    private val resultViewModel by viewModels<ResultViewModel> {
        ResultViewModel.Factory(
            (this.application as LabApplication).repository
        )
    }

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        recyclerView = findViewById(R.id.recyclerView)

        resultViewModel.results.observe(this) {
            recyclerView.adapter = ResultsAdapter(it)
        }
    }
}