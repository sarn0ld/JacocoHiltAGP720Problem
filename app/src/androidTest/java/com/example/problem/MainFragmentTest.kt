package com.example.problem

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.*
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class MainFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @BindValue
    val viewModel = mockk<MainViewModel>(relaxed = true) {
        every { data } returns MutableStateFlow("MockedData")
    }

    @Test
    fun startAppAndPressButton() {
        // Given the Main Fragment is displayed
        launchFragmentInHiltContainer<MainFragment>()

        // Then the data should be displayed
        onView(withId(R.id.message)).check(matches(withText("MockedData")))

        // And When clicking the button
        onView(withId(R.id.button)).perform(click())

        // Then the view model should get called
        verify(exactly = 1) { viewModel.onButtonPress() }
    }
}