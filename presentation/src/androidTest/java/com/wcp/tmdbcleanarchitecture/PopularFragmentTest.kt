package com.wcp.tmdbcleanarchitecture


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.wcp.data.platform.NetworkHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class PopularFragmentTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    lateinit var networkHandler: NetworkHandler

    @Before
    @Throws(Exception::class)
    fun setUp() {
        networkHandler = NetworkHandler(mActivityTestRule.activity)
    }

    @Test
    fun test_ActionBarTitle_Upcoming() {
        onView(
            allOf(
                withText("Popular"),
                withParent(
                    allOf(
                        withId(R.id.action_bar),
                        withParent(withId(R.id.action_bar_container))
                    )
                ),
                isDisplayed()
            )
        )
    }

    @Test
    fun test_ProgressBar_NetworkConnected() {
        if (networkHandler.isConnected) {
            onView(
                allOf(
                    withId(R.id.progress_bar),
                    withParent(
                        allOf(
                            withId(R.id.frameLayout),
                            withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                        )
                    ),
                    isDisplayed()
                )
            )
        }

    }

    @Test
    fun test_TryAgain_NetworkDisconnect() {
        if (!networkHandler.isConnected) {
            if (networkHandler.isConnected) {
                onView(
                    allOf(
                        withId(R.id.btn_try_again),
                        withParent(
                            allOf(
                                withId(R.id.frameLayout),
                                withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                            )
                        ),
                        isDisplayed()
                    )
                )
            }
        }
    }

    @Test
    fun test_ClickRecyclerViewPositionOne_OpenMovieDetail() {
        if (networkHandler.isConnected) {
            runBlocking {
                val recyclerView = onView(
                    allOf(
                        withId(R.id.rv_upcoming_movies),
                        childAtPosition(
                            withId(R.id.frameLayout),
                            0
                        )
                    )
                )
                delay(5000)
                recyclerView.perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
            }
        }

    }


    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }

}
