package com.nigelcardozo.kotlincalculator.main.presenter

import com.nigelcardozo.kotlincalculator.main.view.MainView

class MainPresenter(mainView: MainView) {

    var view: MainView = mainView;
    var operation: Operation? = null
    var inputParameters: String = ""
    var firstArgument: Float? = null

    fun getPresenter(): MainPresenter {

        //THIS ISN'T RIGHT... TEMPORARY - NEED TO ACTUALLY INJECT
        return this;
    }

    fun handleUserInput(input: Int) {
        inputParameters += input.toString()
        view.displayValue(inputParameters)
    }

    fun handleUserInput(input: String) {
        inputParameters += input
        view.displayValue(inputParameters)
    }

    fun handleAdditionButtonPressed() {
        if (inputParameters != "") {
            val x = inputParameters
            firstArgument = x.toFloat()

            operation = Operation.ADD
            inputParameters = ""
        }
    }

    fun handleSubtractionButtonPressed() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun handleMultiplicactionButtonPressed() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun handleDivisionButtonPressed() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun handleEqualsButtonPressed() {
        if (firstArgument != null && inputParameters.length > 0 && operation != null) {

            //SHOULD USE A LIBRARY TO DO THIS
            when (operation) {
                Operation.ADD -> {
                    val x = firstArgument
                    val y = inputParameters.toFloat()

                    val result = x?.plus(y)

                    if (result != null) {
                        var resultString = result.toString()

                        if (resultString.endsWith(".0")) {
                            resultString = resultString.substring(0, resultString.length - 2)

                            handleEqualsComplete(resultString)
                        }

                        view.displayValue(resultString)
                    }
                };
            }
        }
    }

    fun handleEqualsComplete(resultString: String) {
        firstArgument = null
        inputParameters = resultString
        operation = null
    }

    fun handleClearButtonPressed() {
        inputParameters = ""
        view.displayValue("")
    }

    fun handleAllClear() {
        operation = null
        inputParameters = ""
        firstArgument = null

        view.displayValue("")
    }

    fun handleBackspaceButtonPressed() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    enum class Operation {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }
}