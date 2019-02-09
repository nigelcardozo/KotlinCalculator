package com.nigelcardozo.kotlincalculator

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.nigelcardozo.kotlincalculator.activity.MainActivity
import com.nigelcardozo.kotlincalculator.main.presenter.MainPresenter
import com.nigelcardozo.kotlincalculator.main.view.MainView
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.declare
import android.app.Activity
import java.lang.Thread.sleep


@RunWith(AndroidJUnit4::class)

class ControlledActivityTestRule<T : Activity> : ActivityTestRule<T> {
    constructor(activityClass: Class<T>) : super(activityClass, false) {}

    constructor(activityClass: Class<T>, initialTouchMode: Boolean) : super(activityClass, initialTouchMode, true) {}

    constructor(activityClass: Class<T>, initialTouchMode: Boolean, launchActivity: Boolean) : super(
        activityClass,
        initialTouchMode,
        launchActivity
    ) {
    }

    fun finish() {
        finishActivity()
    }

    fun relaunchActivity(seconds: Int) {
        finishActivity()
        sleep(seconds)
        launchActivity()
        sleep(seconds)
    }

    fun launchActivity() {
        launchActivity(activityIntent)
    }

    fun sleep(seconds: Int) {
        if (seconds > 0) {
            try {
                Thread.sleep((seconds * 1000).toLong())
            } catch (ex: Exception) {
            }

        }
    }
}

class MainActivityTest : KoinTest {

    @MockK(relaxed = true)
    private lateinit var mainViewMock: MainView

    @Rule @JvmField
    var activityRule = ControlledActivityTestRule<MainActivity>(
        MainActivity::class.java
    )

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        //declare { single { (mainView: MainView) -> MainPresenter(mainView) } }

        //worksheetItemsLiveData = MutableLiveData()
        //every { worksheetViewModel.worksheetItems() } returns worksheetItemsLiveData
    }

    @After
    fun afterEach() {
        activityRule.relaunchActivity(5)
    }

    @Test
    @Throws(Exception::class)
    fun clickButtonNumber1() {
        onView(withId(R.id.tvCalcOutput)).check(matches(withText("")))
        onView(withId(R.id.btnNum1)).perform(click())
        onView(withId(R.id.tvCalcOutput)).check(matches(withText("1")))
    }

    @Test
    @Throws(Exception::class)
    fun clickButtonNumber2() {
        onView(withId(R.id.tvCalcOutput)).check(matches(withText("")))
        onView(withId(R.id.btnNum2)).perform(click())
        onView(withId(R.id.tvCalcOutput)).check(matches(withText("2")))
    }

    @Test
    @Throws(Exception::class)
    fun clickMultipleNumericButtons() {
        onView(withId(R.id.tvCalcOutput)).check(matches(withText("")))
        onView(withId(R.id.btnNum3)).perform(click())
        onView(withId(R.id.btnNum4)).perform(click())
        onView(withId(R.id.btnNum5)).perform(click())
        onView(withId(R.id.tvCalcOutput)).check(matches(withText("345")))
    }

    @Test
    @Throws(Exception::class)
    fun clickMultipleNumericButtonsWithBackKeyCorrection() {
        onView(withId(R.id.tvCalcOutput)).check(matches(withText("")))
        onView(withId(R.id.btnNum7)).perform(click())
        onView(withId(R.id.btnNum8)).perform(click())
        onView(withId(R.id.btnNum9)).perform(click())
        onView(withId(R.id.btnBackSpace)).perform(click())
        onView(withId(R.id.btnNum0)).perform(click())
        onView(withId(R.id.tvCalcOutput)).check(matches(withText("780")))
    }

    @Test
    @Throws(Exception::class)
    fun simpleAdditionTest() {
        onView(withId(R.id.tvCalcOutput)).check(matches(withText("")))
        onView(withId(R.id.btnNum1)).perform(click())
        onView(withId(R.id.btnNum2)).perform(click())
        onView(withId(R.id.btnOperatorAdd)).perform(click())
        onView(withId(R.id.btnNum3)).perform(click())
        onView(withId(R.id.btnNum4)).perform(click())
        onView(withId(R.id.btnOperatorEquals)).perform(click())
        onView(withId(R.id.tvCalcOutput)).check(matches(withText("46")))
    }

    @Test
    @Throws(Exception::class)
    fun ClearTest() {
        onView(withId(R.id.tvCalcOutput)).check(matches(withText("")))
        onView(withId(R.id.btnNum1)).perform(click())
        onView(withId(R.id.btnNum1)).perform(click())
        onView(withId(R.id.btnNum1)).perform(click())
        onView(withId(R.id.btnNum1)).perform(click())
        onView(withId(R.id.btnNum1)).perform(click())
        onView(withId(R.id.btnOperatorAdd)).perform(click())
        onView(withId(R.id.btnNum2)).perform(click())
        onView(withId(R.id.btnClear)).perform(click())
        onView(withId(R.id.btnNum3)).perform(click())
        onView(withId(R.id.btnOperatorEquals)).perform(click())
        onView(withId(R.id.tvCalcOutput)).check(matches(withText("11114")))
    }

    @Test
    @Throws(Exception::class)
    fun ClearAllTest() {
        onView(withId(R.id.tvCalcOutput)).check(matches(withText("")))
        onView(withId(R.id.btnNum1)).perform(click())
        onView(withId(R.id.btnNum1)).perform(click())
        onView(withId(R.id.btnNum1)).perform(click())
        onView(withId(R.id.btnNum1)).perform(click())
        onView(withId(R.id.btnNum1)).perform(click())
        onView(withId(R.id.btnOperatorAdd)).perform(click())
        onView(withId(R.id.btnNum2)).perform(click())
        onView(withId(R.id.btnAllClear)).perform(click())
        onView(withId(R.id.btnNum3)).perform(click())
        onView(withId(R.id.btnOperatorEquals)).perform(click())
        onView(withId(R.id.tvCalcOutput)).check(matches(withText("3")))
    }
}