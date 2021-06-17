package dev.carlos.core.extensions

import android.view.KeyEvent
import androidx.annotation.IdRes
import androidx.annotation.RestrictTo
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import org.junit.Assert

@RestrictTo(RestrictTo.Scope.TESTS)
fun String.isTextDisplayed() = Espresso.onView(withText(this)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

@RestrictTo(RestrictTo.Scope.TESTS)
fun Int.isDisplayed(): ViewInteraction = Espresso.onView(ViewMatchers.withId(this))
    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

@RestrictTo(RestrictTo.Scope.TESTS)
fun Int.isNotDisplayed(): ViewInteraction = Espresso.onView(ViewMatchers.withId(this))
    .check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))

@RestrictTo(RestrictTo.Scope.TESTS)
fun Int.click(): ViewInteraction = Espresso.onView(ViewMatchers.withId(this))
    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    .perform(ViewActions.click())

@RestrictTo(RestrictTo.Scope.TESTS)
fun Int.clickOnClickWithId(@IdRes childId: Int): ViewInteraction = Espresso.onView(
    allOf(
        ViewMatchers.withId(childId),
        ViewMatchers.withParent(ViewMatchers.withId(this))
    )
)
    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    .perform(ViewActions.click())

@RestrictTo(RestrictTo.Scope.TESTS)
fun Int.clickOnClickWithText(text: String): ViewInteraction = Espresso.onView(
    allOf(
        withText(text),
        ViewMatchers.withParent(ViewMatchers.withId(this))
    )
)
    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    .perform(ViewActions.click())

@RestrictTo(RestrictTo.Scope.TESTS)
fun Int.typeOn(text: String): ViewInteraction = Espresso.onView(ViewMatchers.withId(this)).perform(typeText(text), closeSoftKeyboard())

@RestrictTo(RestrictTo.Scope.TESTS)
fun Int.pressEnterKey(): ViewInteraction = Espresso.onView(ViewMatchers.withId(this))
    .perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER))

@RestrictTo(RestrictTo.Scope.TESTS)
fun String.isDisplayed(): ViewInteraction = Espresso.onView(ViewMatchers.withSubstring(this))
    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

@RestrictTo(RestrictTo.Scope.TESTS)
fun String.isHinted(): ViewInteraction = Espresso.onView(ViewMatchers.withHint(this))
    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

@RestrictTo(RestrictTo.Scope.TESTS)
fun String.isNotDisplayed(): ViewInteraction = Espresso.onView(withText(this))
    .check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))

@RestrictTo(RestrictTo.Scope.TESTS)
fun String.click(): ViewInteraction = Espresso.onView(withText(this))
    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    .perform(ViewActions.click())

@RestrictTo(RestrictTo.Scope.TESTS)
fun <T> T.isEquals(actual: T) = Assert.assertEquals(this, actual)
