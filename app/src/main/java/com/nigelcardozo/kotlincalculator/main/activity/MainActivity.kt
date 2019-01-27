package com.nigelcardozo.kotlincalculator.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nigelcardozo.kotlincalculator.R
import com.nigelcardozo.kotlincalculator.main.presenter.MainPresenter
import com.nigelcardozo.kotlincalculator.main.view.MainView
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(), MainView {

    private val presenter: MainPresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupOnClickListeners()
    }

    private fun setupOnClickListeners() {

        btnNum0.setOnClickListener {
            presenter.handleUserInput(0)
        }

        btnNum1.setOnClickListener {
            presenter.handleUserInput(1)
        }

        btnNum2.setOnClickListener {
            presenter.handleUserInput(2)
        }

        btnNum3.setOnClickListener {
            presenter.handleUserInput(3)
        }

        btnNum4.setOnClickListener {
            presenter.handleUserInput(4)
        }

        btnNum5.setOnClickListener {
            presenter.handleUserInput(5)
        }

        btnNum6.setOnClickListener {
            presenter.handleUserInput(6)
        }

        btnNum7.setOnClickListener {
            presenter.handleUserInput(7)
        }

        btnNum8.setOnClickListener {
            presenter.handleUserInput(8)
        }

        btnNum9.setOnClickListener {
            presenter.handleUserInput(9)
        }

        btnDecimal.setOnClickListener {
            presenter.handleUserInput(".")
        }

        btnOperatorAdd.setOnClickListener {
            presenter.handleOperation(MainPresenter.Operation.ADD)
        }

        btnOperatorSubtract.setOnClickListener {
            presenter.handleOperation(MainPresenter.Operation.SUBTRACT)
        }

        btnOperatorMultiply.setOnClickListener {
            presenter.handleOperation(MainPresenter.Operation.MULTIPLY)
        }

        btnOperatorDivide.setOnClickListener {
            presenter.handleOperation(MainPresenter.Operation.DIVIDE)
        }

        btnOperatorEquals.setOnClickListener {
            presenter.operationEquals()
        }

        btnClear.setOnClickListener {
            presenter.handleClear(false)
        }

        btnAllClear.setOnClickListener {
            presenter.handleClear(true)
        }

        btnBackSpace.setOnClickListener {
            presenter.handleBackspace()
        }
    }

    //MainView Implementation
    override fun displayValue(result: String) {
        tvCalcOutput.text = result
    }
}
