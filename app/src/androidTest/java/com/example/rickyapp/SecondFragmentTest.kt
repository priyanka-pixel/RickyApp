package com.example.rickyapp

import androidx.core.os.bundleOf
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.rickyapp.utils.launchFragmentInHiltContainer
import org.junit.Before
import org.junit.Test

class SecondFragmentTest {

    @Before
    fun setup() {
        launchFragmentInHiltContainer<SecondFragment>(bundleOf("ID" to 1), R.style.Theme_RickyApp)
    }

    @Test
    fun characterNameText_shouldBeVisible() {
        onView(withId(R.id.textView))
            .check(matches(isDisplayed()))
            .check(matches(withText("Rick Sanchez")))
    }

    @Test
    fun characterGenderText_shouldBeVisible() {
        onView(withId(R.id.textView2))
            .check(matches(isDisplayed()))
            .check(matches(withText("Male")))
    }

    @Test
    fun characterSpeciesText_shouldBeVisible() {
        onView(withId(R.id.textView3))
            .check(matches(isDisplayed()))
            .check(matches(withText("Human")))
    }

    @Test
    fun characterStatusText_shouldBeVisible() {
        onView(withId(R.id.textView4))
            .check(matches(isDisplayed()))
            .check(matches(withText("Alive")))
    }


}