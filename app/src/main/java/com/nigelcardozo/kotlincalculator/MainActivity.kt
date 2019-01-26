package com.nigelcardozo.kotlincalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupOnClickListeners()
    }

    fun setupOnClickListeners() {

        btnNum0.setOnClickListener {
            tvCalcOutput.text = "0"
        }

        btnNum1.setOnClickListener {
            tvCalcOutput.text = "1"
        }

        btnNum2.setOnClickListener {
            tvCalcOutput.text = "2"
        }
    }
}
