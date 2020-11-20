package com.example.aboutmedatabinding

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.input.InputManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.aboutmedatabinding.databinding.ActivityMainBinding
import androidx.databinding.DataBindingUtil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val myName: MyName = MyName("Aleks Haecky")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.myName = myName

        binding.doneButton.setOnClickListener {
            addNickname(it)
        }

        binding.nicknameText.setOnLongClickListener {
            editNickname(it)
            return@setOnLongClickListener true
        }
    }

    private fun editNickname(view: View) = with(binding) {
        invalidateAll()
        nicknameText.visibility = View.GONE
        nicknameEdit.visibility = View.VISIBLE
        doneButton.visibility = View.VISIBLE
    }

    @SuppressLint("ServiceCast")
    private fun addNickname(view: View) {
        with(binding) {
            myName?.nickname = nicknameEdit.text.toString()
            invalidateAll()
            nicknameEdit.visibility = View.GONE
            doneButton.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
        }

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}