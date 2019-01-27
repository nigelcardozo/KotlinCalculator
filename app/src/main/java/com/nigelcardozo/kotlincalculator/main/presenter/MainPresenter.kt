package com.nigelcardozo.kotlincalculator.main.presenter

import com.nigelcardozo.kotlincalculator.main.view.MainView

class MainPresenter(mainView: MainView) {

    var view: MainView = mainView;
    private var operation: Operation? = null
    private var inputParameters: String = ""
    private var firstArgument: Float? = null

    enum class Operation {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }

    fun getPresenter(): MainPresenter {

        //THIS ISN'T RIGHT... TEMPORARY - NEED TO ACTUALLY INJECT
        return this
    }

    fun handleUserInput(input: Int) {
        inputParameters += input.toString()
        view.displayValue(inputParameters)
    }

    fun handleUserInput(input: String) {
        inputParameters += input
        view.displayValue(inputParameters)
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
        if (firstArgument != null && inputParameters.length > 0 && operation != null) {
            calculateAnswer()
        }
    }

    fun handleEqualsComplete(resultString: String) {
        firstArgument = null
        inputParameters = resultString
        operation = null
    }

    fun handleClear(allClear: Boolean) {
        if (allClear) {
            operation = null
            firstArgument = null
        }

        inputParameters = ""
        view.displayValue("")
    }

    fun handleBackspace() {
        if (inputParameters.isNotEmpty()) {
            inputParameters = inputParameters.substring(0, inputParameters.length-1)
        }
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

            view.displayValue(resultString)
        }
    }
}