package br.com.viavarejo.desafio

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import br.com.viavarejo.desafio.views.main.MainActivity
import br.com.viavarejo.desafio.views.main.MainActivityPresenter
import io.mockk.every
import io.mockk.verify
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.component.inject


@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTestTest : DesafioKoinBaseTest() {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity>
            = ActivityTestRule(MainActivity::class.java)

    private val presenter by inject<MainActivityPresenter>()

    @Test
    fun test_texts_on_screen() {
        onView(withText("Alexnaldo Santos")).check(matches(isDisplayed()))
        onView(withText("Welcome to Desafio Android!")).check(matches(isDisplayed()))
    }

    @Test
    fun test_play_button() {
        every { presenter.play(activityRule.activity) } returns Unit
        onView(withText("PLAY")).perform(click())
        verify(atLeast = 1, atMost = 1) {
            presenter.play(activityRule.activity)
        }
    }
}