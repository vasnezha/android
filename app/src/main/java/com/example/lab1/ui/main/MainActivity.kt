package com.example.lab1.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.example.lab1.R
import com.example.lab1.ui.number.NumberFragment
import com.example.lab1.ui.result.ResultActivity
import com.example.lab1.ui.service.ServiceActivity
import com.example.lab1.ui.settings.SettingsActivity
import com.example.lab1.ui.string.StringFragment
import com.google.android.material.button.MaterialButton
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private var fragmentContainer: FragmentContainerView? = null
    private var switchButton: MaterialButton? = null
    private var state by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentContainer = findViewById(R.id.fragmentContainerView)
        switchButton = findViewById(R.id.materialButtonSwitch)

        if (fragmentContainer != null) {
            setStringFragment()
            switchButton?.setOnClickListener {
                switchFragment()
            }
        }
    }

    // Создаём меню (привязка данных)
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    // Назначаем дейсвия для пунктов меню
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.open_result -> {
                startActivity(Intent(this, ResultActivity::class.java))
                true
            }
            R.id.open_browser -> {
                startActivity(Intent(this, OpenBrowserActivity::class.java))
                true
            }
            R.id.open_service -> {
                startActivity(Intent(this, ServiceActivity::class.java))
                true
            }
            R.id.open_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun switchFragment() {
        if (state == STRING_STATE) {
            setNumberFragment()
        } else if (state == NUMBER_STATE) {
            setStringFragment()
        }
    }

    private fun setStringFragment() {
        setFragment(StringFragment.newInstance())
        state = STRING_STATE
    }

    private fun setNumberFragment() {
        setFragment(NumberFragment.newInstance())
        state = NUMBER_STATE
    }

    private fun setFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment)
        fragmentTransaction.commit()
    }

    companion object {
        private const val STRING_STATE = 1
        private const val NUMBER_STATE = 2
    }

}