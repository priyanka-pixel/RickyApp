package com.example.rickyapp

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.rickyapp.characterlist.CharacterAdapter
import com.example.rickyapp.characterlist.CharacterAdapter.*
import com.example.rickyapp.utils.launchFragmentInHiltContainer
import org.hamcrest.CoreMatchers.allOf
import org.junit.Before
import org.junit.Test

class FirstFragmentTest {

    private val navController = TestNavHostController(getApplicationContext())

    @Before
    fun setup() {
        launchFragmentInHiltContainer<FirstFragment>(Bundle(), R.style.Theme_RickyApp) {
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(requireView(), navController)
        }
    }

    @Test
    fun recyclerViewItemClick_shouldShowSecondFragment() {
        onView(withId(R.id.charRecyclerView))
            .check(matches(isDisplayed()))
            .perform(RecyclerViewActions.actionOnItemAtPosition<MyViewHolder>(0, click()))
        assert(navController.currentDestination?.id == R.id.SecondFragment)
    }
}