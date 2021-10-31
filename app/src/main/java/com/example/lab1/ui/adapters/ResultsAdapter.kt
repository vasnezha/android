package com.example.lab1.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab1.R
import com.example.lab1.database.Result

// Адаптер для управления списком
class ResultsAdapter(private val listResults: List<Result>) : RecyclerView.Adapter<ResultsAdapter.ResultViewHolder>() {

    class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Переменная для текствью
        private var fullNameTextView: TextView? = null
        private var fullNameTitleTextView: TextView? = null

        // Инициализация текствью
        init {
            fullNameTextView = itemView.findViewById(R.id.textViewFullname)
            fullNameTitleTextView = itemView.findViewById(R.id.textViewFullnameTitle)
        }

        // Задаем текст для текствью
        fun bind(item: Result) {
            fullNameTextView?.text = item.concatenationResult
            fullNameTitleTextView?.text = item.concatenationResult
        }

        companion object {
            // Создаём разметку для элемента через вьюхолдер
            fun from(parent: ViewGroup): ResultViewHolder {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_result, parent, false)
                return ResultViewHolder(itemView)
            }
        }
    }

    // Создаём разметку для элемента через вьюхолдер
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        return ResultViewHolder.from(parent)
    }

    // Привязываем данные к разметке
    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        // Берем элемент из списка согласно его позиции
        val item = listResults[position]
        // Подставляем данные элемента в разметку
        holder.bind(item)
    }

    // Количество элементов
    override fun getItemCount(): Int = listResults.size
}