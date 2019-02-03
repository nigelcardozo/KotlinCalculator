package com.nigelcardozo.kotlincalculator

import com.nigelcardozo.kotlincalculator.main.presenter.MainPresenter
import com.nigelcardozo.kotlincalculator.main.view.MainView
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test


class MainPresenterTest {

    @MockK(relaxed = true)
    private lateinit var mainViewMock: MainView

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun testHandleNumericalInputSingleDigit() {
        val sut = MainPresenter(mainViewMock)

        sut.handleUserInput(2)

        verify (exactly = 1) {
            mainViewMock.displayValue(any())
        }

        verify {
            mainViewMock.displayValue("2")
        }
    }

    @Test
    fun testHandleNumericalInputMultipleDigits() {
        val sut = MainPresenter(mainViewMock)

        sut.handleUserInput(1)
        sut.handleUserInput(2)
        sut.handleUserInput(3)

        verify (exactly = 3) {
            mainViewMock.displayValue(any())
        }

        verifySequence {
            mainViewMock.displayValue("1")
            mainViewMock.displayValue("12")
            mainViewMock.displayValue("123")
        }
    }

    @Test
    fun testHandleNumericalInputErroneousData() {
        val sut = MainPresenter(mainViewMock)

        sut.handleUserInput(-1)

        verify(exactly = 0) {
            mainViewMock.displayValue(any())
        }
    }

    @Test
    fun testHandleStringInputDecimalPointNoPrefix() {
        val sut = MainPresenter(mainViewMock)

        sut.handleUserInput(".")

        verify (exactly = 1) {
            mainViewMock.displayValue(any())
        }

        verify {
            mainViewMock.displayValue("0.")
        }
    }

    @Test
    fun testHandleStringInputDecimalPointWithPrefix() {
        val sut = MainPresenter(mainViewMock)

        sut.handleUserInput(2)
        sut.handleUserInput(".")
        sut.handleUserInput(5)

        verify (exactly = 3) {
            mainViewMock.displayValue(any())
        }

        verify {
            mainViewMock.displayValue("2")
            mainViewMock.displayValue("2.")
            mainViewMock.displayValue("2.5")
        }
    }

    @Test
    fun testHandleStringInputNotDecimalPoint() {
        val sut = MainPresenter(mainViewMock)

        sut.handleUserInput("S")

        verify (exactly = 0) {
            mainViewMock.displayValue(any())
        }
    }

    @Test
    fun testHandleSingleDigitAdditionOperation() {
        val sut = MainPresenter(mainViewMock)

        sut.handleUserInput(1)
        sut.handleOperation(MainPresenter.Operation.ADD)
        sut.handleUserInput(2)
        sut.operationEquals()

        verify (exactly = 3) {
            mainViewMock.displayValue(any())
        }

        verify {
            mainViewMock.displayValue("1")
            mainViewMock.displayValue("2")
            mainViewMock.displayValue("3")
        }
    }

    @Test
    fun testHandleMultipleDigitAdditionOperation() {
        val sut = MainPresenter(mainViewMock)

        sut.handleUserInput(1)
        sut.handleUserInput(2)
        sut.handleOperation(MainPresenter.Operation.ADD)
        sut.handleUserInput(3)
        sut.handleUserInput(4)
        sut.handleUserInput(5)
        sut.operationEquals()

        verify (exactly = 6) {
            mainViewMock.displayValue(any())
        }

        verify {
            mainViewMock.displayValue("1")
            mainViewMock.displayValue("12")
            mainViewMock.displayValue("3")
            mainViewMock.displayValue("34")
            mainViewMock.displayValue("345")
            mainViewMock.displayValue("357")
        }
    }

    @Test
    fun testHandleDecimalDigitAdditionOperation() {
        val sut = MainPresenter(mainViewMock)

        sut.handleUserInput(1)
        sut.handleUserInput(".")
        sut.handleUserInput(2)
        sut.handleOperation(MainPresenter.Operation.ADD)
        sut.handleUserInput(3)
        sut.handleUserInput(4)
        sut.handleUserInput(5)
        sut.operationEquals()

        verify (exactly = 7) {
            mainViewMock.displayValue(any())
        }

        verify {
            mainViewMock.displayValue("1")
            mainViewMock.displayValue("1.")
            mainViewMock.displayValue("1.2")
            mainViewMock.displayValue("3")
            mainViewMock.displayValue("34")
            mainViewMock.displayValue("345")
            mainViewMock.displayValue("346.2")
        }
    }

    @Test
    fun testHandleSingleDigitSubtractionOperation() {
        val sut = MainPresenter(mainViewMock)

        sut.handleUserInput(5)
        sut.handleOperation(MainPresenter.Operation.SUBTRACT)
        sut.handleUserInput(3)
        sut.operationEquals()

        verify (exactly = 3) {
            mainViewMock.displayValue(any())
        }

        verify {
            mainViewMock.displayValue("5")
            mainViewMock.displayValue("3")
            mainViewMock.displayValue("2")
        }
    }

    @Test
    fun testHandleMultipleDigitSubtractionOperation() {
        val sut = MainPresenter(mainViewMock)

        sut.handleUserInput(5)
        sut.handleUserInput(5)
        sut.handleUserInput(5)
        sut.handleOperation(MainPresenter.Operation.SUBTRACT)
        sut.handleUserInput(5)
        sut.handleUserInput(5)
        sut.handleUserInput(5)
        sut.operationEquals()

        verify (exactly = 7) {
            mainViewMock.displayValue(any())
        }

        verify {
            mainViewMock.displayValue("5")
            mainViewMock.displayValue("55")
            mainViewMock.displayValue("555")
            mainViewMock.displayValue("5")
            mainViewMock.displayValue("55")
            mainViewMock.displayValue("555")
            mainViewMock.displayValue("0")
        }
    }

