package dev.carlos.shortform.feature

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.platform.app.InstrumentationRegistry
import dev.carlos.core.extensions.isTextDisplayed
import dev.carlos.core.extensions.pressEnterKey
import dev.carlos.core.extensions.typeOn
import dev.carlos.core.scheduler.Scheduler
import dev.carlos.core.scheduler.SchedulerProvider
import dev.carlos.shortform.R
import dev.carlos.shortform.data.models.ShortformModel
import dev.carlos.shortform.domain.GetShortformDefinition
import dev.carlos.shortform.domain.ShortformRepository
import dev.carlos.shortform.viewmodels.ShortformViewmodel
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import java.lang.Thread.sleep

class ShortformFragmentTest {

    @Before
    fun setupKoin() {
        startKoin {
            InstrumentationRegistry.getInstrumentation().targetContext
            modules(module {
                factory { GetShortformDefinition(get(), get()) }
                single<Scheduler> { SchedulerProvider() }
                viewModel { ShortformViewmodel(get()) }
            })
        }
    }

    @After
    fun killKoin() {
        stopKoin()
    }

    @Test
    fun shouldShowLongforms_whenSearchingForValidAcronym() {
        acronyms {
            searchValidAcronym()
        } should {
            showLongforms()
        }
    }

    private fun acronyms(func: AcronymsRobot.() -> Unit) =
        AcronymsRobot().apply {
            func()
        }
}

class AcronymsRobot {

    fun searchValidAcronym() {
        loadKoinModules(
            module {
                factory<ShortformRepository> { MockShortformRepository("Valid", loadTwoLongforms()) }
            }
        )
        launchFragmentInContainer<ShortformFragment>()
        R.id.shortform_definition_search_field.typeOn("Valid")
        R.id.shortform_definition_search_field.pressEnterKey()
    }

    infix fun should(func: AcronymsResult.() -> Unit) {
        AcronymsResult().apply { func() }
    }

    private fun loadTwoLongforms() = listOf(
        ShortformModel.LongformModel(
            "Valid 1",
            1,
            101
        ),
        ShortformModel.LongformModel(
            "Valid 2",
            2,
            102
        )
    )
}

class AcronymsResult {
    fun showLongforms() {
        sleep(200)
        "Valid 1".isTextDisplayed()
        "Valid 2".isTextDisplayed()
    }
}
