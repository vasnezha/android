package com.example.lab1.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.lab1.R

class IntentCatcher : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_catcher)
    //привязка данных
        val textView: TextView = findViewById(R.id.textViewSharedLink)
        //в переменную пишем намерение которое получили, когда подедлились ссылкой
        val intent: Intent = intent
        //вытаскиваем данные из намерения
        val sharedText = intent.getStringExtra(Intent.EXTRA_TEXT)
        //подставляем данные в текст
        textView.text = sharedText
    }
}