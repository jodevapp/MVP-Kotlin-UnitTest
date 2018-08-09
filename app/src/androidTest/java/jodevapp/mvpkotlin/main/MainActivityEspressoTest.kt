package jodevapp.mvpkotlin.main


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.test.suitebuilder.annotation.LargeTest
import android.view.View
import android.view.ViewGroup
import jodevapp.mvpkotlin.MainActivity
import jodevapp.mvpkotlin.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityEspressoTest {

    @Rule
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityEspressoTest() {
        val appCompatButton = onView(
                allOf(withId(R.id.btnError), withText("Retry"),
                        childAtPosition(
                                allOf(withId(R.id.lytError),
                                        childAtPosition(
                                                withClassName(`is`("android.support.constraint.ConstraintLayout")),
                                                1)),
                                1),
                        isDisplayed()))
        appCompatButton.perform(click())

    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return (parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position))
            }
        }
    }
}
