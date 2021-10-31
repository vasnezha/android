package com.example.lab1.ui.settings

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.lab1.R
import com.example.lab1.util.SharedPreference

class SettingsActivity : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit var surname: EditText
    private lateinit var email: EditText
    private lateinit var useDifferentColor: CheckBox
    private lateinit var saveChangesButton: Button
    private lateinit var clearSettingsButton: Button

    private val sharedPrefs by lazy { SharedPreference(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        initView()
        initListeners()
        setSettings()
    }

    private fun initView() {
        name = findViewById(R.id.et_name)
        surname = findViewById(R.id.et_surname)
        email = findViewById(R.id.et_email)
        useDifferentColor = findViewById(R.id.cb_new_text_color)
        saveChangesButton = findViewById(R.id.btn_save_changes)
        clearSettingsButton = findViewById(R.id.btn_clear_settings)

    }

    private fun initListeners() {
        useDifferentColor.setOnCheckedChangeListener { _, isChecked ->
            setFiledTextColor(isChecked)
        }
        saveChangesButton.setOnClickListener {
            saveSettings()
        }
        clearSettingsButton.setOnClickListener {
            clearSettings()
        }
    }

    private fun setSettings() {
        name.setText(sharedPrefs.getValueString("name"))
        surname.setText(sharedPrefs.getValueString("surname"))
        email.setText(sharedPrefs.getValueString("email"))
        useDifferentColor.isChecked = sharedPrefs.getValueBoolean("text_color", false)
        setFiledTextColor(useDifferentColor.isChecked)
    }

    private fun saveSettings() {
        sharedPrefs.save("name", name.text.toString())
        sharedPrefs.save("surname", surname.text.toString())
        sharedPrefs.save("email", email.text.toString())
        sharedPrefs.save("text_color", useDifferentColor.isChecked)
        Toast.makeText(this, "Настройки сохранены", Toast.LENGTH_SHORT).show()

    }

    private fun clearSettings() {
        sharedPrefs.clearSharedPreference()
        name.setText("")
        surname.setText("")
        email.setText("")
        useDifferentColor.isChecked = false
    }

    private fun setFiledTextColor(isChecked: Boolean) {
        if (isChecked) {
            name.setTextColor(Color.RED)
            surname.setTextColor(Color.RED)
            email.setTextColor(Color.RED)
        } else {
            name.setTextColor(Color.DKGRAY)
            surname.setTextColor(Color.DKGRAY)
            email.setTextColor(Color.DKGRAY)
        }
    }
}