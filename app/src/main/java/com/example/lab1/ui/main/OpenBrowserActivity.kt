package com.example.lab1.ui.main

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.lab1.R

class OpenBrowserActivity : AppCompatActivity() {

    private lateinit var editTextLinkText: EditText
    private lateinit var materialButtonOpenLink: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_browser)

        editTextLinkText = findViewById(R.id.editTextLinkText)
        materialButtonOpenLink = findViewById(R.id.materialButtonOpenLink)

        materialButtonOpenLink.setOnClickListener {
            // Создание намерения, с действием просмотра и ссылкой в качестве данных
            val intent = Intent().apply {
                // Действие
                action = Intent.ACTION_VIEW
                // Данные
                data = Uri.parse(editTextLinkText.text.toString())
            }
            // Открытие активности с намерением
            startActivity(intent)
        }
    }
}

