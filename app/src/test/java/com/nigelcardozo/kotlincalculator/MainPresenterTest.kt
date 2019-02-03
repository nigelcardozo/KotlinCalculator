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
    fun testHandleInputSingleDigit() {

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
    fun testHandleInputMultipleDigits() {

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
}