package com.nigelcardozo.kotlincalculator.main.presenter

import com.nigelcardozo.kotlincalculator.main.view.MainView

class MainPresenter(val view: MainView) {

    private var operation: Operation? = null
    private var inputParameters: String = ""
    private var firstArgument: Float? = null

    enum class Operation {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }

    //Public Methods
    
    fun handleUserInput(input: Int) {
        if (input < 0) return

        inputParameters += input.toString()
        displayValue(inputParameters)
    }

    fun handleUserInput(input: String) {
        if (input != ".") return

        inputParameters += if (inputParameters != "") {
            input
        } else {
            "0$input"
        }

        displayValue(inputParameters)
    }

    fun handleOperation(operationSelected: Operation) {
        if (inputParameters != "") {
            val x = inputParameters
            firstArgument = x.toFloat()

            inputParameters = ""

            operation = operationSelected
        }
    }

    fun operationEquals() {
        if (firstArgument != null && inputParameters.isNotEmpty() && operation != null) {
            calculateAnswer()
        }
    }

    fun handleClear(allClear: Boolean) {
        if (allClear) {
            operation = null
            firstArgument = null
        }

        inputParameters = ""
        displayValue("")
    }

    fun handleBackspace() {
        if (inputParameters.isNotEmpty()) {
            inputParameters = inputParameters.substring(0, inputParameters.length-1)
        }
    }


    //Private methods

    private fun handleEqualsComplete(resultString: String) {
        firstArgument = null
        inputParameters = resultString
        operation = null
    }

    private fun calculateAnswer() {
        val x = firstArgument
        val y = inputParameters.toFloat()

        var result: Float? = null

        when (operation) {

            Operation.ADD -> {
                result = x?.plus(y)
            }

            Operation.SUBTRACT -> {
                result = x?.minus(y)
            }

            Operation.MULTIPLY -> {
                result = x?.times(y)
            }

            Operation.DIVIDE -> {
                result = x?.div(y)
            }
        }

        if (result != null) {
            var resultString = result.toString()

            if (resultString.endsWith(".0")) {
                resultString = resultString.substring(0, resultString.length - 2)

                handleEqualsComplete(resultString)
            }

            displayValue(resultString)
        }
    }

    private fun displayValue(value: String) {
        view.displayValue(value)
    }
}