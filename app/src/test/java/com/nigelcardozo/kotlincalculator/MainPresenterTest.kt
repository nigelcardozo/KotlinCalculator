package com.nigelcardozo.kotlincalculator

import com.nigelcardozo.kotlincalculator.main.presenter.MainPresenter
import com.nigelcardozo.kotlincalculator.main.view.MainView
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class MainPresenterTest {


    @MockK
    private lateinit var mainViewMock: MainView

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        /*
        codeUtils = CodeUtils(settings)

        every { settings.config } returns config
        every { aircraft.iataCodeWithIcaoFallback(any()) } returns "IATA"
        every { aircraft.icaoCodeWithIataFallback(any()) } returns "ICAO"
        every { airport.iataCodeWithIcaoFallback(any()) } returns "IATA"
        every { airport.icaoCodeWithIataFallback(any()) } returns "ICAO"
        */
    }

    @Test
    fun addTwoNumbersTest() {

        val sut = MainPresenter(mainViewMock)

        assertEquals(7, sut.addTwoNumbers(2, 5))
        /*
        every { config.useICAOCodeForAircraft } returns true
        assertEquals("ICAO", codeUtils.aircraftCode(aircraft))
        */
    }
}