    @Test
    fun testHandleSingleDigitMultiplicationOperation() {
        val sut = MainPresenter(mainViewMock)

        sut.handleUserInput(5)
        sut.handleOperation(MainPresenter.Operation.MULTIPLY)
        sut.handleUserInput(3)
        sut.operationEquals()

        verify (exactly = 3) {
            mainViewMock.displayValue(any())
        }

        verify {
            mainViewMock.displayValue("5")
            mainViewMock.displayValue("3")
            mainViewMock.displayValue("15")
        }
    }

    @Test
    fun testHandleMultipleDigitMultiplicationOperation() {
        val sut = MainPresenter(mainViewMock)

        sut.handleUserInput(1)
        sut.handleUserInput(0)
        sut.handleUserInput(0)
        sut.handleOperation(MainPresenter.Operation.MULTIPLY)
        sut.handleUserInput(5)
        sut.handleUserInput(5)
        sut.handleUserInput(5)
        sut.operationEquals()

        verify (exactly = 7) {
            mainViewMock.displayValue(any())
        }

        verify {
            mainViewMock.displayValue("1")
            mainViewMock.displayValue("10")
            mainViewMock.displayValue("100")
            mainViewMock.displayValue("5")
            mainViewMock.displayValue("55")
            mainViewMock.displayValue("555")
            mainViewMock.displayValue("55500")
        }
    }

    @Test
    fun testHandleSingleDigitDivisionOperation() {
        val sut = MainPresenter(mainViewMock)

        sut.handleUserInput(15)
        sut.handleOperation(MainPresenter.Operation.DIVIDE)
        sut.handleUserInput(3)
        sut.operationEquals()

        verify (exactly = 3) {
            mainViewMock.displayValue(any())
        }

        verify {
            mainViewMock.displayValue("15")
            mainViewMock.displayValue("3")
            mainViewMock.displayValue("5")
        }
    }

    @Test
    fun testHandleMultipleDigitDivisionOperation() {
        val sut = MainPresenter(mainViewMock)

        sut.handleUserInput(5)
        sut.handleUserInput(0)
        sut.handleUserInput(0)
        sut.handleOperation(MainPresenter.Operation.DIVIDE)
        sut.handleUserInput(1)
        sut.handleUserInput(0)
        sut.operationEquals()

        verify (exactly = 6) {
            mainViewMock.displayValue(any())
        }

        verify {
            mainViewMock.displayValue("5")
            mainViewMock.displayValue("50")
            mainViewMock.displayValue("500")
            mainViewMock.displayValue("1")
            mainViewMock.displayValue("10")
            mainViewMock.displayValue("50")
        }
    }

    @Test
    fun testHandleBackSpace() {
        val sut = MainPresenter(mainViewMock)

        sut.handleUserInput(1)
        sut.handleUserInput(2)
        sut.handleUserInput(3)
        sut.handleBackspace()
        sut.handleUserInput(4)

        verify (exactly = 4) {
            mainViewMock.displayValue(any())
        }

        verifySequence {
            mainViewMock.displayValue("1")
            mainViewMock.displayValue("12")
            mainViewMock.displayValue("123")
            mainViewMock.displayValue("124")
        }
    }

    @Test
    fun testHandleBackSpaceNoInput() {
        val sut = MainPresenter(mainViewMock)

        sut.handleBackspace()

        verify (exactly = 0) {
            mainViewMock.displayValue(any())
        }
    }

    @Test
    fun testClear() {
        val sut = MainPresenter(mainViewMock)

        sut.handleUserInput(1)
        sut.handleUserInput(2)
        sut.handleOperation(MainPresenter.Operation.ADD)
        sut.handleUserInput(3)
        sut.handleUserInput(4)
        sut.handleClear(false)
        sut.handleUserInput(5)
        sut.handleUserInput(6)
        sut.operationEquals()

        verify (exactly = 8) {
            mainViewMock.displayValue(any())
        }

        verify {
            mainViewMock.displayValue("1")
            mainViewMock.displayValue("12")
            mainViewMock.displayValue("3")
            mainViewMock.displayValue("34")
            mainViewMock.displayValue("")
            mainViewMock.displayValue("5")
            mainViewMock.displayValue("56")
            mainViewMock.displayValue("68")
        }
    }

    @Test
    fun testHandleAllClear() {
        val sut = MainPresenter(mainViewMock)

        sut.handleUserInput(1)
        sut.handleUserInput(2)
        sut.handleUserInput(3)
        sut.handleClear(true)
        sut.handleUserInput(4)

        verify (exactly = 5) {
            mainViewMock.displayValue(any())
        }

        verifySequence {
            mainViewMock.displayValue("1")
            mainViewMock.displayValue("12")
            mainViewMock.displayValue("123")
            mainViewMock.displayValue("")
            mainViewMock.displayValue("4")
        }
    }

    @Test
    fun testHandleAllClearNoInput() {
        val sut = MainPresenter(mainViewMock)

        sut.handleClear(true)

        verify (exactly = 1) {
            mainViewMock.displayValue("")
        }
    }
}