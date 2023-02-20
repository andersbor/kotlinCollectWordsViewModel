package dk.easj.anbo.collectwordsviewmodel

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("dk.easj.anbo.collectwordsviewmodel", appContext.packageName)
    }

    @Test
    fun typeWords() {
        onView(withText("Collect Words")).check(matches(isDisplayed()))

        onView(withId(R.id.editText_word)).perform(typeText("Anders"))
        onView(withId(R.id.button_save)).perform(click())
        //onView(withId(R.id.button_show)).perform(click())
        onView(withId(R.id.textView_message)).check(matches(withText(("[Anders]"))))

        onView(withId(R.id.editText_word)).perform(clearText())
        onView(withId(R.id.editText_word)).perform(typeText("Peter"))
        onView(withId(R.id.button_save)).perform(click())
        //onView(withId(R.id.button_show)).perform(click())
        onView(withId(R.id.textView_message)).check(matches(withText(("[Anders, Peter]"))))

        //onView(withId(R.id.button_clear)).perform(click())
        onView(withId(R.id.button_show_recyclerView)).perform(click())
        //onView(withId(R.id.textView_message)).check(matches(withText("empty")))
        onView(withText("Add")).check(matches(isDisplayed()))

        onView(withText("Anders")).check(matches(isDisplayed()))
        onView(withText("Peter")).check(matches(isDisplayed()))


    }
}