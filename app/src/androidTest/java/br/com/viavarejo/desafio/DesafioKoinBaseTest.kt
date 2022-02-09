package br.com.viavarejo.desafio

import androidx.test.rule.ActivityTestRule
import br.com.viavarejo.desafio.services.RouterActivityService
import br.com.viavarejo.desafio.services.RouterActivityServiceImpl
import br.com.viavarejo.desafio.views.main.MainActivity
import br.com.viavarejo.desafio.views.main.MainActivityPresenter
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.koin.core.component.inject
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.mock.MockProviderRule
import org.mockito.Mockito

val appModulesUnitTest = module(override = true) {
    single<RouterActivityService> { mockk<RouterActivityServiceImpl>(relaxed = true) }
    single { mockk<MainActivityPresenter>(relaxed = true) }
}

abstract class DesafioKoinBaseTest : KoinTest {

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    @Before
    fun setup() {
        loadKoinModules(appModulesUnitTest)
    }
}