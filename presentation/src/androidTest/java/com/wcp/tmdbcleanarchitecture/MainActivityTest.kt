package com.wcp.tmdbcleanarchitecture


import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Matchers.allOf
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        val textView = onView(
            allOf(
                withId(R.id.tv_title), withText("The Conjuring: The Devil Made Me Do It"),
                withParent(withParent(withId(R.id.rv_upcoming_movies))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("The Conjuring: The Devil Made Me Do It")))

        val viewGroup = onView(
            allOf(
                withParent(
                    allOf(
                        withId(R.id.rv_upcoming_movies),
                        withParent(withId(R.id.frameLayout))
                    )
                ),
                isDisplayed()
            )
        )
        viewGroup.check(matches(isDisplayed()))

        val imageView = onView(
            allOf(
                withId(R.id.iv_poster),
                withParent(withParent(withId(R.id.cv_poster))),
                isDisplayed()
            )
        )
        imageView.check(matches(isDisplayed()))

        val imageView2 = onView(
            allOf(
                withId(R.id.iv_poster),
                withParent(withParent(withId(R.id.cv_poster))),
                isDisplayed()
            )
        )
        imageView2.check(matches(isDisplayed()))

        val imageView3 = onView(
            allOf(
                withId(R.id.iv_poster),
                withParent(withParent(withId(R.id.cv_poster))),
                isDisplayed()
            )
        )
        imageView3.check(matches(isDisplayed()))

        val imageView4 = onView(
            allOf(
                withId(R.id.iv_poster),
                withParent(withParent(withId(R.id.cv_poster))),
                isDisplayed()
            )
        )
        imageView4.check(doesNotExist())

        val imageView5 = onView(
            allOf(
                withId(R.id.iv_poster),
                withParent(withParent(withId(R.id.cv_poster))),
                isDisplayed()
            )
        )
        imageView5.check(doesNotExist())

        val imageView6 = onView(
            allOf(
                withId(R.id.icon),
                withParent(
                    allOf(
                        withId(R.id.navigation_popular), withContentDescription("Popular"),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        imageView6.check(matches(isDisplayed()))

        val imageView7 = onView(
            allOf(
                withId(R.id.icon),
                withParent(
                    allOf(
                        withId(R.id.navigation_popular), withContentDescription("Popular"),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        imageView7.check(matches(isDisplayed()))
    }
}
