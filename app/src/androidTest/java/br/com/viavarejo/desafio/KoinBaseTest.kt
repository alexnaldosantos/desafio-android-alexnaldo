package br.com.viavarejo.desafio

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import br.com.viavarejo.desafio.services.RouterActivityService
import br.com.viavarejo.desafio.services.RouterActivityServiceImpl
import br.com.viavarejo.desafio.views.main.MainActivity
import br.com.viavarejo.desafio.views.main.MainActivityPresenter
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.koin.core.component.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.mock.MockProviderRule
import org.mockito.Mockito

abstract class KoinBaseTest : KoinTest {

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        loadKoinModules(getKoinModules())
    }

    abstract fun getKoinModules() : Module
}