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
import br.com.viavarejo.desafio.views.personagem.listagem.ListagemActivity
import io.mockk.every
import io.mockk.verify
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.component.inject


@RunWith(AndroidJUnit4::class)
@LargeTest
class ListagemActivityTest: DesafioKoinBaseTest() {

    @get:Rule
    var activityRule: ActivityTestRule<ListagemActivity>
            = ActivityTestRule(ListagemActivity::class.java)

    private val presenter by inject<MainActivityPresenter>()

    @Test
    fun test_texts_on_screen() {
    }

    @Test
    fun test_play_button() {

    }
}